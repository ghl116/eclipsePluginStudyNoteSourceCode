package rcpdev.todo.ui.todolist.common.beans.todoitem;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

import rcpdev.common.ui.databinding.ControlBinding;

public class StackBinding extends ControlBinding {

	public static final String STACK_DATA = "stackData";

	public StackBinding(String attribute, Composite control) {
		super();
		setAttribute(attribute);
		assert control.getLayout() instanceof StackLayout;
		super.setControl(control);
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		if (value == null)
			return;
		Composite control = (Composite) getControl();
		StackLayout layout = (StackLayout) control.getLayout();
		Control[] children = control.getChildren();

		for (int i = 0; i < children.length; i++) {
			if (value.equals(children[i].getData(STACK_DATA))) {
				layout.topControl = children[i];
				control.layout();
				return;
			}
		}
	}

	public void handleEvent(Event event) {
		// No Event to handle
	}
}
