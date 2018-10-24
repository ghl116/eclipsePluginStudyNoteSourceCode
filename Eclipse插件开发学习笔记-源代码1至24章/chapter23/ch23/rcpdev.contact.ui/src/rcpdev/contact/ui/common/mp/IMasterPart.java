package rcpdev.contact.ui.common.mp;

import java.beans.PropertyChangeSupport;

public interface IMasterPart {

	public PropertyChangeSupport getDelegate();
	
	public int getSelection();
	
	public String[] getItems();
	
	public void valueChanged(int index,boolean checked);
}
