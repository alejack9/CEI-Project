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
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		idEntry = e.getIDEntry(ID);

		if (idEntry != null) {
			Type funT = idEntry.getType();
			// check that the called function is actually a function
			if (EType.FUNCTION.equalsTo(funT)) {
				// parameters check
				List<Type> params = ((ArrowType) funT).getParamTypes();
				if (exps.size() != params.size())
					toRet.add(new ParametersMismatchError(params.size(), exps.size(), line, column));

				// check that for each "reference" parameter is passed a variable
				for (int i = 0; i < Math.min(exps.size(), params.size()); i++)
					if (params.get(i).isRef() && !(exps.get(i) instanceof ExpVar))
						toRet.add(new PassedExpNotVariableError(i + 1, ID, exps.get(i).line, exps.get(i).column));
			} else
				toRet.add(new FunctionNotExistsError(ID, line, column));
		} else
			toRet.add(new FunctionNotExistsError(ID, line, column));

		// check all expressions
		for (Exp exp : exps)
			toRet.addAll(exp.checkSemantics(e));

		return toRet;
	}

	/**
	 * @return the function type
	 */
	@Override
	public Type inferType() {
		// it's already checked by checkSemantic
//		if (!EType.FUNCTION.equalsTo(idEntry.getType()))
//			TypeErrorsStorage.addError(new TypeError(
//					"Called ID must be a function (actual type: " + idEntry.getType() + ")", line, column));

		ArrowType funT = (ArrowType) idEntry.getType();

		List<Type> parTypes = funT.getParamTypes();

		for (int i = 0; i < parTypes.size(); i++) {
			// get parameter type
			Type parType = parTypes.get(i);
			// get expression type
			Type expType = exps.get(i).inferType();
			// check the equality
			if (!parType.getType().equalsTo(expType))
				TypeErrorsStorage.addError(new TypeError("#" + (i + 1) + " parameter type (" + parType
						+ ") is not equal to expression type (" + expType + ")", line, column));
		}

		// return the function type
		return funT.getReturnType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {

		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		List<Type> parsTypes = ((ArrowType) idEntry.getType()).getParamTypes();

		// Sigma_1
		List<BTEntry> e1 = e.getIDEntry(ID).getE1();

		// Sigma'
		HashMap<String, List<EEffect>> eStar = new HashMap<>();

		for (int i = 0; i < parsTypes.size(); i++) {
			if (parsTypes.get(i).isRef()) {
				// previous behaviour state before statement call
				BTEntry prev = e.getIDEntry(((ExpVar) exps.get(i)).getId());
				// behaviour state after statement call
				BTEntry next = e1.get(i);

				// get the behaviours list of the current variable or creates a new one
				List<EEffect> btList = eStar.getOrDefault(((ExpVar) exps.get(i)).getId(), new LinkedList<>());
				// add to the list the seq operation result
				btList.add(BTHelper.invocationSeq(prev, next));
				// update the behaviour list associated with that variable
				eStar.put(((ExpVar) exps.get(i)).getId(), btList);
			}
		}

		// apply the par operator for each element of the behaviour list associated
		// with each variable
		eStar.entrySet().forEach(entry -> {
			e.getIDEntry(entry.getKey())
					.setLocalEffect(entry.getValue().stream().reduce((a, b) -> BTHelper.par(a, b)).get());

			// if any behaviour is TOP, add an error
			if (e.getIDEntry(entry.getKey()).getRefEffect().compareTo(EEffect.T) == 0)
				toRet.add(new AliasingError(entry.getKey(), ID, line, column));
		});

		return toRet;
	}

	/**
	 * Create the bottom part of AR and jump to the function.
	 */
	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		// old FP in AR
		sb.newLine("push $fp 4");

		// parameters in AR
		List<Type> paramsType = ((ArrowType) idEntry.getType()).getParamTypes();
		// inverted order
		for (int i = exps.size() - 1; i >= 0; i--) {
			// if it's not required a reference, get the expression value in $a0
			if (!paramsType.get(i).isRef())
				exps.get(i).codeGen(nl, sb);
			else {
				// get the current idEntry
				STEntry var = ((ExpVar) exps.get(i)).getIdEntry();
				// if the variable is not a parameter (this means that it's a global variable),
				// put in $a0 the offset of the variable
				if (!var.getType().isParameter())
					sb.newLine("li $a0 ", Integer.toString(var.offset));
				// else, check if it's a reference, if so, put the variable value in $a0,
				// otherwise find the address of the variable and put it in $a0
				else if (var.getType().isRef())
					CodeGenUtils.getVariableCodeGen(var, nl, sb);
				else
					CodeGenUtils.getVariableRefCodeGen(var, nl, sb);
			}

			// push $a0 with appropriate dimension
			sb.newLine("push $a0 ", Integer.toString(paramsType.get(i).getDimension()));
		}

		// push the $al
		sb.newLine("move $al $fp");
		for (int i = 0; i < nl - idEntry.nestingLevel; i++)
			sb.newLine("lw $al 0($al) 4");
		sb.newLine("push $al 4");
		// actual jumps to the function
		sb.newLine("jal ", idEntry.label);
	}

}
