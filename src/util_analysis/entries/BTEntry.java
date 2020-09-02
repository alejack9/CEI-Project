package util_analysis.entries;

import java.util.LinkedList;
import java.util.List;

import behavioural_analysis.EEffect;

/**
 * Behaviour table entry.
 */
public class BTEntry extends Entry {

	/**
	 * Effects list of an ID. It is used to store multiple effects of the same
	 * variable in order to keep the variable even if it has been deleted.<br>
	 * Every time the variable is declared, a new bottom effect is added. The last
	 * effect indicates the "local" effect.
	 */
	private List<EEffect> effects = new LinkedList<EEffect>();

	/**
	 * Sigma1<br>
	 * This field is filled only if the entry is related to a function.
	 */
	private List<BTEntry> e1;

	/**
	 * It's created by default a BOTTOM value
	 */
	public BTEntry() {
		addEffect(EEffect.BOTTOM);
	}

	/**
	 * Constructor for a entry related to a function.
	 * 
	 * @param e1 Sigma1
	 */
	public BTEntry(List<BTEntry> e1) {
		this.e1 = e1;
	}

	/**
	 * Return current local entry effect (the last one of the list).
	 * 
	 * @return the local effect
	 */
	public EEffect getLocalEffect() {
		return effects.get(effects.size() - 1);
	}

	/**
	 * Return first effect of variable required by reference (the effect that the
	 * function call perform).
	 * 
	 * @return the ref effect
	 */
	public EEffect getRefEffect() {
		return effects.get(0);
	}

	/**
	 * Get the sigma1.
	 *
	 * @return the e1
	 */
	public List<BTEntry> getE1() {
		return e1;
	}

	@Override
	public boolean isFunction() {
		return e1 != null;
	}

	public void setLocalEffect(EEffect seq) {
		effects.set(effects.size() - 1, seq);
	}

	public void addEffect(EEffect effect) {
		this.effects.add(effect);
	}

	/**
	 * Check if two environments are equal.
	 *
	 * @param e1   the sigma1
	 * @param e1_1 the sigma1'
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
}
