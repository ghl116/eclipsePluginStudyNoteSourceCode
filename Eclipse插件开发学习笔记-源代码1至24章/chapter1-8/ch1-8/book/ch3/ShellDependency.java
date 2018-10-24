package book.ch3;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ShellDependency {
	public static void main(String[] args) {
		Display display = Display.getDefault();

		Shell shell1 = new Shell(display);
		Shell shell2 = new Shell(shell1);

		shell1.setText("Parent Shell");
		shell2.setText("Child Shell");
		
		shell1.setSize(200,200);
		shell2.setSize(200,200);
		
		shell1.open();
		shell2.open();

		while (!shell1.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();
}

}
