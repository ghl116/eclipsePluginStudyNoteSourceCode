package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingFormLayout {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new FormLayout());
		shell.setSize(200,200);
		shell.open();

		final Button buttonA = new Button(shell,SWT.NONE);
		buttonA.setText("Button A");
		FormData buttonDataA = new FormData();
		buttonDataA.left = new FormAttachment(20,1);
		buttonDataA.right = new FormAttachment(50);
		buttonDataA.top = new FormAttachment(0);
		buttonDataA.bottom = new FormAttachment(50);
		buttonA.setLayoutData(buttonDataA);

		final Button buttonB = new Button(shell, SWT.NONE);
		final FormData buttonDataB = new FormData();
		buttonDataB.top = new FormAttachment(buttonA);
		buttonDataB.left = new FormAttachment(buttonA,0,SWT.RIGHT);
		buttonDataB.right = new FormAttachment(buttonA,60,SWT.RIGHT);
		buttonDataB.bottom = new FormAttachment(buttonA,20,SWT.BOTTOM);
		buttonB.setLayoutData(buttonDataB);
		buttonB.setText("Button B");
	
		shell.layout();
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch())
				display.sleep();
		}
	}

}
