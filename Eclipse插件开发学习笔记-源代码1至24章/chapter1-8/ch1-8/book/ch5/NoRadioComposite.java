package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class NoRadioComposite {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(264,212);
		
		Composite composite = new Composite(shell,SWT.NO_RADIO_GROUP | SWT.BORDER);
		composite.setBounds(10,10,236,158);

		final Composite composite_1 = new Composite(composite, SWT.BORDER);
		composite_1.setBounds(15, 65, 133, 75);

		final Button button = new Button(composite_1, SWT.RADIO);
		button.setText("Radio Button");
		button.setBounds(15, 5, 116, 25);

		final Button button_1 = new Button(composite_1, SWT.RADIO);
		button_1.setBounds(15, 36, 112, 25);
		button_1.setText("Radio Button");

		final Button button_2 = new Button(composite, SWT.RADIO);
		button_2.setBounds(5, 20, 99, 25);
		button_2.setText("Radio Button");

		final Button button_3 = new Button(composite, SWT.RADIO);
		button_3.setBounds(110, 20, 116, 25);
		button_3.setText("Radio Button");
		
		shell.open();
		while(!shell.isDisposed())
			if(!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}

}
