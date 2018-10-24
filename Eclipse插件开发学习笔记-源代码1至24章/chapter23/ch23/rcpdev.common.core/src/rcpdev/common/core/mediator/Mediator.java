package rcpdev.common.core.mediator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Mediator implements PropertyChangeListener {

	private static Mediator instance;

	public static Mediator getInstance() {
		if(instance == null)
			instance = new Mediator();
		return instance;
	}
	
	private Mediator() {
		
	}
	
	private PropertyChangeSupport delegate;
	
	public PropertyChangeSupport getDelegate() {
		if(delegate == null)
			delegate = new MediatorPCSupport(this);
		return delegate;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getDelegate().addPropertyChangeListener(listener);
	}

	public void firePropertyChange(PropertyChangeEvent evt) {
		getDelegate().firePropertyChange(evt);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		getDelegate().removePropertyChangeListener(listener);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		firePropertyChange(evt);
	}
}
