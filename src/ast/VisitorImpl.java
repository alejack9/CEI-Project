package ast;

import java.util.stream.Collectors;

import parser.SimpleBaseVisitor;
import parser.SimpleParser.BinBoolExpContext;
import parser.SimpleParser.AssignParamDefContext;
import parser.SimpleParser.BaseBoolExpContext;
import parser.SimpleParser.BaseExpContext;
import parser.SimpleParser.BinExpContext;
import parser.SimpleParser.BlockContext;
import parser.SimpleParser.BoolExpAssignableContext;
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
import parser.SimpleParser.PrintContext;
import parser.SimpleParser.StatementContext;
import parser.SimpleParser.ValExpContext;
import parser.SimpleParser.VarAssignableContext;
import parser.SimpleParser.VarBoolExpContext;
import parser.SimpleParser.VarExpContext;
import parser.SimpleParser.VarReturnContext;

public class VisitorImpl extends SimpleBaseVisitor<ElementBase> {

	@Override
	public ElementBase visitBlock(BlockContext ctx) {
		return new StmtBlock(ctx.statement().stream().map(s -> (Stmt) visitStatement(s)).collect(Collectors.toList()));
	}

	@Override
	public ElementBase visitStatement(StatementContext ctx) {
		ElementBase toRet = visit(ctx.getChild(0));
		return toRet;
	}

	@Override
	public ElementBase visitDeclarationAssignment(DeclarationAssignmentContext ctx) {
		return new StmtDeclarationAssignment((StmtDeclaration) visit(ctx.declaration()),
				(StmtAssignable) visit(ctx.assignable()));
	}

	@Override
	public ElementBase visitDeclaration(DeclarationContext ctx) {
		return new StmtDeclaration(ctx.TYPE().getText(), ctx.ID().getText());
	}

	@Override
	public ElementBase visitExpAssignable(ExpAssignableContext ctx) {
		Exp exp = (Exp) visit(ctx.exp());

		return new StmtAssignableExp(exp);
	}

	@Override
	public ElementBase visitVarAssignable(VarAssignableContext ctx) {
		return new StmtAssignableVar(ctx.ID().getText());
	}

	@Override
	public ElementBase visitBoolExpAssignable(BoolExpAssignableContext ctx) {
		return new StmtAssignableBoolExp((BoolExp) visit(ctx.boolExp()));
	}

	@Override
	public ElementBase visitFunctionCallAssignable(FunctionCallAssignableContext ctx) {
		return new StmtAssignableFunctionCall((StmtFunctionCall) visit(ctx.functionCall()));
	}

	@Override
	public ElementBase visitDeletion(DeletionContext ctx) {
		return new StmtDelete(ctx.ID().getText());
	}

	@Override
	public ElementBase visitPrint(PrintContext ctx) {
		return new StmtPrint((Exp) visit(ctx.exp()));
	}

	@Override
	public ElementBase visitFunctionDec(FunctionDecContext ctx) {
		return new StmtFunctionDec(ctx.type.getText(), ctx.ID().getText(),
				ctx.paramDec().stream().map(s -> (ParamDec) visitParamDec(s)).collect(Collectors.toList()),
				(StmtBlock) visit(ctx.block()));
	}

	@Override
	public ElementBase visitAssignParamDef(AssignParamDefContext ctx) {
		return new ParamDefAssign((StmtAssignableExp) visit(ctx.assignment()));
	}

	@Override
	public ElementBase visitBoolExpParamDef(BoolExpParamDefContext ctx) {
		return new ParamDefBoolExp((BoolExp) visit(ctx.boolExp()));
	}

	@Override
	public ElementBase visitExpParamDef(ExpParamDefContext ctx) {
		return new ParamDefExp((Exp) visit(ctx.exp()));
	}

	@Override
	public ElementBase visitParamDec(ParamDecContext ctx) {
		return new ParamDec(ctx.var != null, (StmtDeclaration) visit(ctx.declaration()));
	}

	@Override
	public ElementBase visitFunctionCall(FunctionCallContext ctx) {
		return new StmtFunctionCall(ctx.ID().getText(),
				ctx.paramDef().stream().map(s -> (ParamDef) visit(s)).collect(Collectors.toList()));
	}

	@Override
	public ElementBase visitBaseBoolExp(BaseBoolExpContext ctx) {
		return visit(ctx.boolExp());
	}

	@Override
	public ElementBase visitBinBoolExp(BinBoolExpContext ctx) {
		switch (ctx.op.getText()) {
		case "&&":
			return new BoolExpAnd((BoolExp) visit(ctx.left), (BoolExp) visit(ctx.right));
		case "||":
			return new BoolExpOr((BoolExp) visit(ctx.left), (BoolExp) visit(ctx.right));
		default:
			return null;
		}
	}

