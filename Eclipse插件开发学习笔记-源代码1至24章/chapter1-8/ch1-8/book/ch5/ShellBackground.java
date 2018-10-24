package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import com.swtdesigner.SWTResourceManager;

public class ShellBackground extends Shell {

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ShellBackground shell = new ShellBackground(display, SWT.SHELL_TRIM|SWT.CLOSE);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell
	 * @param display
	 * @param style
	 */
	public ShellBackground(Display display, int style) {
		super(display, style);
		createContents();
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(186,151);

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		composite.setBounds(5, 5, 80, 110);

		final Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(ShellBackground.class, "image1.bmp"));
		composite_1.setBounds(91, 5, 80, 110);
		//
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
