package rcpdev.common.ui.databinding;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

public class LabelBinding extends ControlBinding {

	public LabelBinding(String attribute, Label label) {
		super();
		setAttribute(attribute);
		setControl(label);
	}

	public void setValue(Object value) {
		((Label) getControl()).setText(String.valueOf(value));
	}

	public void handleEvent(Event event) {

	}

}
