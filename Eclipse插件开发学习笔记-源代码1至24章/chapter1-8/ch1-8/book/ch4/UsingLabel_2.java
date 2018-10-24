package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class UsingLabel_2 {
	public static void main(String[] args) {
		Display display = Display.getDefault();

		final Shell shell = new Shell(display);
		shell.setSize(150, 150);
		shell.open();

		Label horLine = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL
				| SWT.BORDER);
		horLine.setBounds(10, 10, 100, 20);
		Label verLine = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		verLine.setBounds(110, 10, 20, 100);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
