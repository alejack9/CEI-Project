package ast;

public class STEntry {
	private SPElementBase type;
	private int nestingLevel;
	private int offset;
	
	public STEntry(SPElementBase type, int nestingLevel, int offset) {
		this.type = type;
		this.nestingLevel = nestingLevel;
		this.offset = offset;
	}
	
	public SPElementBase getType() {
		return type;
	}
	public int getNestingLevel() {
		return nestingLevel;
	}
	public int getOffset() {
		return offset;
	}
}
