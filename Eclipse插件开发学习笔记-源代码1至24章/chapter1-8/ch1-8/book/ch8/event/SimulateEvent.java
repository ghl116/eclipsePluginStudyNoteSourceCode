package book.ch8.event;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SimulateEvent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Display display = Display.getDefault();

		final Shell shell = new Shell(display);
		shell.setBounds(0, 0, 140, 140);

		shell.open();

		final Text text = new Text(shell,SWT.BORDER);
		text.setBounds(10,10,100,20);
		
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				display.syncExec(new Runnable() {
					public void run() {
						text.setFocus();
					}
				});
				String string = "Love the method.";
				for (int i = 0; i < string.length(); i++) {
					char ch = string.charAt(i);
					boolean shift = Character.isUpperCase(ch);
					ch = Character.toLowerCase(ch);
					if (shift) {
						Event event = new Event();
						event.type = SWT.KeyDown;
						event.keyCode = SWT.SHIFT;
						display.post(event);
					}
					Event event = new Event();
					event.type = SWT.KeyDown;
					event.character = ch;
					display.post(event);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					event.type = SWT.KeyUp;
					display.post(event);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
					if (shift) {
						event = new Event();
						event.type = SWT.KeyUp;
						event.keyCode = SWT.SHIFT;
						display.post(event);
					}
				}
			}
		}.start();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
