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

splitedExpressionsWithCommaAndKey : (identifier ASSIGN expression (COMMA  identifier ASSIGN expression)*)?;

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

ifStatementWithReturn :
    IF {System.out.println("Conditional : if");}
    expression COLON body ELSE {System.out.println("Conditional : else");}
    COLON body;

printStatement :
  PRINT {System.out.println("Built-in : print");}
  LPAR expression RPAR SEMICOLLON;

statement: ifStatement | printStatement | functionCallStatement | returnStatement;

singleStatement : returnStatement | ifStatementWithReturn;

//block: LBRACE (((statement)* returnStatement (statement)*)+ | ifStatementWithReturn) RBRACE;

block: LBRACE (statement* (returnStatement | ifStatementWithReturn) statement*) RBRACE;

conditionBody : LBRACE (statement)* RBRACE | statement;

expression:
    andExpression (op = OR
    andExpression {System.out.println("Operator : " + $op.text);} )*;

andExpression:
    equalityExpression (op = AND
    equalityExpression {System.out.println("Operator : " + $op.text);} )*;

equalityExpression:
    relationalExpression ((op = EQUAL | op = NOT_EQUAL)
    relationalExpression {System.out.println("Operator : " + $op.text);} )*;

relationalExpression:
    additiveExpression ((op = GREATER_THAN | op = LESS_THAN)
    additiveExpression {System.out.println("Operator : " + $op.text);} )*;

additiveExpression:
    multiplicativeExpression ((op = PLUS | op = MINUS)
    multiplicativeExpression {System.out.println("Operator : " + $op.text);} )*;

multiplicativeExpression:
    preUnaryExpression ((op = MULT | op = DIVIDE )
    preUnaryExpression {System.out.println("Operator : " + $op.text);} )*;

preUnaryExpression:
    ((op = NOT | op = MINUS)
    preUnaryExpression {System.out.println("Operator : " + $op.text);}) | appendExpression ;

appendExpression :
    accessExpression (op = APPEND
     accessExpression {System.out.println("Operator : " + $op.text);} )*;

accessExpression: (otherExpression | anonymousFunction)  ((LPAR functionArguments RPAR) | (LBRACK expression RBRACK))* (sizeExpression)*;

otherExpression:  values | identifier | LPAR (expression) RPAR ;

anonymousFunction :
    {System.out.println("Anonymous Function");}
    functionArgumentsDeclaration ARROW block;

sizeExpression: DOT SIZE {System.out.println("Size");};

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
