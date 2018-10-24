package book.ch3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class UsingImage {

	public static void main(String[] args) {
		Display display = Display.getDefault();

		Shell shell = new Shell(display);
		shell.setSize(180, 150);

		shell.open();

		Image iconImage = display.getSystemImage(SWT.ICON_QUESTION);
		shell.setImage(iconImage);
		Image buttonImage = new Image(display, UsingImage.class
				.getResourceAsStream("buttonImage.gif"));

		Button button = new Button(shell, SWT.NONE);
		button.setBounds(10, 50, 140, 50);
		button.setImage(buttonImage);
		button.setText("Image Button");

		Label label = new Label(shell,SWT.NONE);
		label.setImage(buttonImage);
		label.setBounds(10,10,30,30);
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		buttonImage.dispose();

		display.dispose();
	}
}
