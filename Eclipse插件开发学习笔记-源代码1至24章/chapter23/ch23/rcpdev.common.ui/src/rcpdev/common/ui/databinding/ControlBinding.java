package rcpdev.common.ui.databinding;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;

public abstract class ControlBinding implements IBinding, Listener {

	private Control control;

	private String attribute;

	protected Object value;

	private PropertyChangeSupport delegate;

	public ControlBinding() {
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

	public Control getControl() {
		return control;
	}

	/**
	 * Subclasses should override this class to implements listener
	 * registratinon on control.
	 * 
	 * @param control
	 */
	public void setControl(Control control) {
		this.control = control;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
