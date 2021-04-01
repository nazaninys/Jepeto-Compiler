grammar Jepeto;

jepeto : program EOF;

program : (functionDeclaration)*  main  (functionDeclaration)*;

functionDeclaration :
    FUNC id = IDENTIFIER {System.out.println("FunctionDec : " + $id.text);}
    functionArgumentsDeclaration  COLON body;

functionArgumentsDeclaration :
    LPAR (id1 = IDENTIFIER {System.out.println("ArgumentDec : " + $id1.text);}
    (COMMA id2 = IDENTIFIER {System.out.println("ArgumentDec : " + $id2.text);})*)? RPAR ;

body : singleStatement | block;

main :
    MAIN {System.out.println("Main");}
    COLON (functionCallStatement | printStatement)? ;

functionCall : otherExpression ((LPAR functionArguments RPAR) | (LBRACK expression RBRACK))* LPAR functionArguments RPAR;

functionArguments : splitedExpressionsWithComma | splitedExpressionsWithCommaAndKey;

splitedExpressionsWithComma : (expression (COMMA expression)*)?;

splitedExpressionsWithCommaAndKey : (singleArgumentWithKey (COMMA  singleArgumentWithKey)*)?;

singleArgumentWithKey : identifier ASSIGN expression;

functionCallStatement :
    {{System.out.println("FunctionCall");}}
    functionCall SEMICOLLON;

returnStatement :
    RETURN {System.out.println("Return");}
    (expression | VOID) SEMICOLLON;

ifStatement :
    IF {System.out.println("Conditional : if");}
    expression COLON conditionBody   (ELSE {System.out.println("Conditional : else");}
    COLON conditionBody)?;

printStatement :
  PRINT {System.out.println("Built-in : print");}
  LPAR expression RPAR SEMICOLLON;

statement: ifStatement | printStatement | functionCallStatement | returnStatement;

singleStatement : returnStatement | ifStatementWithReturn;

ifStatementWithReturn :
    IF {System.out.println("Conditional : if");}
    expression COLON body ELSE {System.out.println("Conditional : else");}
    COLON body;

block: LBRACE (((statement)* returnStatement (statement)*)+ | ifStatementWithReturn) RBRACE;

conditionBody : LBRACE (statement)* RBRACE | statement;

expression:
    andExpression (OR {System.out.println("Operator : or");}
    andExpression)*;

andExpression:
    equalityExpression (AND {System.out.println("Operator : and");}
    equalityExpression)*;

equalityExpression:
    relationalExpression ((EQUAL {System.out.println("Operator : is");}
    | NOT_EQUAL {System.out.println("Operator : not");}
    ) relationalExpression)*;

relationalExpression:
    additiveExpression ((GREATER_THAN {System.out.println("Operator : >");}
    | LESS_THAN {System.out.println("Operator : <");}
    ) additiveExpression)*;

additiveExpression:
    multiplicativeExpression ((PLUS {System.out.println("Operator : +");}
    | MINUS {System.out.println("Operator : -");}
    ) multiplicativeExpression)*;

multiplicativeExpression:
    preUnaryExpression ((MULT {System.out.println("Operator : *");}
    | DIVIDE {System.out.println("Operator : /");}
    ) preUnaryExpression)*;

preUnaryExpression:
    ((NOT {System.out.println("Operator : ~");}
    | MINUS {System.out.println("Operator : -");}
    ) preUnaryExpression) | accessExpression;

accessExpression: otherExpression ((LPAR functionArguments RPAR) | (LBRACK expression RBRACK) | appendExpression)*;


otherExpression:  (values | identifier | anonymousFunction | LPAR (expression) RPAR) (sizeExpression) ?;

anonymousFunction :
    {System.out.println("Anonymous Function");}
    functionArgumentsDeclaration ARROW block;

sizeExpression: DOT SIZE {System.out.println("Size");};

appendExpression : APPEND {{System.out.println("Operator : ::");}} otherExpression;

values: boolValue | STRING_VALUE | INT_VALUE | listValue;

listValue: LBRACK splitedExpressionsWithComma RBRACK;

boolValue : TRUE | FALSE ;

identifier: IDENTIFIER;


FUNC: 'func';
MAIN: 'main';
SIZE: 'size';

PRINT: 'print';
RETURN: 'return';
VOID: 'void';

IF: 'if';
ELSE: 'else';

PLUS: '+';
MINUS: '-';
MULT: '*';
DIVIDE: '/';

EQUAL: 'is';
NOT_EQUAL: 'not';
GREATER_THAN: '>';
LESS_THAN: '<';

AND: 'and';
OR: 'or';
NOT: '~';

APPEND: '::';

BOOLEAN: 'boolean';
STRING: 'string';
INT: 'int';

TRUE: 'true';
FALSE: 'false';

ARROW: '->';

ASSIGN: '=';

LPAR: '(';
RPAR: ')';
LBRACK: '[';
RBRACK: ']';
LBRACE: '{';
RBRACE: '}';

COMMA: ',';
DOT: '.';
COLON: ':';
SEMICOLLON: ';';

INT_VALUE: '0' | [1-9][0-9]*;
IDENTIFIER: [a-zA-Z_][A-Za-z0-9_]*;
STRING_VALUE: '"'~["]*'"';
COMMENT: ('#' ~( '\r' | '\n')*) -> skip;
WS: ([ \t\n\r]) -> skip;
