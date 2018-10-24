package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MonitorMenu {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		final Shell shell = new Shell(display);
		shell.setSize(200, 200);
		shell.setText("Menu Demo");

		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);

		MenuItem fileMenuItem = new MenuItem(bar, SWT.CASCADE);
		fileMenuItem.setText("File");

		final Menu fileMenu = new Menu(fileMenuItem);
		fileMenuItem.setMenu(fileMenu);

		final MenuItem openMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		
		openMenuItem.addHelpListener(new HelpListener() {
			public void helpRequested(final HelpEvent e) {
				MessageBox msgBox = new MessageBox(shell, SWT.ICON_INFORMATION
						| SWT.OK);
				msgBox.setText("Help Message");
				msgBox.setMessage("Choose this to open a new file");
				msgBox.open();
			}
		});
		openMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				FileDialog fd = new FileDialog(shell,SWT.OPEN);
				fd.open();
			}
		});
		openMenuItem.setText("Open");

		final MenuItem exitMenuItem = new MenuItem(fileMenu, SWT.NONE);
		exitMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				shell.close();
			}
		});
		exitMenuItem.setText("Exit");

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

}
