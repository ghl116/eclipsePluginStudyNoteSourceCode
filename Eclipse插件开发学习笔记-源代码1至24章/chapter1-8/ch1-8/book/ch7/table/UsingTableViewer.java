package book.ch7.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import book.ch7.list.ListModel;
import book.ch7.list.User;

public class UsingTableViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(200, 200);
		shell.setLayout(new FillLayout());

		Table table = new Table(shell, SWT.BORDER);
		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("ID");
		column1.setWidth(100);
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setWidth(100);
		column2.setText("Name");
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableViewer viewer = new TableViewer(table);	
		viewer.setContentProvider(new TableContentProvider());
		viewer.setLabelProvider(new TableLabelProvider());

		ListModel input = new ListModel();
		viewer.setInput(input);

		shell.open();
		shell.layout();

		input.add(new User("1", "张三"));
		input.add(new User("2", "李四"));
		input.add(new User("3", "王五"));

		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();

	}

}
