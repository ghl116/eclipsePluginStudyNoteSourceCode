package book.ch3;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MyShell extends Shell {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell myShell = new MyShell(display);
		myShell.open();
		display.dispose();
	}

	public MyShell(Display display) {
		super(display);
	}

	public void checkSubclass() {

	}

}
