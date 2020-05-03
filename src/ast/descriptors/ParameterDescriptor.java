package ast.descriptors;

public class ParameterDescriptor extends VariableDescriptor {
	boolean var;
	
	public ParameterDescriptor(VariableDescriptor vd, boolean var) {
		super(vd.getId(), vd.getType());
		this.var = var;
	}
	
	public boolean isVar() {
		return this.var;
	}
}
