package com.example.ui;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;

/**
 * TODO 
 * @2006-12-28
 * @author xuli
 */
public class MyRootEditPart extends ScalableFreeformRootEditPart {
	protected LayeredPane createPrintableLayers() {
		FreeformLayeredPane layeredPane = new FreeformLayeredPane();
		layeredPane.add(new BackgroundLayer(), BackgroundLayer.BACKGROUND_LAYER);
		layeredPane.add(new FreeformLayer(), PRIMARY_LAYER);
		layeredPane.add(new ConnectionLayer(), CONNECTION_LAYER);
		return layeredPane;
	}
}
