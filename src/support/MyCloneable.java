package support;

public interface MyCloneable extends Cloneable {
	/**
	 * Clone the object that invoke it
	 * @return object cloned
	 */
	public Object clone();
}
