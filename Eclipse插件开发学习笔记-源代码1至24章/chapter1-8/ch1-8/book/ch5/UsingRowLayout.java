package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingRowLayout {

	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display, SWT.SHELL_TRIM);
			shell.setText("RowLayout Demo");
			shell.setSize(235, 139);
			shell.setLayout(new RowLayout());// 和FillLayout一样，不指定方向的话，默认为横向

			Button button = new Button(shell, SWT.NONE);
			final RowData rowData = new RowData();
			rowData.height = 32;
			rowData.width = 103;
			button.setLayoutData(rowData);
			button.setText("button");

			Button button_1 = new Button(shell, SWT.NONE);
			final RowData rowData_1 = new RowData();
			rowData_1.height = 107;
			rowData_1.width = 43;
			button_1.setLayoutData(rowData_1);
			button_1.setText("button");

			Button button_2 = new Button(shell, SWT.NONE);
			final RowData rowData_2 = new RowData();
			rowData_2.height = 37;
			rowData_2.width = 68;
			button_2.setLayoutData(rowData_2);
			button_2.setText("button");
			shell.open();
			shell.layout();

			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
