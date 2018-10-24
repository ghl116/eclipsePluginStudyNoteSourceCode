package com.example.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;


/**
 * TODO 
 * @2006-12-27
 * @author xuli
 */
public class BorderAnchor extends ChopboxAnchor {

	public BorderAnchor(IFigure figure) {
		super(figure);
	}

	public Point getLocation(Point reference) {
		Point p;
		p = getOwner().getBounds().getCenter();
		getOwner().translateToAbsolute(p);
		if (reference.x < p.x){
			if(getOwner() instanceof TreeItemFigure)
				p = ((TreeItemFigure)getOwner()).getLeftAnchorLoc();
			else
				p = ((StepFigure)getOwner()).getLeftAnchorLoc();
		}
		else{
			if(getOwner() instanceof TreeItemFigure)
				p = ((TreeItemFigure)getOwner()).getRightAnchorLoc();
			else
				p = ((StepFigure)getOwner()).getRightAnchorLoc();
		}
		getOwner().translateToAbsolute(p);
		return p;

	}
}
