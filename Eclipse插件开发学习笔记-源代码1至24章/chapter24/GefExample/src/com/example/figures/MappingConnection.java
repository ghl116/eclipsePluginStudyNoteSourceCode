package com.example.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class MappingConnection extends PolylineConnection {

	protected void outlineShape(Graphics g) {
		g.pushState();
		g.setForegroundColor(ColorConstants.blue);
		g.drawLine(this.getStart(), this.getEnd());
		g.popState();
	}
}
