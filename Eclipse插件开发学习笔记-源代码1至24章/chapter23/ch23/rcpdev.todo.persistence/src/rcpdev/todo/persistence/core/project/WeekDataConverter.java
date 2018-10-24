package rcpdev.todo.persistence.core.project;

import oracle.toplink.mappings.DatabaseMapping;
import oracle.toplink.mappings.converters.Converter;
import oracle.toplink.sessions.Session;

public class WeekDataConverter implements Converter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8235400128422448840L;

	public Object convertDataValueToObjectValue(Object arg0, Session arg1) {
		String data = (String) arg0;
		boolean[] datamap = new boolean[7];
		for (int i = 0; i < datamap.length; i++)
			datamap[i] = (data.charAt(i) == '1');
		return datamap;
	}

	public Object convertObjectValueToDataValue(Object arg0, Session arg1) {
		StringBuffer sb = new StringBuffer();
		boolean[] datamap = (boolean[]) arg0;
		for (int i = 0; i < datamap.length; i++) {
			sb.append(datamap[i] ? '1' : '0');
		}
		return sb.toString();
	}

	public void initialize(DatabaseMapping arg0, Session arg1) {
	}

	public boolean isMutable() {
		return false;
	}

}
