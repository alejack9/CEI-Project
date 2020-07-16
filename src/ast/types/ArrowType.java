package ast.types;

import java.util.List;

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

	public void setParamTypes(List<Type> paramTypes) {
		this.paramTypes = paramTypes;
	}

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
}
