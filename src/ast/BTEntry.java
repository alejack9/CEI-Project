package ast;

import behavioural_analysis.EEffect;
import support.MyCloneable;

public class BTEntry implements MyCloneable {
	private EEffect effect;
	
	public BTEntry() {
		this(EEffect.BOTTOM);
	}

	public BTEntry(EEffect effect) {
		this.effect = effect;
	}
	
	public EEffect getEffect() {
		return this.effect;
	}
	
	public Object clone() {
		return new BTEntry(effect);
	}
}
