package ast.descriptors;

/**
 * Used to describe a function, a variable or a parameter
 */
public abstract class Descriptor {
	protected String type;
	protected String id;
	
	protected Descriptor(String id, String type) {
		this.id = id;
		this.type = type;
	}
	
	/**
	 * @return The id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * @return The type of the element
	 */
	public String getType() {
		return this.type;
	}
}
