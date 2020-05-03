grammar Simple;

/**
 * Exercise 1
 * added:
 * 	- declaration and declaration with assignment
 * 	- function declaration
 * 	- function call
 * 	- boolean expressions
 * 	- return statement
 * 	- If-Then-Else statement
 * 	- "Int" and "Bool" data type
 */

// THIS IS THE PARSER INPUT

block		: '{' statement* '}';

statement	: returnRule ';'
			| functionCall ';'
			| functionDec // does not need ';'
			| ifThenElse  // does not need ';'
			| declaration ';'
			| declarationAssignment ';'
			| assignment ';'
			| deletion ';' 
			| print ';' 
			| block; // does not need ';'
			
// eg:
// Int variable
declaration	: TYPE ID;

// eg:
// Int variable = var1+var2
declarationAssignment: declaration '=' assignable;

// used for group rules that can be assigned to a variable
assignable	: ID			#varAssignable
			| exp			#expAssignable
			| boolExp		#boolExpAssignable
			| functionCall	#functionCallAssignable;

// eg:
// variable = 10
assignment	: ID '=' assignable;

// eg:
// delete a
// NOTE: checks are performed by semantic analyzer
deletion	: 'delete' ID;

// eg:
// print variable
print		: 'print' exp;

// eg:
// void fun (Int x, var Int y) { }
functionDec	: type=(TYPE|'void') ID '(' (paramDec (',' paramDec)*)? ')' block;

// the pattern of parameter declarations
// eg:
// var Int x
paramDec	: var='var'? declaration ;

// the possible rules that can be used in a function call as parameter 
paramDef	: assignment		#assignParamDef
			| boolExp			#boolExpParamDef
			| exp				#expParamDef;

// eg:
// fun(var1, var2)
functionCall: ID '(' (paramDef (',' paramDef)*)? ')' ;

// eg:
// if(x==4) then {} else {}
ifThenElse	: ifThen elseRule?;

// eg:
// if(x==4) then {}
ifThen		: 'if' '(' boolExp ')' 'then' block;

// eg:
// else {}
elseRule	: 'else' block;

// possible returns (ID, expressions or function call)
// eg:
// return var1
returnRule	: 'return' ID							#varReturn
			| 'return' boolExp						#boolExpReturn
			| 'return' exp							#expReturn
			| 'return' functionCall 				#funCallReturn;

// boolean expressions
// split apart from "exp" to avoid statements like "if(3+3) then {}"
boolExp		: '(' boolExp ')'						#baseBoolExp
			| left=boolExp op='&&' right=boolExp	#binBoolExp
			| left=boolExp op='||' right=boolExp	#binBoolExp
			| cond									#condBoolExp
			| ID									#varBoolExp;

// represent a relationship operation
// eg:
// 3+3==6
// NOTE: exp==exp has been split apart because
//	1) we cannot use "boolExp" to avoid the possibility to write "(var1&&var2)==5"
//	2) we cannot use "exp" because there isn't "cond" in "exp"
//	3) we cannot use "cond" to avoid the possibility to write "var1==var2==var3"
cond		: left=not ('==' right=not)?			#equalNotCond
			| left=not '==' right=ID				#equalNotIdCond
			| left=ID  '==' right=not				#equalIdNotCond
			| left=exp '==' right=exp				#equalCond
			| left=exp '>=' right=exp				#greatEqCond
			| left=exp '<=' right=exp				#lessEqCond
			| left=exp '>' right=exp				#greatCond
			| left=exp '<' right=exp				#lessCond;

// represents a "not statement"
// eg:
// !(var1==var2)			
not: '!' '(' boolExp ')';

// represents an expression between two or more numbers
// eg:
// 1+2+3+var1
exp			: '(' exp ')'							#baseExp
			| '-' exp								#negExp
			| left=exp op=('*' | '/') right=exp		#binExp
			| left=exp op=('+' | '-') right=exp		#binExp
			| ID 									#varExp	
		    | NUMBER							    #valExp;


// THIS IS THE LEXER INPUT

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