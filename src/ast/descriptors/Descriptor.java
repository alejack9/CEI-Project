package ast.descriptors;

public abstract class Descriptor {
	protected String type;
	protected String id;
	
	protected Descriptor(String id, String type) {
		this.id = id;
		this.type = type;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getType() {
		return this.type;
	}
}
