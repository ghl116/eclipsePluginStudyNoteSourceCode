package rcpdev.todo.core.model.series.info;

import java.util.Calendar;
import java.util.Date;

import rcpdev.todo.core.model.series.SeriesInfo;

public class MonthInfo extends SeriesInfo {

	private int dayOfMonth;

	private int weekOfMonth;

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public int getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setWeekOfMonth(int weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}

	@Override
	protected boolean fit(Date date) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(getBaseDate());

		int startYear = cal.get(Calendar.YEAR);
		int startMonth = cal.get(Calendar.MONTH);
		cal.setTime(date);

		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH);
		int monthSpan = (currentYear - startYear) * 12 + currentMonth
				- startMonth;
		if (monthSpan % getInterval() != 0)
			return false;
		return cal.get(Calendar.DAY_OF_MONTH) == getDayOfMonth();
	}

}
