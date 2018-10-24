package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class UsingLabel {

	public static void main(String[] args) {
		Display display = Display.getDefault();

		final Image image1 = new Image(display, UsingLabel.class
				.getResourceAsStream("image1.bmp"));
		final Image image2 = new Image(display, UsingLabel.class
				.getResourceAsStream("image2.bmp"));

		final Shell shell = new Shell(display);
		shell.setSize(80, 150);

		final Label label = new Label(shell, SWT.BORDER);
		label.setImage(image1);
		label.setBounds(10, 10, 60, 50);

		final Label textLabel = new Label(shell, SWT.BORDER);
		textLabel.setBounds(10, 70, 60, 15);
		textLabel.setText("Image 1");

		final Button switchButton = new Button(shell, SWT.NONE);
		switchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				label.setImage(image2);
				textLabel.setText("Image 2");
			}
		});
		switchButton.setText("Switch");
		switchButton.setBounds(10, 91, 60, 19);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		image1.dispose();
		image2.dispose();
		display.dispose();
	}

}
