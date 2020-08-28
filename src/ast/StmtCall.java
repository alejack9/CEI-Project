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
import ast.errors.PassedReferenceNotVarError;
import ast.errors.SemanticError;

public class StmtCall extends Stmt {

	private List<Exp> exps;
	private String ID;
	private STEntry idEntry;

	public StmtCall(String ID, List<Exp> exps, int line, int column) {
		super(line, column);
		this.ID = ID;
		this.exps = exps;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		idEntry = e.getIDEntry(ID);

		if (idEntry == null)
			toRet.add(new FunctionNotExistsError(ID, line, column));
		else {
			Type funT = idEntry.getType();
			if (!EType.FUNCTION.equalsTo(funT))
				toRet.add(new FunctionNotExistsError(ID, line, column));
			else {
				List<Type> params = ((ArrowType) funT).getParamTypes();
				if (exps.size() != params.size())
					toRet.add(new ParametersMismatchError(params.size(), exps.size(), line, column));
				for (int i = 0; i < Math.min(exps.size(), params.size()); i++)
					if (params.get(i).isRef() && !(exps.get(i) instanceof ExpVar))
						toRet.add(new PassedReferenceNotVarError(i + 1, ID, exps.get(i).line, exps.get(i).column));
			}
		}

		for (Exp exp : exps)
			toRet.addAll(exp.checkSemantics(e));

		return toRet;
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {

		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		List<Type> types = ((ArrowType) idEntry.getType()).getParamTypes();

		List<BTEntry> e1 = e.getIDEntry(ID).getE1();

		HashMap<String, List<EEffect>> ePrimo = new HashMap<>();

		for (int i = 0; i < types.size(); i++) {
			if (types.get(i).isRef()) {
				BTEntry prev = e.getIDEntry(((ExpVar) exps.get(i)).getId());
				BTEntry next = e1.get(i);

				List<EEffect> btList = ePrimo.getOrDefault(((ExpVar) exps.get(i)).getId(), new LinkedList<>());

				btList.add(BTHelper.invocationSeq(prev, next));
				ePrimo.put(((ExpVar) exps.get(i)).getId(), btList);
			}
		}

		ePrimo.entrySet().forEach(entry -> {
			e.getIDEntry(entry.getKey())
					.setLocalEffect(entry.getValue().stream().reduce((a, b) -> BTHelper.par(a, b)).get());

			if (e.getIDEntry(entry.getKey()).getRefEffect().compareTo(EEffect.T) == 0)
				toRet.add(new AliasingError(entry.getKey(), ID, line, column));
		});

		return toRet;
	}

	@Override
	public Type inferType() {
		if (!EType.FUNCTION.equalsTo(idEntry.getType()))
			TypeErrorsStorage.addError(new TypeError(
					"Called ID must be a function (actual type: " + idEntry.getType() + ")", line, column));

		ArrowType funT = (ArrowType) idEntry.getType();

		List<Type> parTs = funT.getParamTypes();

		for (int i = 0; i < parTs.size(); i++) {
			Type parT = parTs.get(i);
			Type expT = exps.get(i).inferType();
			if (!parT.getType().equalsTo(expT))
				TypeErrorsStorage.addError(new TypeError(
						"#" + (i + 1) + " parameter type (" + parT + ") is not equal to expression type (" + expT + ")",
						line, column));
		}

		return funT.getReturnType();
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
		sb.newLine("push $fp 4");
		List<Type> paramsType = ((ArrowType) idEntry.getType()).getParamTypes();
		for (int i = exps.size() - 1; i >= 0; i--) {
			if (!paramsType.get(i).isRef())
				exps.get(i)._codeGen(nl, sb);
			else {
				STEntry var = ((ExpVar) exps.get(i)).getIdEntry();
				if (!var.getType().isParameter())
					sb.newLine("li $a0 ", Integer.toString(var.offset));
				else {
					if (var.getType().isRef())
						CodeGenUtils.getVariableCodeGen(var, nl, sb);
					else {
						sb.newLine("move $al $fp");
						for (int j = 0; j < nl - var.nestingLevel; j++)
							sb.newLine("lw $al 0($al) 4");
						sb.newLine("addi $a0 $al ", Integer.toString(var.offset));
					}
				}
			}
			sb.newLine("push $a0 ", Integer.toString(paramsType.get(i).getDimension()));
		}
		sb.newLine("move $al $fp");
		for (int i = 0; i < nl - idEntry.nestingLevel; i++)
			sb.newLine("lw $al 0($al) 4");
		sb.newLine("push $al 4");
		sb.newLine("jal ", idEntry.label);
	}

}
