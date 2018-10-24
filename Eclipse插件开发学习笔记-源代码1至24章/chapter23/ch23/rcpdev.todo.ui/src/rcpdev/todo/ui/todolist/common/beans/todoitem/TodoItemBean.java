package rcpdev.todo.ui.todolist.common.beans.todoitem;

import java.util.Date;

import rcpdev.common.ui.databinding.AbstractBean;
import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.model.series.SeriesInfo;
import rcpdev.todo.core.model.series.TodoSeries;
import rcpdev.todo.core.model.series.info.DayInfo;
import rcpdev.todo.core.model.series.info.MonthInfo;
import rcpdev.todo.core.model.series.info.WeekInfo;

public class TodoItemBean extends AbstractBean {

	public static final String ATTR_TYPE = "type";

	public static final String ATTR_SUBJECT = "subject";

	public static final String ATTR_CONTENT = "content";

	public static final String ATTR_SERIES_TYPE = "seriesType";

	public static final String ATTR_START_DATE = "startDate";

	public static final String ATTR_START_TIME = "startTime";

	public static final String ATTR_STOP_TIME = "stopTime";

	public static final String ATTR_STOP_TYPE = "stopType";

	public static final String ATTR_STOP_DATE = "stopDate";

	public static final String ATTR_BY_DAY_TYPE = "byDayType";

	public static final String ATTR_BY_DAY_COUNT = "byDayCount";

	public static final String ATTR_BY_WEEK_COUNT = "byWeekCount";

	public static final String ATTR_BY_WEEK_DATA = "byWeekData";

	public static final String ATTR_BY_MONTH_COUNT = "byMonthCount";

	public static final String ATTR_BY_MONTH_DAY = "byMonthDay";

	public static final int TYPE_SINGLE = 0;

	public static final int TYPE_SERIES = 1;

	private int type = TYPE_SINGLE;

	private int startTime;

	private int stopTime;

	private String subject;

	private String content;

	private Date startDate;

	public static final int SERIES_TYPE_DAY = 0;

	public static final int SERIES_TYPE_WEEK = 1;

	public static final int SERIES_TYPE_MONTH = 2;

	private int seriesType = SERIES_TYPE_DAY;

	public static final int STOP_TYPE_DATE = 0;

	public static final int STOP_TYPE_NONE = 2;

	private int stopType = STOP_TYPE_DATE;

	private Date stopDate;

	public static final int BY_DAY_TYPE_COUNT = 0;

	public static final int BY_DAY_TYPE_WEEKDAY = 1;

	public static final int BY_DAY_TYPE_WEEKEND = 2;

	private int byDayType = BY_DAY_TYPE_COUNT;

	private int byDayCount;

	private int byWeekCount;

	private int byMonthCount;

	private int byMonthDay;

	private boolean[] byWeekData;

	public TodoItemBean() {
		setStartDate(new Date());
		setStopDate(new Date());
		setStartTime(16);
		setStopTime(17);
		setByDayCount(1);
		setByWeekCount(1);
		setByMonthCount(1);
		setByMonthDay(1);
		setByWeekData(new boolean[7]);
	}

	public TodoItemBean(TodoItem item) {
		this();
		setStartDate(item.getDate());
		setStartTime(item.getStartTime());
		setStopTime(item.getStopTime());
		setSubject(item.getSubject());
		setContent(item.getContent());
		setType(TYPE_SINGLE);
		if (item instanceof TodoSeries) {
			setType(TYPE_SERIES);
			TodoSeries series = (TodoSeries) item;
			setStopDate(series.getStopDate());
			switch (series.getSeriesType()) {
			case TodoSeries.TYPE_DATE:
				setStopType(STOP_TYPE_DATE);
				break;
			case TodoSeries.TYPE_ENDLESS:
				setStopType(STOP_TYPE_NONE);
			}
			if (series.getInfo() instanceof DayInfo) {
				DayInfo info = (DayInfo) series.getInfo();
				setSeriesType(TodoItemBean.SERIES_TYPE_DAY);
				setByDayType(info.getInfoType());
				setByDayCount(info.getInterval());
			}
			if (series.getInfo() instanceof WeekInfo) {
				WeekInfo info = (WeekInfo) series.getInfo();
				setSeriesType(TodoItemBean.SERIES_TYPE_WEEK);
				setByWeekCount(info.getInterval());
				setByWeekData(info.getWeekmap());
			}
			if (series.getInfo() instanceof MonthInfo) {
				MonthInfo info = (MonthInfo) series.getInfo();
				setSeriesType(TodoItemBean.SERIES_TYPE_MONTH);
				setByMonthCount(info.getInterval());
				setByMonthDay(info.getDayOfMonth());
			}

		}
	}

