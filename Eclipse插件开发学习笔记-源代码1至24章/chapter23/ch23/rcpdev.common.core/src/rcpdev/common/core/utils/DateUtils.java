package rcpdev.common.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final DateFormat ddf = new SimpleDateFormat("MMM,dd");

	public static final DateFormat nFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static final DateFormat keyFormat = new SimpleDateFormat("MM-dd");

	public static final Date truncToDay(Date oldDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	private static long daySeconds = 24 * 3600*1000;

	public static final long subtract(Date source, Date subtract) {
		long result = source.getTime() - subtract.getTime();
		return result / daySeconds;

	}
}
