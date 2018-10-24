package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class ListSelection {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell listDemoShell = new Shell(display);
		listDemoShell.setText("List Demo");
		listDemoShell.setSize(126, 142);

		final List list = new List(listDemoShell, SWT.BORDER|SWT.VERTICAL);
		list.addSelectionListener(new SelectionListener() {

			private int oldIndex = -1;

			private String oldContent = null;

			public void widgetSelected(final SelectionEvent e) {
				if (oldIndex != -1)
					list.setItem(oldIndex, oldContent);
				int index = list.getSelectionIndex();
				System.out.println(e.item);
				oldIndex = index;
				oldContent = list.getItem(index);
				list.setItem(index, oldContent + " selected");
			}

			public void widgetDefaultSelected(final SelectionEvent e) {
				if (oldIndex != -1)
					list.setItem(oldIndex, oldContent);
				int index = list.getSelectionIndex();
				oldIndex = index;
				oldContent = list.getItem(index);
				list.setItem(index, oldContent + " default selected");
			}
		});
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");

		list.setBounds(10, 10, 99, 94);
		listDemoShell.open();

		while (!listDemoShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}
}
