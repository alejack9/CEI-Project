package util_analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import util_analysis.entries.Entry;

public class EnvironmentFun<T extends Entry> implements Environment<T> {

	Environment<T> env;
	
	public EnvironmentFun(Environment<T> e) { env = e; }
	
	@Override
	public boolean add(String id, T entry) {
		return env.add(id, entry);
	}

	@Override
	public T update(String id, T entry) {
		return env.update(id, entry);
	}

	@Override
	public void openScope() {
		env.openScope();
	}

	@Override
	public void closeScope() {
		env.closeScope();
	}

	@Override
	public boolean containsID(String id) {
		return getIDEntry(id) != null;
	}

	@Override
	public boolean containsIDLocal(String id) {
		return getLocalIDEntry(id) != null;
	}

	@Override
	public T deleteVariable(String id) {
		return env.deleteVariable(id);
	}

	private boolean isFun(T entry) {
		return entry != null &&
				(entry.IsFunction());
	}
	

	
	@Override
	public T getIDEntry(String id) {
		T entry = env.getIDEntry(id);
		if(isFun(entry))
			return entry;
		return null;
	}

	@Override
	public T getLocalIDEntry(String id) {
		T entry = env.getLocalIDEntry(id);
		if(isFun(entry) )
			return entry;
		return null;
	}

	@Override
	public int getNestingLevel() {
		return env.getNestingLevel();
	}

	@Override
	public int getOffset() {
		return env.getOffset();
	}

	@Override
	public Map<String, T> getAllVariables() {
		return env.getAllVariables().entrySet().stream()
				.filter(x -> isFun(x.getValue()))
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
	}

	@Override
	public Environment<T> getCurrentScopeEnv() {
//		return new EnvironmentFun<T>(env.getCurrentScopeEnv());
		return env.getCurrentScopeEnv();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		return new EnvironmentFun<T>((Environment<T>) env.clone());
	}

	@Override
	public void addScope(Map<String, T> currentScope) {
		env.addScope(currentScope);
	}

	@Override
	public Map<String, T> getCurrentScope() {
		return env.getCurrentScope();
	}

	@Override
	public LinkedList<HashMap<String, T>> getAllFunctions() {
		// TODO Auto-generated method stub
		return null;
	}
}
