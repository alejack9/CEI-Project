package util_analysis;

import ast.STEntry;

public interface Environment {
	public boolean addOrUpdate(String id, STEntry symTableEntry);
	public boolean add(String id, STEntry symTableEntry);
	public void openScope();
	public void closeScope();
	public boolean containsID(String id);
	public boolean containsIDLocal(String id);
	public STEntry deleteVariable(String id);
	public STEntry getIDEntry(String id);
	public STEntry getLocalIDEntry(String id);
	public int getNestingLevel();
	public int getOffset();
}
