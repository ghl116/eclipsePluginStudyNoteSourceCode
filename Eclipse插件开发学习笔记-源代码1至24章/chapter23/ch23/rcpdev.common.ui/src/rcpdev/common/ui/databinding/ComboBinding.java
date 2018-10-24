package rcpdev.common.ui.databinding;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

import rcpdev.common.core.utils.BeanAccess;

public class ComboBinding extends ControlBinding {

	public ComboBinding(String attribute, Combo text) {
		super();
		setAttribute(attribute);
		setControl(text);
	}

	public ComboBinding(String attribute, CCombo text) {
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
			String text = (String)BeanAccess.get(event.widget, "text");
			Object oldVal = getValue();
			value = text;
			firePropertyChange(new PropertyChangeEvent(this, BINDING_VAL,
					oldVal, text));
		}
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);

		if (getControl() instanceof Combo) {
			Combo text = (Combo) getControl();
			// Remove listener before setting text
			text.removeListener(SWT.Modify, this);

			text.setText(value == null ? "" : value.toString());

			// Re-register listener after set text.
			text.addListener(SWT.Modify, this);
		}
		if (getControl() instanceof CCombo) {
			CCombo text = (CCombo) getControl();
			text.removeListener(SWT.Modify, this);

			text.setText(value == null ? "" : value.toString());

			// Re-register listener after set text.
			text.addListener(SWT.Modify, this);
		}
	}
}
