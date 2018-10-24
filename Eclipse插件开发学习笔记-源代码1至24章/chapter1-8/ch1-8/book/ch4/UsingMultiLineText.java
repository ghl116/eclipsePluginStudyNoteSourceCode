package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/*
 * 使用多行文本框
 */
public class UsingMultiLineText {

	private static Text text_2;

	private static Text text;

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell usingTextShell = new Shell(display);
		usingTextShell.setText("Using Text");
		usingTextShell.setSize(207, 177);

		text = new Text(usingTextShell, SWT.V_SCROLL | SWT.BORDER
				| SWT.H_SCROLL);
		text.setBounds(10, 77, 179, 56);

		text_2 = new Text(usingTextShell, SWT.V_SCROLL | SWT.BORDER | SWT.WRAP
				| SWT.H_SCROLL);
		text_2.setBounds(10, 15, 179, 56);
		usingTextShell.open();

		while (!usingTextShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}
}
