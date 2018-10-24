package rcpdev.contact.core.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.IAdaptable;

import rcpdev.common.core.utils.BeanAccess;

public abstract class CommonObject implements IAdaptable,
		PropertyChangeListener, Cloneable {

	PropertyChangeSupport delegate;

	public CommonObject() {
		super();
		delegate = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(listener);
	}

	public void firePropertyChange(PropertyChangeEvent evt) {
		delegate.firePropertyChange(evt);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(listener);
	}

	public void propertyChange(PropertyChangeEvent event) {

	}

	public PropertyChangeSupport getDelegate() {
		return delegate;
	}

	public void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		delegate.firePropertyChange(propertyName, oldValue, newValue);
	}

	public Object getAdapter(Class adapterClass) {
		if (PropertyChangeSupport.class.equals(adapterClass))
			return delegate;
		return null;
	}

	protected void setValue(String attribute, Object newValue) {
		Object oldValue = BeanAccess.get(this, attribute);
		BeanAccess.setDirect(this, attribute, newValue);
		firePropertyChange(attribute, oldValue, newValue);
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
