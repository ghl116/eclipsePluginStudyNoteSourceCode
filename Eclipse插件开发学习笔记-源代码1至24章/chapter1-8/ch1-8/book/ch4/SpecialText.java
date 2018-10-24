package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/*
 * 一个只能输入数字的文本框和一个会把输入内容变成大写的文本框
 */
public class SpecialText {
	private static Text upperText;

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setText("Using Text");
		shell.setSize(189, 108);

		final Text numberText = new Text(shell, SWT.BORDER);
		numberText.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				try {
					Integer.parseInt(e.text);
				} catch (Exception exce) {
					e.doit = false;
				}
			}
		});
		numberText.setBounds(22, 10, 138, 21);

		upperText = new Text(shell, SWT.BORDER);
		upperText.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.text = e.text.toUpperCase();
			}
		});
		upperText.setBounds(22, 40, 138, 20);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}
}
