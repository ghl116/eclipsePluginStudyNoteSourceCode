package book.ch3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UsingRegion {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();

		final Shell shell = new Shell(display, SWT.NO_TRIM);

		Region region = new Region(display);
		region.add(new Rectangle(10, 10, 10, 100));
		region.add(new Rectangle(10, 100, 100, 10));
		region.add(new Rectangle(10,10,100,10));
		region.add(new Rectangle(100,10,10,100));

		shell.setRegion(region);
		Color color = new Color(null, 255, 0, 0);
		shell.setBackground(color);

		shell.open();
		
		shell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent event) {
				shell.close();
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
