package rcpdev.common.ui.databinding;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

public class ChecksBinding implements IBinding, SelectionListener {

	public static final String DATA_INDEX_KEY = "ChecksBinding.dataIndexKey";

	private PropertyChangeSupport delegate;

	private String attribute;

	private boolean[] value;

	private Button[] checks;

	public ChecksBinding(String attribute, Button[] checks) {
		delegate = new PropertyChangeSupport(this);
		this.attribute = attribute;
		this.checks = checks;
		for (int i = 0; i < checks.length; i++) {
			checks[i].addSelectionListener(this);
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

	public void setValue(Object newValue) {
		this.value = (boolean[]) newValue;
		for (int i = 0; i < checks.length; i++) {
			Integer index = (Integer) checks[i].getData(DATA_INDEX_KEY);
			checks[i].removeSelectionListener(this);
			Boolean sel = (Boolean) value[index];
			checks[i].setSelection(sel != null ? sel : Boolean.FALSE);
			checks[i].addSelectionListener(this);
		}
	}

	public void widgetDefaultSelected(SelectionEvent e) {
	}

	public void widgetSelected(SelectionEvent e) {
		Button check = (Button) e.widget;
		boolean[] oldVal = value;
		boolean[] newVal = value.clone();
		Integer index = (Integer) check.getData(DATA_INDEX_KEY);
		newVal[index] = check.getSelection();
		this.value = newVal;
		delegate.firePropertyChange(new PropertyChangeEvent(this, BINDING_VAL,
				oldVal, newVal));
	}

}
