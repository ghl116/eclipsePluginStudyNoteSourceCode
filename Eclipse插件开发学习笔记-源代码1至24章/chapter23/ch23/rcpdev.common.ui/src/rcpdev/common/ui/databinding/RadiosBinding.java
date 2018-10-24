package rcpdev.common.ui.databinding;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

public class RadiosBinding implements IBinding, SelectionListener {

	public static final String DATA_KEY = "RadiosBinding.dataKey";

	private PropertyChangeSupport delegate;

	private String attribute;

	private Object value;

	private Button[] radios;

	public RadiosBinding(String attribute, Button[] radios) {
		delegate = new PropertyChangeSupport(this);
		this.attribute = attribute;
		this.radios = radios;
		for (int i = 0; i < radios.length; i++) {
			radios[i].addSelectionListener(this);
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(listener);
	}

	public String getAttribute() {
		return attribute;
	}

	public Object getValue() {
		return value;
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(listener);
	}

	public void setValue(Object value) {
		this.value = value;
		for (int i = 0; i < radios.length; i++) {
			radios[i].removeSelectionListener(this);
			radios[i].setSelection(radios[i].getData(DATA_KEY).equals(value));
			radios[i].addSelectionListener(this);
		}
	}

	public void widgetDefaultSelected(SelectionEvent e) {
	}

	public void widgetSelected(SelectionEvent e) {
		Button radio = (Button) e.widget;
		Object oldVal = getValue();
		Object newVal = radio.getData(DATA_KEY);
		this.value = newVal;
		delegate.firePropertyChange(new PropertyChangeEvent(this, BINDING_VAL,
				oldVal, newVal));
	}

}
