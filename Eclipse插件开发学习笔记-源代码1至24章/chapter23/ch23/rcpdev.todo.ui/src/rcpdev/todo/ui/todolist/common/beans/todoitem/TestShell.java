package rcpdev.todo.ui.todolist.common.beans.todoitem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TestShell extends Shell {

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			TestShell shell = new TestShell(display, SWT.SHELL_TRIM);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell
	 * 
	 * @param display
	 * @param style
	 */
	public TestShell(Display display, int style) {
		super(display, style);
		createContents();
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(500, 375);

		final Button button = new Button(this, SWT.RADIO);
		button.setText("Radio Button");
		button.setBounds(60, 75, 120, 30);

		final Button button_1 = new Button(this, SWT.RADIO);
		button_1.setText("Radio Button");
		button_1.setBounds(60, 111, 120, 30);
		for (int i = 0; i < 43; i++)
			button_1.addListener(i, new Listener() {
				public void handleEvent(Event event) {
					System.out.println(event.type);
				}
			});

		final Button button_2 = new Button(this, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				button_1.setSelection(true);
			}
		});
		button_2.setText("button");
		button_2.setBounds(110, 165, 120, 30);
		//
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
