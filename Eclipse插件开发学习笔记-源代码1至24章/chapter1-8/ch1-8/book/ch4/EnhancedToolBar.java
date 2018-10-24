package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class EnhancedToolBar {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(273, 150);

		final ToolBar horToolBar = new ToolBar(shell, SWT.SHADOW_OUT | SWT.RIGHT);
		horToolBar.setBounds(0, 0, 500, 50);

		
		final ToolItem toolItem1 = new ToolItem(horToolBar, SWT.SEPARATOR);
		Text text = new Text(horToolBar,SWT.BORDER);
		text.setSize(120,20);
		toolItem1.setControl(text);
		toolItem1.setWidth(120);
		toolItem1.setText("DROP DOWN");
		
		

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();
	}

}
