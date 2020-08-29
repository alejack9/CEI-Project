package ast.types;

import java.util.List;

/**
 * Represents function type
 */
public class ArrowType extends Type {

	private List<Type> paramTypes;

	private Type retType;

	@Override
	public EType getType() {
		return EType.FUNCTION;
	}

	public List<Type> getParamTypes() {
		return paramTypes;
	}

	public Type getReturnType() {
		return retType;
	}

	/**
	 * Sets the types of function parameters
	 *
	 * @param paramTypes the parameters types
	 */
	public void setParamTypes(List<Type> paramTypes) {
		this.paramTypes = paramTypes;
	}

	/**
	 * Sets the function return type
	 *
	 * @param retType the return type
	 */
	public void setRetType(Type retType) {
		this.retType = retType;
	}

	@Override
	public Object clone() {
		ArrowType toRet = (ArrowType) EType.FUNCTION.getType(isParameter, isRef);
		toRet.setParamTypes(paramTypes);
		toRet.setRetType(retType);
		return toRet;
	}

	@Override
	public int _getDimension() {
		return 0;
	}
}
