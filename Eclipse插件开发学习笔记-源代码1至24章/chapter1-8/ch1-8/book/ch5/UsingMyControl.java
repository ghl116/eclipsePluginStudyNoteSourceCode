package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingMyControl {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(176, 193);

		MyControl control1 = new MyControl(shell, SWT.NONE);
		control1.setBounds(10, 10, 120, 25);

		MyControl control2 = new MyControl(shell, SWT.NONE);
		control2.setBounds(10, 40, 120, 25);

		MyControl control3 = new MyControl(shell, SWT.NONE);
		control3.setBounds(10, 70, 120, 25);

		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

}
