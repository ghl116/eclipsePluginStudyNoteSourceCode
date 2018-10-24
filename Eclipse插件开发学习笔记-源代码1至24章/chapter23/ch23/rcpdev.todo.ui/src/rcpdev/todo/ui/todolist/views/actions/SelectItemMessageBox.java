package rcpdev.todo.ui.todolist.views.actions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SelectItemMessageBox extends MessageBox {

	public SelectItemMessageBox(Shell parent) {
		super(parent, SWT.OK | SWT.ICON_WARNING);
		setText("Warning");
		setMessage("Select an item to operate on.");
	}

	@Override
	public void checkSubclass() {

	}
}
