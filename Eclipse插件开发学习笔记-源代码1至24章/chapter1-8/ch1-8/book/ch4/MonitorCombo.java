package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MonitorCombo {

	private static Combo combo;

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(200, 138);
		shell.setText("Combo Demo");

		combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] { "a", "b", "c", "d" });
				combo.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent e) {
						Combo combo = (Combo)e.widget;
						String text = combo.getText();
						System.out.println("Combo Text is " + text);
					}
				});
		combo.setBounds(22, 10, 106, 20);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}
	}
