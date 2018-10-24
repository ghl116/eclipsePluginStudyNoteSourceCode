package hellogef.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;

public class NodeFigure extends Shape {

	private String name;

	private Label label;

	public NodeFigure() {
		this.label = new Label();
		this.add(label);
	}

	public String getText() {
		return this.label.getText();
	}

	public Rectangle getTextBounds() {
		return this.label.getTextBounds();
	}

	public void setName(String name) {
		this.name = name;
		this.label.setText(name);
		this.repaint();
	}

	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
		this.label.setBounds(rect);
	}

	/**
	 * @see Shape#fillShape(Graphics)
	 */
	protected void fillShape(Graphics graphics) {
		graphics.pushState();
		Rectangle bound = this.getBounds().getCopy();

		graphics.setBackgroundColor(ColorConstants.lightGray);
		graphics.fillRoundRectangle(bound, 30, 30);

		graphics.popState();
	}

	protected void outlineShape(Graphics graphics) {
		Rectangle r = getBounds().getCopy();
		r.x = r.x + lineWidth / 2;
		r.y = r.y + lineWidth / 2;
		r.width = r.width - Math.max(1, lineWidth);
		r.height = r.height - Math.max(1, lineWidth);
		graphics.drawRoundRectangle(r,30,30);
	}
}