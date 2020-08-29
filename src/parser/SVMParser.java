// Generated from SVM.g4 by ANTLR 4.4
package parser;

import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, PRINT=3, JUMPREG=4, JUMPLABEL=5, ADDINTEGER=6, MOVE=7, 
		BRANCHEQ=8, BRANCHGT=9, BRANCHGE=10, BRANCHLT=11, BRANCHLE=12, POP=13, 
		LOADWORD=14, STOREWORD=15, PUSH=16, ADD=17, SUB=18, MUL=19, DIV=20, NEG=21, 
		LOADINTEGER=22, BRANCH=23, REG=24, COL=25, LABEL=26, NUMBER=27, WHITESP=28, 
		ERR=29;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "'print'", "'jr'", "'jal'", "'addi'", "'move'", 
		"'beq'", "'bgt'", "'bge'", "'blt'", "'ble'", "'pop'", "'lw'", "'sw'", 
		"'push'", "'add'", "'sub'", "'mul'", "'div'", "'neg'", "'li'", "'b'", 
		"REG", "':'", "LABEL", "NUMBER", "WHITESP", "ERR"
	};
	public static final int
		RULE_assembly = 0, RULE_instruction = 1;
	public static final String[] ruleNames = {
		"assembly", "instruction"
	};

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SVMParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AssemblyContext extends ParserRuleContext {
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public AssemblyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assembly; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAssembly(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssemblyContext assembly() throws RecognitionException {
		AssemblyContext _localctx = new AssemblyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_assembly);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRINT) | (1L << JUMPREG) | (1L << JUMPLABEL) | (1L << ADDINTEGER) | (1L << MOVE) | (1L << BRANCHEQ) | (1L << BRANCHGT) | (1L << BRANCHGE) | (1L << BRANCHLT) | (1L << BRANCHLE) | (1L << POP) | (1L << LOADWORD) | (1L << STOREWORD) | (1L << PUSH) | (1L << ADD) | (1L << SUB) | (1L << MUL) | (1L << DIV) | (1L << NEG) | (1L << LOADINTEGER) | (1L << BRANCH) | (1L << LABEL))) != 0)) {
				{
				{
				setState(4); instruction();
				}
				}
				setState(9);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class InstructionContext extends ParserRuleContext {
		public Token r1;
		public Token offset;
		public Token r2;
		public Token dimension;
		public Token dest;
		public Token origin;
		public TerminalNode BRANCH() { return getToken(SVMParser.BRANCH, 0); }
		public TerminalNode ADDINTEGER() { return getToken(SVMParser.ADDINTEGER, 0); }
		public TerminalNode BRANCHLT() { return getToken(SVMParser.BRANCHLT, 0); }
		public TerminalNode BRANCHLE() { return getToken(SVMParser.BRANCHLE, 0); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode MUL() { return getToken(SVMParser.MUL, 0); }
		public TerminalNode NEG() { return getToken(SVMParser.NEG, 0); }
		public TerminalNode BRANCHEQ() { return getToken(SVMParser.BRANCHEQ, 0); }
		public TerminalNode LOADINTEGER() { return getToken(SVMParser.LOADINTEGER, 0); }
		public TerminalNode JUMPLABEL() { return getToken(SVMParser.JUMPLABEL, 0); }
		public TerminalNode ADD() { return getToken(SVMParser.ADD, 0); }
		public TerminalNode COL() { return getToken(SVMParser.COL, 0); }
		public TerminalNode BRANCHGE() { return getToken(SVMParser.BRANCHGE, 0); }
		public TerminalNode DIV() { return getToken(SVMParser.DIV, 0); }
		public TerminalNode NUMBER(int i) {
			return getToken(SVMParser.NUMBER, i);
		}
		public TerminalNode BRANCHGT() { return getToken(SVMParser.BRANCHGT, 0); }
		public TerminalNode PRINT() { return getToken(SVMParser.PRINT, 0); }
		public TerminalNode SUB() { return getToken(SVMParser.SUB, 0); }
		public TerminalNode MOVE() { return getToken(SVMParser.MOVE, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public TerminalNode PUSH() { return getToken(SVMParser.PUSH, 0); }
		public TerminalNode POP() { return getToken(SVMParser.POP, 0); }
		public TerminalNode JUMPREG() { return getToken(SVMParser.JUMPREG, 0); }
		public TerminalNode LOADWORD() { return getToken(SVMParser.LOADWORD, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(SVMParser.NUMBER); }
		public TerminalNode STOREWORD() { return getToken(SVMParser.STOREWORD, 0); }
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		int _la;
		try {
			setState(57);
			switch (_input.LA(1)) {
			case LOADINTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(10); match(LOADINTEGER);
				setState(11); match(REG);
				setState(12); match(NUMBER);
				}
				break;
			case BRANCH:
				enterOuterAlt(_localctx, 2);
				{
				setState(13); match(BRANCH);
				setState(14); match(LABEL);
				}
				break;
			case LABEL:
				enterOuterAlt(_localctx, 3);
				{
				setState(15); match(LABEL);
				setState(16); match(COL);
				}
				break;
			case PUSH:
				enterOuterAlt(_localctx, 4);
				{
				setState(17); match(PUSH);
				setState(18); match(REG);
				setState(19); match(NUMBER);
				}
				break;
			case LOADWORD:
				enterOuterAlt(_localctx, 5);
				{
				setState(20); match(LOADWORD);
				setState(21); ((InstructionContext)_localctx).r1 = match(REG);
				setState(22); ((InstructionContext)_localctx).offset = match(NUMBER);
				setState(23); match(T__1);
				setState(24); ((InstructionContext)_localctx).r2 = match(REG);
				setState(25); match(T__0);
				setState(26); ((InstructionContext)_localctx).dimension = match(NUMBER);
				}
				break;
			case STOREWORD:
				enterOuterAlt(_localctx, 6);
				{
				setState(27); match(STOREWORD);
				setState(28); ((InstructionContext)_localctx).r1 = match(REG);
				setState(29); ((InstructionContext)_localctx).offset = match(NUMBER);
				setState(30); match(T__1);
				setState(31); ((InstructionContext)_localctx).r2 = match(REG);
				setState(32); match(T__0);
				setState(33); ((InstructionContext)_localctx).dimension = match(NUMBER);
				}
				break;
			case POP:
				enterOuterAlt(_localctx, 7);
				{
				setState(34); match(POP);
				setState(35); match(NUMBER);
				}
				break;
			case ADD:
			case SUB:
			case MUL:
			case DIV:
				enterOuterAlt(_localctx, 8);
				{
				setState(36);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << MUL) | (1L << DIV))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(37); ((InstructionContext)_localctx).dest = match(REG);
				setState(38); ((InstructionContext)_localctx).r1 = match(REG);
				setState(39); ((InstructionContext)_localctx).r2 = match(REG);
				}
				break;
			case BRANCHEQ:
			case BRANCHGT:
			case BRANCHGE:
			case BRANCHLT:
			case BRANCHLE:
				enterOuterAlt(_localctx, 9);
				{
				setState(40);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BRANCHEQ) | (1L << BRANCHGT) | (1L << BRANCHGE) | (1L << BRANCHLT) | (1L << BRANCHLE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(41); ((InstructionContext)_localctx).r1 = match(REG);
				setState(42); ((InstructionContext)_localctx).r2 = match(REG);
				setState(43); match(LABEL);
				}
				break;
			case NEG:
				enterOuterAlt(_localctx, 10);
				{
				setState(44); match(NEG);
				setState(45); match(REG);
				}
				break;
			case MOVE:
				enterOuterAlt(_localctx, 11);
				{
				setState(46); match(MOVE);
				setState(47); ((InstructionContext)_localctx).dest = match(REG);
				setState(48); ((InstructionContext)_localctx).origin = match(REG);
				}
				break;
			case ADDINTEGER:
				enterOuterAlt(_localctx, 12);
				{
				setState(49); match(ADDINTEGER);
				setState(50); ((InstructionContext)_localctx).dest = match(REG);
				setState(51); ((InstructionContext)_localctx).r1 = match(REG);
				setState(52); match(NUMBER);
				}
				break;
			case JUMPLABEL:
				enterOuterAlt(_localctx, 13);
				{
				setState(53); match(JUMPLABEL);
				setState(54); match(LABEL);
				}
				break;
			case JUMPREG:
				enterOuterAlt(_localctx, 14);
				{
				setState(55); match(JUMPREG);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 15);
				{
				setState(56); match(PRINT);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37>\4\2\t\2\4\3\t"+
		"\3\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3<\n\3\3\3\2\2\4\2\4\2\4\3\2\23\26\3\2\n\16J\2\t\3\2\2"+
		"\2\4;\3\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b\13\3\2\2\2\t\7\3\2\2\2\t\n\3\2"+
		"\2\2\n\3\3\2\2\2\13\t\3\2\2\2\f\r\7\30\2\2\r\16\7\32\2\2\16<\7\35\2\2"+
		"\17\20\7\31\2\2\20<\7\34\2\2\21\22\7\34\2\2\22<\7\33\2\2\23\24\7\22\2"+
		"\2\24\25\7\32\2\2\25<\7\35\2\2\26\27\7\20\2\2\27\30\7\32\2\2\30\31\7\35"+
		"\2\2\31\32\7\3\2\2\32\33\7\32\2\2\33\34\7\4\2\2\34<\7\35\2\2\35\36\7\21"+
		"\2\2\36\37\7\32\2\2\37 \7\35\2\2 !\7\3\2\2!\"\7\32\2\2\"#\7\4\2\2#<\7"+
		"\35\2\2$%\7\17\2\2%<\7\35\2\2&\'\t\2\2\2\'(\7\32\2\2()\7\32\2\2)<\7\32"+
		"\2\2*+\t\3\2\2+,\7\32\2\2,-\7\32\2\2-<\7\34\2\2./\7\27\2\2/<\7\32\2\2"+
		"\60\61\7\t\2\2\61\62\7\32\2\2\62<\7\32\2\2\63\64\7\b\2\2\64\65\7\32\2"+
		"\2\65\66\7\32\2\2\66<\7\35\2\2\678\7\7\2\28<\7\34\2\29<\7\6\2\2:<\7\5"+
		"\2\2;\f\3\2\2\2;\17\3\2\2\2;\21\3\2\2\2;\23\3\2\2\2;\26\3\2\2\2;\35\3"+
		"\2\2\2;$\3\2\2\2;&\3\2\2\2;*\3\2\2\2;.\3\2\2\2;\60\3\2\2\2;\63\3\2\2\2"+
		";\67\3\2\2\2;9\3\2\2\2;:\3\2\2\2<\5\3\2\2\2\4\t;";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}