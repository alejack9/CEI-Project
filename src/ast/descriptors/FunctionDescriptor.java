package ast.descriptors;

import java.util.Collection;

public class FunctionDescriptor extends VariableDescriptor {
	private Collection<ParameterDescriptor> parametersDescriptors;
	
	public FunctionDescriptor(String id, String type, Collection<ParameterDescriptor> parametersDescriptors) {
		super(id, type);
		this.parametersDescriptors = parametersDescriptors;
	}
	
	public Collection<ParameterDescriptor> getParametersDescriptors() {
		return this.parametersDescriptors;
	}
	
	@Override
	public String getType() {
		String sb = "(%)->#";
		
		sb.replace("%", this.parametersDescriptors.stream().map(ParameterDescriptor::getType).reduce((a,b) -> a+","+b).orElse(""));
		sb.replace("#", super.getType());
		
		return sb;
	}
}
