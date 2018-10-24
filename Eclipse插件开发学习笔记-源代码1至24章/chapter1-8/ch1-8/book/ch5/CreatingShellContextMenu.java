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
		 * �ڴ���Menu��ʱ������Ҳ����ֱ�ӵ���Menu(Control control)�Ĺ��캯��
		 * ������캯�����Լ�Ѱ��Controlʵ�����ϲ��Shell������ ����POP_UPΪĬ��style�������� Menu(Shell
		 * shell, int style)�Ĺ��캯��. �������Ĺ��캯��Ҳ��������д:Menu menu = new
		 * Menu(container); Ч����ȫһ��
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
