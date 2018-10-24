package rcpdev.todo.core.model.series.info;

import java.util.Calendar;
import java.util.Date;

import rcpdev.common.core.utils.DateUtils;
import rcpdev.todo.core.model.series.SeriesInfo;

public class DayInfo extends SeriesInfo {

	public static final int NORMAL = 0;

	public static final int WEEKDAY = 1;

	public static final int WEEKEND = 2;

	private int infoType;

	@Override
	protected boolean fit(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		Date startDate = getBaseDate();
		long totalDate = DateUtils.subtract(date, startDate);
		switch (infoType) {
		case NORMAL:
			return totalDate % getInterval() == 0;
		case WEEKDAY:
			return dayOfWeek != Calendar.SATURDAY
					&& dayOfWeek != Calendar.SUNDAY;
		case WEEKEND:
			return dayOfWeek == Calendar.SATURDAY
					|| dayOfWeek == Calendar.SUNDAY;
		}
		return false;
	}

	public int getInfoType() {
		return infoType;
	}

	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}

}
