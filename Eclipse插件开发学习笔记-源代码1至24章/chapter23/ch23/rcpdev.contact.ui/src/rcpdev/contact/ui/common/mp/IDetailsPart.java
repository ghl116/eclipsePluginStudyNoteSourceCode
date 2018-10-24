package rcpdev.contact.ui.common.mp;

import java.beans.PropertyChangeSupport;


public interface IDetailsPart {

	public void changePage(int newIndex);
	
	public PropertyChangeSupport getDelegate();
	
	public void setValue(int index,Object value);
}
