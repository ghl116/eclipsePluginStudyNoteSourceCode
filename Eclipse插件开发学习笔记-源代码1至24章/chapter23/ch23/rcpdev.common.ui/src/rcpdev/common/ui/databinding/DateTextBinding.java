package rcpdev.common.ui.databinding;

import java.beans.PropertyChangeEvent;
import java.text.ParsePosition;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

import rcpdev.common.core.utils.DateUtils;

public class DateTextBinding extends TextBinding {

	public DateTextBinding(String attribute, Text text) {
		super(attribute, text);
	}

	@Override
	public void handleEvent(Event event) {
		if (SWT.Modify == event.type) {
			String text = ((Text) event.widget).getText();
			Date newDate = DateUtils.nFormat.parse(text, new ParsePosition(0));
			Object oldVal = getValue();
			value = newDate;
			firePropertyChange(new PropertyChangeEvent(this, BINDING_VAL,
					oldVal, newDate));
		}
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		Text text = (Text) getControl();
		// Remove listener before setting text
		text.removeListener(SWT.Modify, this);

		text.setText(value == null ? "" : DateUtils.nFormat
				.format((Date) value));

		// Re-register listener after set text.
		text.addListener(SWT.Modify, this);
	}
}
