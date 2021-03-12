grammar Jepeto;

jepeto : program EOF;

program : (functionDeclaration | main)*;

//functionDeclaration : FUNC IDENTIFIER functionArgumentsDeclaration  COLON functionBody; //done

//functionBody : statement | block; //done
functionDeclaration : FUNC IDENTIFIER functionArgumentsDeclaration  COLON  functionBody;

functionBody : (statement)* ;

functionArgumentsDeclaration : LPAR (IDENTIFIER (COMMA IDENTIFIER)*)? RPAR ; // done

anonymousFunction :functionArgumentsDeclaration ARROW block; //done

main : MAIN COLON (functionCallStatement | printStatement)? ; // done

functionCall : IDENTIFIER LPAR functionArguments RPAR;

// methodCall: otherExpression ((LPAR functionArguments RPAR) | (LBRACK expression RBRACK))* (LPAR functionArguments RPAR);
singleArgument : expression | anonymousFunction;

splitedExpressionsWithComma : (singleArgument (COMMA singleArgument)*)?; //done

singleArgumentWithKey : IDENTIFIER ASSIGN expression | anonymousFunction;
splitedExpressionsWithCommaAndKey : (singleArgumentWithKey (singleArgumentWithKey)*)?; //done

functionArguments : splitedExpressionsWithComma | splitedExpressionsWithCommaAndKey; //done

functionCallStatement : functionCall SEMICOLLON; //done
returnStatement : RETURN expression SEMICOLLON; //done

ifStatement : IF expression COLON statement  (ELSE COLON statement )?; //done

printStatement :  PRINT LPAR expression RPAR SEMICOLLON; //done


statement: ifStatement | printStatement | functionCallStatement | returnStatement | block;

block: LBRACE (statement)* RBRACE;//done

expression: orExpression (ASSIGN expression)?;//done

orExpression: andExpression (OR andExpression)*;//done

andExpression: equalityExpression (AND equalityExpression)*;//done

equalityExpression: relationalExpression ((EQUAL | NOT_EQUAL) relationalExpression)*;//done

relationalExpression: additiveExpression ((GREATER_THAN | LESS_THAN) additiveExpression)*;//done

additiveExpression: multiplicativeExpression ((PLUS | MINUS) multiplicativeExpression)*;//done

multiplicativeExpression: preUnaryExpression ((MULT | DIVIDE) preUnaryExpression)*;//done

preUnaryExpression: ((NOT | MINUS ) preUnaryExpression) | accessExpression;//done

accessExpression: otherExpression ((LPAR functionArguments RPAR) | (LBRACK expression RBRACK))*
                   (sizeExpression | appendExpression) ?;

otherExpression: values | identifier | LPAR (expression) RPAR; //| anonymousFunction;

sizeExpression: DOT SIZE;

appendExpression : APPEND expression;


values: boolValue | STRING_VALUE | INT_VALUE | listValue;

listValue: LBRACK splitedExpressionsWithComma RBRACK;

boolValue : TRUE | FALSE ; //done

identifier: IDENTIFIER; //done


FUNC: 'func';
MAIN: 'main';
SIZE: 'size';

PRINT: 'print';
RETURN: 'return';

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