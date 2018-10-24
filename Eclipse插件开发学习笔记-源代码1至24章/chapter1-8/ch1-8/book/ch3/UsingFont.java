package book.ch3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class UsingFont {
	public static void main(String[] args) {
		Display display = Display.getDefault();

		Shell shell = new Shell(display);
		shell.setSize(140, 140);

		shell.open();

		Font sysFont = display.getSystemFont();
		Font createdTahoma = new Font(display, "Tahoma", 10, SWT.BOLD);
		Font createdArial = new Font(display,"Arial",12,SWT.ITALIC);

		Label label1 = new Label(shell, SWT.NONE);
		label1.setBounds(10, 10, 100, 20);
		label1.setText("Tahoma,10,Bold");
		label1.setFont(createdTahoma);

		Label label2 = new Label(shell, SWT.NONE);
		label2.setBounds(10, 40, 100, 20);
		label2.setText("Arial,12,Italic");
		label2.setFont(createdArial);
		
		Label label3 = new Label(shell, SWT.NONE);
		label3.setBounds(10, 80, 100, 20);
		label3.setText("System Font");
		label3.setFont(sysFont);
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		createdTahoma.dispose();
		createdArial.dispose();
		display.dispose();
	}
}
