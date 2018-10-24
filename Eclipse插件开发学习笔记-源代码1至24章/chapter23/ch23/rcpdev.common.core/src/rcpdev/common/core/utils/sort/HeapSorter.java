package rcpdev.common.core.utils.sort;

import java.util.List;

import rcpdev.common.core.utils.BeanAccess;

/**
 * <code>HeapSort</code> use max heap to sort the data.
 * 
 * @author JIANGHA
 * 
 */

public class HeapSorter implements Sorter {

	private List array;

	private int heapSize = 0;

	private boolean useCompareTo;

	private String[] attrs;

	private boolean[] asc;

	/**
	 * 
	 * @arg source List of <code>java.lang.Comparable</code> data.
	 * @return List of data that has been sorted.
	 */
	public synchronized List sort(List source) {
		useCompareTo = true;
		array = source;
		heapSize = array.size() - 1;
		buildMaxHeap();
		for (int i = array.size() - 1; i > 0; i--) {
			SorterUtil.exchange(array, 0, i);
			heapSize--;
			maxHeapify(0);
		}
		return array;
	}

	public synchronized List sort(List source, String[] attribute,
			boolean[] ascending) {
		useCompareTo = false;
		attrs = attribute;
		asc = ascending;
		array = source;
		heapSize = array.size() - 1;
		buildMaxHeap();
		for (int i = array.size() - 1; i > 0; i--) {
			SorterUtil.exchange(array, 0, i);
			heapSize--;
			maxHeapify(0);
		}
		return array;
	}

	private void buildMaxHeap() {
		for (int i = (array.size() - 1) / 2; i >= 0; i--)
			maxHeapify(i);
	}

	private void maxHeapify(int i) {
		int left = left(i);
		int right = right(i);
		int largest = 0;
		if (left <= heapSize && (compare(array.get(left), array.get(i)) < 0))
			largest = left;
		else
			largest = i;

		if (right <= heapSize
				&& (compare(array.get(right), array.get(largest)) > 0))
			largest = right;
		if (largest != i) {
			SorterUtil.exchange(array, i, largest);
			maxHeapify(largest);
		}
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * (i + 1);
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
