package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingFillLayout {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setText("FillLayout Demo");
		shell.setSize(300, 150);
		FillLayout layout = new FillLayout(SWT.VERTICAL);
		layout.marginHeight = 4;
		layout.marginWidth = 6;
		layout.spacing = 10;
		
		shell.setLayout(layout);
		

		Button button = new Button(shell, SWT.NONE);
		button.setText("button1");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("button2");

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.setText("button3");
		shell.open();
		shell.layout();
		
		while(!shell.isDisposed())
			if(!display.readAndDispatch())
				display.sleep();

	}

}
