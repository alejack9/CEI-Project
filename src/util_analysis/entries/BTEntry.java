package util_analysis.entries;

import java.util.List;

import behavioural_analysis.EEffect;
import util_analysis.Environment;

public class BTEntry implements Entry {
	private EEffect effect;
	private List<BTEntry> e1;
	
	public BTEntry() {
		this(EEffect.BOTTOM);
	}

	public BTEntry(EEffect effect) {
		this.effect = effect;
	}
	
	public BTEntry(List<BTEntry> e1) {
		this.e1 = e1;
	}
	
	public EEffect getEffect() {
		return this.effect;
	}
	
	public Object clone() {
		return new BTEntry(effect);
	}

	@Override
	public boolean IsFunction() {
		return e1 != null;
	}
	
	public List<BTEntry> getE1() {
		return e1;
	}

	public void setE1(List<BTEntry> e1) {
		this.e1 = e1;
	}
	
	public static boolean areEqual(List<BTEntry> e1, List<BTEntry> e1_1) {
		if(e1.size() != e1_1.size()) return false;
		for(int i = 0; i < e1.size(); i++)
			if(!e1.get(i).equals(e1_1.get(i))) return false;
		
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(!(obj instanceof BTEntry)) return false;
		BTEntry casted = (BTEntry) obj;
		if(e1 != null) {
			if(casted.e1 == null || !areEqual(e1, casted.e1)) return false;
		}
		else
			if(effect.compareTo(casted.effect) != 0) return false;
		
		return true;
	}
}
