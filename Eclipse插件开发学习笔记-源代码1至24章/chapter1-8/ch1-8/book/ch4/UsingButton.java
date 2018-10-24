package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingButton {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(200, 160);
		shell.setText("Button Demo");

		Image image = new Image(display, UsingButton.class
				.getResourceAsStream("demo.gif"));
		Button button = new Button(shell, SWT.RIGHT_TO_LEFT);
		button.setImage(image);
		button.setText("Push Button");
		button.setBounds(20, 10, 150, 25);
		
		final Button checkButton = new Button(shell, SWT.CHECK);
		checkButton.setImage(image);
		checkButton.setText("Check Button");
		checkButton.setBounds(20, 45, 150, 20);

		final Button radioButton = new Button(shell, SWT.RADIO);
		radioButton.setImage(image);
		radioButton.setText("Radio Button");
		radioButton.setBounds(20, 70, 150, 20);

		final Button toggleButton = new Button(shell, SWT.TOGGLE);
		toggleButton.setImage(image);
		toggleButton.setText("Toggle Button");
		toggleButton.setBounds(20, 95, 150, 25);

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		image.dispose();
		display.dispose();
	}

}
