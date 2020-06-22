grammar SimplePlus;

// THIS IS THE PARSER INPUT

block	    : '{' statement* '}';

statement   : declaration 
            | assignment ';'
		    | deletion ';'
		    | print ';'
		    | ret ';'
		    | ite
		    | call ';'
		    | block;


declaration : decFun
            | decVar ;

decFun	    : type ID '(' (arg (',' arg)*)? ')' block ;

decVar      : type ID ('=' exp)? ';' ;

type        : 'int'
            | 'bool'
            | 'void';

arg         : type ref? ID;

ref         : 'var';

assignment  : ID '=' exp;

deletion    : 'delete' ID;

print	    : 'print' exp;

ret	    : 'return' (exp)?;

ite         : 'if' '(' exp ')' statement ('else' statement)?;

call        : ID '(' (exp(',' exp)*)? ')';

exp	    : '(' exp ')'										#baseExp
	    | '-' exp											#negExp
	    | '!' exp                                           #notExp
	    | left=exp op=('*' | '/')               right=exp   #binExp
	    | left=exp op=('+' | '-')               right=exp   #binExp
	    | left=exp op=('<' | '<=' | '>' | '>=') right=exp   #binExp
	    | left=exp op=('=='| '!=')              right=exp   #binExp
	    | left=exp op='&&'                      right=exp   #binExp
	    | left=exp op='||'                      right=exp   #binExp
	    | call                                              #callExp
	    | BOOL                                              #boolExp
	    | ID												#varExp
	    | NUMBER											#valExp;


// THIS IS THE LEXER INPUT

//Booleans
BOOL        : 'true'|'false';

//IDs
fragment 
CHAR 	    : 'a'..'z' |'A'..'Z' ;
ID          : CHAR (CHAR | DIGIT)* ;

//Numbers
fragment 
DIGIT	    : '0'..'9';
NUMBER      : DIGIT+;




//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMMENTS 	: '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMMENTS   : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMMENTS)* '*/' -> skip;
