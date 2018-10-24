package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingCombo {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell comboDemoShell = new Shell(display);
		comboDemoShell.setText("Combo Demo");
		comboDemoShell.setSize(197, 113);

		final Combo combo = new Combo(comboDemoShell, SWT.NONE);
		combo.setItems(new String[] { "a", "b", "c", "d" });
		combo.add("abc");
		combo.add("def");
		combo.setBounds(22, 10, 104, 20);
		comboDemoShell.open();

		while (!comboDemoShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

}
