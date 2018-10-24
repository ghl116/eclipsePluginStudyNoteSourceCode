package rcpdev.todo.core.model.series;

import java.util.Date;

public abstract class SeriesInfo implements Cloneable {

	private String id;

	private Date baseDate;

	private int interval;

	private SeriesInfo next;

	public SeriesInfo() {
		super();
		interval = 1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBaseDate() {
		return baseDate;
	}

	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public SeriesInfo getNext() {
		return next;
	}

	public void setNext(SeriesInfo next) {
		this.next = next;
	}

	protected abstract boolean fit(Date date);

	public boolean fitDate(Date date) {
		SeriesInfo info = this;
		while (info != null) {
			if (!info.fit(date))
				return false;
			info = info.getNext();
		}
		return true;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
