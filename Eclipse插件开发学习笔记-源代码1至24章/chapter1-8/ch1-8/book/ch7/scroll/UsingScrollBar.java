package book.ch7.scroll;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.graphics.Rectangle;

public class UsingScrollBar {

	private Shell sShell = null; // @jve:decl-index=0:visual-constraint="10,10"
	private Slider slider = null;

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new Shell(SWT.SHELL_TRIM);
		sShell.setText("Shell");
		sShell.setImage(null);
		sShell.setSize(new Point(499, 228));
//		sShell.setLayout(new GridLayout());
		slider = new Slider(sShell, SWT.NONE);
		slider.setBounds(new Rectangle(31, 46, 419, 16));
		slider.setSelection(40);
		slider.setThumb(30);
		
	}
}
