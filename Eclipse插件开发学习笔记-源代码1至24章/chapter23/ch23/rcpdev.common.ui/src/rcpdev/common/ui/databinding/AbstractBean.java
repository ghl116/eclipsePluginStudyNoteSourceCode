package rcpdev.common.ui.databinding;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.IAdaptable;

public class AbstractBean implements IAdaptable, PropertyChangeListener {

	private PropertyChangeSupport delegate;

	public AbstractBean() {
		delegate = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(listener);
	}

	public void firePropertyChange(PropertyChangeEvent evt) {
		delegate.firePropertyChange(evt);
	}

	public void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		delegate.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(listener);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		
	}

	public Object getAdapter(Class adapterClass) {
		if (PropertyChangeListener.class.equals(adapterClass))
			return this;
		if (PropertyChangeSupport.class.equals(adapterClass))
			return delegate;
		return null;
	}
}
