package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class MyLayout extends Layout {

	public int margin = 0;
	
	public int spacing = 1;

	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint,
			boolean flushCache) {
		Control[] children = composite.getChildren();
		int width = 0;
		int height = 0;

		for (int i = 0; i < children.length; i++) {
			Control control = children[i];
			Point size = control.computeSize(wHint, hHint);
			MyData data = (MyData) control.getLayoutData();
			width = Math.max(width, size.x);
			height = Math.max(height, size.y * 100 / data.ratio);
		}
		return new Point(width + 2 * margin, height + 2 * margin + (children.length - 1)*spacing);
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		Control[] children = composite.getChildren();
		Rectangle clientArea = composite.getClientArea();
		int width = clientArea.width - 2 * margin;
		int height = clientArea.height - 2 * margin - spacing * (children.length - 1);
		Point current = new Point(clientArea.x+margin, clientArea.y+margin);
		for (int i = 0; i < children.length; i++) {
			Control control = children[i];
			MyData data = (MyData) control.getLayoutData();
			int currentWidth = width;
			int currentHeight = height * data.ratio / 100;
			control.setBounds(current.x, current.y, currentWidth, currentHeight);
			current.y += currentHeight + spacing;
		}
	}

}
