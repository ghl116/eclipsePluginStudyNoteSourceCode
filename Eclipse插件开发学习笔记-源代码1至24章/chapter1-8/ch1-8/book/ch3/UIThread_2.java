package book.ch3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UIThread_2 {

	public static void main(String args[]) {
		try {
			final Display display = Display.getDefault();
			Shell shell = new Shell(display, SWT.SHELL_TRIM);
			shell.setText("Multi Thread");
			shell.setSize(204, 92);
			final Button button = new Button(shell, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					Thread thread = new Thread() {
						public void run() {
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							display.syncExec(new Runnable() {
								public void run() {
									button.setText("Execution Done");
								}
							});
						}
					};
					thread.start();
				}
			});
			button.setText("button");
			button.setBounds(20, 15, 155, 25);

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

}
