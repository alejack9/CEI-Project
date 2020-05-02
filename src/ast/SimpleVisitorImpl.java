package ast;

import java.util.LinkedList;
import java.util.List;

import parser.SimpleBaseVisitor;
import parser.SimpleParser.BinBoolExpContext;
import parser.SimpleParser.AssignParamDefContext;
import parser.SimpleParser.AssignmentContext;
import parser.SimpleParser.BaseBoolExpContext;
import parser.SimpleParser.BaseExpContext;
import parser.SimpleParser.BinExpContext;
import parser.SimpleParser.BlockContext;
import parser.SimpleParser.BoolExpAssignableContext;
import parser.SimpleParser.BoolExpContext;
import parser.SimpleParser.BoolExpParamDefContext;
import parser.SimpleParser.BoolExpReturnContext;
import parser.SimpleParser.CondBoolExpContext;
import parser.SimpleParser.DeclarationAssignmentContext;
import parser.SimpleParser.DeclarationContext;
import parser.SimpleParser.DeletionContext;
import parser.SimpleParser.ElseRuleContext;
import parser.SimpleParser.EqualCondContext;
import parser.SimpleParser.EqualIdNotCondContext;
import parser.SimpleParser.EqualNotCondContext;
import parser.SimpleParser.EqualNotIdCondContext;
import parser.SimpleParser.ExpAssignableContext;
import parser.SimpleParser.ExpParamDefContext;
import parser.SimpleParser.ExpReturnContext;
import parser.SimpleParser.FunCallReturnContext;
import parser.SimpleParser.FunctionCallAssignableContext;
import parser.SimpleParser.FunctionCallContext;
import parser.SimpleParser.FunctionDecContext;
import parser.SimpleParser.GreatCondContext;
import parser.SimpleParser.GreatEqCondContext;
import parser.SimpleParser.IfThenContext;
import parser.SimpleParser.IfThenElseContext;
import parser.SimpleParser.LessCondContext;
import parser.SimpleParser.LessEqCondContext;
import parser.SimpleParser.NegExpContext;
import parser.SimpleParser.NotContext;
import parser.SimpleParser.ParamDecContext;
import parser.SimpleParser.ParamDefContext;
import parser.SimpleParser.PrintContext;
import parser.SimpleParser.ReturnRuleContext;
import parser.SimpleParser.StatementContext;
import parser.SimpleParser.ValExpContext;
import parser.SimpleParser.VarAssignableContext;
import parser.SimpleParser.VarBoolExpContext;
import parser.SimpleParser.VarExpContext;
import parser.SimpleParser.VarReturnContext;

public class SimpleVisitorImpl extends SimpleBaseVisitor<SimpleElementBase> {
	
	@Override
	public SimpleElementBase visitBlock(BlockContext ctx) {

		List<SimpleStmt> children = new LinkedList<SimpleStmt>();

		for(StatementContext stmtCtx : ctx.statement())
			children.add((SimpleStmt)visitStatement(stmtCtx));

		return new SimpleStmtBlock(children);
	}
	
	@Override
	public SimpleElementBase visitStatement(StatementContext ctx) {
		SimpleElementBase toRet = ctx.getChild(0) == null? null: visit(ctx.getChild(0)); 
		return toRet;
	}
	
	@Override
	public SimpleElementBase visitDeclarationAssignment(DeclarationAssignmentContext ctx) {
		return new SimpleStmtDeclarationAssignment((SimpleStmtDeclaration)visit(ctx.declaration()),(SimpleStmtAssignable) visit(ctx.assignable())) ;
	}

	@Override
	public SimpleElementBase visitDeclaration(DeclarationContext ctx) {
		return new SimpleStmtDeclaration(ctx.TYPE().getText(),ctx.ID().getText());
	}
	
	@Override
	public SimpleElementBase visitExpAssignable(ExpAssignableContext ctx) {
		SimpleExp exp = (SimpleExp) visit(ctx.exp());

		return new SimpleStmtAssignableExp(exp);
	}
	
	@Override
	public SimpleElementBase visitVarAssignable(VarAssignableContext ctx) {
		return new SimpleStmtAssignableVar(ctx.ID().getText()) ;
	}
	
	@Override
	public SimpleElementBase visitBoolExpAssignable(BoolExpAssignableContext ctx) {
		SimpleBoolExp exp = (SimpleBoolExp) visit(ctx.boolExp());
		
		return new SimpleStmtAssignableBoolExp(exp) ;
	}
	
