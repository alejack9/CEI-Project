package ast;

public abstract class SPExpBin extends SPExp {
	
	protected SPExp leftSide, rightSide;

	protected abstract String getOp();
	
	protected SPExpBin(SPExp left, SPExp right) {
		this.leftSide = left;
		this.rightSide = right;
	}
}
