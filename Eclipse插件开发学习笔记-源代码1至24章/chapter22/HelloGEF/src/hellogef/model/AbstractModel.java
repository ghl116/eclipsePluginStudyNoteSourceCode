package hellogef.model;

import hellogef.tools.STextPropertyDescriptor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public abstract class AbstractModel  implements Cloneable, Serializable, IPropertySource  {

	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	final public static String PROP_NAME = "NAME";

	protected String name = "";

	protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new STextPropertyDescriptor(PROP_NAME, "Ãû³Æ")};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (this.name.equals(name)) {
			return;
		}
		this.name = name;
		firePropertyChange(PROP_NAME, null, name);
	}

	public Object getEditableValue() {
		return this;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	public Object getPropertyValue(Object id) {
		if (PROP_NAME.equals(id))
			return getName();
		return null;
	}

	public boolean isPropertySet(Object id) {
		return true;
	}

	public void resetPropertyValue(Object id) {

	}

	public void setPropertyValue(Object id, Object value) {
		if (PROP_NAME.equals(id))
			setName((String) value);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}


	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue) {
		listeners.firePropertyChange(prop, old, newValue);
	}

	protected void fireStructureChange(String prop, Object child) {
		listeners.firePropertyChange(prop, null, child);
	}

}
