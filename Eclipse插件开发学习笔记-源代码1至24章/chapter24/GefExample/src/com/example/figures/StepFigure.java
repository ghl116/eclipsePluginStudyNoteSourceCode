package com.example.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import com.example.image.IconFactory;
import com.example.image.ImageConstants;
import com.example.model.FStepModel;

/**
 * TODO 
 * @2006-12-30
 * @author xuli
 */
public class StepFigure extends Shape {
	private ImageFigure downImg = null;
	private ImageFigure typeImg = null;
	public boolean ifExpand = false;
	private Label label;
	private FStepModel model = null;

	public StepFigure(FStepModel m) {
		this.label = new Label();
		model = m;
		typeImg = new ImageFigure(IconFactory.getImageDescriptor(((FStepModel)model).getIcon()).createImage());
		this.add(typeImg);
		this.downImg = new ImageFigure(IconFactory.getImageDescriptor(ImageConstants.STEP_EXPAND).createImage()); //$NON-NLS-1$
		this.add(downImg);
		label.setText(model.getName());
		label.setTextAlignment(Label.LEFT);
		label.setIconTextGap(5);
		this.add(label);
	}

	public String getText() {
		return this.label.getText();
	}

	public Rectangle getTextBounds() {
		return this.label.getTextBounds();
	}

	public void setName(String name) {
		this.label.setText(name);
		this.repaint();
	}

	public Point getLeftAnchorLoc(){
		Point rst = Point.SINGLETON;
		rst.x = this.getBounds().x;
		rst.y = this.getBounds().y + 15;
		return rst;
	}

	public Point getRightAnchorLoc(){
		Point rst = Point.SINGLETON;
		rst.x = this.getBounds().x + this.getBounds().width;
		rst.y = this.getBounds().y + 15;
		return rst;
	}

	/**
	 * @see Shape#fillShape(Graphics)
	 */
	protected void fillShape(Graphics graphics) {
		
	}

	/**
	 * @see Shape#outlineShape(Graphics)
	 */
	protected void outlineShape(Graphics graphics) {
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
		graphics.setForegroundColor(new Color(null,211,213,220));
		graphics.drawRoundRectangle(bound, 8, 8);

		graphics.popState();
	}

	public void setBounds(Rectangle rect) {
		super.setBounds(rect);

		typeImg.setSize(typeImg.getPreferredSize());
		typeImg.setLocation(new Point(rect.x + 8,rect.y + 6));
		
		Rectangle tmp = new Rectangle(rect.x + 25,rect.y + 2,100,30);
		tmp.height = 25;
		this.label.setBounds(tmp);
		tmp.width = 15;
		tmp.height = 20;
		tmp.x = tmp.x + tmp.width+88;
		tmp.y += 2;
		this.downImg.setBounds(tmp);
	}
}