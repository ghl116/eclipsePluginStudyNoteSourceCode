package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class UsingMessageBox_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();

		final Shell messageboxDemoShell = new Shell(display);
		messageboxDemoShell.setText("MessageBox Demo");
		messageboxDemoShell.setSize(224, 79);
		messageboxDemoShell.open();

		Button showTheMessageButton = new Button(messageboxDemoShell, SWT.PUSH);
		showTheMessageButton.setText("Show the Message Box");
		showTheMessageButton.setBounds(10, 10, 196, 24);
		showTheMessageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox mb = new MessageBox(messageboxDemoShell, SWT.OK|SWT.ICON_ERROR);
				mb.setText("Error");
				mb.setMessage("You have encountered an error!");
				mb.open();
			}
		});

		while (!messageboxDemoShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
