grammar Simple;

// THIS IS THE PARSER INPUT 

block		: '{' statement* '}';

// added declaration
statement	: returnRule ';'
			| functionCall ';'
			| functionDec
			| ifThenElse 
			| declaration ';'
			| declarationAssignment ';'
			| assignment ';'
			| deletion ';' 
			| print ';' 
			| block;
			
// added declaration rule

declaration	: TYPE ID;

declarationAssignment: declaration '=' assignable;

assignable	: ID			#varAssignable
			| exp			#expAssignable
			| boolExp		#boolExpAssignable
			| functionCall	#functionCallAssignable;

assignment	: ID '=' assignable;

deletion	: 'delete' ID;

print		: 'print' exp;

functionDec	: type=(TYPE|'void') ID '(' (paramDec (',' paramDec)*)? ')' block;

paramDec	: var='var'? declaration ;

// cancellato variableref
paramDef	:  assignment		#assignParamDef
			| boolExp			#boolExpParamDef
			| exp				#expParamDef;



functionCall: ID '(' (paramDef (',' paramDef)*)? ')' ;

ifThenElse	: ifThen elseRule?;

ifThen		: 'if' '(' boolExp ')' 'then' block;

elseRule	: 'else' block;

returnRule	: 'return' ID							#varReturn
			| 'return' boolExp						#boolExpReturn
			| 'return' exp							#expReturn
			| 'return' functionCall 				#funCallReturn;

boolExp		: '(' boolExp ')'						#baseBoolExp
			| left=boolExp op='&&' right=boolExp	#binBoolExp
			| left=boolExp op='||' right=boolExp	#binBoolExp
			| cond									#condBoolExp
			| ID									#varBoolExp;

cond		: left=not ('==' right=not)?			#equalNotCond
			| left=not '==' right=ID				#equalNotIdCond
			| left=ID  '==' right=not				#equalIdNotCond
			| left=exp '==' right=exp				#equalCond
			| left=exp '>=' right=exp				#greatEqCond
			| left=exp '<=' right=exp				#lessEqCond
			| left=exp '>' right=exp				#greatCond
			| left=exp '<' right=exp				#lessCond;
			
not: '!' '(' boolExp ')';

exp			: '(' exp ')'							#baseExp
			| '-' exp								#negExp
			| left=exp op=('*' | '/') right=exp		#binExp
			| left=exp op=('+' | '-') right=exp		#binExp
			| ID 									#varExp	
		    | NUMBER							    #valExp;



// THIS IS THE LEXER INPUT

// added TYPE token (possible types)
TYPE			: 'Bool' | 'Int';

//IDs
fragment CHAR 	: 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//Numbers
fragment DIGIT	: '0'..'9';	
NUMBER          : DIGIT+;


//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMMENTS 	: '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMMENTS)* '*/' -> skip;