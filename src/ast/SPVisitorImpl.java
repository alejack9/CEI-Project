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

public class SPVisitorImpl extends SimplePlusBaseVisitor<SPElementBase> {
	
	private List<SPArg> lastArgs = new LinkedList<SPArg>();
	
	@Override
	public SPStmt visitStatement(StatementContext ctx) {
		return (SPStmt)visit(ctx.getChild(0));
	}
	
	@Override
	public SPStmtDec visitDeclaration(DeclarationContext ctx) {
		return (SPStmtDec)visit(ctx.getChild(0));
	}
	
	@Override
	public SPStmtAssignment visitAssignment(AssignmentContext ctx) {
		return new SPStmtAssignment(ctx.ID().getText(),(SPExp) visit(ctx.exp()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPStmtDelete visitDeletion(DeletionContext ctx) {
		return new SPStmtDelete(ctx.ID().getText(), ctx.ID().getSymbol().getLine(), ctx.ID().getSymbol().getCharPositionInLine());
	}
	
	@Override
	public SPStmtPrint visitPrint(PrintContext ctx) {
		return new SPStmtPrint((SPExp) visit(ctx.exp()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPStmtRet visitRet(RetContext ctx) {
		return new SPStmtRet(ctx.exp() == null ? null : (SPExp) visit(ctx.exp()), lastArgs, ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPStmtIte visitIte(IteContext ctx) {
		return new SPStmtIte((SPExp) visit(ctx.exp()), visitStatement(ctx.statement(0)), ctx.statement(1) == null ? null : visitStatement(ctx.statement(1)), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPStmtCall visitCall(CallContext ctx) {
		List<SPExp> exps = ctx.exp() == null ? Collections.emptyList() : ctx.exp().stream().map(p->(SPExp)visit(p)).collect(Collectors.toList());
		return new SPStmtCall(ctx.ID().getText(), exps, ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPStmtBlock visitBlock(BlockContext ctx) {
		List<SPStmt> children =
			ctx.statement().stream().map(this::visitStatement).collect(Collectors.toList());
		return new SPStmtBlock(children, ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPExp visitBaseExp(BaseExpContext ctx) {
		return (SPExp)visit(ctx.exp());
	}
	
	@Override
	public SPExpNeg visitNegExp(NegExpContext ctx) {
		return new SPExpNeg((SPExp) visit(ctx.exp()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPExpNot visitNotExp(NotExpContext ctx) {
		return new SPExpNot((SPExp) visit(ctx.exp()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPExp visitBinExp(BinExpContext ctx) {
		SPExp left = (SPExp) visit(ctx.left);
		SPExp right = (SPExp) visit(ctx.right);
		switch (ctx.op.getText()) {
			case "+": return new SPExpSum(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case "-": return new SPExpDiff(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case "*": return new SPExpMult(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case "/": return new SPExpDiv(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case "<": return new SPExpLessThan(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case "<=": return new SPExpLessThanEq(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case ">": return new SPExpGreaterThan(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case ">=": return new SPExpGreaterThanEq(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case "==": return new SPExpEqual(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case "!=": return new SPExpNotEqual(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case "&&": return new SPExpAnd(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			case "||": return new SPExpOr(left, right, ctx.start.getLine(), ctx.start.getCharPositionInLine());
			default: return null; //this should not happen
		}
	}
	
	@Override
	public SPExpCall visitCallExp(CallExpContext ctx) {
		return new SPExpCall(visitCall(ctx.call()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPExpBool visitBoolExp(BoolExpContext ctx) {
		return new SPExpBool(ctx.getText().equals("true"), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPExpVar visitVarExp(VarExpContext ctx) {
		return new SPExpVar(ctx.ID().getText(), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public SPExpVal visitValExp(ValExpContext ctx) {
		return new SPExpVal(Integer.parseInt(ctx.NUMBER().getText()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
	}

	@Override
	public SPStmtDecFun visitDecFun(DecFunContext ctx) {
		lastArgs = ctx.arg() == null ? Collections.emptyList() : ctx.arg().stream().map(this::visitArg).collect(Collectors.toList());
		return new SPStmtDecFun(
				EType.getEnum(ctx.type().getText()).getType(),
				ctx.ID().getText(),
				lastArgs,
				visitBlock(ctx.block()),
				ctx.start.getLine(),
				ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPArg visitArg(ArgContext ctx) {
		return new SPArg(
				ctx.type().getText(),
				ctx.ID().getText(),
				ctx.ref() != null,
				ctx.start.getLine(),
				ctx.start.getCharPositionInLine());
	}
	
	@Override
	public SPStmtDecVar visitDecVar(DecVarContext ctx) {
		return new SPStmtDecVar(
				EType.getEnum(ctx.type().getText()).getType(),
				ctx.ID().getText(),
				ctx.exp() == null ? null :
				(SPExp)visit(ctx.exp()),
				ctx.start.getLine(),
				ctx.start.getCharPositionInLine());
	}
}
