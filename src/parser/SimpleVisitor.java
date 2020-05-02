// Generated from Simple.g4 by ANTLR 4.6
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimpleParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SimpleParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SimpleParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(SimpleParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#declarationAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationAssignment(SimpleParser.DeclarationAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varAssignable}
	 * labeled alternative in {@link SimpleParser#assignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssignable(SimpleParser.VarAssignableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expAssignable}
	 * labeled alternative in {@link SimpleParser#assignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpAssignable(SimpleParser.ExpAssignableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpAssignable}
	 * labeled alternative in {@link SimpleParser#assignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpAssignable(SimpleParser.BoolExpAssignableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallAssignable}
	 * labeled alternative in {@link SimpleParser#assignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallAssignable(SimpleParser.FunctionCallAssignableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(SimpleParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#deletion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeletion(SimpleParser.DeletionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(SimpleParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#functionDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDec(SimpleParser.FunctionDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#paramDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamDec(SimpleParser.ParamDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignParamDef}
	 * labeled alternative in {@link SimpleParser#paramDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignParamDef(SimpleParser.AssignParamDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpParamDef}
	 * labeled alternative in {@link SimpleParser#paramDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpParamDef(SimpleParser.BoolExpParamDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expParamDef}
	 * labeled alternative in {@link SimpleParser#paramDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpParamDef(SimpleParser.ExpParamDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(SimpleParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#ifThenElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenElse(SimpleParser.IfThenElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#ifThen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThen(SimpleParser.IfThenContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#elseRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseRule(SimpleParser.ElseRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varReturn}
	 * labeled alternative in {@link SimpleParser#returnRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarReturn(SimpleParser.VarReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpReturn}
	 * labeled alternative in {@link SimpleParser#returnRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpReturn(SimpleParser.BoolExpReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expReturn}
	 * labeled alternative in {@link SimpleParser#returnRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpReturn(SimpleParser.ExpReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funCallReturn}
	 * labeled alternative in {@link SimpleParser#returnRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCallReturn(SimpleParser.FunCallReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condBoolExp}
	 * labeled alternative in {@link SimpleParser#boolExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondBoolExp(SimpleParser.CondBoolExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseBoolExp}
	 * labeled alternative in {@link SimpleParser#boolExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseBoolExp(SimpleParser.BaseBoolExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varBoolExp}
	 * labeled alternative in {@link SimpleParser#boolExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarBoolExp(SimpleParser.VarBoolExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binBoolExp}
	 * labeled alternative in {@link SimpleParser#boolExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinBoolExp(SimpleParser.BinBoolExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalNotCond}
	 * labeled alternative in {@link SimpleParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualNotCond(SimpleParser.EqualNotCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalNotIdCond}
	 * labeled alternative in {@link SimpleParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualNotIdCond(SimpleParser.EqualNotIdCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalIdNotCond}
	 * labeled alternative in {@link SimpleParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualIdNotCond(SimpleParser.EqualIdNotCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalCond}
	 * labeled alternative in {@link SimpleParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualCond(SimpleParser.EqualCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code greatEqCond}
	 * labeled alternative in {@link SimpleParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreatEqCond(SimpleParser.GreatEqCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessEqCond}
	 * labeled alternative in {@link SimpleParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessEqCond(SimpleParser.LessEqCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code greatCond}
	 * labeled alternative in {@link SimpleParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreatCond(SimpleParser.GreatCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessCond}
	 * labeled alternative in {@link SimpleParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessCond(SimpleParser.LessCondContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(SimpleParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link SimpleParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseExp(SimpleParser.BaseExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link SimpleParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExp(SimpleParser.VarExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binExp}
	 * labeled alternative in {@link SimpleParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinExp(SimpleParser.BinExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valExp}
	 * labeled alternative in {@link SimpleParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValExp(SimpleParser.ValExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExp}
	 * labeled alternative in {@link SimpleParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExp(SimpleParser.NegExpContext ctx);
}