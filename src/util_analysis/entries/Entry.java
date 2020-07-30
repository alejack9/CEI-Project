package util_analysis.entries;

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
	
	@Override
	public abstract Object clone();
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public boolean isDeleted() {
		return this.deleted;
	}

}
