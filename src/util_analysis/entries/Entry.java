package util_analysis.entries;

import java.util.List;

import behavioural_analysis.EEffect;
import support.MyCloneable;

public abstract class Entry implements MyCloneable {
	protected boolean deleted = false;

	public abstract boolean IsFunction();
	
	protected abstract boolean _equals(Object obj);
	
	@Override
	public final boolean equals(Object obj) {
		return _equals(obj)
				&& ((Entry)obj).deleted == deleted;
	}
	
//	protected abstract Entry _clone();
	
	@Override
	public abstract Object clone();
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public boolean isDeleted() {
		return this.deleted;
	}

}
