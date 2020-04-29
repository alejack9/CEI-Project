// Generated from Simple.g4 by ANTLR 4.6
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, TYPE=28, ID=29, NUMBER=30, WS=31, LINECOMMENTS=32, 
		BLOCKCOMMENTS=33;
	public static final int
		RULE_block = 0, RULE_statement = 1, RULE_declaration = 2, RULE_declarationAssignment = 3, 
		RULE_assignable = 4, RULE_assignment = 5, RULE_deletion = 6, RULE_print = 7, 
		RULE_functionDec = 8, RULE_paramDef = 9, RULE_variableRef = 10, RULE_functionCall = 11, 
		RULE_ifThenElse = 12, RULE_ifThen = 13, RULE_elseRule = 14, RULE_returnRule = 15, 
		RULE_boolExp = 16, RULE_cond = 17, RULE_not = 18, RULE_exp = 19;
	public static final String[] ruleNames = {
		"block", "statement", "declaration", "declarationAssignment", "assignable", 
		"assignment", "deletion", "print", "functionDec", "paramDef", "variableRef", 
		"functionCall", "ifThenElse", "ifThen", "elseRule", "returnRule", "boolExp", 
		"cond", "not", "exp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "';'", "'='", "'delete'", "'print'", "'void'", "'('", 
		"','", "')'", "'var'", "'if'", "'then'", "'else'", "'return'", "'&&'", 
		"'||'", "'=='", "'>='", "'<='", "'>'", "'<'", "'!'", "'-'", "'*'", "'/'", 
		"'+'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "TYPE", "ID", "NUMBER", "WS", "LINECOMMENTS", 
		"BLOCKCOMMENTS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Simple.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimpleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__0);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__11) | (1L << T__14) | (1L << TYPE) | (1L << ID))) != 0)) {
				{
				{
				setState(41);
				statement();
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public ReturnRuleContext returnRule() {
			return getRuleContext(ReturnRuleContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FunctionDecContext functionDec() {
			return getRuleContext(FunctionDecContext.class,0);
		}
		public IfThenElseContext ifThenElse() {
			return getRuleContext(IfThenElseContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public DeclarationAssignmentContext declarationAssignment() {
			return getRuleContext(DeclarationAssignmentContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public DeletionContext deletion() {
			return getRuleContext(DeletionContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				returnRule();
				setState(50);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				functionCall();
				setState(53);
				match(T__2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				functionDec();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(56);
				ifThenElse();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(57);
				declaration();
				setState(58);
				match(T__2);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(60);
				declarationAssignment();
				setState(61);
				match(T__2);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(63);
				assignment();
				setState(64);
				match(T__2);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(66);
				deletion();
				setState(67);
				match(T__2);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(69);
				print();
				setState(70);
				match(T__2);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(72);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(SimpleParser.TYPE, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(TYPE);
			setState(76);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationAssignmentContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public DeclarationAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationAssignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitDeclarationAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationAssignmentContext declarationAssignment() throws RecognitionException {
		DeclarationAssignmentContext _localctx = new DeclarationAssignmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declarationAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			declaration();
			setState(79);
			match(T__3);
			setState(80);
			assignable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignableContext extends ParserRuleContext {
		public AssignableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignable; }
	 
		public AssignableContext() { }
		public void copyFrom(AssignableContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarAssignableContext extends AssignableContext {
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public VarAssignableContext(AssignableContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitVarAssignable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolExpAssignableContext extends AssignableContext {
		public BoolExpContext boolExp() {
			return getRuleContext(BoolExpContext.class,0);
		}
		public BoolExpAssignableContext(AssignableContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitBoolExpAssignable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpAssignableContext extends AssignableContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ExpAssignableContext(AssignableContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitExpAssignable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionCallAssignableContext extends AssignableContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FunctionCallAssignableContext(AssignableContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitFunctionCallAssignable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignableContext assignable() throws RecognitionException {
		AssignableContext _localctx = new AssignableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignable);
		try {
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new VarAssignableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				match(ID);
				}
				break;
			case 2:
				_localctx = new ExpAssignableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				exp(0);
				}
				break;
			case 3:
				_localctx = new BoolExpAssignableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				boolExp(0);
				}
				break;
			case 4:
				_localctx = new FunctionCallAssignableContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(85);
				functionCall();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(ID);
			setState(89);
			match(T__3);
			setState(90);
			assignable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeletionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public DeletionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deletion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitDeletion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeletionContext deletion() throws RecognitionException {
		DeletionContext _localctx = new DeletionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_deletion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__4);
			setState(93);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__5);
			setState(96);
			exp(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDecContext extends ParserRuleContext {
		public Token type;
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(SimpleParser.TYPE, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public FunctionDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitFunctionDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDecContext functionDec() throws RecognitionException {
		FunctionDecContext _localctx = new FunctionDecContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			((FunctionDecContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__6 || _la==TYPE) ) {
				((FunctionDecContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(99);
			match(ID);
			setState(100);
			match(T__7);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(101);
				declaration();
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(102);
					match(T__8);
					setState(103);
					declaration();
					}
					}
					setState(108);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(111);
			match(T__9);
			setState(112);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamDefContext extends ParserRuleContext {
		public ParamDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramDef; }
	 
		public ParamDefContext() { }
		public void copyFrom(ParamDefContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpParamDefContext extends ParamDefContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ExpParamDefContext(ParamDefContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitExpParamDef(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarRefParamDefContext extends ParamDefContext {
		public VariableRefContext variableRef() {
			return getRuleContext(VariableRefContext.class,0);
		}
		public VarRefParamDefContext(ParamDefContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitVarRefParamDef(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolExpParamDefContext extends ParamDefContext {
		public BoolExpContext boolExp() {
			return getRuleContext(BoolExpContext.class,0);
		}
		public BoolExpParamDefContext(ParamDefContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitBoolExpParamDef(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignParamDefContext extends ParamDefContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public AssignParamDefContext(ParamDefContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitAssignParamDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamDefContext paramDef() throws RecognitionException {
		ParamDefContext _localctx = new ParamDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_paramDef);
		try {
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new VarRefParamDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				variableRef();
				}
				break;
			case 2:
				_localctx = new AssignParamDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				assignment();
				}
				break;
			case 3:
				_localctx = new BoolExpParamDefContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				boolExp(0);
				}
				break;
			case 4:
				_localctx = new ExpParamDefContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				exp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableRefContext extends ParserRuleContext {
		public Token var;
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public VariableRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableRef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitVariableRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableRefContext variableRef() throws RecognitionException {
		VariableRefContext _localctx = new VariableRefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_variableRef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(120);
				((VariableRefContext)_localctx).var = match(T__10);
				}
			}

			setState(123);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public List<ParamDefContext> paramDef() {
			return getRuleContexts(ParamDefContext.class);
		}
		public ParamDefContext paramDef(int i) {
			return getRuleContext(ParamDefContext.class,i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(ID);
			setState(126);
			match(T__7);
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__10) | (1L << T__22) | (1L << T__23) | (1L << ID) | (1L << NUMBER))) != 0)) {
				{
				setState(127);
				paramDef();
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(128);
					match(T__8);
					setState(129);
					paramDef();
					}
					}
					setState(134);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(137);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfThenElseContext extends ParserRuleContext {
		public IfThenContext ifThen() {
			return getRuleContext(IfThenContext.class,0);
		}
		public ElseRuleContext elseRule() {
			return getRuleContext(ElseRuleContext.class,0);
		}
		public IfThenElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifThenElse; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitIfThenElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfThenElseContext ifThenElse() throws RecognitionException {
		IfThenElseContext _localctx = new IfThenElseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ifThenElse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			ifThen();
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(140);
				elseRule();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfThenContext extends ParserRuleContext {
		public BoolExpContext boolExp() {
			return getRuleContext(BoolExpContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfThenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifThen; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitIfThen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfThenContext ifThen() throws RecognitionException {
		IfThenContext _localctx = new IfThenContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ifThen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__11);
			setState(144);
			match(T__7);
			setState(145);
			boolExp(0);
			setState(146);
			match(T__9);
			setState(147);
			match(T__12);
			setState(148);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElseRuleContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ElseRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseRule; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitElseRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseRuleContext elseRule() throws RecognitionException {
		ElseRuleContext _localctx = new ElseRuleContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_elseRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__13);
			setState(151);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnRuleContext extends ParserRuleContext {
		public ReturnRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnRule; }
	 
		public ReturnRuleContext() { }
		public void copyFrom(ReturnRuleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarReturnContext extends ReturnRuleContext {
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public VarReturnContext(ReturnRuleContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitVarReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolExpReturnContext extends ReturnRuleContext {
		public BoolExpContext boolExp() {
			return getRuleContext(BoolExpContext.class,0);
		}
		public BoolExpReturnContext(ReturnRuleContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitBoolExpReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpReturnContext extends ReturnRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ExpReturnContext(ReturnRuleContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitExpReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnRuleContext returnRule() throws RecognitionException {
		ReturnRuleContext _localctx = new ReturnRuleContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_returnRule);
		try {
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new VarReturnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				match(T__14);
				setState(154);
				match(ID);
				}
				break;
			case 2:
				_localctx = new BoolExpReturnContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
				match(T__14);
				setState(156);
				boolExp(0);
				}
				break;
			case 3:
				_localctx = new ExpReturnContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(157);
				match(T__14);
				setState(158);
				exp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolExpContext extends ParserRuleContext {
		public BoolExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExp; }
	 
		public BoolExpContext() { }
		public void copyFrom(BoolExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CondBoolExpContext extends BoolExpContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public CondBoolExpContext(BoolExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitCondBoolExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BaseBoolExpContext extends BoolExpContext {
		public BoolExpContext boolExp() {
			return getRuleContext(BoolExpContext.class,0);
		}
		public BaseBoolExpContext(BoolExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitBaseBoolExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarBoolExpContext extends BoolExpContext {
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public VarBoolExpContext(BoolExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitVarBoolExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinBoolExpContext extends BoolExpContext {
		public BoolExpContext left;
		public Token op;
		public BoolExpContext right;
		public List<BoolExpContext> boolExp() {
			return getRuleContexts(BoolExpContext.class);
		}
		public BoolExpContext boolExp(int i) {
			return getRuleContext(BoolExpContext.class,i);
		}
		public BinBoolExpContext(BoolExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitBinBoolExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolExpContext boolExp() throws RecognitionException {
		return boolExp(0);
	}

	private BoolExpContext boolExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BoolExpContext _localctx = new BoolExpContext(_ctx, _parentState);
		BoolExpContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_boolExp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new BaseBoolExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(162);
				match(T__7);
				setState(163);
				boolExp(0);
				setState(164);
				match(T__9);
				}
				break;
			case 2:
				{
				_localctx = new CondBoolExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(166);
				cond();
				}
				break;
			case 3:
				{
				_localctx = new VarBoolExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(167);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(178);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(176);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new BinBoolExpContext(new BoolExpContext(_parentctx, _parentState));
						((BinBoolExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExp);
						setState(170);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(171);
						((BinBoolExpContext)_localctx).op = match(T__15);
						setState(172);
						((BinBoolExpContext)_localctx).right = boolExp(5);
						}
						break;
					case 2:
						{
						_localctx = new BinBoolExpContext(new BoolExpContext(_parentctx, _parentState));
						((BinBoolExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_boolExp);
						setState(173);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(174);
						((BinBoolExpContext)_localctx).op = match(T__16);
						setState(175);
						((BinBoolExpContext)_localctx).right = boolExp(4);
						}
						break;
					}
					} 
				}
				setState(180);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CondContext extends ParserRuleContext {
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
	 
		public CondContext() { }
		public void copyFrom(CondContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EqualNotIdCondContext extends CondContext {
		public NotContext left;
		public Token right;
		public NotContext not() {
			return getRuleContext(NotContext.class,0);
		}
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public EqualNotIdCondContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitEqualNotIdCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualIdNotCondContext extends CondContext {
		public Token left;
		public NotContext right;
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public NotContext not() {
			return getRuleContext(NotContext.class,0);
		}
		public EqualIdNotCondContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitEqualIdNotCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessEqCondContext extends CondContext {
		public ExpContext left;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public LessEqCondContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitLessEqCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualCondContext extends CondContext {
		public ExpContext left;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public EqualCondContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitEqualCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreatCondContext extends CondContext {
		public ExpContext left;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public GreatCondContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitGreatCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualNotCondContext extends CondContext {
		public NotContext left;
		public NotContext right;
		public List<NotContext> not() {
			return getRuleContexts(NotContext.class);
		}
		public NotContext not(int i) {
			return getRuleContext(NotContext.class,i);
		}
		public EqualNotCondContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitEqualNotCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreatEqCondContext extends CondContext {
		public ExpContext left;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public GreatEqCondContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitGreatEqCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessCondContext extends CondContext {
		public ExpContext left;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public LessCondContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitLessCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_cond);
		try {
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new EqualNotCondContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				((EqualNotCondContext)_localctx).left = not();
				setState(184);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(182);
					match(T__17);
					setState(183);
					((EqualNotCondContext)_localctx).right = not();
					}
					break;
				}
				}
				break;
			case 2:
				_localctx = new EqualNotIdCondContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				((EqualNotIdCondContext)_localctx).left = not();
				setState(187);
				match(T__17);
				setState(188);
				((EqualNotIdCondContext)_localctx).right = match(ID);
				}
				break;
			case 3:
				_localctx = new EqualIdNotCondContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(190);
				((EqualIdNotCondContext)_localctx).left = match(ID);
				setState(191);
				match(T__17);
				setState(192);
				((EqualIdNotCondContext)_localctx).right = not();
				}
				break;
			case 4:
				_localctx = new EqualCondContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(193);
				((EqualCondContext)_localctx).left = exp(0);
				setState(194);
				match(T__17);
				setState(195);
				((EqualCondContext)_localctx).right = exp(0);
				}
				break;
			case 5:
				_localctx = new GreatEqCondContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(197);
				((GreatEqCondContext)_localctx).left = exp(0);
				setState(198);
				match(T__18);
				setState(199);
				((GreatEqCondContext)_localctx).right = exp(0);
				}
				break;
			case 6:
				_localctx = new LessEqCondContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(201);
				((LessEqCondContext)_localctx).left = exp(0);
				setState(202);
				match(T__19);
				setState(203);
				((LessEqCondContext)_localctx).right = exp(0);
				}
				break;
			case 7:
				_localctx = new GreatCondContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(205);
				((GreatCondContext)_localctx).left = exp(0);
				setState(206);
				match(T__20);
				setState(207);
				((GreatCondContext)_localctx).right = exp(0);
				}
				break;
			case 8:
				_localctx = new LessCondContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(209);
				((LessCondContext)_localctx).left = exp(0);
				setState(210);
				match(T__21);
				setState(211);
				((LessCondContext)_localctx).right = exp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotContext extends ParserRuleContext {
		public BoolExpContext boolExp() {
			return getRuleContext(BoolExpContext.class,0);
		}
		public NotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotContext not() throws RecognitionException {
		NotContext _localctx = new NotContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_not);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(T__22);
			setState(216);
			match(T__7);
			setState(217);
			boolExp(0);
			setState(218);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	 
		public ExpContext() { }
		public void copyFrom(ExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseExpContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public BaseExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitBaseExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarExpContext extends ExpContext {
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public VarExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitVarExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinExpContext extends ExpContext {
		public ExpContext left;
		public Token op;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public BinExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitBinExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValExpContext extends ExpContext {
		public TerminalNode NUMBER() { return getToken(SimpleParser.NUMBER, 0); }
		public ValExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitValExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegExpContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public NegExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitNegExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				{
				_localctx = new BaseExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(221);
				match(T__7);
				setState(222);
				exp(0);
				setState(223);
				match(T__9);
				}
				break;
			case T__23:
				{
				_localctx = new NegExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(225);
				match(T__23);
				setState(226);
				exp(5);
				}
				break;
			case ID:
				{
				_localctx = new VarExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(227);
				match(ID);
				}
				break;
			case NUMBER:
				{
				_localctx = new ValExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(228);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(239);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(237);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new BinExpContext(new ExpContext(_parentctx, _parentState));
						((BinExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(231);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(232);
						((BinExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__24 || _la==T__25) ) {
							((BinExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(233);
						((BinExpContext)_localctx).right = exp(5);
						}
						break;
					case 2:
						{
						_localctx = new BinExpContext(new ExpContext(_parentctx, _parentState));
						((BinExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(234);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(235);
						((BinExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__23 || _la==T__26) ) {
							((BinExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(236);
						((BinExpContext)_localctx).right = exp(4);
						}
						break;
					}
					} 
				}
				setState(241);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 16:
			return boolExp_sempred((BoolExpContext)_localctx, predIndex);
		case 19:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean boolExp_sempred(BoolExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#\u00f5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\7\2-\n\2\f\2\16\2\60\13\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3L\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\5\6Y\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\7\nk\n\n\f\n\16\nn\13\n\5\np\n\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\5\13y\n\13\3\f\5\f|\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\7\r\u0085"+
		"\n\r\f\r\16\r\u0088\13\r\5\r\u008a\n\r\3\r\3\r\3\16\3\16\5\16\u0090\n"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\5\21\u00a2\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22"+
		"\u00ab\n\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u00b3\n\22\f\22\16\22\u00b6"+
		"\13\22\3\23\3\23\3\23\5\23\u00bb\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00d8\n\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00e8\n\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\7\25\u00f0\n\25\f\25\16\25\u00f3\13\25\3\25"+
		"\2\4\"(\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\5\4\2\t\t\36"+
		"\36\3\2\33\34\4\2\32\32\35\35\u0109\2*\3\2\2\2\4K\3\2\2\2\6M\3\2\2\2\b"+
		"P\3\2\2\2\nX\3\2\2\2\fZ\3\2\2\2\16^\3\2\2\2\20a\3\2\2\2\22d\3\2\2\2\24"+
		"x\3\2\2\2\26{\3\2\2\2\30\177\3\2\2\2\32\u008d\3\2\2\2\34\u0091\3\2\2\2"+
		"\36\u0098\3\2\2\2 \u00a1\3\2\2\2\"\u00aa\3\2\2\2$\u00d7\3\2\2\2&\u00d9"+
		"\3\2\2\2(\u00e7\3\2\2\2*.\7\3\2\2+-\5\4\3\2,+\3\2\2\2-\60\3\2\2\2.,\3"+
		"\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\61\62\7\4\2\2\62\3\3\2\2\2\63"+
		"\64\5 \21\2\64\65\7\5\2\2\65L\3\2\2\2\66\67\5\30\r\2\678\7\5\2\28L\3\2"+
		"\2\29L\5\22\n\2:L\5\32\16\2;<\5\6\4\2<=\7\5\2\2=L\3\2\2\2>?\5\b\5\2?@"+
		"\7\5\2\2@L\3\2\2\2AB\5\f\7\2BC\7\5\2\2CL\3\2\2\2DE\5\16\b\2EF\7\5\2\2"+
		"FL\3\2\2\2GH\5\20\t\2HI\7\5\2\2IL\3\2\2\2JL\5\2\2\2K\63\3\2\2\2K\66\3"+
		"\2\2\2K9\3\2\2\2K:\3\2\2\2K;\3\2\2\2K>\3\2\2\2KA\3\2\2\2KD\3\2\2\2KG\3"+
		"\2\2\2KJ\3\2\2\2L\5\3\2\2\2MN\7\36\2\2NO\7\37\2\2O\7\3\2\2\2PQ\5\6\4\2"+
		"QR\7\6\2\2RS\5\n\6\2S\t\3\2\2\2TY\7\37\2\2UY\5(\25\2VY\5\"\22\2WY\5\30"+
		"\r\2XT\3\2\2\2XU\3\2\2\2XV\3\2\2\2XW\3\2\2\2Y\13\3\2\2\2Z[\7\37\2\2[\\"+
		"\7\6\2\2\\]\5\n\6\2]\r\3\2\2\2^_\7\7\2\2_`\7\37\2\2`\17\3\2\2\2ab\7\b"+
		"\2\2bc\5(\25\2c\21\3\2\2\2de\t\2\2\2ef\7\37\2\2fo\7\n\2\2gl\5\6\4\2hi"+
		"\7\13\2\2ik\5\6\4\2jh\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mp\3\2\2\2"+
		"nl\3\2\2\2og\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\7\f\2\2rs\5\2\2\2s\23\3\2\2"+
		"\2ty\5\26\f\2uy\5\f\7\2vy\5\"\22\2wy\5(\25\2xt\3\2\2\2xu\3\2\2\2xv\3\2"+
		"\2\2xw\3\2\2\2y\25\3\2\2\2z|\7\r\2\2{z\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\7"+
		"\37\2\2~\27\3\2\2\2\177\u0080\7\37\2\2\u0080\u0089\7\n\2\2\u0081\u0086"+
		"\5\24\13\2\u0082\u0083\7\13\2\2\u0083\u0085\5\24\13\2\u0084\u0082\3\2"+
		"\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u0081\3\2\2\2\u0089\u008a\3\2"+
		"\2\2\u008a\u008b\3\2\2\2\u008b\u008c\7\f\2\2\u008c\31\3\2\2\2\u008d\u008f"+
		"\5\34\17\2\u008e\u0090\5\36\20\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2"+
		"\2\u0090\33\3\2\2\2\u0091\u0092\7\16\2\2\u0092\u0093\7\n\2\2\u0093\u0094"+
		"\5\"\22\2\u0094\u0095\7\f\2\2\u0095\u0096\7\17\2\2\u0096\u0097\5\2\2\2"+
		"\u0097\35\3\2\2\2\u0098\u0099\7\20\2\2\u0099\u009a\5\2\2\2\u009a\37\3"+
		"\2\2\2\u009b\u009c\7\21\2\2\u009c\u00a2\7\37\2\2\u009d\u009e\7\21\2\2"+
		"\u009e\u00a2\5\"\22\2\u009f\u00a0\7\21\2\2\u00a0\u00a2\5(\25\2\u00a1\u009b"+
		"\3\2\2\2\u00a1\u009d\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2!\3\2\2\2\u00a3"+
		"\u00a4\b\22\1\2\u00a4\u00a5\7\n\2\2\u00a5\u00a6\5\"\22\2\u00a6\u00a7\7"+
		"\f\2\2\u00a7\u00ab\3\2\2\2\u00a8\u00ab\5$\23\2\u00a9\u00ab\7\37\2\2\u00aa"+
		"\u00a3\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00b4\3\2"+
		"\2\2\u00ac\u00ad\f\6\2\2\u00ad\u00ae\7\22\2\2\u00ae\u00b3\5\"\22\7\u00af"+
		"\u00b0\f\5\2\2\u00b0\u00b1\7\23\2\2\u00b1\u00b3\5\"\22\6\u00b2\u00ac\3"+
		"\2\2\2\u00b2\u00af\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5#\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00ba\5&\24\2"+
		"\u00b8\u00b9\7\24\2\2\u00b9\u00bb\5&\24\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb"+
		"\3\2\2\2\u00bb\u00d8\3\2\2\2\u00bc\u00bd\5&\24\2\u00bd\u00be\7\24\2\2"+
		"\u00be\u00bf\7\37\2\2\u00bf\u00d8\3\2\2\2\u00c0\u00c1\7\37\2\2\u00c1\u00c2"+
		"\7\24\2\2\u00c2\u00d8\5&\24\2\u00c3\u00c4\5(\25\2\u00c4\u00c5\7\24\2\2"+
		"\u00c5\u00c6\5(\25\2\u00c6\u00d8\3\2\2\2\u00c7\u00c8\5(\25\2\u00c8\u00c9"+
		"\7\25\2\2\u00c9\u00ca\5(\25\2\u00ca\u00d8\3\2\2\2\u00cb\u00cc\5(\25\2"+
		"\u00cc\u00cd\7\26\2\2\u00cd\u00ce\5(\25\2\u00ce\u00d8\3\2\2\2\u00cf\u00d0"+
		"\5(\25\2\u00d0\u00d1\7\27\2\2\u00d1\u00d2\5(\25\2\u00d2\u00d8\3\2\2\2"+
		"\u00d3\u00d4\5(\25\2\u00d4\u00d5\7\30\2\2\u00d5\u00d6\5(\25\2\u00d6\u00d8"+
		"\3\2\2\2\u00d7\u00b7\3\2\2\2\u00d7\u00bc\3\2\2\2\u00d7\u00c0\3\2\2\2\u00d7"+
		"\u00c3\3\2\2\2\u00d7\u00c7\3\2\2\2\u00d7\u00cb\3\2\2\2\u00d7\u00cf\3\2"+
		"\2\2\u00d7\u00d3\3\2\2\2\u00d8%\3\2\2\2\u00d9\u00da\7\31\2\2\u00da\u00db"+
		"\7\n\2\2\u00db\u00dc\5\"\22\2\u00dc\u00dd\7\f\2\2\u00dd\'\3\2\2\2\u00de"+
		"\u00df\b\25\1\2\u00df\u00e0\7\n\2\2\u00e0\u00e1\5(\25\2\u00e1\u00e2\7"+
		"\f\2\2\u00e2\u00e8\3\2\2\2\u00e3\u00e4\7\32\2\2\u00e4\u00e8\5(\25\7\u00e5"+
		"\u00e8\7\37\2\2\u00e6\u00e8\7 \2\2\u00e7\u00de\3\2\2\2\u00e7\u00e3\3\2"+
		"\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8\u00f1\3\2\2\2\u00e9"+
		"\u00ea\f\6\2\2\u00ea\u00eb\t\3\2\2\u00eb\u00f0\5(\25\7\u00ec\u00ed\f\5"+
		"\2\2\u00ed\u00ee\t\4\2\2\u00ee\u00f0\5(\25\6\u00ef\u00e9\3\2\2\2\u00ef"+
		"\u00ec\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2"+
		"\2\2\u00f2)\3\2\2\2\u00f3\u00f1\3\2\2\2\25.KXlox{\u0086\u0089\u008f\u00a1"+
		"\u00aa\u00b2\u00b4\u00ba\u00d7\u00e7\u00ef\u00f1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}