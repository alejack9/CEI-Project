package util_analysis.entries;

import behavioural_analysis.EEffect;
import util_analysis.Environment;

public class BTEntry implements Entry {
	private EEffect effect;
	private Environment<BTEntry> e0;
	private Environment<BTEntry> e1;
	
	public BTEntry() {
		this(EEffect.BOTTOM);
	}

	public BTEntry(EEffect effect) {
		this.effect = effect;
	}
	public BTEntry(Environment<BTEntry> e0, Environment<BTEntry> e1) {
		this.e0 = e0;
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
		return e0 != null;
	}
	

	public Environment<BTEntry> getE0() {
		return e0;
	}
	
	public Environment<BTEntry> getE1() {
		return e1;
	}
	
	public void setE0(Environment<BTEntry> e0) {
		this.e0 = e0;
	}
	
	public void setE1(Environment<BTEntry> e1) {
		this.e1 = e1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(!(obj instanceof BTEntry)) return false;
		BTEntry casted = (BTEntry) obj;
		if(e0 != null) {
			if(casted.e0 == null || !e0.equals(casted.e0)
				|| !e1.equals(casted.e1)) return false;
		}
		else
			if(effect.compareTo(casted.effect) != 0) return false;
		
		return true;
	}
}
