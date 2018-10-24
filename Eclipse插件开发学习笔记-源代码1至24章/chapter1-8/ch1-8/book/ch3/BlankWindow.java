package book.ch3;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BlankWindow {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(100, 100);

		shell.open();
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}
