package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MonitorShell {

	public static void main(String args[]) {

		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.SHELL_TRIM);

		shell.setText("SWT Window");
		shell.setSize(400, 200);
		final Text text = new Text(shell, SWT.READ_ONLY | SWT.MULTI
				| SWT.BORDER | SWT.V_SCROLL);
		text.setBounds(10, 10, 380, 150);
		shell.addShellListener(new ShellListener() {
			public void shellActivated(ShellEvent e) {
				text.append("Shell has been activated\n");
			}

			public void shellClosed(ShellEvent e) {
				System.out.println("Shell has been closed");
			}

			public void shellDeactivated(ShellEvent e) {
				text.append("Shell has been deactivated\n");
			}

			public void shellDeiconified(ShellEvent e) {
				text.append("Shell has been deiconified\n");
			}

			public void shellIconified(ShellEvent e) {
				text.append("Shell has been iconified\n");
			}
		});

		shell.open();

		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

}
