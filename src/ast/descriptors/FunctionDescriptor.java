package ast.descriptors;

import java.util.List;

public class FunctionDescriptor extends VariableDescriptor {
	
	private List<ParameterDescriptor> parametersDescriptors;
	
	public FunctionDescriptor(String id, String type, List<ParameterDescriptor> parametersDescriptors) {
		super(id, type);
		this.parametersDescriptors = parametersDescriptors;
	}
	
	public List<ParameterDescriptor> getParametersDescriptors() {
		return this.parametersDescriptors;
	}
	
	/**
	 * @return The function type following the "(param1,param2,...)->returnType" pattern
	 */
	@Override
	public String getType() {
		// setup the pattern
		String sb = "(%)->" + super.getType();
		
		// filling the pattern with parameters' type
		sb.replace("%",
				// returns a string representing parameters type as "par1,par2,..."
				parametersDescriptors.stream().map(ParameterDescriptor::getType).reduce((a,b) -> a + "," + b).orElse(""));
		
		return sb;
	}
}
