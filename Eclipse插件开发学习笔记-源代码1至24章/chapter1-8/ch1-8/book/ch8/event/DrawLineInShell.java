package book.ch8.event;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DrawLineInShell {
	public static void main(String args[]) {

		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setSize(300, 300);
		shell.setText("Draw Line In Shell");
		shell.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		MouseTracker tracker = new MouseTracker(shell);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	protected static class MouseTracker implements MouseListener,
			MouseMoveListener {

		protected boolean drawing = false;

		protected Point originPoint = new Point(0, 0);

		protected Canvas canvas;

		public MouseTracker(Canvas canvas) {
			this.canvas = canvas;
			canvas.addMouseListener(this);
			canvas.addMouseMoveListener(this);
		}

		public void mouseDown(MouseEvent e) {
			originPoint.x = e.x;
			originPoint.y = e.y;
			drawing = true;
		}

		public void mouseUp(MouseEvent e) {
			drawing = false;
		}

		public void mouseMove(MouseEvent e) {
			GC gc = new GC(canvas);
			if (drawing) {
				gc.drawLine(originPoint.x, originPoint.y, e.x, e.y);
				originPoint.x = e.x;
				originPoint.y = e.y;
			}
		}

		public void mouseDoubleClick(MouseEvent e) {

		}

	}
}
