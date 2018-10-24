package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class CreatingGroup {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(212, 193);

		final Group group = new Group(shell, SWT.NONE);
		group.setText("This is a Group");
		group.setBounds(15, 10, 173, 60);

		final Group borderedGroup = new Group(shell, SWT.BORDER);
		borderedGroup.setText("This is a bordered Group");
		borderedGroup.setBounds(15, 89, 173, 60);

		

		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

}
