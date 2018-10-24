package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class FileDialog_2 {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		final Shell shell = new Shell(display);
		shell.setText("FileDialog Demo");
		shell.setSize(225, 111);

		final Button openFiledialogButton = new Button(shell, SWT.NONE);
		openFiledialogButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);

				fileDialog.setFilterNames(new String[] { "Text Files",
						"Executable Files", "Both files", "All files" });
				fileDialog.setFilterExtensions(new String[] { "*.txt", "*.exe",
						"*.exe;*.txt", "*.*" });

				fileDialog.open();
			}
		});
		openFiledialogButton.setText("Open FileDialog");
		openFiledialogButton.setBounds(49, 24, 115, 25);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

}
