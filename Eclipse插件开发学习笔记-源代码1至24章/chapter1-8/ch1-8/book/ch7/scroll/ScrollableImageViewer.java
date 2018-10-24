package book.ch7.scroll;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class ScrollableImageViewer extends Composite {

	public ScrollableImageViewer(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		setSize(new Point(300, 200));
		setLayout(new GridLayout());
	}

}
