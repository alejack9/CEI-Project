package support;

public interface MyCloneable extends Cloneable {
	/**
	 * Clone the object that invoke it
	 * @return object - the cloned object
	 */
	public Object clone();
}
