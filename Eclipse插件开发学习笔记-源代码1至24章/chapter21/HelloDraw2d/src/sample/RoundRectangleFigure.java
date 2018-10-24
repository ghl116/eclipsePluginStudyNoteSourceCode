package sample;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author xuli
 * @2006-12-20
 * TODO 
 */
public class RoundRectangleFigure extends Shape {

	/**
	 * Creates a RectangleFigure.
	 */
	public RoundRectangleFigure() { }

	/**
	 * @see Shape#fillShape(Graphics)
	 */
	protected void fillShape(Graphics graphics) {
		Rectangle r = getBounds();
		int x = r.x + lineWidth / 2;
		int y = r.y + lineWidth / 2;
		int w = r.width - Math.max(1, lineWidth);
		int h = r.height - Math.max(1, lineWidth);
		Color clr = new Color(Display.getCurrent(),0,255,255);
		graphics.setBackgroundColor(clr);
		graphics.fillRoundRectangle(new Rectangle(x,y,w,h), 20,20);
		graphics.drawString("Ô²½Ç¾ØÐÎ", r.getCenter());
	}

	/**
	 * @see Shape#outlineShape(Graphics)
	 */
	protected void outlineShape(Graphics graphics) {
		Rectangle r = getBounds();
		int x = r.x + lineWidth / 2;
		int y = r.y + lineWidth / 2;
		int w = r.width - Math.max(1, lineWidth);
		int h = r.height - Math.max(1, lineWidth);
		graphics.drawRoundRectangle(new Rectangle(x,y,w,h), 20,20);
		
	}
	public static void main(String args[]){

		Shell shell = new Shell();  
		shell.open();
		shell.setText("Draw2d Ô²½Ç¾ØÐÎ");
		LightweightSystem lws = new LightweightSystem(shell);   
		RoundRectangleFigure rfigure = new RoundRectangleFigure();
		lws.setContents(rfigure);

		Display display = Display.getDefault();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ())
				display.sleep ();
		}
	}
}

