package rcpdev.todo.core.model.series.info;

import java.util.Calendar;
import java.util.Date;

import rcpdev.todo.core.model.series.SeriesInfo;

public class YearInfo extends SeriesInfo {

	private int monthOfYear;

	@Override
	protected boolean fit(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) == monthOfYear;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

}
