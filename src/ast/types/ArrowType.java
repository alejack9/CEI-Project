package ast.types;

import java.util.List;

public class ArrowType extends Type {
	private List<Type> paramTypes;
	private Type retType;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EType getType() {
		return EType.FUNCTION;
	}

	public List<Type> getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(List<Type> paramTypes) {
		this.paramTypes = paramTypes;
	}

	public Type getRetType() {
		return retType;
	}

	public void setRetType(Type retType) {
		this.retType = retType;
	}
}
