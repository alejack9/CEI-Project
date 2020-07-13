package ast;

import behavioural_analysis.EEffect;

public class BTEntry {
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
}
