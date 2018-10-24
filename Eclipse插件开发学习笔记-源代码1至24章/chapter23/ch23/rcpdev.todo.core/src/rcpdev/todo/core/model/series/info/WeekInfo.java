package rcpdev.todo.core.model.series.info;

import java.util.Calendar;
import java.util.Date;

import rcpdev.todo.core.model.series.SeriesInfo;

public class WeekInfo extends SeriesInfo {

	private boolean[] weekmap;

	public WeekInfo() {
		super();
		weekmap = new boolean[7];
	}

	public boolean[] getWeekmap() {
		return weekmap;
	}

	public void setWeekmap(boolean[] newMap) {
		this.weekmap = newMap;
	}

	@Override
	protected boolean fit(Date date) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(getBaseDate());

		int startYear = cal.get(Calendar.YEAR);
		int startWeek = cal.get(Calendar.WEEK_OF_YEAR);
		cal.setTime(date);

		int currentYear = cal.get(Calendar.YEAR);
		int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
		int weekspan = (currentYear - startYear) * 52 + currentWeek - startWeek;
		if (weekspan % getInterval() != 0)
			return false;
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			return getWeekmap()[0];
		case Calendar.MONDAY:
			return getWeekmap()[1];
		case Calendar.TUESDAY:
			return getWeekmap()[2];
		case Calendar.WEDNESDAY:
			return getWeekmap()[3];
		case Calendar.THURSDAY:
			return getWeekmap()[4];
		case Calendar.FRIDAY:
			return getWeekmap()[5];
		case Calendar.SATURDAY:
			return getWeekmap()[6];
		}
		return false;
	}


}
