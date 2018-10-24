package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingCoolBar {
	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(300, 100);
		shell.setText("CoolBar Demo");

		Image toolBarImage1 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar1.gif"));
		Image toolBarImage2 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar2.gif"));
		Image toolBarImage3 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar3.gif"));
		Image toolBarImage4 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar4.gif"));

		final CoolBar coolBar = new CoolBar(shell, SWT.NONE);
		coolBar.setBounds(0, 0, 400, 30);

		final CoolItem coolItem1 = new CoolItem(coolBar, SWT.PUSH);
		final Button button1 = new Button(coolBar, SWT.NONE);
		button1.setImage(toolBarImage1);
		button1.setSize(25, 25);
		coolItem1.setControl(button1);
		coolItem1.setSize(coolItem1.computeSize(25, 25));

		final CoolItem coolItem2 = new CoolItem(coolBar, SWT.PUSH);
		final Button button2 = new Button(coolBar, SWT.NONE);
		button2.setImage(toolBarImage2);
		button2.setSize(25, 25);
		coolItem2.setControl(button2);
		coolItem2.setSize(coolItem2.computeSize(25, 25));
		
		final CoolItem coolItem3 = new CoolItem(coolBar, SWT.PUSH);
		final Button button3 = new Button(coolBar, SWT.NONE);
		button3.setImage(toolBarImage3);
		button3.setSize(25, 25);
		coolItem3.setControl(button3);
		coolItem3.setSize(coolItem3.computeSize(25, 25));
		
		final CoolItem coolItem4 = new CoolItem(coolBar, SWT.PUSH);
		final Button button4 = new Button(coolBar, SWT.NONE);
		button4.setImage(toolBarImage4);
		button4.setSize(25, 25);
		coolItem4.setControl(button4);
		coolItem4.setSize(coolItem4.computeSize(25, 25));

		final CoolItem newItemCoolItem = new CoolItem(coolBar, SWT.PUSH);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		toolBarImage1.dispose();
		toolBarImage2.dispose();
		toolBarImage3.dispose();
		toolBarImage4.dispose();
		display.dispose();
	}

}
