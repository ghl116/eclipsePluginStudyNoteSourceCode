package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class MonitorTab {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell tabfolderDemoShell = new Shell(display);
		tabfolderDemoShell.setText("TabFolder Demo");
		tabfolderDemoShell.setSize(270, 288);

		Image image1 = new Image(display, MonitorTab.class
				.getResourceAsStream("image1.bmp"));
		Image image2 = new Image(display, MonitorTab.class
				.getResourceAsStream("image2.bmp"));

		final TabFolder tabFolder = new TabFolder(tabfolderDemoShell, SWT.NONE);
		
		final TabItem tab1TabItem = new TabItem(tabFolder, SWT.NONE);
		tab1TabItem.setImage(image1);
		tab1TabItem.setText("Tab 1");

		final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tab1TabItem.setControl(composite_1);

		final TabItem tab2TabItem = new TabItem(tabFolder, SWT.NONE);
		tab2TabItem.setText("Tab 2");
		tab2TabItem.setImage(image2);
		
		tabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int index = tabFolder.getSelectionIndex();
				TabItem item = tabFolder.getItem(index);
				System.out.println("The tab with name " + item.getText()
						+ " is selected");
			}
		});
		tabFolder.setBounds(10, 10, 242, 234);

		tabfolderDemoShell.open();

		while (!tabfolderDemoShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		image1.dispose();
		image2.dispose();
		display.dispose();
	}

}
