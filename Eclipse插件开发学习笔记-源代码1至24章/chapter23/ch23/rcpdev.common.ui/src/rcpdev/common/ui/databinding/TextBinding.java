package rcpdev.common.ui.databinding;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

public class TextBinding extends ControlBinding {

	public TextBinding(String attribute, Text text) {
		super();
		setAttribute(attribute);
		setControl(text);
	}

	@Override
	public void setControl(Control control) {
		assert control instanceof Text;
		super.setControl(control);
		control.addListener(SWT.Modify, this);
	}

	public void handleEvent(Event event) {
		if (SWT.Modify == event.type) {
			String text = ((Text) event.widget).getText();
			Object oldVal = getValue();
			super.setValue(text);
			firePropertyChange(new PropertyChangeEvent(this, BINDING_VAL,
					oldVal, text));
		}
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		Text text = (Text) getControl();
		// Remove listener before setting text
		text.removeListener(SWT.Modify, this);

		text.setText(value == null ? "" : value.toString());

		// Re-register listener after set text.
		text.addListener(SWT.Modify, this);
	}
}
