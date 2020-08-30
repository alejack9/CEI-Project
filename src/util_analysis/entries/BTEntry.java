package util_analysis.entries;

import java.util.LinkedList;
import java.util.List;

import behavioural_analysis.EEffect;

/**
 * Behaviour table entry.
 */
public class BTEntry extends Entry {
	
	/**
	 * Effects list on an ID, it contains max two values (if it's a declared variable and not a function or a simple parameter).
	 * <br> <br>
	 * If the variable is a parameter passed by reference, the first
	 * value represents variable effect before the invocation
	 * and the second is the effect created by function body.
	 */
	private List<EEffect> effects = new LinkedList<EEffect>();
	
	/** 
	 * List of effects created by function body on function parameters.
	 * <br> <br>
	 * This is set only if the entry is related to a function.
	 * */
	private List<BTEntry> e1;

	/**
	 * It's created by default a BOTTOM value, then at the time of a declaration it's added another local effect.
	 */
	public BTEntry() {
		addEffect(EEffect.BOTTOM);
	}

	/**
	 * Constructor for a entry related to a function.
	 * @param e1 effects on parameters
	 */
	public BTEntry(List<BTEntry> e1) {
		this.e1 = e1;
	}

	/**
	 * Gets actual entry effect.
	 * @return the local effect
	 */
	public EEffect getLocalEffect() {
		return effects.get(effects.size() - 1);
	}

	/**
	 * Gets effect of variable passed by reference (the effect before function call).
	 * @return the ref effect
	 */
	public EEffect getRefEffect() {
		return effects.get(0);
	}

	@Override
	public boolean isFunction() {
		return e1 != null;
	}

	/**
	 * Gets the e1.
	 *
	 * @return the e1
	 */
	public List<BTEntry> getE1() {
		return e1;
	}

	/**
	 * Are equal.
	 *
	 * @param e1 the e 1
	 * @param e1_1 the e 1 1
	 * @return true, if successful
	 */
	public static boolean areEqual(List<BTEntry> e1, List<BTEntry> e1_1) {
		if (e1.size() != e1_1.size())
			return false;
		for (int i = 0; i < e1.size(); i++)
			if (e1.get(i).getRefEffect().compareTo(e1_1.get(i).getRefEffect()) != 0)
				return false;

		return true;
	}

	@Override
	public boolean _equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof BTEntry))
			return false;
		BTEntry casted = (BTEntry) obj;
		if (e1 != null) {
			if (casted.e1 == null || !areEqual(e1, casted.e1))
				return false;
		} else
			for (int i = 0; i < effects.size(); i++)
				if (effects.get(i).compareTo(casted.effects.get(i)) != 0)
					return false;

		return true;
	}

	@Override
	public Object clone() {
		BTEntry toRet = new BTEntry();
		toRet.deleted = this.deleted;

		if (e1 == null) {
			toRet.effects = new LinkedList<EEffect>();
			for (EEffect e : effects)
				toRet.effects.add(e);
		} else {
			toRet.e1 = new LinkedList<BTEntry>();
			for (BTEntry e : e1)
				toRet.e1.add((BTEntry) e.clone());
		}
		return toRet;
	}

	public void setLocalEffect(EEffect seq) {
		effects.set(effects.size() - 1, seq);
	}

	public void addEffect(EEffect effect) {
		this.effects.add(effect);
	}
}
