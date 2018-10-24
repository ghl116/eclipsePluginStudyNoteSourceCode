package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class UsingList {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell listDemoShell = new Shell(display);
		listDemoShell.setText("List Demo");
		listDemoShell.setSize(190, 200);

		final List list = new List(listDemoShell, SWT.BORDER);
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		list.setBounds(10, 10, 115, 150);
		listDemoShell.open();

		while (!listDemoShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

}