	@Override
	public ElementBase visitCondBoolExp(CondBoolExpContext ctx) {
		return visit(ctx.cond());
	}

	@Override
	public ElementBase visitVarBoolExp(VarBoolExpContext ctx) {
		return new BoolExpVar(ctx.ID().getText());
	}

	public ElementBase visitIfThenElse(IfThenElseContext ctx) {
		return new StmtIfThenElse((StmtIfThen) visit(ctx.ifThen()),
				ctx.elseRule() == null ? null : (StmtElseRule) visit(ctx.elseRule()));
	}

	public ElementBase visitIfThen(IfThenContext ctx) {
		return new StmtIfThen((BoolExp) visit(ctx.boolExp()), (StmtBlock) visit(ctx.block()));
	}

	public ElementBase visitElseRule(ElseRuleContext ctx) {
		return new StmtElseRule((StmtBlock) visit(ctx.block()));
	}

	@Override
	public ElementBase visitNot(NotContext ctx) {
		return new CondNot((BoolExp) visit(ctx.boolExp()));
	}

	@Override
	public ElementBase visitEqualNotCond(EqualNotCondContext ctx) {
		return new CondEqualNot((CondNot) visit(ctx.left), ctx.right == null ? null : (CondNot) visit(ctx.right));
	}

	@Override
	public ElementBase visitEqualNotIdCond(EqualNotIdCondContext ctx) {
		return new CondEqualNotId((CondNot) visit(ctx.left), ctx.right.getText());
	}

	@Override
	public ElementBase visitEqualIdNotCond(EqualIdNotCondContext ctx) {
		return new CondEqualIdNot(ctx.left.getText(), (CondNot) visit(ctx.right));
	}

	@Override
	public ElementBase visitEqualCond(EqualCondContext ctx) {
		return new CondEqual((Exp) visit(ctx.left), (Exp) visit(ctx.right));
	}

	@Override
	public ElementBase visitGreatEqCond(GreatEqCondContext ctx) {
		return new CondGreatEqual((Exp) visit(ctx.left), (Exp) visit(ctx.right));
	}

	@Override
	public ElementBase visitGreatCond(GreatCondContext ctx) {
		return new CondGreat((Exp) visit(ctx.left), (Exp) visit(ctx.right));
	}

	@Override
	public ElementBase visitLessEqCond(LessEqCondContext ctx) {
		return new CondLessEqual((Exp) visit(ctx.left), (Exp) visit(ctx.right));
	}

	@Override
	public ElementBase visitLessCond(LessCondContext ctx) {
		return new CondLess((Exp) visit(ctx.left), (Exp) visit(ctx.right));
	}

	@Override
	public ElementBase visitVarReturn(VarReturnContext ctx) {
		return new ReturnVar(ctx.ID().getText());
	}

	@Override
	public ElementBase visitBoolExpReturn(BoolExpReturnContext ctx) {
		return new ReturnBoolExp((BoolExp) visit(ctx.boolExp()));
	}

	@Override
	public ElementBase visitExpReturn(ExpReturnContext ctx) {
		return new ReturnExp((Exp) visit(ctx.exp()));
	}

	@Override
	public ElementBase visitFunCallReturn(FunCallReturnContext ctx) {
		return new ReturnFunCall((StmtFunctionCall) visit(ctx.functionCall()));
	}

	@Override
	public ElementBase visitBaseExp(BaseExpContext ctx) {
		return visit(ctx.exp());
	}

	@Override
	public ElementBase visitNegExp(NegExpContext ctx) {
		return new ExpNeg((Exp) visit(ctx.exp()));
	}

	@Override
	public ElementBase visitBinExp(BinExpContext ctx) {
		switch (ctx.op.getText()) {
		case "+":
			return new ExpSum((Exp) visit(ctx.left), (Exp) visit(ctx.right));
		case "-":
			return new ExpDiff((Exp) visit(ctx.left), (Exp) visit(ctx.right));
		case "*":
			return new ExpMult((Exp) visit(ctx.left), (Exp) visit(ctx.right));
		case "/":
			return new ExpDiv((Exp) visit(ctx.left), (Exp) visit(ctx.right));
		default:
			return null;
		}
	}

	@Override
	public ElementBase visitValExp(ValExpContext ctx) {
		return new ExpVal(Integer.parseInt(ctx.NUMBER().getText()));
	}

	@Override
	public ElementBase visitVarExp(VarExpContext ctx) {
		return new ExpVar(ctx.ID().getText());
	}
}
