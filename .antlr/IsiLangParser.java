// Generated from /mnt/c/Users/enzos/Documents/local/isilanguage/IsiLang.g4 by ANTLR 4.9.2

	import br.edu.ufabc.isilanguage.compiler.datastructures.IsiSymbol;
	import br.edu.ufabc.isilanguage.compiler.datastructures.IsiVariable;
	import br.edu.ufabc.isilanguage.compiler.datastructures.IsiSymbolTable;
	import br.edu.ufabc.isilanguage.compiler.exceptions.IsiSemanticException;
	import br.edu.ufabc.isilanguage.compiler.ast.IsiProgram;
	import br.edu.ufabc.isilanguage.compiler.ast.AbstractCommand;
	import br.edu.ufabc.isilanguage.compiler.ast.CommandEnquanto;
	import br.edu.ufabc.isilanguage.compiler.ast.CommandLeitura;
	import br.edu.ufabc.isilanguage.compiler.ast.CommandEscrita;
	import br.edu.ufabc.isilanguage.compiler.ast.CommandAtribuicao;
	import br.edu.ufabc.isilanguage.compiler.ast.CommandDecisao;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, SC=15, OP=16, ATTR=17, VIR=18, 
		ACH=19, FCH=20, OPREL=21, BOOLEAN=22, OPFUNCAOMAT=23, ID=24, NUMBER=25, 
		TEXT=26, CHAR=27, WS=28, MLCOMMENT=29, SLCOMMENT=30;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdenquanto = 10, RULE_expr = 11, RULE_funcaomat = 12, 
		RULE_termo = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
			"cmdattrib", "cmdselecao", "cmdenquanto", "expr", "funcaomat", "termo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'caractere'", 
			"'logico'", "'leia'", "'escreva'", "'se'", "'senao'", "'enquanto'", "'faca'", 
			"'('", "')'", "';'", null, "'='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "BOOLEAN", 
			"OPFUNCAOMAT", "ID", "NUMBER", "TEXT", "CHAR", "WS", "MLCOMMENT", "SLCOMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private int _tipo;
		private String _varName;
		private String _varValue;
		private ArrayList<Integer> _tipoVar = new ArrayList<Integer>();
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _opfunmat;
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> listaEnq;


		public void verificaID(String id){
			if (!symbolTable.exists(id))
				throw new IsiSemanticException("Symbol "+id+" not declared");
		}

		public String typeToString(int isiType) {
			switch (isiType) {
				case 0:
					return "NUMBER";
				case 1:
					return "TEXT";
				case 2:
					return "CHAR";
				case 3:
					return "BOOLEAN";
				default:
					return "";
			}
		}

		public void verificaCompatibilidade(ArrayList<Integer> tipos) {
			int tipoEsq = tipos.get(0);
			for (int tipo: tipos) {
				if (tipoEsq != tipo) {
					String errorMsg = String.format("Type mismatch: %s and %s", typeToString(tipoEsq), typeToString(tipo));
					tipos.removeAll(tipos);
					throw new IsiSemanticException(errorMsg);
				}
			}
			tipos.removeAll(tipos);
		}

		public void verificaAttrib(String id) {
			if (symbolTable.exists(id) && symbolTable.get(id) == null)
				throw new IsiSemanticException(String.format("\"%s\" has not been initialized.", id));
		}

		public void checkInitialized(String id) {
	        if(!symbolTable.checkInitialized(id))
	            throw new IsiSemanticException("Symbol "+id+" not initialized");
	    }

	    public void setInitialized(String id) {
	        symbolTable.setInitializedBy(id);
	    }

	    public void showWarningsUnusedVariables() {
	        for(IsiSymbol s: symbolTable.getNotUsedSymbols())
	            System.out.println("Warning: Variável <" + s.getName() + "> foi declarada, mas nao utilizada");
	    }

		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos())
				System.out.println(c);
		}

		public void generateCode(){
			program.generateTarget();
		}

		public ArrayList<String> getWarnings() {
	        ArrayList<String> warnings = new ArrayList<String>();
	        for(IsiSymbol s: symbolTable.getNotUsedSymbols())
	            warnings.add(String.format("Warning: Variavel <%s> foi declarada, mas nao utilizada", s.getName()));
	        return warnings;
	    }

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			decl();
			setState(30);
			bloco();
			setState(31);
			match(T__1);

								program.setVarTable(symbolTable);
								program.setComandos(stack.pop());
								showWarningsUnusedVariables();
							  
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

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				declaravar();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0) );
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

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			tipo();
			setState(40);
			match(ID);

				                      	 _varName = _input.LT(-1).getText();
				                      	 _varValue = null;
				                      	 symbol = new IsiVariable(_varName, _tipo, _varValue);
				                      	 if (!symbolTable.exists(_varName))
				                      	    symbolTable.add(symbol);
				                      	 else
				                      	 	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
			                         
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(42);
				match(VIR);
				setState(43);
				match(ID);

					                  		 _varName = _input.LT(-1).getText();
					                  		 _varValue = null;
					                  		 symbol = new IsiVariable(_varName, _tipo, _varValue);
					                  		 if (!symbolTable.exists(_varName))
					                  		    symbolTable.add(symbol);
					                  		 else
					                  		 	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
				                         
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
			match(SC);
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

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(T__2);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(T__3);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				match(T__4);
				 _tipo = IsiVariable.CHAR;  
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				match(T__5);
				 _tipo = IsiVariable.BOOLEAN;  
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

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

						curThread = new ArrayList<AbstractCommand>();
				        stack.push(curThread);
			          
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(63);
				cmd();
				}
				}
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << ID))) != 0) );
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

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdenquantoContext cmdenquanto() {
			return getRuleContext(CmdenquantoContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				cmdleitura();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				cmdattrib();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				cmdselecao();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 5);
				{
				setState(72);
				cmdenquanto();
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

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__6);
			setState(76);
			match(AP);
			setState(77);
			match(ID);

								verificaID(_input.LT(-1).getText());
								_readID = _input.LT(-1).getText();
							 
			setState(79);
			match(FP);
			setState(80);
			match(SC);

								IsiVariable var = (IsiVariable)symbolTable.get(_readID);
								CommandLeitura cmd = new CommandLeitura(_readID, var);
								setInitialized(_readID);
								stack.peek().add(cmd);
							 
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

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__7);
			setState(84);
			match(AP);
			setState(85);
			match(ID);

									verificaID(_input.LT(-1).getText());
				                  	_writeID = _input.LT(-1).getText();
									checkInitialized(_writeID);
			                     
			setState(87);
			match(FP);
			setState(88);
			match(SC);

			               		 	CommandEscrita cmd = new CommandEscrita(_writeID);
			               	  		stack.peek().add(cmd);
			               		 
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

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(ID);

									verificaID(_input.LT(-1).getText());
									verificaAttrib(_input.LT(-1).getText());
			                    	_exprID = _input.LT(-1).getText();
									_tipoVar.add(symbolTable.getTypeBy(_exprID));
			                    
			setState(93);
			match(ATTR);

									_exprContent = "";
								
			setState(95);
			expr();
			setState(96);
			match(SC);

							 		verificaCompatibilidade(_tipoVar);
			               	 		CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
									setInitialized(_exprID);
									stack.peek().add(cmd);
			               		
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

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__8);
			setState(100);
			match(AP);
			setState(101);
			match(ID);

												verificaID(_input.LT(-1).getText());
												verificaAttrib(_input.LT(-1).getText());
												_exprDecision = _input.LT(-1).getText();
												_tipoVar.add(symbolTable.getTypeBy(_input.LT(-1).getText()));
									  		  
			setState(103);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(105);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

												verificaAttrib(_input.LT(-1).getText());
												if (_input.LT(-1).getText().matches("\\d+(\\.\\d+)?"))
													_tipoVar.add(IsiVariable.NUMBER);
												else {
													verificaID(_input.LT(-1).getText());
													_tipoVar.add(symbolTable.getTypeBy(_input.LT(-1).getText()));
												}
												_exprDecision += _input.LT(-1).getText();
											  
			setState(107);
			match(FP);
			 verificaCompatibilidade(_tipoVar); 
			setState(109);
			match(ACH);

												curThread = new ArrayList<AbstractCommand>();
			                    				stack.push(curThread);
			                    			  
			setState(112); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(111);
				cmd();
				}
				}
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << ID))) != 0) );
			setState(116);
			match(FCH);
			 listaTrue = stack.pop(); 
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(118);
				match(T__9);
				setState(119);
				match(ACH);

													curThread = new ArrayList<AbstractCommand>();
													stack.push(curThread);
												  
				{
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(121);
					cmd();
					}
					}
					setState(124); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << ID))) != 0) );
				}
				setState(126);
				match(FCH);

													listaFalse = stack.pop();
													CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
													stack.peek().add(cmd);
												  
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

	public static class CmdenquantoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdenquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdenquanto; }
	}

	public final CmdenquantoContext cmdenquanto() throws RecognitionException {
		CmdenquantoContext _localctx = new CmdenquantoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdenquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__10);
			setState(132);
			match(AP);
			setState(133);
			match(ID);

												 	  verificaID(_input.LT(-1).getText());
													  verificaAttrib(_input.LT(-1).getText());
													  _exprDecision = _input.LT(-1).getText();
													  _tipoVar.add(symbolTable.getTypeBy(_input.LT(-1).getText()));
													
			setState(135);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(137);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

														verificaAttrib(_input.LT(-1).getText());
														if (_input.LT(-1).getText().matches("\\d+(\\.\\d+)?"))
															_tipoVar.add(IsiVariable.NUMBER);
														else {
															verificaID(_input.LT(-1).getText());
															_tipoVar.add(symbolTable.getTypeBy(_input.LT(-1).getText()));
														}
														_exprDecision += _input.LT(-1).getText();
													
			setState(139);
			match(FP);
			 verificaCompatibilidade(_tipoVar); 
			setState(141);
			match(T__11);
			setState(142);
			match(ACH);

													  curThread = new ArrayList<AbstractCommand>();
			                           				  stack.push(curThread);
			                           				
			setState(145); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(144);
				cmd();
				}
				}
				setState(147); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << ID))) != 0) );
			setState(149);
			match(FCH);

			                            			  listaEnq = stack.pop();
			                            			  CommandEnquanto cmd = new CommandEnquanto(_exprDecision, listaEnq);
			                            			  stack.peek().add(cmd);
			                           				
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

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<FuncaomatContext> funcaomat() {
			return getRuleContexts(FuncaomatContext.class);
		}
		public FuncaomatContext funcaomat(int i) {
			return getRuleContext(FuncaomatContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case ID:
			case NUMBER:
			case TEXT:
			case CHAR:
				{
				setState(152);
				termo();
				}
				break;
			case OPFUNCAOMAT:
				{
				setState(153);
				funcaomat();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(156);
				match(OP);
				 _exprContent += _input.LT(-1).getText(); 
				setState(160);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOLEAN:
				case ID:
				case NUMBER:
				case TEXT:
				case CHAR:
					{
					setState(158);
					termo();
					}
					break;
				case OPFUNCAOMAT:
					{
					setState(159);
					funcaomat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(166);
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

	public static class FuncaomatContext extends ParserRuleContext {
		public TerminalNode OPFUNCAOMAT() { return getToken(IsiLangParser.OPFUNCAOMAT, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode VIR() { return getToken(IsiLangParser.VIR, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public FuncaomatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcaomat; }
	}

	public final FuncaomatContext funcaomat() throws RecognitionException {
		FuncaomatContext _localctx = new FuncaomatContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_funcaomat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(OPFUNCAOMAT);

									  _opfunmat = _input.LT(-1).getText();
									  if (_input.LT(-1).getText().equals("logaritmo"))
										_exprContent += String.format("(%s", _input.LT(-1).getText());
									  else
									  	_exprContent += _input.LT(-1).getText();
									
			setState(169);
			match(AP);

									  _exprContent += _input.LT(-1).getText();
									
			setState(171);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

									  if (_input.LT(-1).getText().matches("\\d+(\\.\\d+)?"))
									  	_tipoVar.add(IsiVariable.NUMBER);
									  else {
									  	verificaID(_input.LT(-1).getText());
										checkInitialized(_input.LT(-1).getText());
				               	   	  	_tipoVar.add(symbolTable.getTypeBy(_input.LT(-1).getText()));
									  }

									  _exprContent += _input.LT(-1).getText();
									
			setState(173);
			match(VIR);

									  if (_opfunmat.equals("logaritmo")) {
										_exprContent += ") / Math.log(";
									  }
									  else
									  	_exprContent += _input.LT(-1).getText();
									
			setState(175);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

									  if (_input.LT(-1).getText().matches("\\d+(\\.\\d+)?"))
									  	_tipoVar.add(IsiVariable.NUMBER);
									  else {
									  	verificaID(_input.LT(-1).getText());
										checkInitialized(_input.LT(-1).getText());
									  	_tipoVar.add(symbolTable.getTypeBy(_input.LT(-1).getText()));
									  }

									  if (_opfunmat.equals("raiz"))
										_exprContent += String.format("1/%s", _input.LT(-1).getText());
									  else
									    _exprContent += _input.LT(-1).getText();
									
			setState(177);
			match(FP);

									  if (_opfunmat.equals("logaritmo"))
									  	_exprContent += _input.LT(-1).getText();
									  _exprContent += _input.LT(-1).getText();
									
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

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode CHAR() { return getToken(IsiLangParser.CHAR, 0); }
		public TerminalNode TEXT() { return getToken(IsiLangParser.TEXT, 0); }
		public TerminalNode BOOLEAN() { return getToken(IsiLangParser.BOOLEAN, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_termo);
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				match(ID);

								   	   	verificaID(_input.LT(-1).getText());
										checkInitialized(_input.LT(-1).getText());
					               	   	_tipoVar.add(symbolTable.getTypeBy(_input.LT(-1).getText()));
									   	_exprContent += _input.LT(-1).getText();
				                 	  
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				match(NUMBER);

									   	_tipoVar.add(IsiVariable.NUMBER);
				              		    _exprContent += _input.LT(-1).getText();
				              		  
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(184);
				match(CHAR);

									    _tipoVar.add(IsiVariable.CHAR);
				              		    _exprContent += _input.LT(-1).getText();
				              		  
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(186);
				match(TEXT);

									    _tipoVar.add(IsiVariable.TEXT);
				              		    _exprContent += _input.LT(-1).getText();
				               		  
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 5);
				{
				setState(188);
				match(BOOLEAN);

										_tipoVar.add(IsiVariable.BOOLEAN);
				              			_exprContent += _input.LT(-1).getText();
				               		  
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00c3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6"+
		"\3&\n\3\r\3\16\3\'\3\4\3\4\3\4\3\4\3\4\3\4\7\4\60\n\4\f\4\16\4\63\13\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5?\n\5\3\6\3\6\6\6C\n\6\r\6"+
		"\16\6D\3\7\3\7\3\7\3\7\3\7\5\7L\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13s\n\13"+
		"\r\13\16\13t\3\13\3\13\3\13\3\13\3\13\3\13\6\13}\n\13\r\13\16\13~\3\13"+
		"\3\13\3\13\5\13\u0084\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\6\f\u0094\n\f\r\f\16\f\u0095\3\f\3\f\3\f\3\r\3\r\5\r\u009d"+
		"\n\r\3\r\3\r\3\r\3\r\5\r\u00a3\n\r\7\r\u00a5\n\r\f\r\16\r\u00a8\13\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00c1\n\17\3\17\2\2\20"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\3\3\2\32\33\2\u00c9\2\36\3\2\2"+
		"\2\4%\3\2\2\2\6)\3\2\2\2\b>\3\2\2\2\n@\3\2\2\2\fK\3\2\2\2\16M\3\2\2\2"+
		"\20U\3\2\2\2\22]\3\2\2\2\24e\3\2\2\2\26\u0085\3\2\2\2\30\u009c\3\2\2\2"+
		"\32\u00a9\3\2\2\2\34\u00c0\3\2\2\2\36\37\7\3\2\2\37 \5\4\3\2 !\5\n\6\2"+
		"!\"\7\4\2\2\"#\b\2\1\2#\3\3\2\2\2$&\5\6\4\2%$\3\2\2\2&\'\3\2\2\2\'%\3"+
		"\2\2\2\'(\3\2\2\2(\5\3\2\2\2)*\5\b\5\2*+\7\32\2\2+\61\b\4\1\2,-\7\24\2"+
		"\2-.\7\32\2\2.\60\b\4\1\2/,\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3"+
		"\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\7\21\2\2\65\7\3\2\2\2\66\67\7"+
		"\5\2\2\67?\b\5\1\289\7\6\2\29?\b\5\1\2:;\7\7\2\2;?\b\5\1\2<=\7\b\2\2="+
		"?\b\5\1\2>\66\3\2\2\2>8\3\2\2\2>:\3\2\2\2><\3\2\2\2?\t\3\2\2\2@B\b\6\1"+
		"\2AC\5\f\7\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\13\3\2\2\2FL\5\16"+
		"\b\2GL\5\20\t\2HL\5\22\n\2IL\5\24\13\2JL\5\26\f\2KF\3\2\2\2KG\3\2\2\2"+
		"KH\3\2\2\2KI\3\2\2\2KJ\3\2\2\2L\r\3\2\2\2MN\7\t\2\2NO\7\17\2\2OP\7\32"+
		"\2\2PQ\b\b\1\2QR\7\20\2\2RS\7\21\2\2ST\b\b\1\2T\17\3\2\2\2UV\7\n\2\2V"+
		"W\7\17\2\2WX\7\32\2\2XY\b\t\1\2YZ\7\20\2\2Z[\7\21\2\2[\\\b\t\1\2\\\21"+
		"\3\2\2\2]^\7\32\2\2^_\b\n\1\2_`\7\23\2\2`a\b\n\1\2ab\5\30\r\2bc\7\21\2"+
		"\2cd\b\n\1\2d\23\3\2\2\2ef\7\13\2\2fg\7\17\2\2gh\7\32\2\2hi\b\13\1\2i"+
		"j\7\27\2\2jk\b\13\1\2kl\t\2\2\2lm\b\13\1\2mn\7\20\2\2no\b\13\1\2op\7\25"+
		"\2\2pr\b\13\1\2qs\5\f\7\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2uv\3"+
		"\2\2\2vw\7\26\2\2w\u0083\b\13\1\2xy\7\f\2\2yz\7\25\2\2z|\b\13\1\2{}\5"+
		"\f\7\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u0081\7\26\2\2\u0081\u0082\b\13\1\2\u0082\u0084\3\2\2\2\u0083x\3\2\2"+
		"\2\u0083\u0084\3\2\2\2\u0084\25\3\2\2\2\u0085\u0086\7\r\2\2\u0086\u0087"+
		"\7\17\2\2\u0087\u0088\7\32\2\2\u0088\u0089\b\f\1\2\u0089\u008a\7\27\2"+
		"\2\u008a\u008b\b\f\1\2\u008b\u008c\t\2\2\2\u008c\u008d\b\f\1\2\u008d\u008e"+
		"\7\20\2\2\u008e\u008f\b\f\1\2\u008f\u0090\7\16\2\2\u0090\u0091\7\25\2"+
		"\2\u0091\u0093\b\f\1\2\u0092\u0094\5\f\7\2\u0093\u0092\3\2\2\2\u0094\u0095"+
		"\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\u0098\7\26\2\2\u0098\u0099\b\f\1\2\u0099\27\3\2\2\2\u009a\u009d\5\34"+
		"\17\2\u009b\u009d\5\32\16\2\u009c\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d"+
		"\u00a6\3\2\2\2\u009e\u009f\7\22\2\2\u009f\u00a2\b\r\1\2\u00a0\u00a3\5"+
		"\34\17\2\u00a1\u00a3\5\32\16\2\u00a2\u00a0\3\2\2\2\u00a2\u00a1\3\2\2\2"+
		"\u00a3\u00a5\3\2\2\2\u00a4\u009e\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4"+
		"\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\31\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9"+
		"\u00aa\7\31\2\2\u00aa\u00ab\b\16\1\2\u00ab\u00ac\7\17\2\2\u00ac\u00ad"+
		"\b\16\1\2\u00ad\u00ae\t\2\2\2\u00ae\u00af\b\16\1\2\u00af\u00b0\7\24\2"+
		"\2\u00b0\u00b1\b\16\1\2\u00b1\u00b2\t\2\2\2\u00b2\u00b3\b\16\1\2\u00b3"+
		"\u00b4\7\20\2\2\u00b4\u00b5\b\16\1\2\u00b5\33\3\2\2\2\u00b6\u00b7\7\32"+
		"\2\2\u00b7\u00c1\b\17\1\2\u00b8\u00b9\7\33\2\2\u00b9\u00c1\b\17\1\2\u00ba"+
		"\u00bb\7\35\2\2\u00bb\u00c1\b\17\1\2\u00bc\u00bd\7\34\2\2\u00bd\u00c1"+
		"\b\17\1\2\u00be\u00bf\7\30\2\2\u00bf\u00c1\b\17\1\2\u00c0\u00b6\3\2\2"+
		"\2\u00c0\u00b8\3\2\2\2\u00c0\u00ba\3\2\2\2\u00c0\u00bc\3\2\2\2\u00c0\u00be"+
		"\3\2\2\2\u00c1\35\3\2\2\2\17\'\61>DKt~\u0083\u0095\u009c\u00a2\u00a6\u00c0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}