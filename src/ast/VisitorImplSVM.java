/*
 * 
 */
package ast;

import java.util.HashMap;
import parser.*;
import support.Regs;

// TODO: Auto-generated Javadoc
/**
 * The Class VisitorImplSVM.
 */
public class VisitorImplSVM extends SVMBaseVisitor<Void> {

	/** The code. */
	private int[] code = new int[ExecuteVM.CODESIZE];
	
	/** The i. */
	private int i = 0;
	
	/** The labels indexes. */
	private HashMap<String, Integer> labelsIndexes = new HashMap<String, Integer>();
	
	/** The label ref. */
	private HashMap<Integer, String> labelRef = new HashMap<Integer, String>();

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int[] getCode() {
		return code;
	}

	/**
	 * Visit assembly.
	 *
	 * @param ctx the ctx
	 * @return the void
	 */
	@Override
	public Void visitAssembly(SVMParser.AssemblyContext ctx) {
		visitChildren(ctx);
		for (Integer refAdd : labelRef.keySet()) {
			code[refAdd] = labelsIndexes.get(labelRef.get(refAdd));
		}
		return null;
	}

	/**
	 * Visit instruction.
	 *
	 * @param ctx the ctx
	 * @return the void
	 */
	@Override
	public Void visitInstruction(SVMParser.InstructionContext ctx) {
		switch (ctx.getStart().getType()) {

		case SVMLexer.LOADINTEGER:
			code[i++] = SVMParser.LOADINTEGER;
			code[i++] = Regs.valueOf(ctx.REG(0).getText()).ordinal();
			code[i++] = Integer.parseInt(ctx.NUMBER(0).getText());
			break;

		case SVMLexer.BRANCH:
			code[i++] = SVMParser.BRANCH;
			labelRef.put(i++, ctx.LABEL().getText());
			break;

		case SVMLexer.LABEL:
			labelsIndexes.put(ctx.LABEL().getText(), i);
			break;

		case SVMLexer.PUSH:
			code[i++] = SVMParser.PUSH;
			code[i++] = Regs.valueOf(ctx.REG(0).getText()).ordinal();
			code[i++] = Integer.parseInt(ctx.NUMBER(0).getText());
			break;

		case SVMLexer.LOADWORD:
			code[i++] = SVMParser.LOADWORD;
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Integer.parseInt(ctx.offset.getText());
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			code[i++] = Integer.parseInt(ctx.dimension.getText());
			break;

		case SVMLexer.STOREWORD:
			code[i++] = SVMParser.STOREWORD;
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Integer.parseInt(ctx.offset.getText());
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			code[i++] = Integer.parseInt(ctx.dimension.getText());
			break;

		case SVMLexer.ADD:
			code[i++] = SVMParser.ADD;
			code[i++] = Regs.valueOf(ctx.dest.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			break;
		case SVMLexer.SUB:
			code[i++] = SVMParser.SUB;
			code[i++] = Regs.valueOf(ctx.dest.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			break;
		case SVMLexer.MUL:
			code[i++] = SVMParser.MUL;
			code[i++] = Regs.valueOf(ctx.dest.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			break;
		case SVMLexer.DIV:
			code[i++] = SVMParser.DIV;
			code[i++] = Regs.valueOf(ctx.dest.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			break;
		case SVMLexer.POP:
			code[i++] = SVMParser.POP;
			code[i++] = Integer.parseInt(ctx.NUMBER(0).getText());

			break;

		case SVMLexer.BRANCHEQ:
			code[i++] = SVMParser.BRANCHEQ;
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			labelRef.put(i++, ctx.LABEL().getText());
			break;

		case SVMLexer.BRANCHGT:
			code[i++] = SVMParser.BRANCHGT;
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			labelRef.put(i++, ctx.LABEL().getText());
			break;

		case SVMLexer.BRANCHGE:
			code[i++] = SVMParser.BRANCHGE;
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			labelRef.put(i++, ctx.LABEL().getText());
			break;

		case SVMLexer.BRANCHLT:
			code[i++] = SVMParser.BRANCHLT;
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			labelRef.put(i++, ctx.LABEL().getText());
			break;

		case SVMLexer.BRANCHLE:
			code[i++] = SVMParser.BRANCHLE;
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r2.getText()).ordinal();
			labelRef.put(i++, ctx.LABEL().getText());
			break;

		case SVMLexer.NEG:
			code[i++] = SVMParser.NEG;
			code[i++] = Regs.valueOf(ctx.REG(0).getText()).ordinal();
			break;

		case SVMLexer.MOVE:
			code[i++] = SVMParser.MOVE;
			code[i++] = Regs.valueOf(ctx.dest.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.origin.getText()).ordinal();
			break;

		case SVMLexer.ADDINTEGER:
			code[i++] = SVMParser.ADDINTEGER;
			code[i++] = Regs.valueOf(ctx.dest.getText()).ordinal();
			code[i++] = Regs.valueOf(ctx.r1.getText()).ordinal();
			code[i++] = Integer.parseInt(ctx.NUMBER(0).getText());
			break;

		case SVMLexer.JUMPLABEL:
			code[i++] = SVMParser.JUMPLABEL;
			labelRef.put(i++, ctx.LABEL().getText());
			break;

		case SVMLexer.JUMPREG:
			code[i++] = SVMParser.JUMPREG;
			break;

		case SVMLexer.PRINT:
			code[i++] = SVMParser.PRINT;
			break;

		default:
			break;
		}
		return null;
	}
}
