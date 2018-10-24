package book.ch2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class saf extends Shell {

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			saf shell = new saf(display, SWT.SHELL_TRIM);
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

	/**
	 * Create the shell
	 * @param display
	 * @param style
	 */
	public saf(Display display, int style) {
		super(display, style);
		createContents();
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(243, 113);
		RowLayout layout = new RowLayout();
		layout.pack = false;
		setLayout(layout);

		final Button button = new Button(this, SWT.NONE);
		final RowData rowData = new RowData();
		rowData.height = 27;
		rowData.width = 38;
		button.setLayoutData(rowData);
		button.setText("button");

		final Button button_1 = new Button(this, SWT.NONE);
		final RowData rowData_1 = new RowData();
		rowData_1.height = 77;
		button_1.setLayoutData(rowData_1);
		button_1.setText("button");

		final Button button_2 = new Button(this, SWT.NONE);
		final RowData rowData_2 = new RowData();
		rowData_2.height = 17;
		rowData_2.width = 73;
		button_2.setLayoutData(rowData_2);
		button_2.setText("button");
		//
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
