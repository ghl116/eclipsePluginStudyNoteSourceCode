package book.ch7.list;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingListViewer {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(400, 400);
		shell.setLayout(new FillLayout());

		ListViewer viewer = new ListViewer(shell, SWT.BORDER);

		viewer.setContentProvider(new ListContentProvider());
		viewer.setLabelProvider(new ListLabelProvider());

		ListModel input = new ListModel();
		viewer.setInput(input);
		
		shell.open();
		shell.layout();
		
		input.add(new User("1","张三"));
		input.add(new User("2","李四"));
		input.add(new User("3","王五"));


		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}

}