	@Override
	public SimpleElementBase visitFunctionCallAssignable(FunctionCallAssignableContext ctx) {
		
		SimpleStmtFunctionCall fun = (SimpleStmtFunctionCall) visit(ctx.functionCall());

		return new SimpleStmtAssignableFunctionCall(fun) ;
	}
	
	@Override
	public SimpleElementBase visitDeletion(DeletionContext ctx) {
		return new SimpleStmtDelete(ctx.ID().getText());
	}
	
	@Override
	public SimpleElementBase visitPrint(PrintContext ctx) {

		SimpleExp exp = (SimpleExp) visit(ctx.exp());
		return new SimpleStmtPrint(exp);
	}

	@Override
	public SimpleElementBase visitFunctionDec(FunctionDecContext ctx) {
		
		List<SimpleParamDec> params = new LinkedList<SimpleParamDec>();

		for(ParamDecContext paramCtx : ctx.paramDec())
			params.add((SimpleParamDec) visitParamDec(paramCtx));

		return new SimpleStmtFunctionDec(ctx.type.getText(), ctx.ID().getText(),params, (SimpleStmtBlock)visit(ctx.block()));
	}
	
	@Override
	public SimpleElementBase visitAssignParamDef(AssignParamDefContext ctx) {
		return new SimpleParamDefAssign((SimpleStmtAssignableExp)visit(ctx.assignment()));
	}
	
	@Override
	public SimpleElementBase visitBoolExpParamDef(BoolExpParamDefContext ctx) {
		return new SimpleParamDefBoolExp((SimpleBoolExp)visit(ctx.boolExp()));
	}
	
	@Override
	public SimpleElementBase visitExpParamDef(ExpParamDefContext ctx) {
		return new SimpleParamDefExp((SimpleExp)visit(ctx.exp()));
	}
	
	
	@Override
	public SimpleElementBase visitParamDec(ParamDecContext ctx) {
		return new SimpleParamDec(ctx.var.getText() != null, (SimpleStmtDeclaration)visit(ctx.declaration()));
	}
	
	@Override
	public SimpleElementBase visitFunctionCall(FunctionCallContext ctx) {
		List<SimpleParamDef> params = new LinkedList<SimpleParamDef>();
		for(ParamDefContext contexes : ctx.paramDef())
			params.add((SimpleParamDef) visit(contexes));

		return new SimpleStmtFunctionCall(ctx.ID().getText(), params);
	}
	

	/**
	 * RULE boolExp START
	 */
	
	@Override
	public SimpleElementBase visitBaseBoolExp(BaseBoolExpContext ctx) {
		return visit(ctx.boolExp());
	}

	@Override
	public SimpleElementBase visitBinBoolExp(BinBoolExpContext ctx) {
		SimpleBoolExp left = (SimpleBoolExp) visit(ctx.left);

		SimpleBoolExp right = (SimpleBoolExp) visit(ctx.right);

		switch (ctx.op.getText()) {
			case "&&": return new SimpleBoolExpAnd(left, right);
			case "||": return new SimpleBoolExpOr(left, right);
			default: return null; //this should not happen
		}
	}

	@Override
	public SimpleElementBase visitCondBoolExp(CondBoolExpContext ctx) {
		return visit(ctx.cond());
	}
	
	@Override
	public SimpleElementBase visitVarBoolExp(VarBoolExpContext ctx) {
		return new SimpleBoolExpVar(ctx.ID().getText());
	}

	/**
	 * RULE boolExp END
	 */
	
	/**
	 * RULE ifThenElse START
	 */
	
	public SimpleElementBase visitIfThenElse(IfThenElseContext ctx) {
		return new SimpleStmtIfThenElse((SimpleStmtIfThen)visit(ctx.ifThen()), ctx.elseRule() == null ? null : (SimpleStmtElseRule)visit(ctx.elseRule()));
	}
	
	public SimpleElementBase visitIfThen(IfThenContext ctx) {
		return new SimpleStmtIfThen((SimpleBoolExp)visit(ctx.boolExp()), (SimpleStmtBlock)visit(ctx.block()));
	}
	
	public SimpleElementBase visitElseRule(ElseRuleContext ctx) {
		return new SimpleStmtElseRule((SimpleStmtBlock)visit(ctx.block()));
	}
	
	/**
	 * RULE ifThenElse END
	 */

	@Override
	public SimpleElementBase visitNot(NotContext ctx) {
		return new SimpleCondNot((SimpleBoolExp)visit(ctx.boolExp()));
	}

