package util_analysis.entries;

import support.MyCloneable;

public abstract class Entry implements MyCloneable {
	protected boolean deleted = false;

	protected abstract boolean _equals(Object obj);

	public abstract boolean isFunction();

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return this.deleted;
	}

	@Override
	public final boolean equals(Object obj) {
		return _equals(obj) && ((Entry) obj).deleted == deleted;
	}

	@Override
	public abstract Object clone();

}
