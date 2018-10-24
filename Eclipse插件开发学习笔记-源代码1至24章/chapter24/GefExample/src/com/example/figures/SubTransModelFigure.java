package com.example.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import com.example.GefExamplePlugin;
import com.example.image.IconFactory;
import com.example.image.ImageConstants;

/**
 * TODO 
 * @2006-12-30
 * @author xuli
 */
public class SubTransModelFigure extends Figure {

	private Label name = new Label();

	private SubTransContainerFigure containerFigure = new SubTransContainerFigure();

	public SubTransModelFigure() {
		ToolbarLayout layout = new ToolbarLayout();
		layout.setVertical(true);
		layout.setStretchMinorAxis(true);
		setLayoutManager(layout);
		setBorder(new LineBorder(new Color(null,211,213,220)));
		setOpaque(true);
		name.setIcon(IconFactory.
				getImageDescriptor(ImageConstants.FRAME_LOGO).createImage());
		name.setIconAlignment(Label.LEFT);
		Font f = new Font(GefExamplePlugin.getActiveWorkbenchShell().getDisplay(),"courier", 10, 1); 
		name.setFont(f);
		name.setText("Á÷³Ì");
		name.setIconTextGap(10);
		name.setLabelAlignment(PositionConstants.LEFT);
		add(name);
		add(containerFigure);
		this.setOpaque(false);
	}

	public SubTransContainerFigure getContainerFigure() {
		return containerFigure;
	}

	public void setName(String name) {
		this.name.setText(name);
	}

	public void setIcon(Image icon){
		name.setIcon(icon);
	}

	public void setIcon(String icon){
		name.setIcon(IconFactory.
				getImageDescriptor(icon).createImage());
	}

	public void paint(Graphics graphics){
		graphics.pushState();
		Rectangle bound = this.getBounds().getCopy();
		bound.height -= 2;
		bound.width -= 2;
		graphics.setBackgroundColor(new Color(null,246,246,246));
		graphics.setForegroundColor(new Color(null,246,246,246));
		graphics.fillGradient(bound, false);
		graphics.setLineStyle(SWT.LINE_SOLID);
		graphics.setForegroundColor(new Color(null,211,213,220));
		graphics.drawRoundRectangle(bound, 8, 8);
		bound.height = 9;
		graphics.setBackgroundColor(ColorConstants.lightBlue);
		graphics.setForegroundColor(new Color(null,246,246,246));
		graphics.fillGradient(bound, true);
		bound.y +=9;
		bound.height = 10;
		graphics.setBackgroundColor(ColorConstants.lightBlue);
		graphics.setForegroundColor(ColorConstants.lightBlue);
		graphics.fillGradient(bound, true);
		bound.y +=10;
		bound.height = 9;
		graphics.setForegroundColor(ColorConstants.lightBlue);
		graphics.setBackgroundColor(new Color(null,246,246,246));
		graphics.fillGradient(bound, true);
		bound.height = 28;
		bound.y -= 19;
		graphics.popState();
		super.paint(graphics);
	}
}
