package rcpdev.common.core.utils;

public class StringUtils {

	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	public static int compare(String a, String b) {
		if (a == b)
			return 0;
		if (a == null)
			return -1;
		if (b == null)
			return 1;
		return a.compareTo(b);
	}
}
