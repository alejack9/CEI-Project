package behavioural_analysis;

import util_analysis.Environment;
import util_analysis.entries.BTEntry;

public class BTHelper {

	private BTHelper() {
	}

	public static EEffect max(EEffect a, EEffect b) {
		return a.compareTo(b) > 0 ? a : b;
	}

	public static EEffect seq(EEffect a, EEffect b) {
		EEffect max = BTHelper.max(a, b);

		if (max.compareTo(EEffect.RW) <= 0)
			return max;

		if (a.compareTo(EEffect.RW) <= 0 && b.compareTo(EEffect.D) == 0
				|| a.compareTo(EEffect.D) == 0 && b.compareTo(EEffect.BOTTOM) == 0)
			return EEffect.D;

		return EEffect.T;
	}

	public static EEffect par(EEffect a, EEffect b) {
		if (b.compareTo(EEffect.BOTTOM) == 0)
			return a;
		if (a.compareTo(EEffect.BOTTOM) == 0)
			return b;
		if (a == b && b.compareTo(EEffect.RW) == 0)
			return EEffect.RW;
		return EEffect.T;
	}

	public static EEffect invocationSeq(BTEntry a, BTEntry b) {
		return b != null ? BTHelper.seq(a.getLocalEffect(), b.getRefEffect()) : a.getLocalEffect();
	}

	public static void maxModifyEnv(Environment<BTEntry> e, Environment<BTEntry> tempE) {
		e.getAllIDs().entrySet().stream().filter(k -> !k.getValue().isFunction())
				.forEach(en -> e.getIDEntry(en.getKey()).setLocalEffect(
						BTHelper.max(en.getValue().getLocalEffect(), tempE.getIDEntry(en.getKey()).getLocalEffect())));
	}
}
