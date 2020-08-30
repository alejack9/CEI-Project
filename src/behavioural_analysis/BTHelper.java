package behavioural_analysis;

import util_analysis.Environment;
import util_analysis.entries.BTEntry;

/**
 * Support class for code behavioural analysis. <br>
 * It contains operations on effects.
 */
public class BTHelper {

	private BTHelper() {
	}

	/**
	 * Compare two effects.
	 * 
	 * @param a first effect
	 * @param b second effect
	 * @return maximum of the two effects
	 */
	public static EEffect max(EEffect a, EEffect b) {
		return a.compareTo(b) > 0 ? a : b;
	}

	/**
	 * Apply <code>seq</code> rule to calculate final effect after two consecutive
	 * effects.
	 * 
	 * @param a first effect
	 * @param b second effect
	 * @return final effect
	 */
	public static EEffect seq(EEffect a, EEffect b) {
		EEffect max = BTHelper.max(a, b);

		if (max.compareTo(EEffect.RW) <= 0)
			return max;

		if (a.compareTo(EEffect.RW) <= 0 && b.compareTo(EEffect.D) == 0
				|| a.compareTo(EEffect.D) == 0 && b.compareTo(EEffect.BOTTOM) == 0)
			return EEffect.D;

		return EEffect.T;
	}

	/**
	 * Apply <code>par</code> rule between two effects.
	 * 
	 * @param a first effects
	 * @param b second effect
	 * @return final effect
	 */
	public static EEffect par(EEffect a, EEffect b) {
		if (b.compareTo(EEffect.BOTTOM) == 0)
			return a;
		if (a.compareTo(EEffect.BOTTOM) == 0)
			return b;
		if (a == b && b.compareTo(EEffect.RW) == 0)
			return EEffect.RW;
		return EEffect.T;
	}

	/**
	 * Handle referenced parameter effect after function invocation. <br>
	 * It calculates <code>seq</code> effect between the variable effect before
	 * function invocation and after that.
	 * 
	 * @param a the effect of the variable before the function invocation
	 * @param b the effect of the variable after the function invocation
	 * @return final effect
	 */
	public static EEffect invocationSeq(BTEntry a, BTEntry b) {
		return b != null ? BTHelper.seq(a.getLocalEffect(), b.getRefEffect()) : a.getLocalEffect();
	}

	/**
	 * For all variable in Environment <b>e</b>, it updates its effect with the max
	 * between current value and the correspondent in <b>tempE</b>
	 * 
	 * @param e     environment to update
	 * @param tempE environment with new effects
	 */
	public static void maxModifyEnv(Environment<BTEntry> e, Environment<BTEntry> tempE) {
		e.getAllIDs().entrySet().stream().filter(k -> !k.getValue().isFunction())
				.forEach(en -> e.getIDEntry(en.getKey()).setLocalEffect(
						BTHelper.max(en.getValue().getLocalEffect(), tempE.getIDEntry(en.getKey()).getLocalEffect())));
	}
}
