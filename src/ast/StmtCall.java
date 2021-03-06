package ast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTHelper;
import behavioural_analysis.EEffect;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import support.Tuple;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.AliasingError;
import ast.errors.BehaviourError;
import ast.errors.FunctionNotExistsError;
import ast.errors.ParametersMismatchError;
import ast.errors.PassedExpNotVariableError;
import ast.errors.SemanticError;

/**
 * The class of call statements ("f()" or "g(x)").
 */
public class StmtCall extends Stmt {

	private List<Exp> exps;
	private List<EEffect> expsBehaviour = new LinkedList<EEffect>();

	private String ID;
	private STEntry idEntry;

	/**
	 * @param ID     the id of the called function
	 * @param exps   the passed parameters expressions
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public StmtCall(String ID, List<Exp> exps, int line, int column) {
		super(line, column);
		this.ID = ID;
		this.exps = exps;
		exps.forEach(s -> expsBehaviour.add(EEffect.BOTTOM));
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		idEntry = e.getIDEntry(ID);

		if (idEntry != null) {
			Type funT = idEntry.getType();
			// Check that the called function is actually a function
			if (EType.FUNCTION.equalsTo(funT)) {
				// Parameters check
				List<Type> params = ((ArrowType) funT).getParamTypes();
				if (exps.size() != params.size())
					toRet.add(new ParametersMismatchError(params.size(), exps.size(), line, column));

				// Check that for each "reference" parameter is passed a variable
				for (int i = 0; i < Math.min(exps.size(), params.size()); i++)
					if (params.get(i).isRef() && !(exps.get(i) instanceof ExpVar))
						toRet.add(new PassedExpNotVariableError(i + 1, ID, exps.get(i).line, exps.get(i).column));
			} else
				toRet.add(new FunctionNotExistsError(ID, line, column));
		} else
			toRet.add(new FunctionNotExistsError(ID, line, column));

		// Check all expressions
		for (Exp exp : exps)
			toRet.addAll(exp.checkSemantics(e));

		return toRet;
	}

	/**
	 * @return the function type
	 */
	@Override
	public Type inferType() {
		// It's already checked by checkSemantic
//		if (!EType.FUNCTION.equalsTo(idEntry.getType()))
//			TypeErrorsStorage.addError(new TypeError(
//					"Called ID must be a function (actual type: " + idEntry.getType() + ")", line, column));

		ArrowType funT = (ArrowType) idEntry.getType();

		List<Type> parTypes = funT.getParamTypes();

		for (int i = 0; i < parTypes.size(); i++) {
			// Get parameter type
			Type parType = parTypes.get(i);
			// Get expression type
			Type expType = exps.get(i).inferType();
			// Check the equality
			if (!parType.getType().equalsTo(expType))
				TypeErrorsStorage.addError(new TypeError("#" + (i + 1) + " parameter type (" + parType
						+ ") is not equal to expression type (" + expType + ")", line, column));
		}

		// Return the function type
		return funT.getReturnType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {

		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		List<Type> parsTypes = ((ArrowType) idEntry.getType()).getParamTypes();

		// Sigma_1
		List<BTEntry> e1 = e.getIDEntry(ID).getE1();

		// Sigma'
		HashMap<String, Tuple<Integer, List<EEffect>>> eStar = new HashMap<>();

		for (int i = 0; i < parsTypes.size(); i++) {
			if (parsTypes.get(i).isRef()) {
				// Get previous behaviour state before statement call
				BTEntry prev = e.getIDEntry(((ExpVar) exps.get(i)).getId());
				// Get behaviour state after statement call
				BTEntry next = e1.get(i);

				// Get the behaviours list of the current variable or creates a new one
				List<EEffect> btList = eStar.getOrDefault(((ExpVar) exps.get(i)).getId(),
						new Tuple<>(i, new LinkedList<>())).y;
				// Add to the list the seq operation result
				// NOTE: the method performs a seq operation between the "local" effect of
				// "prev" and the "ref" effect of "next"
				btList.add(BTHelper.invocationSeq(prev, next));
				// Update the behaviour list associated with that variable
				eStar.put(((ExpVar) exps.get(i)).getId(), new Tuple<>(i, btList));
			}
			// check not-referenced variables
			else
				toRet.addAll(exps.get(i).inferBehaviour(e));
		}

		// The "setLocalEffect" represents the theoretical function "update"; before the
		// update, the "par" operation is performed on all retrieved effects
		eStar.entrySet().forEach(entry -> {
			e.getIDEntry(entry.getKey())
					.setLocalEffect(entry.getValue().y.stream().reduce((a, b) -> BTHelper.par(a, b)).get());

			expsBehaviour.set(entry.getValue().x, e.getIDEntry(entry.getKey()).getLocalEffect());

			// If any local behaviour (upgraded by the previous command) is TOP, add an
			// error
			if (e.getIDEntry(entry.getKey()).getLocalEffect().compareTo(EEffect.T) == 0)
				toRet.add(new AliasingError(entry.getKey(), ID, line, column));
		});

		return toRet;
	}

	/**
	 * Create the bottom part of AR and jump to the function.
	 */
	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		// Old FP in AR
		sb.newLine("push $fp 4");

		// Parameters in AR
		List<Type> paramsType = ((ArrowType) idEntry.getType()).getParamTypes();
		// Decreasing order
		for (int i = exps.size() - 1; i >= 0; i--) {
			// If it's not required a reference, get the expression value in $a0
			if (!paramsType.get(i).isRef())
				exps.get(i).codeGen(nl, sb);
			else {
				// Get the current idEntry
				STEntry var = ((ExpVar) exps.get(i)).getIdEntry();
				// If the variable is not a parameter (this means that it's a global variable),
				// put in $a0 the offset of the variable
				if (!var.getType().isParameter())
					sb.newLine("li $a0 ", Integer.toString(var.offset));
				// Else, check if it's a reference, if so, put the variable value in $a0,
				// otherwise find the address of the variable and put it in $a0
				else if (var.getType().isRef())
					CodeGenUtils.getVariableCodeGen(var, nl, sb);
				else
					CodeGenUtils.getVariableRefCodeGen(var, nl, sb);
			}

			// Push $a0 with appropriate dimension
			sb.newLine("push $a0 ", Integer.toString(paramsType.get(i).getDimension()));

			// If the variable has been deleted by the function invocation
			if (exps.get(i) instanceof ExpVar && expsBehaviour.get(i).compareTo(EEffect.D) == 0)
				((ExpVar) exps.get(i)).getIdEntry().setDeleted(true);
		}

		// Push the $al
		sb.newLine("move $al $fp");
		for (int i = 0; i < nl - idEntry.nestingLevel; i++)
			sb.newLine("lw $al 0($al) 4");
		sb.newLine("push $al 4");
		sb.newLine("jal ", idEntry.label);
	}
}
