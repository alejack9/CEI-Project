package util_analysis;

import ast.STEntry;

public interface Environment {
	public void addID(String id, STEntry symTableEntry);
	public void openScope();
	public void closeScope();
	public boolean containsID(String id);
	public boolean containsIDLocal(String id);
	public void deleteVariable(String id);
	public STEntry getIDEntry(String id);
	public STEntry getLocalIDEntry(String id);
	public int getNestingLevel();
}
