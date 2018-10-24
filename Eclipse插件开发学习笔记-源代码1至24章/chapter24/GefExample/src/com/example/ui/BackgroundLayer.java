package com.example.ui;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;

/**
 * TODO 
 * @2006-12-28
 * @author xuli
 */
public class BackgroundLayer extends FreeformLayer {
	public static final String BACKGROUND_LAYER = "Background Layer"; //$NON-NLS-1$

	public BackgroundLayer() {
		setOpaque(true);
	}


	protected void paintFigure(Graphics graphics) {
		if(isOpaque()) {
			graphics.setForegroundColor(ColorConstants.white);
			graphics.setBackgroundColor(ColorConstants.blue);
			graphics.fillGradient(getBounds(), true);
		}
	}
}
