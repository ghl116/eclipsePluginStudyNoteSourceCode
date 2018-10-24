package rcpdev.common.core.utils;

public class MiscUtils {

	public static final boolean equals(Object a, Object b) {
		if (a == b)
			return true;
		if (a != null && b != null)
			return a.equals(b);
		return false;
	}
}
