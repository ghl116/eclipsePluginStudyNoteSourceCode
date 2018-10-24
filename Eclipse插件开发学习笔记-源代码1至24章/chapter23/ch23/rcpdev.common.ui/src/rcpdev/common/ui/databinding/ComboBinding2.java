package rcpdev.common.ui.databinding;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

public class ComboBinding2 extends ControlBinding {

	public ComboBinding2(String attribute, Combo text) {
		super();
		setAttribute(attribute);
		setControl(text);
	}

	public ComboBinding2(String attribute, CCombo text) {
		super();
		setAttribute(attribute);
		setControl(text);
	}

	public void setControl(Control control) {
		assert control instanceof Combo || control instanceof CCombo;
		super.setControl(control);
		control.addListener(SWT.Modify, this);
	}

	public void handleEvent(Event event) {
		if (SWT.Modify == event.type) {
			Combo combo = (Combo) event.widget;
			Object oldVal = getValue();
			value = Integer.valueOf(combo.getSelectionIndex());
			firePropertyChange(new PropertyChangeEvent(this, BINDING_VAL,
					oldVal, value));
		}
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);

		if (getControl() instanceof Combo) {
			Combo combo = (Combo) getControl();
			// Remove listener before setting text
			combo.removeListener(SWT.Modify, this);

			combo.select(Integer.valueOf(value.toString()));

			// Re-register listener after set text.
			combo.addListener(SWT.Modify, this);
		}
		if (getControl() instanceof CCombo) {
			CCombo combo = (CCombo) getControl();
			combo.removeListener(SWT.Modify, this);

			combo.select(Integer.valueOf(value.toString()));

			// Re-register listener after set text.
			combo.addListener(SWT.Modify, this);
		}
	}
}
