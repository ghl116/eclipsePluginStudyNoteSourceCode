package rcpdev.todo.core.model.series;

import java.util.Date;
import java.util.Vector;

import rcpdev.todo.core.model.TodoItem;

public class TodoSeries extends TodoItem {

	public static final int TYPE_ENDLESS = 0;

	public static final int TYPE_DATE = 1;

	private int seriesType;

	private Date stopDate;

	private Vector<TodoItem> items;

	private SeriesInfo info;

	public TodoSeries() {
		super();
		items = new Vector<TodoItem>();
	}

	public int getSeriesType() {
		return seriesType;
	}

	public void setSeriesType(int seriesType) {
		this.seriesType = seriesType;
	}

	@Override
	public TodoItem fitDate(Date date) {
		int itemLength = items.size();
		for (int i = 0; i < itemLength; i++) {
			if (items.get(i).fitDate(date) != null)
				return items.get(i);
		}
		if (date.compareTo(getDate()) < 0)
			return null;
		switch (seriesType) {
		case TYPE_ENDLESS:
			break;
		case TYPE_DATE:
			if (date.compareTo(getStopDate()) > 0)
				return null;
		}
		if (getInfo().fitDate(date))
			return this;
		return null;
	}

	public SeriesInfo getInfo() {
		return info;
	}

	public void setInfo(SeriesInfo info) {
		this.info = info;
		info.setBaseDate(getDate());
	}

	public Vector<TodoItem> getItems() {
		return items;
	}

	// TODO
	public void addItem(TodoItem item) {
		item.setSeries(this);
		items.add(item);
	}

	// TODO
	public void removeItem(TodoItem item) {
		item.setSeries(null);
		items.remove(item);
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	@Override
	public void copy(TodoItem source) {
		assert source instanceof TodoSeries;
		super.copy(source);
		TodoSeries sSer = (TodoSeries) source;
		setSeriesType(sSer.getSeriesType());
		setStopDate(sSer.getStopDate());
		setInfo(sSer.getInfo());
	}

}
