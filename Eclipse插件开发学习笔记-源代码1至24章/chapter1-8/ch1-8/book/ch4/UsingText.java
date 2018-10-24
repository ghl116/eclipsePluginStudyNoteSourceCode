package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/*
 * 展示文本框的各种样式
 */
public class UsingText {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell usingTextShell = new Shell(display);
		usingTextShell.setText("Using Text");
		usingTextShell.setSize(502, 75);

		final Text normalText = new Text(usingTextShell, SWT.BORDER);
		normalText.setBounds(10, 10, 112, 20);
		normalText.setText("abc");

		final Text passwordText = new Text(usingTextShell, SWT.PASSWORD	| SWT.BORDER);
		passwordText.setBounds(255, 10, 112, 20);
		passwordText.setText("abc");

		final Text readonlyText = new Text(usingTextShell, SWT.READ_ONLY | SWT.BORDER);
		readonlyText.setBounds(375, 10, 112, 20);
		readonlyText.setText("abc");

		final Text borderlessText = new Text(usingTextShell, SWT.NONE);
		borderlessText.setText("abc");
		borderlessText.setBounds(132, 10, 112, 20);

		usingTextShell.open();

		while (!usingTextShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}
}
