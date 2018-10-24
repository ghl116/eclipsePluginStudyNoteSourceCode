package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CompositeCursor {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(123, 145);

		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(10, 10, 90, 40);

		final Composite composite_1 = new Composite(shell, SWT.BORDER);
		composite_1.setBounds(10, 56, 90, 40);

		Cursor cursor1 = display.getSystemCursor(SWT.CURSOR_HAND);
		composite.setCursor(cursor1);

		ImageData cursorData = new ImageData(CompositeCursor.class
				.getResourceAsStream("cursor.gif"));
		Cursor cursor2 = new Cursor(display, cursorData, 0, 0);
		composite_1.setCursor(cursor2);

		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		cursor2.dispose();
		display.dispose();
	}

}
