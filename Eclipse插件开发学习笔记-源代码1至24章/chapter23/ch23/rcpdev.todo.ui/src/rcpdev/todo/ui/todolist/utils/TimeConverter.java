package rcpdev.todo.ui.todolist.utils;

import java.text.DateFormat;
import java.util.Calendar;

public class TimeConverter {
	public static synchronized String convert(int indicator) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
		cal.set(Calendar.MINUTE, indicator * 30);
		return df.format(cal.getTime());
	}
}
