package util_analysis.entries;

import java.util.LinkedList;
import java.util.List;

import behavioural_analysis.EEffect;

public class BTEntry extends Entry {
	private List<EEffect> effects = new LinkedList<EEffect>();
	private List<BTEntry> e1;

	public BTEntry() {
		addEffect(EEffect.BOTTOM);
	}

	public BTEntry(List<BTEntry> e1) {
		this.e1 = e1;
	}

	public EEffect getLocalEffect() {
		return effects.get(effects.size() - 1);
	}

	public EEffect getRefEffect() {
		return effects.get(0);
	}

	@Override
	public boolean isFunction() {
		return e1 != null;
	}

	public List<BTEntry> getE1() {
		return e1;
	}

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
