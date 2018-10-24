package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingColorDialog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();

		final Shell shell = new Shell(display);
		shell.setSize(200, 100);
		shell.open();

		Button button = new Button(shell, SWT.PUSH);
		button.setBounds(10, 10, 100, 20);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(shell);
				RGB rgb = cd.open();
				if (rgb != null) {
					Color color = new Color(Display.getDefault(), rgb);
					// Use Color
					color.dispose();
				}

			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
