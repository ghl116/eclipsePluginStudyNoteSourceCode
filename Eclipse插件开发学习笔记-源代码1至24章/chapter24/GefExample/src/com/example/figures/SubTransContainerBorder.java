package com.example.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class SubTransContainerBorder extends AbstractBorder {

	public Insets getInsets(IFigure figure) {
		return new Insets(1, 0, 0, 0);
	}

	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		Point left = new Point(getPaintRectangle(figure, insets).getTopLeft().x,getPaintRectangle(figure, insets).getTopLeft().y + 10);
		Point right = new Point(getPaintRectangle(figure, insets).getTopRight().x,getPaintRectangle(figure, insets).getTopRight().y + 10);
		graphics.drawLine(left, right);
	}
}
