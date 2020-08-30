package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.BehaviourError;
import ast.errors.IdAlreadytExistsError;
import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.ListOfMapEnv;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.SemanticError;

/**
 * The class of function declaration statements ("void f( ... ) { ... }").
 */
public class StmtDecFun extends StmtDec {

	private Type type;

	private String id;
	private STEntry idEntry;

	private List<Arg> args;

	private StmtBlock block;

	/**
	 * @param type   the return type of the function
	 * @param ID     the id of the function
	 * @param args   the parameters required by the function
	 * @param block  the block of the function
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public StmtDecFun(Type type, String ID, List<Arg> args, StmtBlock block, int line, int column) {
		super(line, column);
		this.type = type;
		this.id = ID;
		this.args = args;
		this.block = block;
	}

	/**
	 * Add the label to the idEntry, create a new environment with function-only and
	 * use it to check the block.
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		ArrowType funtionType = (ArrowType) EType.FUNCTION.getType();

		idEntry = new STEntry(funtionType);
		idEntry.label = CodeGenUtils.freshLabel();

		if (!e.add(id, idEntry))
			toRet.add(new IdAlreadytExistsError(id, line, column));

		Environment<STEntry> funEnv = new ListOfMapEnv<STEntry>(e.getAllFunctions(), e.getOffset(),
				e.getNestingLevel());
		// Set the correct offset
		funEnv.setOffset(e.getOffset());

		funEnv.openScope();
		// Increase the nesting level only when enters in a function
		funEnv.increaseNestingLevel();
		List<Type> parsTypes = new LinkedList<Type>();
		// "add" modifies the offset so it stores it to reset later
		int oldEnvOffset = funEnv.getOffset();
		// Starting offset is 4 (to skip the AL block in AR)
		int paroffset = 4;
		for (int i = 0; i < args.size(); i++) {
			STEntry toAdd = new STEntry(args.get(i).getType());
			parsTypes.add(toAdd.getType());
			if (!funEnv.add(args.get(i).getId(), toAdd))
				toRet.add(new IdAlreadytExistsError(args.get(i).getId(), args.get(i).line, args.get(i).column));
			else
				toAdd.offset = paroffset;
			// The next offset is the current value summed by the dimension of the current
			// variable
			paroffset += args.get(i).getType().getDimension();
		}

		funtionType.setParamTypes(parsTypes);
		funtionType.setRetType(type);

		// Reset offset
		funEnv.setOffset(oldEnvOffset);

		toRet.addAll(block.checkSemanticsSameScope(funEnv));

		// Close scope and reset offset
		funEnv.closeScope();
		funEnv.decreaseNestingLevel();
		funEnv.setOffset(oldEnvOffset);
		return toRet;
	}

	/**
	 * @return null
	 */
	@Override
	public Type inferType() {
		// Infer type of all args
		this.args.forEach(Arg::inferType);

		Type blockT = this.block.inferType();

		if (!type.getType().equalsTo(blockT))
			TypeErrorsStorage.addError(new TypeError(
					"Block return type (" + blockT + ") not equals to function return type (" + type + ")",
					this.block.line, this.block.column));
		return null;
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		// Create the function-only environment
		Environment<BTEntry> funEnv = new ListOfMapEnv<BTEntry>(e.getAllFunctions(), e.getOffset(),
				e.getNestingLevel());

		// Setup for fixed point method
		// Starting with all parameters in BOTTOM state
		// e1 represents the theoretical sigma1
		List<BTEntry> e1 = new LinkedList<BTEntry>();
		args.stream().forEach(arg -> e1.add(new BTEntry()));
		// e1_1 represents the theoretical sigma1'
		List<BTEntry> e1_1 = new LinkedList<BTEntry>();
		args.stream().forEach(arg -> e1_1.add(new BTEntry()));

		// Add function for direct recursion with default behaviour states (BOTTOM)
		funEnv.add(id, new BTEntry(e1_1));
		do {
			funEnv.openScope();
			// Refresh errors list
			toRet = new LinkedList<BehaviourError>();
			// Reset all parameters' behaviour state to BOTTOM
			for (int i = 0; i < e1.size(); i++)
				funEnv.add(args.get(i).getId(), new BTEntry());

			// Infer block behaviour without open a new scope (opened in line 144)
			toRet.addAll(block.inferBehaviourSameScope(funEnv));

			// Backup sigma1' into sigma1 and update all parameters value in sigma1'
			for (int i = 0; i < e1.size(); i++) {
				e1.set(i, (BTEntry) e1_1.get(i).clone());
				e1_1.set(i, funEnv.getIDEntry(args.get(i).getId()));
			}

			funEnv.closeScope();

			// Update function's sigma1 with sigma1'
			funEnv.update(id, new BTEntry(e1_1));
			
			// If the two sigmas are equal, then fixed point method ends
		} while (!BTEntry.areEqual(e1, e1_1));
		// Add function entry in the main environment
		e.add(id, funEnv.getIDEntry(id));
		return toRet;
	}

	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		String end = CodeGenUtils.freshLabel();
		sb.newLine("b ", end);
		sb.newLine(idEntry.label, ":");
		sb.newLine("move $fp $sp");
		sb.newLine("push $ra 4");

		block.codeGen(nl + 1, sb);

		sb.newLine("lw $ra 0($sp) 4");
		// Remove all parameters
		sb.newLine("addi $sp $sp ", Integer.toString(
				args.stream().map(Arg::getType).map(Type::getDimension).reduce((a, b) -> a + b).orElse(0) + 8));
		sb.newLine("lw $fp 0($sp) 4");
		sb.newLine("pop 4");
		sb.newLine("jr");
		sb.newLine(end, ":");
	}
}
