package ast;

public abstract class SPExpBin extends SPExp {
	
	protected SPExp leftSide, rightSide;
	
	protected SPExpBin(SPExp left, SPExp right) {
		this.leftSide = left;
		this.rightSide = right;
	}
}
