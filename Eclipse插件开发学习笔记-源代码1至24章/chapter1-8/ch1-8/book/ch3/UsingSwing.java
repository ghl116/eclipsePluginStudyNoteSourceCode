package book.ch3;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingSwing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setBounds(100, 100, 200, 100);
		shell.open();
		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setBounds(0, 0, 200, 100);

		Frame frame = SWT_AWT.new_Frame(composite);
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel, BorderLayout.CENTER);

		JButton button = new JButton();
		button.setBounds(10, 10, 180, 20);
		button.setText("Swing Button");
		panel.add(button);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
