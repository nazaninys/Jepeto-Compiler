grammar Jepeto;

jepeto : program EOF;//done

program : (functionDeclaration)*  main  (functionDeclaration)*;//done

functionDeclaration : FUNC IDENTIFIER functionArgumentsDeclaration  COLON  functionBody;

functionArgumentsDeclaration : LPAR (IDENTIFIER (COMMA IDENTIFIER)*)? RPAR ; // done

functionBody : singleStatement | block;

singleStatement : returnStatement | ifStatementWithReturn;

ifStatementWithReturn : IF expression COLON (singleStatement | block) ELSE COLON (singleStatement | block);

block: LBRACE (((statement)* returnStatement (statement)*)+ | ifStatementWithReturn) RBRACE;

main : MAIN COLON (functionCallStatement | printStatement)? ; // done

functionCall : otherExpression ((LPAR functionArguments RPAR) | (LBRACK expression RBRACK))* LPAR functionArguments RPAR; // Identifier -> otherExpression or not

functionArguments : splitedExpressionsWithComma | splitedExpressionsWithCommaAndKey; //done

splitedExpressionsWithComma : (singleArgument (COMMA singleArgument)*)?; //done

singleArgument : expression | anonymousFunction;//done

splitedExpressionsWithCommaAndKey : (singleArgumentWithKey (COMMA  singleArgumentWithKey)*)?; //done

singleArgumentWithKey : identifier ASSIGN (expression | anonymousFunction);//done

functionCallStatement : functionCall SEMICOLLON; //done

returnStatement : RETURN (expression)? SEMICOLLON; //done

ifStatement : IF expression COLON conditionBody   (ELSE COLON conditionBody)?; //done
conditionBody : LBRACE (statement)* RBRACE | statement;
printStatement :  PRINT LPAR expression RPAR SEMICOLLON; //done

statement: ifStatement | printStatement | functionCallStatement | returnStatement;



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

otherExpression:  values | identifier | anonymousFunction | LPAR (expression) RPAR;
anonymousFunction : functionArgumentsDeclaration ARROW block; //done

sizeExpression: DOT SIZE;//done

appendExpression : APPEND expression;//done


values: boolValue | STRING_VALUE | INT_VALUE | listValue;//done

listValue: LBRACK splitedExpressionsWithComma RBRACK;//done

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
