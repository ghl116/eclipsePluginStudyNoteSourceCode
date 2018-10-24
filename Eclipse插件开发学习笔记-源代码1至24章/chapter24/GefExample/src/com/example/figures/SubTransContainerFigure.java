package com.example.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class SubTransContainerFigure extends Figure {
	public SubTransContainerFigure() {
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		setBorder(new SubTransContainerBorder());
		setOpaque(false);
	}
}