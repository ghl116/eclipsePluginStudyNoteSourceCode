package book.ch3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ColoredLabel {

	public static void main(String[] args) {
		Display display = Display.getDefault();

		Shell shell = new Shell(display);
		shell.setSize(120, 80);

		shell.open();

		Color createdWhite = new Color(display,255,255,255);
		Color systemBlack = display.getSystemColor(SWT.COLOR_BLACK);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(10, 10, 100, 20);
		label.setBackground(systemBlack);
		label.setForeground(createdWhite);
		label.setText("Colored label");
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		createdWhite.dispose();

		display.dispose();
	}

}
