package rcpdev.common.core.utils.sort;

import java.util.List;

/**
 * Provide sorting utils based on {@link Comparable#compareTo(Object)}
 * 
 * @author Harper Jiang
 * @version 1.0
 * @see java.lang.Comparable
 */
public interface Sorter {

	/**
	 * 
	 * @param source
	 * @return
	 */
	public List sort(List source);
	
	/**
	 * 
	 * @param source
	 * @param attribute used in sort.
	 * @return
	 */
	public List sort(List source, String[] attribute,boolean[] ascending);
}
