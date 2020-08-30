// Generated from SimplePlus.g4 by ANTLR 4.4
package parser;

import java.util.List;
import java.util.LinkedList;
import ast.errors.LexicalError;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimplePlusParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface SimplePlusVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#ret}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRet(@NotNull SimplePlusParser.RetContext ctx);

	/**
	 * Visit a parse tree produced by the {@code baseExp} labeled alternative in
	 * {@link SimplePlusParser#exp}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseExp(@NotNull SimplePlusParser.BaseExpContext ctx);

	/**
	 * Visit a parse tree produced by the {@code binExp} labeled alternative in
	 * {@link SimplePlusParser#exp}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinExp(@NotNull SimplePlusParser.BinExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#assignment}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull SimplePlusParser.AssignmentContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#decVar}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecVar(@NotNull SimplePlusParser.DecVarContext ctx);

	/**
	 * Visit a parse tree produced by the {@code boolExp} labeled alternative in
	 * {@link SimplePlusParser#exp}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExp(@NotNull SimplePlusParser.BoolExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#type}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull SimplePlusParser.TypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code callExp} labeled alternative in
	 * {@link SimplePlusParser#exp}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExp(@NotNull SimplePlusParser.CallExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#declaration}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(@NotNull SimplePlusParser.DeclarationContext ctx);

	/**
	 * Visit a parse tree produced by the {@code notExp} labeled alternative in
	 * {@link SimplePlusParser#exp}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExp(@NotNull SimplePlusParser.NotExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#call}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(@NotNull SimplePlusParser.CallContext ctx);

	/**
	 * Visit a parse tree produced by the {@code varExp} labeled alternative in
	 * {@link SimplePlusParser#exp}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExp(@NotNull SimplePlusParser.VarExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#ref}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRef(@NotNull SimplePlusParser.RefContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#print}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(@NotNull SimplePlusParser.PrintContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#deletion}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeletion(@NotNull SimplePlusParser.DeletionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#arg}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(@NotNull SimplePlusParser.ArgContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#statement}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull SimplePlusParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code valExp} labeled alternative in
	 * {@link SimplePlusParser#exp}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValExp(@NotNull SimplePlusParser.ValExpContext ctx);

	/**
	 * Visit a parse tree produced by the {@code negExp} labeled alternative in
	 * {@link SimplePlusParser#exp}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExp(@NotNull SimplePlusParser.NegExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#block}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull SimplePlusParser.BlockContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#ite}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIte(@NotNull SimplePlusParser.IteContext ctx);

	/**
	 * Visit a parse tree produced by {@link SimplePlusParser#decFun}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecFun(@NotNull SimplePlusParser.DecFunContext ctx);
}