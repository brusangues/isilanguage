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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, SC=15, OP=16, ATTR=17, VIR=18, 
		ACH=19, FCH=20, OPREL=21, BOOLEAN=22, OPFUNCAOMAT=23, ID=24, NUMBER=25, 
		TEXT=26, CHAR=27, WS=28, MLCOMMENT=29, SLCOMMENT=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", 
			"FCH", "OPREL", "BOOLEAN", "OPFUNCAOMAT", "ID", "NUMBER", "TEXT", "CHAR", 
			"WS", "MLCOMMENT", "SLCOMMENT"
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


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u0114\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00ad\n\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00b8\n\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\5\30\u00cf\n\30\3\31\3\31\7\31\u00d3\n\31\f\31\16\31\u00d6"+
		"\13\31\3\32\6\32\u00d9\n\32\r\32\16\32\u00da\3\32\3\32\6\32\u00df\n\32"+
		"\r\32\16\32\u00e0\5\32\u00e3\n\32\3\33\3\33\3\33\3\33\7\33\u00e9\n\33"+
		"\f\33\16\33\u00ec\13\33\3\33\3\33\3\34\3\34\3\34\3\34\5\34\u00f4\n\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\7\36\u0100\n\36\f\36"+
		"\16\36\u0103\13\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\7\37\u010e"+
		"\n\37\f\37\16\37\u0111\13\37\3\37\3\37\4\u00ea\u0101\2 \3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= \3\2\t\5\2"+
		",-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\5\2\13\f\17\17\"\"\4\2\f"+
		"\f\17\17\2\u0123\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3\2\2\2\5H\3\2\2\2\7Q\3\2\2\2\tX"+
		"\3\2\2\2\13^\3\2\2\2\rh\3\2\2\2\17o\3\2\2\2\21t\3\2\2\2\23|\3\2\2\2\25"+
		"\177\3\2\2\2\27\u0085\3\2\2\2\31\u008e\3\2\2\2\33\u0093\3\2\2\2\35\u0095"+
		"\3\2\2\2\37\u0097\3\2\2\2!\u0099\3\2\2\2#\u009b\3\2\2\2%\u009d\3\2\2\2"+
		"\'\u009f\3\2\2\2)\u00a1\3\2\2\2+\u00ac\3\2\2\2-\u00b7\3\2\2\2/\u00ce\3"+
		"\2\2\2\61\u00d0\3\2\2\2\63\u00d8\3\2\2\2\65\u00e4\3\2\2\2\67\u00ef\3\2"+
		"\2\29\u00f7\3\2\2\2;\u00fb\3\2\2\2=\u0109\3\2\2\2?@\7r\2\2@A\7t\2\2AB"+
		"\7q\2\2BC\7i\2\2CD\7t\2\2DE\7c\2\2EF\7o\2\2FG\7c\2\2G\4\3\2\2\2HI\7h\2"+
		"\2IJ\7k\2\2JK\7o\2\2KL\7r\2\2LM\7t\2\2MN\7q\2\2NO\7i\2\2OP\7=\2\2P\6\3"+
		"\2\2\2QR\7p\2\2RS\7w\2\2ST\7o\2\2TU\7g\2\2UV\7t\2\2VW\7q\2\2W\b\3\2\2"+
		"\2XY\7v\2\2YZ\7g\2\2Z[\7z\2\2[\\\7v\2\2\\]\7q\2\2]\n\3\2\2\2^_\7e\2\2"+
		"_`\7c\2\2`a\7t\2\2ab\7c\2\2bc\7e\2\2cd\7v\2\2de\7g\2\2ef\7t\2\2fg\7g\2"+
		"\2g\f\3\2\2\2hi\7n\2\2ij\7q\2\2jk\7i\2\2kl\7k\2\2lm\7e\2\2mn\7q\2\2n\16"+
		"\3\2\2\2op\7n\2\2pq\7g\2\2qr\7k\2\2rs\7c\2\2s\20\3\2\2\2tu\7g\2\2uv\7"+
		"u\2\2vw\7e\2\2wx\7t\2\2xy\7g\2\2yz\7x\2\2z{\7c\2\2{\22\3\2\2\2|}\7u\2"+
		"\2}~\7g\2\2~\24\3\2\2\2\177\u0080\7u\2\2\u0080\u0081\7g\2\2\u0081\u0082"+
		"\7p\2\2\u0082\u0083\7c\2\2\u0083\u0084\7q\2\2\u0084\26\3\2\2\2\u0085\u0086"+
		"\7g\2\2\u0086\u0087\7p\2\2\u0087\u0088\7s\2\2\u0088\u0089\7w\2\2\u0089"+
		"\u008a\7c\2\2\u008a\u008b\7p\2\2\u008b\u008c\7v\2\2\u008c\u008d\7q\2\2"+
		"\u008d\30\3\2\2\2\u008e\u008f\7h\2\2\u008f\u0090\7c\2\2\u0090\u0091\7"+
		"e\2\2\u0091\u0092\7c\2\2\u0092\32\3\2\2\2\u0093\u0094\7*\2\2\u0094\34"+
		"\3\2\2\2\u0095\u0096\7+\2\2\u0096\36\3\2\2\2\u0097\u0098\7=\2\2\u0098"+
		" \3\2\2\2\u0099\u009a\t\2\2\2\u009a\"\3\2\2\2\u009b\u009c\7?\2\2\u009c"+
		"$\3\2\2\2\u009d\u009e\7.\2\2\u009e&\3\2\2\2\u009f\u00a0\7}\2\2\u00a0("+
		"\3\2\2\2\u00a1\u00a2\7\177\2\2\u00a2*\3\2\2\2\u00a3\u00ad\t\3\2\2\u00a4"+
		"\u00a5\7@\2\2\u00a5\u00ad\7?\2\2\u00a6\u00a7\7>\2\2\u00a7\u00ad\7?\2\2"+
		"\u00a8\u00a9\7?\2\2\u00a9\u00ad\7?\2\2\u00aa\u00ab\7#\2\2\u00ab\u00ad"+
		"\7?\2\2\u00ac\u00a3\3\2\2\2\u00ac\u00a4\3\2\2\2\u00ac\u00a6\3\2\2\2\u00ac"+
		"\u00a8\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad,\3\2\2\2\u00ae\u00af\7v\2\2\u00af"+
		"\u00b0\7t\2\2\u00b0\u00b1\7w\2\2\u00b1\u00b8\7g\2\2\u00b2\u00b3\7h\2\2"+
		"\u00b3\u00b4\7c\2\2\u00b4\u00b5\7n\2\2\u00b5\u00b6\7u\2\2\u00b6\u00b8"+
		"\7g\2\2\u00b7\u00ae\3\2\2\2\u00b7\u00b2\3\2\2\2\u00b8.\3\2\2\2\u00b9\u00ba"+
		"\7r\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7v\2\2\u00bc\u00bd\7g\2\2\u00bd"+
		"\u00be\7p\2\2\u00be\u00bf\7e\2\2\u00bf\u00c0\7k\2\2\u00c0\u00cf\7c\2\2"+
		"\u00c1\u00c2\7n\2\2\u00c2\u00c3\7q\2\2\u00c3\u00c4\7i\2\2\u00c4\u00c5"+
		"\7c\2\2\u00c5\u00c6\7t\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8\7v\2\2\u00c8"+
		"\u00c9\7o\2\2\u00c9\u00cf\7q\2\2\u00ca\u00cb\7t\2\2\u00cb\u00cc\7c\2\2"+
		"\u00cc\u00cd\7k\2\2\u00cd\u00cf\7|\2\2\u00ce\u00b9\3\2\2\2\u00ce\u00c1"+
		"\3\2\2\2\u00ce\u00ca\3\2\2\2\u00cf\60\3\2\2\2\u00d0\u00d4\t\4\2\2\u00d1"+
		"\u00d3\t\5\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\62\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d9"+
		"\t\6\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00d8\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00e2\3\2\2\2\u00dc\u00de\7\60\2\2\u00dd\u00df\t"+
		"\6\2\2\u00de\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00dc\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\64\3\2\2\2\u00e4\u00ea\7$\2\2\u00e5\u00e6\7^\2\2\u00e6\u00e9"+
		"\7$\2\2\u00e7\u00e9\13\2\2\2\u00e8\u00e5\3\2\2\2\u00e8\u00e7\3\2\2\2\u00e9"+
		"\u00ec\3\2\2\2\u00ea\u00eb\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ed\3\2"+
		"\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\7$\2\2\u00ee\66\3\2\2\2\u00ef\u00f3"+
		"\7)\2\2\u00f0\u00f1\7^\2\2\u00f1\u00f4\7)\2\2\u00f2\u00f4\13\2\2\2\u00f3"+
		"\u00f0\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\7)"+
		"\2\2\u00f68\3\2\2\2\u00f7\u00f8\t\7\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa"+
		"\b\35\2\2\u00fa:\3\2\2\2\u00fb\u00fc\7\61\2\2\u00fc\u00fd\7,\2\2\u00fd"+
		"\u0101\3\2\2\2\u00fe\u0100\13\2\2\2\u00ff\u00fe\3\2\2\2\u0100\u0103\3"+
		"\2\2\2\u0101\u0102\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0104\3\2\2\2\u0103"+
		"\u0101\3\2\2\2\u0104\u0105\7,\2\2\u0105\u0106\7\61\2\2\u0106\u0107\3\2"+
		"\2\2\u0107\u0108\b\36\2\2\u0108<\3\2\2\2\u0109\u010a\7\61\2\2\u010a\u010b"+
		"\7\61\2\2\u010b\u010f\3\2\2\2\u010c\u010e\n\b\2\2\u010d\u010c\3\2\2\2"+
		"\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0112"+
		"\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\b\37\2\2\u0113>\3\2\2\2\20\2"+
		"\u00ac\u00b7\u00ce\u00d2\u00d4\u00da\u00e0\u00e2\u00e8\u00ea\u00f3\u0101"+
		"\u010f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}