package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingDirectoryDialog {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		final Shell shell = new Shell(display);
		shell.setSize(200, 200);
		shell.setText("DirectoryDialog Demo");

		final Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				DirectoryDialog dirDialog = new DirectoryDialog(shell);
				dirDialog.setFilterPath("D:\\");
				System.out.println(dirDialog.open());
			}
		});
		button.setText("Open DirectoryDialog");
		button.setBounds(20, 25, 135, 25);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

}
