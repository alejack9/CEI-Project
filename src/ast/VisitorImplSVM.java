package ast;

import java.util.HashMap;
import parser.*;

public class VisitorImplSVM extends SVMBaseVisitor<Void> {	
	
    private int[] code = new int[10000];    
    private int i = 0;
    private HashMap<String,Integer> labelsIndexes = new HashMap<String,Integer>();
    private HashMap<Integer,String> labelRef = new HashMap<Integer,String>();
    
    public int[] getCode() {
    	return code;
    }
    
    @Override 
    public Void visitAssembly(SVMParser.AssemblyContext ctx) { 
    	visitChildren(ctx);
    	for (Integer refAdd: labelRef.keySet()) {
            code[refAdd]=labelsIndexes.get(labelRef.get(refAdd));
    	}
    	return null;
    }
    
    @Override 
    public Void visitInstruction(SVMParser.InstructionContext ctx) { 
    	switch (ctx.getStart().getType()) {
    		
    		case SVMLexer.LOADINTEGER:
    			code[i++] = SVMParser.LOADINTEGER;
    			code[i++] = getRegIndex(ctx.REG(0).getText());
    			code[i++] = Integer.parseInt(ctx.NUMBER().getText());
    			break;
    	
    		case SVMLexer.BRANCH:
				code[i++] = SVMParser.BRANCH;
	            labelRef.put(i++,ctx.LABEL().getText());
				break;
			
    		case SVMLexer.LABEL:
				labelsIndexes.put(ctx.LABEL().getText(),i);
				break;
				
			case SVMLexer.PUSH:
				code[i++] = SVMParser.PUSH;
				code[i++] = getRegIndex(ctx.REG(0).getText());
				break;
				
    		case SVMLexer.LOADWORD:
				code[i++] = SVMParser.LOADWORD;
				code[i++] = SVMParser.STOREWORD;
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = Integer.parseInt(ctx.NUMBER().getText());
				code[i++] = getRegIndex(ctx.r2.getText());
				break;
			
			case SVMLexer.STOREWORD:
				code[i++] = SVMParser.STOREWORD;
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = Integer.parseInt(ctx.NUMBER().getText());
				code[i++] = getRegIndex(ctx.r2.getText());
				break;
				
			case SVMLexer.ADD:
				code[i++] = SVMParser.ADD;
				code[i++] = getRegIndex(ctx.dest.getText());
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = getRegIndex(ctx.r2.getText());
				break;
			case SVMLexer.SUB:
				code[i++] = SVMParser.SUB;
				code[i++] = getRegIndex(ctx.dest.getText());
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = getRegIndex(ctx.r2.getText());
				break;
			case SVMLexer.MUL:
				code[i++] = SVMParser.MUL;
				code[i++] = getRegIndex(ctx.dest.getText());
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = getRegIndex(ctx.r2.getText());
				break;
			case SVMLexer.DIV:
				code[i++] = SVMParser.DIV;
				code[i++] = getRegIndex(ctx.dest.getText());
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = getRegIndex(ctx.r2.getText());
				break;
			case SVMLexer.POP:
				code[i++] = SVMParser.POP;
				break;
				
			case SVMLexer.BRANCHEQ:
				code[i++] = SVMParser.BRANCHEQ;
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = getRegIndex(ctx.r2.getText());
                labelRef.put(i++,ctx.LABEL().getText());
				break;	
				
			case SVMLexer.BRANCHGT:
				code[i++] = SVMParser.BRANCHGT;
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = getRegIndex(ctx.r2.getText());
                labelRef.put(i++,ctx.LABEL().getText());
                break;
			
			case SVMLexer.BRANCHGE:
				code[i++] = SVMParser.BRANCHGE;
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = getRegIndex(ctx.r2.getText());
                labelRef.put(i++,ctx.LABEL().getText());
                break;
			
			case SVMLexer.BRANCHLT:
				code[i++] = SVMParser.BRANCHLT;
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = getRegIndex(ctx.r2.getText());
                labelRef.put(i++,ctx.LABEL().getText());
                break;
			
			case SVMLexer.BRANCHLE:
				code[i++] = SVMParser.BRANCHLE;
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = getRegIndex(ctx.r2.getText());
                labelRef.put(i++,ctx.LABEL().getText());
                break;
                
			case SVMLexer.NEG:
				code[i++] = SVMParser.NEG;
				code[i++] = getRegIndex(ctx.REG(0).getText());
                break;
                
			case SVMLexer.MOVE:
				code[i++] = SVMParser.MOVE;
				code[i++] = getRegIndex(ctx.dest.getText());
				code[i++] = getRegIndex(ctx.origin.getText());
                break;
                
			case SVMLexer.ADDINTEGER:
				code[i++] = SVMParser.ADDINTEGER;
				code[i++] = getRegIndex(ctx.dest.getText());
				code[i++] = getRegIndex(ctx.r1.getText());
				code[i++] = Integer.parseInt(ctx.NUMBER().getText());
                break;
                
			case SVMLexer.JUMPLABEL:
				code[i++] = SVMParser.JUMPLABEL;
				labelRef.put(i++, ctx.LABEL().getText());
                break;
                
			case SVMLexer.JUMPREG:
				code[i++] = SVMParser.JUMPREG;
                break;
                
			case SVMLexer.PRINT:
				code[i++] = SVMParser.PRINT;
                break;
                
			default:
	            break;	
    	}
    	return null;
    }
    
    private int getRegIndex(String reg) {
    	switch(reg) {
    	case "$a0":
    		return 0;
    	case "$t1":
    		return 1;
    	case "$fp":
    		return 2;
    	case "$ra":
    		return 3;
    	case "$hp":
    		return 4;
    	case "$sp":
    		return 5;
    	case "$al":
    		return 6;
    	default:
    		return -1;
    	}
    	}
}
