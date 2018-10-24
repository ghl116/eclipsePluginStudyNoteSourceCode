package com.example.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import com.example.image.IconFactory;
import com.example.model.TreeItemModel;

/**
 * TODO 
 * @2006-12-22
 * @author xuli
 */
public class TreeItemFigure extends Shape{
	private ImageFigure expImg = null;
	private ImageFigure typeImg = null;
	private Label label = null;
	private TreeItemModel model = null;

	public TreeItemFigure(){
		super();
		init();

	}
	public TreeItemFigure(TreeItemModel model){
		this.model = model;
		//this.setSize(model.getSize());
		init();
	}
	public void init(){
		createLabel();
		this.add(label);
		getExpImg();
		getTypeImg();
	}

	public void createLabel(){
		label = new Label();
		//label.setBackgroundColor(ColorConstants.red);
		label.setText(model.getName());
		label.setTextPlacement(PositionConstants.NORTH );
		label.setTextAlignment(PositionConstants.LEFT );
		label.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent me) {

				label.setForegroundColor(ColorConstants.orange);

			}

			public void mouseExited(MouseEvent me) {
				label.setForegroundColor(ColorConstants.black);

			}

			public void mouseHover(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			public void mouseMoved(MouseEvent me) {
				// TODO Auto-generated method stub

			}
		});
	}

	public Point getLeftAnchorLoc(){
		Point rst = Point.SINGLETON;
		rst.x = this.getBounds().x + 15;
		rst.y = this.getBounds().y + 10;
		return rst;
	}
	public Point getRightAnchorLoc(){
		Point rst = Point.SINGLETON;
		rst.x = this.label.getTextBounds().x + 86;
		rst.y = this.label.getTextBounds().y+6;
		return rst;
	}

	private void getExpImg(){
		try {
			Rectangle temp = null;
			if(expImg != null){
				temp = expImg.getBounds();
				this.remove(expImg);

			}
			String img = model.getExpImg();
			if(img != null){
				expImg = new ImageFigure(IconFactory.
						getImageDescriptor(img).createImage());
				if(temp != null){
					expImg.setBounds(temp);
				}
				this.add(expImg);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getTypeImg(){
		try {
			if(typeImg != null){
				this.remove(typeImg);
			}
			typeImg = new ImageFigure(IconFactory.
					getImageDescriptor(model.getTypeImg()).createImage());
			this.add(typeImg);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void fillShape(Graphics graphics) {
		
	}

	protected void outlineShape(Graphics graphics) {
		graphics.pushState();
		PointList plist = new PointList();
		Rectangle tmp = this.getBounds();

		plist.addPoint(tmp.x+6, tmp.y);
		plist.addPoint(tmp.x+6, tmp.y +10);
		plist.addPoint(tmp.x+15,tmp.y+10);

		int l = 0;
		if(!model.isIfroot()){
			TreeItemModel pa = (TreeItemModel)model.getParent();
			int ccount = pa.getChildren().size();
			if(pa.getChildren().indexOf(model) != ccount-1){
				l +=model.refreshRegion().height;
				if(ccount >1){
					PointList plist2 = new PointList();
					plist2.addPoint(tmp.x+6, tmp.y +10);
					plist2.addPoint(tmp.x+6, tmp.y +10+l);
					plist2.addPoint(tmp.x+20, tmp.y +10+l);
				}
			}
		}
		graphics.popState();
	}

	public void setBounds(Rectangle rect) {

		super.setBounds(rect);

		if(this.expImg != null){
			getExpImg();
			this.expImg.setBounds(new Rectangle(model.getExpLocation(),model.getExpSize()));
		}
		this.typeImg.setBounds(new Rectangle(model.getTypeLocation(),model.getTypeSize()));
		this.label.setBounds(new Rectangle(model.getLabelLocation(),model.getLabelSize()));
	}

	public void setName(String text){
		this.label.setText(text);
	}

}
