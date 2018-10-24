package rcpdev.common.core.utils.sort;

import java.util.List;

public class SorterUtil {

	public static void exchange(List array,int i,int j) {
		Object buffer = array.get(i);
		array.set(i, array.get(j));
		array.set(j, buffer);
	}
}
 