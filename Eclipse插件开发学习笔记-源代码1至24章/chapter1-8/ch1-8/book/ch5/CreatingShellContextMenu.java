package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class CreatingShellContextMenu {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		final Shell shell = new Shell(display);
		shell.setSize(100, 120);

		Composite container = new Composite(shell, SWT.BORDER);
		container.setBounds(10, 10, 80, 80);

		Menu menu = new Menu(shell, SWT.POP_UP);
		/*
		 * 在创建Menu的时候，我们也可以直接调用Menu(Control control)的构造函数
		 * 这个构造函数会自己寻找Control实例最上层的Shell容器， 并以POP_UP为默认style参数调用 Menu(Shell
		 * shell, int style)的构造函数. 因此上面的构造函数也可以这样写:Menu menu = new
		 * Menu(container); 效果完全一样
		 */
		container.setMenu(menu);

		MenuItem menuItem1 = new MenuItem(menu, SWT.NONE);
		menuItem1.setText("Menu item1");
		menuItem1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				MessageBox mb = new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
				mb.setText("Notification");
				mb.setMessage("Item 1 has been selected!");
				mb.open();
			}
		});

		MenuItem menuItem2 = new MenuItem(menu, SWT.NONE);
		menuItem2.setText("Menu item2");

		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

}
