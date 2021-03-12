// Generated from D:/Term 6/compiler/Phase 1/src/main\Jepeto.g4 by ANTLR 4.8
package main;
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
		FUNC=1, MAIN=2, SIZE=3, PRINT=4, RETURN=5, IF=6, ELSE=7, PLUS=8, MINUS=9, 
		MULT=10, DIVIDE=11, EQUAL=12, NOT_EQUAL=13, GREATER_THAN=14, LESS_THAN=15, 
		AND=16, OR=17, NOT=18, APPEND=19, BOOLEAN=20, STRING=21, INT=22, TRUE=23, 
		FALSE=24, ARROW=25, ASSIGN=26, LPAR=27, RPAR=28, LBRACK=29, RBRACK=30, 
		LBRACE=31, RBRACE=32, COMMA=33, DOT=34, COLON=35, SEMICOLLON=36, INT_VALUE=37, 
		IDENTIFIER=38, STRING_VALUE=39, COMMENT=40, WS=41;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"FUNC", "MAIN", "SIZE", "PRINT", "RETURN", "IF", "ELSE", "PLUS", "MINUS", 
			"MULT", "DIVIDE", "EQUAL", "NOT_EQUAL", "GREATER_THAN", "LESS_THAN", 
			"AND", "OR", "NOT", "APPEND", "BOOLEAN", "STRING", "INT", "TRUE", "FALSE", 
			"ARROW", "ASSIGN", "LPAR", "RPAR", "LBRACK", "RBRACK", "LBRACE", "RBRACE", 
			"COMMA", "DOT", "COLON", "SEMICOLLON", "INT_VALUE", "IDENTIFIER", "STRING_VALUE", 
			"COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'func'", "'main'", "'size'", "'print'", "'return'", "'if'", "'else'", 
			"'+'", "'-'", "'*'", "'/'", "'is'", "'not'", "'>'", "'<'", "'and'", "'or'", 
			"'~'", "'::'", "'boolean'", "'string'", "'int'", "'true'", "'false'", 
			"'->'", "'='", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", "'.'", 
			"':'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FUNC", "MAIN", "SIZE", "PRINT", "RETURN", "IF", "ELSE", "PLUS", 
			"MINUS", "MULT", "DIVIDE", "EQUAL", "NOT_EQUAL", "GREATER_THAN", "LESS_THAN", 
			"AND", "OR", "NOT", "APPEND", "BOOLEAN", "STRING", "INT", "TRUE", "FALSE", 
			"ARROW", "ASSIGN", "LPAR", "RPAR", "LBRACK", "RBRACK", "LBRACE", "RBRACE", 
			"COMMA", "DOT", "COLON", "SEMICOLLON", "INT_VALUE", "IDENTIFIER", "STRING_VALUE", 
			"COMMENT", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u00f6\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3"+
		"\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\7&\u00d3\n&\f"+
		"&\16&\u00d6\13&\5&\u00d8\n&\3\'\3\'\7\'\u00dc\n\'\f\'\16\'\u00df\13\'"+
		"\3(\3(\7(\u00e3\n(\f(\16(\u00e6\13(\3(\3(\3)\3)\7)\u00ec\n)\f)\16)\u00ef"+
		"\13)\3)\3)\3*\3*\3*\3*\2\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+\3\2\t\3\2\63;\3\2"+
		"\62;\5\2C\\aac|\6\2\62;C\\aac|\3\2$$\4\2\f\f\17\17\5\2\13\f\17\17\"\""+
		"\2\u00fa\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\3U\3\2\2\2\5Z\3\2\2\2\7_\3\2\2\2\td\3\2\2\2\13j\3\2\2\2\rq\3"+
		"\2\2\2\17t\3\2\2\2\21y\3\2\2\2\23{\3\2\2\2\25}\3\2\2\2\27\177\3\2\2\2"+
		"\31\u0081\3\2\2\2\33\u0084\3\2\2\2\35\u0088\3\2\2\2\37\u008a\3\2\2\2!"+
		"\u008c\3\2\2\2#\u0090\3\2\2\2%\u0093\3\2\2\2\'\u0095\3\2\2\2)\u0098\3"+
		"\2\2\2+\u00a0\3\2\2\2-\u00a7\3\2\2\2/\u00ab\3\2\2\2\61\u00b0\3\2\2\2\63"+
		"\u00b6\3\2\2\2\65\u00b9\3\2\2\2\67\u00bb\3\2\2\29\u00bd\3\2\2\2;\u00bf"+
		"\3\2\2\2=\u00c1\3\2\2\2?\u00c3\3\2\2\2A\u00c5\3\2\2\2C\u00c7\3\2\2\2E"+
		"\u00c9\3\2\2\2G\u00cb\3\2\2\2I\u00cd\3\2\2\2K\u00d7\3\2\2\2M\u00d9\3\2"+
		"\2\2O\u00e0\3\2\2\2Q\u00e9\3\2\2\2S\u00f2\3\2\2\2UV\7h\2\2VW\7w\2\2WX"+
		"\7p\2\2XY\7e\2\2Y\4\3\2\2\2Z[\7o\2\2[\\\7c\2\2\\]\7k\2\2]^\7p\2\2^\6\3"+
		"\2\2\2_`\7u\2\2`a\7k\2\2ab\7|\2\2bc\7g\2\2c\b\3\2\2\2de\7r\2\2ef\7t\2"+
		"\2fg\7k\2\2gh\7p\2\2hi\7v\2\2i\n\3\2\2\2jk\7t\2\2kl\7g\2\2lm\7v\2\2mn"+
		"\7w\2\2no\7t\2\2op\7p\2\2p\f\3\2\2\2qr\7k\2\2rs\7h\2\2s\16\3\2\2\2tu\7"+
		"g\2\2uv\7n\2\2vw\7u\2\2wx\7g\2\2x\20\3\2\2\2yz\7-\2\2z\22\3\2\2\2{|\7"+
		"/\2\2|\24\3\2\2\2}~\7,\2\2~\26\3\2\2\2\177\u0080\7\61\2\2\u0080\30\3\2"+
		"\2\2\u0081\u0082\7k\2\2\u0082\u0083\7u\2\2\u0083\32\3\2\2\2\u0084\u0085"+
		"\7p\2\2\u0085\u0086\7q\2\2\u0086\u0087\7v\2\2\u0087\34\3\2\2\2\u0088\u0089"+
		"\7@\2\2\u0089\36\3\2\2\2\u008a\u008b\7>\2\2\u008b \3\2\2\2\u008c\u008d"+
		"\7c\2\2\u008d\u008e\7p\2\2\u008e\u008f\7f\2\2\u008f\"\3\2\2\2\u0090\u0091"+
		"\7q\2\2\u0091\u0092\7t\2\2\u0092$\3\2\2\2\u0093\u0094\7\u0080\2\2\u0094"+
		"&\3\2\2\2\u0095\u0096\7<\2\2\u0096\u0097\7<\2\2\u0097(\3\2\2\2\u0098\u0099"+
		"\7d\2\2\u0099\u009a\7q\2\2\u009a\u009b\7q\2\2\u009b\u009c\7n\2\2\u009c"+
		"\u009d\7g\2\2\u009d\u009e\7c\2\2\u009e\u009f\7p\2\2\u009f*\3\2\2\2\u00a0"+
		"\u00a1\7u\2\2\u00a1\u00a2\7v\2\2\u00a2\u00a3\7t\2\2\u00a3\u00a4\7k\2\2"+
		"\u00a4\u00a5\7p\2\2\u00a5\u00a6\7i\2\2\u00a6,\3\2\2\2\u00a7\u00a8\7k\2"+
		"\2\u00a8\u00a9\7p\2\2\u00a9\u00aa\7v\2\2\u00aa.\3\2\2\2\u00ab\u00ac\7"+
		"v\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7w\2\2\u00ae\u00af\7g\2\2\u00af\60"+
		"\3\2\2\2\u00b0\u00b1\7h\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7n\2\2\u00b3"+
		"\u00b4\7u\2\2\u00b4\u00b5\7g\2\2\u00b5\62\3\2\2\2\u00b6\u00b7\7/\2\2\u00b7"+
		"\u00b8\7@\2\2\u00b8\64\3\2\2\2\u00b9\u00ba\7?\2\2\u00ba\66\3\2\2\2\u00bb"+
		"\u00bc\7*\2\2\u00bc8\3\2\2\2\u00bd\u00be\7+\2\2\u00be:\3\2\2\2\u00bf\u00c0"+
		"\7]\2\2\u00c0<\3\2\2\2\u00c1\u00c2\7_\2\2\u00c2>\3\2\2\2\u00c3\u00c4\7"+
		"}\2\2\u00c4@\3\2\2\2\u00c5\u00c6\7\177\2\2\u00c6B\3\2\2\2\u00c7\u00c8"+
		"\7.\2\2\u00c8D\3\2\2\2\u00c9\u00ca\7\60\2\2\u00caF\3\2\2\2\u00cb\u00cc"+
		"\7<\2\2\u00ccH\3\2\2\2\u00cd\u00ce\7=\2\2\u00ceJ\3\2\2\2\u00cf\u00d8\7"+
		"\62\2\2\u00d0\u00d4\t\2\2\2\u00d1\u00d3\t\3\2\2\u00d2\u00d1\3\2\2\2\u00d3"+
		"\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d8\3\2"+
		"\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00cf\3\2\2\2\u00d7\u00d0\3\2\2\2\u00d8"+
		"L\3\2\2\2\u00d9\u00dd\t\4\2\2\u00da\u00dc\t\5\2\2\u00db\u00da\3\2\2\2"+
		"\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00deN\3"+
		"\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e4\7$\2\2\u00e1\u00e3\n\6\2\2\u00e2"+
		"\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2"+
		"\2\2\u00e5\u00e7\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8\7$\2\2\u00e8"+
		"P\3\2\2\2\u00e9\u00ed\7%\2\2\u00ea\u00ec\n\7\2\2\u00eb\u00ea\3\2\2\2\u00ec"+
		"\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2"+
		"\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1\b)\2\2\u00f1R\3\2\2\2\u00f2\u00f3"+
		"\t\b\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\b*\2\2\u00f5T\3\2\2\2\b\2\u00d4"+
		"\u00d7\u00dd\u00e4\u00ed\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}