package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ComboCoolBar {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(300, 100);
		shell.setText("CoolBar Demo");

		final CoolBar coolBar = new CoolBar(shell, SWT.NONE);
		coolBar.setBounds(0, 0, 400, 30);

		final CoolItem coolItem1 = new CoolItem(coolBar, SWT.PUSH);
		final Combo combo = new Combo(coolBar, SWT.NONE);
		combo.setSize(80, 25);
		coolItem1.setControl(combo);
		coolItem1.setSize(coolItem1.computeSize(80, 25));

		final CoolItem newItemCoolItem = new CoolItem(coolBar, SWT.PUSH);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
