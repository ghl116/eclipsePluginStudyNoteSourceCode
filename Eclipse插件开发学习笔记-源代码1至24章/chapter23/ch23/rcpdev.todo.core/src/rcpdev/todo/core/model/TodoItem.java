package rcpdev.todo.core.model;

import java.util.Date;

import rcpdev.common.core.utils.DateUtils;
import rcpdev.todo.core.model.series.TodoSeries;

public class TodoItem implements Cloneable {

	private String id;

	private String subject;

	private String content;

	private boolean enableAlert;

	private int alertTime;

	private Date date;

	private int startTime;

	private int stopTime;

	private TodoSeries series;

	public TodoItem() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TodoItem fitDate(Date date) {
		if (date.equals(getDate()))
			return this;
		return null;
	}

	public int getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(int alertTime) {
		this.alertTime = alertTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = DateUtils.truncToDay(date);
	}

	public boolean isEnableAlert() {
		return enableAlert;
	}

	public void setEnableAlert(boolean enableAlert) {
		this.enableAlert = enableAlert;
	}

	public TodoSeries getSeries() {
		return series;
	}

	public void setSeries(TodoSeries series) {
		this.series = series;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getStopTime() {
		return stopTime;
	}

	public void setStopTime(int stopTime) {
		this.stopTime = stopTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void copy(TodoItem from) {
		this.setSubject(from.getSubject());
		this.setContent(from.getContent());
		this.setDate(from.getDate());
		this.setStartTime(from.getStartTime());
		this.setStopTime(from.getStopTime());
	}

	public boolean equals(Object object) {
		if (!(object instanceof TodoItem))
			return false;
		if (((TodoItem) object).getId() == null || getId() == null)
			return super.equals(object);
		return getId().equals(((TodoItem) object).getId());
	}

	public int hashCode() {
		if (getId() == null)
			return 37;
		return getId().hashCode();
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