	public TodoItem extractItem() {
		TodoItem newItem = null;
		if (TodoItemBean.TYPE_SINGLE == getType()) {
			// Single Item
			newItem = new TodoItem();
		} else {
			// Series Item
			TodoSeries series = new TodoSeries();
			series.setStopDate(getStopDate());
			switch (getStopType()) {
			case TodoItemBean.STOP_TYPE_DATE:
				series.setSeriesType(TodoSeries.TYPE_DATE);
				break;
			case TodoItemBean.STOP_TYPE_NONE:
				series.setSeriesType(TodoSeries.TYPE_ENDLESS);
				break;
			}
			SeriesInfo info = null;
			switch (getSeriesType()) {
			case TodoItemBean.SERIES_TYPE_DAY:
				DayInfo dayInfo = new DayInfo();
				dayInfo.setInterval(getByDayCount());
				dayInfo.setInfoType(getByDayType());
				info = dayInfo;
				break;
			case TodoItemBean.SERIES_TYPE_WEEK:
				WeekInfo weekInfo = new WeekInfo();
				weekInfo.setInterval(getByWeekCount());
				weekInfo.setWeekmap(getByWeekData());
				info = weekInfo;
				break;
			case TodoItemBean.SERIES_TYPE_MONTH:
				MonthInfo monthInfo = new MonthInfo();
				monthInfo.setInterval(getByMonthCount());
				monthInfo.setDayOfMonth(getByMonthDay());
				info = monthInfo;
				break;
			}
			series.setInfo(info);

			newItem = series;
		}
		newItem.setSubject(getSubject());
		newItem.setContent(getContent());
		newItem.setDate(getStartDate());
		newItem.setStartTime(getStartTime());
		newItem.setStopTime(getStopTime());
		return newItem;
	}

	public boolean[] getByWeekData() {
		return byWeekData;
	}

	public void setByWeekData(boolean[] byWeekData) {
		Object oldValue = getByWeekData();
		this.byWeekData = byWeekData;
		firePropertyChange(ATTR_BY_WEEK_DATA, oldValue, byWeekData);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		int oldType = getType();
		this.type = type;
		firePropertyChange(ATTR_TYPE, oldType, type);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		String oldContent = getContent();
		this.content = content;
		firePropertyChange(ATTR_CONTENT, oldContent, content);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		String oldSubject = getSubject();
		this.subject = subject;
		firePropertyChange(ATTR_SUBJECT, oldSubject, subject);
	}

	public int getSeriesType() {
		return seriesType;
	}

	public void setSeriesType(int seriesType) {
		int oldSt = getSeriesType();
		this.seriesType = seriesType;
		firePropertyChange(ATTR_SERIES_TYPE, oldSt, seriesType);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		Date oldStartDate = getStartDate();
		this.startDate = startDate;
		firePropertyChange(ATTR_START_DATE, oldStartDate, startDate);
		setStopDate(startDate);
	}

	public int getStopType() {
		return stopType;
	}

	public void setStopType(int stopType) {
		int oldType = getStopType();
		this.stopType = stopType;
		firePropertyChange(ATTR_STOP_TYPE, oldType, stopType);
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		Date oldDate = getStopDate();
		this.stopDate = stopDate;
		firePropertyChange(ATTR_STOP_DATE, oldDate, stopDate);
	}

	public int getByDayType() {
		return byDayType;
	}

	public void setByDayType(int byDayType) {
		int oldType = getByDayType();
		this.byDayType = byDayType;
		firePropertyChange(ATTR_BY_DAY_TYPE, oldType, byDayType);
	}

	public int getByDayCount() {
		return byDayCount;
	}

	public void setByDayCount(int byDayCount) {
		int oldCount = getByDayCount();
		this.byDayCount = byDayCount;
		firePropertyChange(ATTR_BY_DAY_COUNT, oldCount, byDayCount);
	}

	public int getByMonthCount() {
		return byMonthCount;
	}

	public void setByMonthCount(int byMonthCount) {
		int oldCount = getByMonthCount();
		this.byMonthCount = byMonthCount;
		firePropertyChange(ATTR_BY_MONTH_COUNT, oldCount, byMonthCount);
	}

	public int getByMonthDay() {
		return byMonthDay;
	}

	public void setByMonthDay(int byMonthDay) {
		int oldDay = getByMonthDay();
		this.byMonthDay = byMonthDay;
		firePropertyChange(ATTR_BY_MONTH_DAY, oldDay, byMonthDay);
	}

	public int getByWeekCount() {
		return byWeekCount;
	}

	public void setByWeekCount(int byWeekCount) {
		int oldCount = getByWeekCount();
		this.byWeekCount = byWeekCount;
		firePropertyChange(ATTR_BY_WEEK_COUNT, oldCount, byWeekCount);
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		int oldStart = getStartTime();
		this.startTime = startTime;
		firePropertyChange(ATTR_START_TIME, oldStart, startTime);
	}

	public int getStopTime() {
		return stopTime;
	}

	public void setStopTime(int stopTime) {
		int oldStop = getStopTime();
		this.stopTime = stopTime;
		firePropertyChange(ATTR_STOP_TIME, oldStop, stopTime);
	}

}
