package book.ch3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

public class UsingSystemTray {

	public static void main(String[] args) {
		Display display = Display.getDefault();

		Shell shell = new Shell(display);
		shell.setSize(120, 80);

		shell.open();

		Tray systemTray = display.getSystemTray();
		TrayItem newItem = new TrayItem(systemTray, SWT.NONE);
		newItem.setImage(display.getSystemImage(SWT.ICON_INFORMATION));
		newItem.setToolTipText("Test Tray!");

		final Menu menu = new Menu(shell, SWT.POP_UP);
		MenuItem item1 = new MenuItem(menu, SWT.PUSH);
		item1.setText("Menu Item 1");
		MenuItem item2 = new MenuItem(menu, SWT.PUSH);
		item2.setText("Menu Item 2");

		newItem.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				menu.setVisible(true);
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		newItem.dispose();
		display.dispose();
	}
}
