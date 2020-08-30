/*
 * 
 */
package ast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ast.types.EType;
import parser.SimplePlusBaseVisitor;
import parser.SimplePlusParser.ArgContext;
import parser.SimplePlusParser.AssignmentContext;
import parser.SimplePlusParser.BaseExpContext;
import parser.SimplePlusParser.BinExpContext;
import parser.SimplePlusParser.BlockContext;
import parser.SimplePlusParser.BoolExpContext;
import parser.SimplePlusParser.CallContext;
import parser.SimplePlusParser.CallExpContext;
import parser.SimplePlusParser.DecFunContext;
import parser.SimplePlusParser.DecVarContext;
import parser.SimplePlusParser.DeclarationContext;
import parser.SimplePlusParser.DeletionContext;
import parser.SimplePlusParser.IteContext;
import parser.SimplePlusParser.NegExpContext;
import parser.SimplePlusParser.NotExpContext;
import parser.SimplePlusParser.PrintContext;
import parser.SimplePlusParser.RetContext;
import parser.SimplePlusParser.StatementContext;
import parser.SimplePlusParser.ValExpContext;
import parser.SimplePlusParser.VarExpContext;

public class VisitorImplSP extends SimplePlusBaseVisitor<ElementBase> {

	/**
	 * A list of last dimensions of arguments.<br />
	 * Used to pass to the return element the parameters dimension
	 */
	private List<Integer> lastArgsDimension = new LinkedList<Integer>();

	@Override
	public Stmt visitStatement(StatementContext ctx) {
		return (Stmt) visit(ctx.getChild(0));
	}

	@Override
	public StmtDec visitDeclaration(DeclarationContext ctx) {
		return (StmtDec) visit(ctx.getChild(0));
	}

	@Override
	public StmtAssignment visitAssignment(AssignmentContext ctx) {
		return new StmtAssignment(ctx.ID().getText(), (Exp) visit(ctx.exp()), ctx.start.getLine(),
				ctx.start.getCharPositionInLine());
	}

	@Override
	public StmtDelete visitDeletion(DeletionContext ctx) {
		return new StmtDelete(ctx.ID().getText(), ctx.ID().getSymbol().getLine(),
				ctx.ID().getSymbol().getCharPositionInLine());
	}

	@Override
	public StmtPrint visitPrint(PrintContext ctx) {
		return new StmtPrint((Exp) visit(ctx.exp()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public StmtRet visitRet(RetContext ctx) {
		return new StmtRet(ctx.exp() == null ? null : (Exp) visit(ctx.exp()), lastArgsDimension, ctx.start.getLine(),
				ctx.start.getCharPositionInLine());
	}

	@Override
	public StmtIte visitIte(IteContext ctx) {
		return new StmtIte((Exp) visit(ctx.exp()), visitStatement(ctx.statement(0)),
				ctx.statement(1) == null ? null : visitStatement(ctx.statement(1)), ctx.start.getLine(),
				ctx.start.getCharPositionInLine());
	}

	@Override
	public StmtCall visitCall(CallContext ctx) {
		List<Exp> exps = ctx.exp() == null ? Collections.emptyList()
				: ctx.exp().stream().map(p -> (Exp) visit(p)).collect(Collectors.toList());
		return new StmtCall(ctx.ID().getText(), exps, ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public StmtBlock visitBlock(BlockContext ctx) {
		List<Stmt> children = ctx.statement().stream().map(this::visitStatement).collect(Collectors.toList());
		return new StmtBlock(children, ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public Exp visitBaseExp(BaseExpContext ctx) {
		return (Exp) visit(ctx.exp());
	}

	@Override
	public ExpNeg visitNegExp(NegExpContext ctx) {
		return new ExpNeg((Exp) visit(ctx.exp()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public ExpNot visitNotExp(NotExpContext ctx) {
		return new ExpNot((Exp) visit(ctx.exp()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public Exp visitBinExp(BinExpContext ctx) {
		Exp left = (Exp) visit(ctx.left);
		Exp right = (Exp) visit(ctx.right);
		switch (ctx.op.getText()) {
		case "+":
			return new ExpSum(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "-":
			return new ExpSub(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "*":
			return new ExpMult(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "/":
			return new ExpDiv(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "<":
			return new ExpLessThan(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "<=":
			return new ExpLessThanEq(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case ">":
			return new ExpGreaterThan(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case ">=":
			return new ExpGreaterThanEq(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "==":
			return new ExpEqual(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "!=":
			return new ExpNotEqual(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "&&":
			return new ExpAnd(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		case "||":
			return new ExpOr(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		default:
			return null;
		}
	}

	@Override
	public ExpCall visitCallExp(CallExpContext ctx) {
		return new ExpCall(visitCall(ctx.call()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public ExpBool visitBoolExp(BoolExpContext ctx) {
		return new ExpBool(ctx.getText().equals("true"), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public ExpVar visitVarExp(VarExpContext ctx) {
		return new ExpVar(ctx.ID().getText(), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public ExpVal visitValExp(ValExpContext ctx) {
		return new ExpVal(Integer.parseInt(ctx.NUMBER().getText()), ctx.start.getLine(),
				ctx.start.getCharPositionInLine());
	}

	@Override
	public StmtDecFun visitDecFun(DecFunContext ctx) {
		List<Arg> args = new LinkedList<Arg>();
		// reset the lastArgsDimension list
		lastArgsDimension = new LinkedList<Integer>();
		if (ctx.arg() != null)
			for (ArgContext spArg : ctx.arg()) {
				// add arguments to "args" array
				args.add(visitArg(spArg));
				// add dimension to list
				lastArgsDimension.add(args.get(args.size() - 1).getType().getDimension());
			}
		StmtDecFun toRet = new StmtDecFun(EType.getEnum(ctx.type().getText()).getType(), ctx.ID().getText(), args,
				visitBlock(ctx.block()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
		// reset the lastArgsDimension list again
		lastArgsDimension = new LinkedList<Integer>();
		return toRet;
	}

	@Override
	public Arg visitArg(ArgContext ctx) {
		return new Arg(ctx.type().getText(), ctx.ID().getText(), ctx.ref() != null, ctx.start.getLine(),
				ctx.start.getCharPositionInLine());
	}

	@Override
	public StmtDecVar visitDecVar(DecVarContext ctx) {
		return new StmtDecVar(EType.getEnum(ctx.type().getText()).getType(), ctx.ID().getText(),
				ctx.exp() == null ? null : (Exp) visit(ctx.exp()), ctx.start.getLine(),
				ctx.start.getCharPositionInLine());
	}
}
