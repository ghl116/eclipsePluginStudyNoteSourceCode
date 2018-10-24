package book.ch3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ManualDispose {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(100, 100);

		final Label label = new Label(shell, SWT.NONE);
		label.setBounds(10, 10, 20, 20);
		label.setText("A");

		shell.open();
		shell.layout();

		shell.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent event) {
				if (event.button == 1)
					label.dispose();
				else
					label.setText("B");
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
