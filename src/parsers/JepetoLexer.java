// Generated from D:/Term 6/compiler/Phase 1/src/main/grammar\Jepeto.g4 by ANTLR 4.8
package parsers;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JepetoLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FUNC=1, MAIN=2, SIZE=3, PRINT=4, RETURN=5, VOID=6, IF=7, ELSE=8, PLUS=9, 
		MINUS=10, MULT=11, DIVIDE=12, EQUAL=13, NOT_EQUAL=14, GREATER_THAN=15, 
		LESS_THAN=16, AND=17, OR=18, NOT=19, APPEND=20, BOOLEAN=21, STRING=22, 
		INT=23, TRUE=24, FALSE=25, ARROW=26, ASSIGN=27, LPAR=28, RPAR=29, LBRACK=30, 
		RBRACK=31, LBRACE=32, RBRACE=33, COMMA=34, DOT=35, COLON=36, SEMICOLLON=37, 
		INT_VALUE=38, IDENTIFIER=39, STRING_VALUE=40, COMMENT=41, WS=42;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"FUNC", "MAIN", "SIZE", "PRINT", "RETURN", "VOID", "IF", "ELSE", "PLUS", 
			"MINUS", "MULT", "DIVIDE", "EQUAL", "NOT_EQUAL", "GREATER_THAN", "LESS_THAN", 
			"AND", "OR", "NOT", "APPEND", "BOOLEAN", "STRING", "INT", "TRUE", "FALSE", 
			"ARROW", "ASSIGN", "LPAR", "RPAR", "LBRACK", "RBRACK", "LBRACE", "RBRACE", 
			"COMMA", "DOT", "COLON", "SEMICOLLON", "INT_VALUE", "IDENTIFIER", "STRING_VALUE", 
			"COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'func'", "'main'", "'size'", "'print'", "'return'", "'void'", 
			"'if'", "'else'", "'+'", "'-'", "'*'", "'/'", "'is'", "'not'", "'>'", 
			"'<'", "'and'", "'or'", "'~'", "'::'", "'boolean'", "'string'", "'int'", 
			"'true'", "'false'", "'->'", "'='", "'('", "')'", "'['", "']'", "'{'", 
			"'}'", "','", "'.'", "':'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FUNC", "MAIN", "SIZE", "PRINT", "RETURN", "VOID", "IF", "ELSE", 
			"PLUS", "MINUS", "MULT", "DIVIDE", "EQUAL", "NOT_EQUAL", "GREATER_THAN", 
			"LESS_THAN", "AND", "OR", "NOT", "APPEND", "BOOLEAN", "STRING", "INT", 
			"TRUE", "FALSE", "ARROW", "ASSIGN", "LPAR", "RPAR", "LBRACK", "RBRACK", 
			"LBRACE", "RBRACE", "COMMA", "DOT", "COLON", "SEMICOLLON", "INT_VALUE", 
			"IDENTIFIER", "STRING_VALUE", "COMMENT", "WS"
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


	public JepetoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Jepeto.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2,\u00fd\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&"+
		"\3\'\3\'\3\'\7\'\u00da\n\'\f\'\16\'\u00dd\13\'\5\'\u00df\n\'\3(\3(\7("+
		"\u00e3\n(\f(\16(\u00e6\13(\3)\3)\7)\u00ea\n)\f)\16)\u00ed\13)\3)\3)\3"+
		"*\3*\7*\u00f3\n*\f*\16*\u00f6\13*\3*\3*\3+\3+\3+\3+\2\2,\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,\3\2\t\3\2\63;\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\3\2$"+
		"$\4\2\f\f\17\17\5\2\13\f\17\17\"\"\2\u0101\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M"+
		"\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\3W\3\2\2\2\5\\\3"+
		"\2\2\2\7a\3\2\2\2\tf\3\2\2\2\13l\3\2\2\2\rs\3\2\2\2\17x\3\2\2\2\21{\3"+
		"\2\2\2\23\u0080\3\2\2\2\25\u0082\3\2\2\2\27\u0084\3\2\2\2\31\u0086\3\2"+
		"\2\2\33\u0088\3\2\2\2\35\u008b\3\2\2\2\37\u008f\3\2\2\2!\u0091\3\2\2\2"+
		"#\u0093\3\2\2\2%\u0097\3\2\2\2\'\u009a\3\2\2\2)\u009c\3\2\2\2+\u009f\3"+
		"\2\2\2-\u00a7\3\2\2\2/\u00ae\3\2\2\2\61\u00b2\3\2\2\2\63\u00b7\3\2\2\2"+
		"\65\u00bd\3\2\2\2\67\u00c0\3\2\2\29\u00c2\3\2\2\2;\u00c4\3\2\2\2=\u00c6"+
		"\3\2\2\2?\u00c8\3\2\2\2A\u00ca\3\2\2\2C\u00cc\3\2\2\2E\u00ce\3\2\2\2G"+
		"\u00d0\3\2\2\2I\u00d2\3\2\2\2K\u00d4\3\2\2\2M\u00de\3\2\2\2O\u00e0\3\2"+
		"\2\2Q\u00e7\3\2\2\2S\u00f0\3\2\2\2U\u00f9\3\2\2\2WX\7h\2\2XY\7w\2\2YZ"+
		"\7p\2\2Z[\7e\2\2[\4\3\2\2\2\\]\7o\2\2]^\7c\2\2^_\7k\2\2_`\7p\2\2`\6\3"+
		"\2\2\2ab\7u\2\2bc\7k\2\2cd\7|\2\2de\7g\2\2e\b\3\2\2\2fg\7r\2\2gh\7t\2"+
		"\2hi\7k\2\2ij\7p\2\2jk\7v\2\2k\n\3\2\2\2lm\7t\2\2mn\7g\2\2no\7v\2\2op"+
		"\7w\2\2pq\7t\2\2qr\7p\2\2r\f\3\2\2\2st\7x\2\2tu\7q\2\2uv\7k\2\2vw\7f\2"+
		"\2w\16\3\2\2\2xy\7k\2\2yz\7h\2\2z\20\3\2\2\2{|\7g\2\2|}\7n\2\2}~\7u\2"+
		"\2~\177\7g\2\2\177\22\3\2\2\2\u0080\u0081\7-\2\2\u0081\24\3\2\2\2\u0082"+
		"\u0083\7/\2\2\u0083\26\3\2\2\2\u0084\u0085\7,\2\2\u0085\30\3\2\2\2\u0086"+
		"\u0087\7\61\2\2\u0087\32\3\2\2\2\u0088\u0089\7k\2\2\u0089\u008a\7u\2\2"+
		"\u008a\34\3\2\2\2\u008b\u008c\7p\2\2\u008c\u008d\7q\2\2\u008d\u008e\7"+
		"v\2\2\u008e\36\3\2\2\2\u008f\u0090\7@\2\2\u0090 \3\2\2\2\u0091\u0092\7"+
		">\2\2\u0092\"\3\2\2\2\u0093\u0094\7c\2\2\u0094\u0095\7p\2\2\u0095\u0096"+
		"\7f\2\2\u0096$\3\2\2\2\u0097\u0098\7q\2\2\u0098\u0099\7t\2\2\u0099&\3"+
		"\2\2\2\u009a\u009b\7\u0080\2\2\u009b(\3\2\2\2\u009c\u009d\7<\2\2\u009d"+
		"\u009e\7<\2\2\u009e*\3\2\2\2\u009f\u00a0\7d\2\2\u00a0\u00a1\7q\2\2\u00a1"+
		"\u00a2\7q\2\2\u00a2\u00a3\7n\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5\7c\2\2"+
		"\u00a5\u00a6\7p\2\2\u00a6,\3\2\2\2\u00a7\u00a8\7u\2\2\u00a8\u00a9\7v\2"+
		"\2\u00a9\u00aa\7t\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7p\2\2\u00ac\u00ad"+
		"\7i\2\2\u00ad.\3\2\2\2\u00ae\u00af\7k\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1"+
		"\7v\2\2\u00b1\60\3\2\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7t\2\2\u00b4\u00b5"+
		"\7w\2\2\u00b5\u00b6\7g\2\2\u00b6\62\3\2\2\2\u00b7\u00b8\7h\2\2\u00b8\u00b9"+
		"\7c\2\2\u00b9\u00ba\7n\2\2\u00ba\u00bb\7u\2\2\u00bb\u00bc\7g\2\2\u00bc"+
		"\64\3\2\2\2\u00bd\u00be\7/\2\2\u00be\u00bf\7@\2\2\u00bf\66\3\2\2\2\u00c0"+
		"\u00c1\7?\2\2\u00c18\3\2\2\2\u00c2\u00c3\7*\2\2\u00c3:\3\2\2\2\u00c4\u00c5"+
		"\7+\2\2\u00c5<\3\2\2\2\u00c6\u00c7\7]\2\2\u00c7>\3\2\2\2\u00c8\u00c9\7"+
		"_\2\2\u00c9@\3\2\2\2\u00ca\u00cb\7}\2\2\u00cbB\3\2\2\2\u00cc\u00cd\7\177"+
		"\2\2\u00cdD\3\2\2\2\u00ce\u00cf\7.\2\2\u00cfF\3\2\2\2\u00d0\u00d1\7\60"+
		"\2\2\u00d1H\3\2\2\2\u00d2\u00d3\7<\2\2\u00d3J\3\2\2\2\u00d4\u00d5\7=\2"+
		"\2\u00d5L\3\2\2\2\u00d6\u00df\7\62\2\2\u00d7\u00db\t\2\2\2\u00d8\u00da"+
		"\t\3\2\2\u00d9\u00d8\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00db"+
		"\u00dc\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00d6\3\2"+
		"\2\2\u00de\u00d7\3\2\2\2\u00dfN\3\2\2\2\u00e0\u00e4\t\4\2\2\u00e1\u00e3"+
		"\t\5\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5P\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00eb\7$\2\2\u00e8"+
		"\u00ea\n\6\2\2\u00e9\u00e8\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2"+
		"\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee"+
		"\u00ef\7$\2\2\u00efR\3\2\2\2\u00f0\u00f4\7%\2\2\u00f1\u00f3\n\7\2\2\u00f2"+
		"\u00f1\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2"+
		"\2\2\u00f5\u00f7\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00f8\b*\2\2\u00f8"+
		"T\3\2\2\2\u00f9\u00fa\t\b\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\b+\2\2\u00fc"+
		"V\3\2\2\2\b\2\u00db\u00de\u00e4\u00eb\u00f4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}