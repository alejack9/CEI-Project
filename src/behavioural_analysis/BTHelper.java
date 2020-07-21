package behavioural_analysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import util_analysis.Environment;
import util_analysis.entries.BTEntry;

public class BTHelper {
	
	private BTHelper() { }
	
	public static EEffect max(EEffect a, EEffect b) {
		return a.compareTo(b) > 0 ? a : b;
	}
	
	public static EEffect seq(EEffect a, EEffect b) {
		EEffect max = BTHelper.max(a, b);
		
		if(max.compareTo(EEffect.RW) <= 0) return max;
		
		if(a.compareTo(EEffect.RW) <= 0 && b.compareTo(EEffect.D) == 0
				|| a.compareTo(EEffect.D) == 0 && b.compareTo(EEffect.BOTTOM) == 0)
			return EEffect.D;
		
		return EEffect.T;
	}
	
	public static EEffect par(EEffect a, EEffect b) {
		if(b.compareTo(EEffect.BOTTOM) == 0) return a;
		if(a.compareTo(EEffect.BOTTOM) == 0) return b;
		if(a == b && b.compareTo(EEffect.RW) == 0) return EEffect.RW;
		return EEffect.T;
	}
	
	public static EEffect par(BTEntry a, BTEntry b) {
		if(a == null) return b.getEffect();
		if(b == null) return a.getEffect();
		return par(a.getEffect(), b.getEffect());
	}
	
	public static EEffect seq(BTEntry a, BTEntry b) {
		return b != null ? BTHelper.seq(a.getEffect(), b.getEffect()) : a.getEffect();
	}
	
	public static EEffect max(BTEntry a, BTEntry b) {
		return b != null ?
				a.getEffect().compareTo(b.getEffect()) > 0 ?
						a.getEffect()
						: b.getEffect()
				: a.getEffect();
	}

	public static void maxModifyEnv(Environment<BTEntry> e, Environment<BTEntry> tempE) {
		e.getAllIDs().entrySet().stream().filter(k -> !k.getValue().IsFunction())
			.forEach(en -> e.update(
					en.getKey(),
					new BTEntry(BTHelper.max(
							en.getValue().getEffect(),
							tempE.getIDEntry(en.getKey()).getEffect()
		))));
	}
}
