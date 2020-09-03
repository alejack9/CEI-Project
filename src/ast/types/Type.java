package ast.types;

/**
 * The base class for all types.
 */
public abstract class Type implements Cloneable {

	/**
	 * Get the {@link EType EType} value associated to the Type object.
	 *
	 * @return the related EType value
	 */
	public abstract EType getType();

	protected boolean isParameter;

	protected boolean isRef;

	protected Type(boolean isParameter, boolean isRef) {
		this.isParameter = isParameter;
		this.isRef = isRef;
	}

	protected Type() {
		this(false, false);
	}

	/**
	 * Get the byte dimension of the type.
	 *
	 * @return the dimension
	 */
	protected abstract int _getDimension();

	/**
	 * Get the byte dimension of the type. <br>
	 * If the type is related to a variable required by reference, then return 4 as
	 * address dimension.
	 * 
	 * @return the dimension
	 */
	public int getDimension() {
		return isRef ? 4 : _getDimension();
	}

	/**
	 * Get dimension of the referenced type. <br>
	 * 
	 * @return the dimension
	 */
	public int getReferencedDimension() {
		return _getDimension();
	}

	/**
	 * Check if the type is related to a parameter.
	 *
	 * @return true if it's a parameter, false otherwise
	 */
	public boolean isParameter() {
		return this.isParameter;
	}

	/**
	 * Check if the type is related to a parameter required by reference.
	 *
	 * @return true if it's required by reference, false otherwise
	 */
	public boolean isRef() {
		return this.isRef;
	}

	@Override
	public String toString() {
		return this.getType().toString();
	}

	/**
	 * Compare Type object with another object.
	 *
	 * @param e the object to compare
	 * @return true if they are equals, false otherwise
	 */
	@Override
	public boolean equals(Object e) {
		if (e == null)
			return false;
		if (this == e)
			return true;
		if (!(e instanceof Type))
			return false;

		Type casted = (Type) e;
		if (casted.getType().compareTo(getType()) != 0)
			return false;
		if (isParameter != casted.isParameter || isRef != casted.isRef)
			return false;

		return true;
	}

	@Override
	public Object clone() {
		return getType().getType(isParameter, isRef);
	}

}
