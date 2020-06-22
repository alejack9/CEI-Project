package util_analysis;

import ast.STEntry;

public interface Environment {
	public void addVariable(String id, STEntry symTableEntry);
	public void openScope();
	public void closeScope();
	public boolean containsVariable(String id);
	public void deleteVariable(String id);
	public STEntry getIDEntry(String id);
	public STEntry getLocalIDEntry(String id);
}