	/**
	 * RULE cond START
	 */
	
	@Override
	public SimpleElementBase visitEqualNotCond(EqualNotCondContext ctx) {
		return new SimpleCondEqualNot((SimpleCondNot)visit(ctx.left), ctx.right == null ? null : (SimpleCondNot)visit(ctx.right));
	}
	
	@Override
	public SimpleElementBase visitEqualNotIdCond(EqualNotIdCondContext ctx) {
		return new SimpleCondEqualNotId((SimpleCondNot)visit(ctx.left), ctx.right.getText());
	}
	
	@Override
	public SimpleElementBase visitEqualIdNotCond(EqualIdNotCondContext ctx) {
		return new SimpleCondEqualIdNot(ctx.left.getText(), (SimpleCondNot)visit(ctx.right));
	}
	
	@Override
	public SimpleElementBase visitEqualCond(EqualCondContext ctx) {
		return new SimpleCondEqual((SimpleExp)visit(ctx.left), (SimpleExp)visit(ctx.right));
	}
	
	@Override
	public SimpleElementBase visitGreatEqCond(GreatEqCondContext ctx) {

		SimpleExp left = (SimpleExp) visit(ctx.left);
		SimpleExp right = (SimpleExp) visit(ctx.right);
		
		return new SimpleCondGreatEqual(left, right);
	}

	@Override
	public SimpleElementBase visitGreatCond(GreatCondContext ctx) {

		SimpleExp left = (SimpleExp) visit(ctx.left);
		SimpleExp right = (SimpleExp) visit(ctx.right);
		
		return new SimpleCondGreat(left, right);
	}

	@Override
	public SimpleElementBase visitLessEqCond(LessEqCondContext ctx) {

		SimpleExp left = (SimpleExp) visit(ctx.left);
		SimpleExp right = (SimpleExp) visit(ctx.right);
		
		return new SimpleCondLessEqual(left, right);
	}

	@Override
	public SimpleElementBase visitLessCond(LessCondContext ctx) {

		SimpleExp left = (SimpleExp) visit(ctx.left);
		SimpleExp right = (SimpleExp) visit(ctx.right);
		
		return new SimpleCondLess(left, right);
	}
	
	/**
	 * RULE cond END
	 */
	
	/**
	 * RULE returnRule START
	 */
	
	@Override
	public SimpleElementBase visitVarReturn(VarReturnContext ctx) {
		return new SimpleReturnVar(ctx.ID().getText());
	}

	@Override
	public SimpleElementBase visitBoolExpReturn(BoolExpReturnContext ctx) {
		return new SimpleReturnBoolExp((SimpleBoolExp)visit(ctx.boolExp()));
	}

	@Override
	public SimpleElementBase visitExpReturn(ExpReturnContext ctx) {
		return new SimpleReturnExp((SimpleExp)visit(ctx.exp()));
	}
	
	@Override
	public SimpleElementBase visitFunCallReturn(FunCallReturnContext ctx) {
		return new SimpleReturnFunCall((SimpleStmtFunctionCall)visit(ctx.functionCall()));
	}
	/**
	 * RULE returnRule END
	 */
	
	/**
	 * RULE exp START
	 */
	
	@Override
	public SimpleElementBase visitBaseExp(BaseExpContext ctx) {
		return visit(ctx.exp());
	}
	
	@Override
	public SimpleElementBase visitNegExp(NegExpContext ctx) {
		return new SimpleExpNeg((SimpleExp) visit(ctx.exp()));
	}
	
	@Override
	public SimpleElementBase visitBinExp(BinExpContext ctx) {

		SimpleExp left = (SimpleExp) visit(ctx.left);

		SimpleExp right = (SimpleExp) visit(ctx.right);

		switch (ctx.op.getText()) {
			case "+": return new SimpleExpSum(left, right);
			case "-": return new SimpleExpDiff(left, right);
			case "*": return new SimpleExpMult(left, right);
			case "/": return new SimpleExpDiv(left, right);
			default: return null; //this should not happen
		}

	}
	
	@Override
	public SimpleElementBase visitValExp(ValExpContext ctx) {
		return new SimpleExpVal(Integer.parseInt(ctx.NUMBER().getText()));
	}
	
	@Override
	public SimpleElementBase visitVarExp(VarExpContext ctx) {
		return new SimpleExpVar(ctx.ID().getText());
	}

	/**
	 * RULE exp END
	 */
}
