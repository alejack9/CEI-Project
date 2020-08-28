package behavioural_analysis;

import util_analysis.Environment;
import util_analysis.entries.BTEntry;

/**
 * Support class for code behavioural analysis.
 * It contains operations on Effects Set.
 *
 */
public class BTHelper {

	private BTHelper() {
	}

	/**
	 * Compares two effects.
	 * @param a first effect
	 * @param b second effect
	 * @return maximum of the two effects
	 */
	public static EEffect max(EEffect a, EEffect b) {
		return a.compareTo(b) > 0 ? a : b;
	}

	/**
	 * Applies <i>seq</i> rule to calculate final effect after two consecutive effects on the same ID.
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
	 * Applies <i>par</i> rule to handle effects order and check parameters aliasing.
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
	 * Handles ref parameter effect after function invocation.
	 * It calculates seq effect between the variable effect before function invocation and after that.
	 * 
	 * @param a the variable and its effect in the environment
	 * @param b the variable and its effect in the function body
	 * @return final effect 
	 */
	public static EEffect invocationSeq(BTEntry a, BTEntry b) {
		return b != null ? BTHelper.seq(a.getLocalEffect(), b.getRefEffect()) : a.getLocalEffect();
	}

	/**
	 * For all variable in Environment <b>e</b>, it updates its effect with the max between actual value and the correspondent in <b>tempE</b>  
	 * @param e environment to update
	 * @param tempE environment with new effects
	 */
	public static void maxModifyEnv(Environment<BTEntry> e, Environment<BTEntry> tempE) {
		e.getAllIDs().entrySet().stream().filter(k -> !k.getValue().isFunction())
				.forEach(en -> e.getIDEntry(en.getKey()).setLocalEffect(
						BTHelper.max(en.getValue().getLocalEffect(), tempE.getIDEntry(en.getKey()).getLocalEffect())));
	}
}
