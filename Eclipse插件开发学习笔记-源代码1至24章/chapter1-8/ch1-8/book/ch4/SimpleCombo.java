package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SimpleCombo {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(200, 138);

		Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] { "a", "b", "c", "d" });
		combo.setBounds(22, 10, 62, 20);
		combo.add("abc");
		combo.add("def");

		Combo combo_2 = new Combo(shell, SWT.SIMPLE);
		combo_2.setItems(new String[] { "a", "b", "c", "d" });
		combo_2.setBounds(100, 10, 62, 79);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

}
