grammar SVM;

@header {
import java.util.HashMap;
}

@lexer::members {
public int lexicalErrors=0;
}

assembly: (instruction)* ;

instruction:
	LOADINTEGER REG NUMBER
	| BRANCH LABEL
	| LABEL COL
	| PUSH REG
	| LOADWORD r1=REG NUMBER '(' r2=REG ')'
	| STOREWORD r1=REG NUMBER '(' r2=REG ')'
	| (ADD | SUB | MUL | DIV) dest=REG r1=REG r2=REG
	| POP
	| (BRANCHEQ | BRANCHGT | BRANCHGE | BRANCHLT | BRANCHLE) r1=REG r2=REG LABEL
	| NEG REG
	| MOVE dest=REG origin=REG	
	| ADDINTEGER dest=REG r1=REG NUMBER
	| JUMPLABEL LABEL
	| JUMPREG
	| PRINT 
	;
	
PRINT: 'print';
JUMPREG: 'jr';
JUMPLABEL: 'jal';
ADDINTEGER: 'addi';	
MOVE: 'move';
BRANCHEQ: 'beq';
BRANCHGT: 'bgt';
BRANCHGE: 'bge';
BRANCHLT: 'blt';
BRANCHLE: 'ble';
POP: 'pop' ;
LOADWORD: 'lw';
STOREWORD: 'sw';
PUSH: 'push' ;
ADD: 'add';
SUB: 'sub';
MUL: 'mul';
DIV: 'div';
NEG: 'neg';
LOADINTEGER: 'li';
BRANCH: 'b';

REG: '$a0' | '$t1' | '$fp' | '$ra' | '$hp' | '$sp' | '$al';
COL	 : ':' ;
LABEL	 : ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUMBER	 : '0' | ('-')?(('1'..'9')('0'..'9')*) ;

WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+   -> channel(HIDDEN);

ERR   	 : . { System.err.println("Invalid char: "+ getText()); lexicalErrors++;  } -> channel(HIDDEN); 

