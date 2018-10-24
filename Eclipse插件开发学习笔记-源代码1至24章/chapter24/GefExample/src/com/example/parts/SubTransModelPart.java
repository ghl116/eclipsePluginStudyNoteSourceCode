package com.example.parts;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.example.figures.SubTransModelFigure;
import com.example.model.FNode;
import com.example.model.FStepModel;
import com.example.model.FSubTransModel;
import com.example.policies.NodeEditPolicy;
import com.example.policies.SubTransLayoutEditPolicy;

/**
 * TODO 
 * @2007-1-18
 * @author xuli
 */
public class SubTransModelPart extends AbstractPart {

	private SubTransModelFigure expandedFigure = new SubTransModelFigure();
	private boolean ifCollapse = false;

	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_OPEN){
			openAction();
		}
	}

	private void openAction(){
		getSubject().setCollapsed(!getSubject().isCollapsed());
		for(Iterator iter = ((FSubTransModel)this.getModel()).getChildren().iterator();iter.hasNext();){
			FStepModel step = (FStepModel)iter.next();
			if(getSubject().isCollapsed()){
				step.getRoot().hide();
				step.getRoot().hideAllChildVsb();
				ifCollapse = true;
			}else{
				step.getRoot().show();
				step.getRoot().showAllChildVsb();
				ifCollapse = false;
			}
		}
		for(Iterator iter = this.getChildren().iterator();iter.hasNext();){
			StepPart step = (StepPart)iter.next();
			step.refreshAll();
		}
	}

	public boolean getState(){
		return this.ifCollapse;
	}

	public IFigure getContentPane() {
		if (!getSubject().isCollapsed())
			return expandedFigure.getContainerFigure();
		else
			return null;
	}

	protected IFigure createFigure() {
		if(expandedFigure == null){
			return expandedFigure = new SubTransModelFigure();
		}
		return expandedFigure;
	}

	public IFigure getFigure() {

		return expandedFigure;
	}

	protected void refreshVisuals() {
		//super.refreshVisuals();
		Dimension tsize = ((FSubTransModel) getModel()).getSize().getCopy();
		if (!getSubject().isCollapsed()) {
			expandedFigure.setName(((FNode) this.getModel()).getName() );
			Dimension msize = this.calculateSize();
			if(this.getChildren().size() > 1){
				tsize.height = msize.height+17;
				tsize.width = msize.width;
				FSubTransModel model = (FSubTransModel)getModel();
				model.setSize(tsize);
			}else{
				if(msize.width > tsize.width || msize.height > tsize.height){
					tsize.height = msize.height+17;
					FSubTransModel model = (FSubTransModel)getModel();
					model.setSize(tsize);
				}
			}
			Rectangle rectangle = new Rectangle(getSubject().getLocation(), ((FSubTransModel)getModel()).getSize());
			getFigure().setBounds(rectangle);
		} else {
			expandedFigure.setName(((FNode) this.getModel()).getName());
			tsize.height = 28;
			Rectangle rectangle = new Rectangle(getSubject().getLocation(), tsize);
			getFigure().setBounds(rectangle);

		}
	}

	private Dimension calculateSize(){
		int maxW = 0;
		int maxH = 0;
		List steps = this.getChildren();
		if(steps != null && steps.size() > 0){

			StepPart temp = null;
			Dimension s = null;
			Point l = null;
			for(int i = 0; i < steps.size(); i ++){
				temp = (StepPart)steps.get(i);
				s = ((FStepModel)temp.getModel()).getSize();
				l = ((FStepModel)temp.getModel()).getLocation();
				if(l.x +s.width > maxW){
					maxW = l.x + s.width + 50;
				}
				if(l.y + s.height > maxH){
					maxH = l.y + s.height + 50;
				}
			}
		}
		return new Dimension(maxW,maxH);
	}
	
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new SubTransLayoutEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeEditPolicy());
	}

	protected List getModelChildren() {
		return getSubject().getChildren();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		super.propertyChange(evt);
		String pName = evt.getPropertyName();
		if(pName.equals(FStepModel.PRO_FIGURE)){
			this.refreshVisuals();
		}
		if(pName.equals(FStepModel.PRO_CHILD)){
			this.refreshChildren();  // important!!!
			this.refreshVisuals();

		}
		if (FSubTransModel.PROP_COLLAPSED.equals(evt.getPropertyName())) {

			refreshVisuals();
			refreshSourceConnections();
			refreshTargetConnections();
		}
	}

	protected FSubTransModel getSubject() {
		return (FSubTransModel) getModel();
	}

	protected List getModelSourceConnections() {
		return Collections.EMPTY_LIST;
	}

	protected List getModelTargetConnections() {
		return Collections.EMPTY_LIST;
	}

	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		// TODO Auto-generated method stub
		return null;
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		// TODO Auto-generated method stub
		return null;
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}
