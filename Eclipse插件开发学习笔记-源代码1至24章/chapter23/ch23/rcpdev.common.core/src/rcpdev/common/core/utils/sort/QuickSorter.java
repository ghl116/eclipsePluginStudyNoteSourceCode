package rcpdev.common.core.utils.sort;

import java.util.List;
import java.util.Vector;

import rcpdev.common.core.utils.BeanAccess;

public class QuickSorter implements Sorter {

	private List array;

	private boolean useCompareTo;

	private String[] attrs;

	private boolean[] asc;

	public synchronized List sort(List source) {
		useCompareTo = true;
		array = new Vector();
		array.addAll(source);
		quickSort(0, array.size() - 1);
		return array;
	}

	public synchronized List sort(List source, String[] attribute,
			boolean[] ascending) {
		useCompareTo = false;
		attrs = attribute;
		asc = ascending;
		array = new Vector();
		array.addAll(source);
		quickSort(0, array.size() - 1);
		return array;
	}

	private int partition(int start, int end) {
		Object x = array.get(end);
		int i = start - 1;
		for (int j = start; j < end; j++) {
			if (compare(array.get(j), x) < 0) {
				i++;
				SorterUtil.exchange(array, i, j);
			}
		}
		SorterUtil.exchange(array, i + 1, end);
		return i + 1;
	}

	private void quickSort(int start, int end) {
		if (start < end) {
			int partition = partition(start, end);
			quickSort(start, partition - 1);
			quickSort(partition + 1, end);
		}
	}

	private int compare(Object a, Object b) {
		if (useCompareTo)
			return ((Comparable) a).compareTo(b);
		try {
			for (int i = 0; i < attrs.length; i++) {
				Comparable at = (Comparable) BeanAccess.get(a, attrs[i]);
				Comparable bt = (Comparable) BeanAccess.get(b, attrs[i]);
				if (at.compareTo(bt) == 0)
					continue;
				if (!(at.compareTo(bt) > 0 ^ asc[i]))
					return 1;
				else
					return -1;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
}
