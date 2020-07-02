package util_analysis;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;

public class TypeErrorsStorage {
	
	private static List<TypeError> storage = new LinkedList<TypeError>();
	
	private TypeErrorsStorage() {}
	
	public static void addError(TypeError e) {
		storage.add(e);
	}
	
	public static List<TypeError> getTypeErrors(){
		return storage;
	}
}
