package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ShellModality {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell1 = new Shell(display);
		shell1.setText("Shell 1");
		shell1.setSize(206, 149);

		Shell shell2 = new Shell(display);
		shell2.setSize(206, 150);
		shell2.setText("Shell 2");

		shell1.open();
		shell2.open();

		Shell primaryShell = new Shell(shell1, SWT.DIALOG_TRIM
				| SWT.PRIMARY_MODAL);
		primaryShell.setText("This window will block shell 1 only");
		primaryShell.setSize(300, 80);
		primaryShell.open();

		Shell applicationShell = new Shell(shell1, SWT.DIALOG_TRIM
				| SWT.APPLICATION_MODAL);
		applicationShell.setText("This window will block both");
		applicationShell.setSize(300, 80);
		applicationShell.open();

		while (!(shell1.isDisposed() && shell2.isDisposed()))
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}
}
