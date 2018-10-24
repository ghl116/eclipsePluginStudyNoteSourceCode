package com.example.parts;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.example.figures.BorderAnchor;
import com.example.figures.StepFigure;
import com.example.model.FNode;
import com.example.model.FStepModel;
import com.example.model.FSubTransModel;
import com.example.model.TreeItemModel;
import com.example.policies.NodeDirectEditPolicy;
import com.example.policies.StepEditPolicy;

/**
 * TODO 
 * @2006-12-30
 * @author xuli
 */
public class StepPart extends AbstractPart {

	public void performRequest(Request req) {

		if(req.getType().equals(RequestConstants.REQ_OPEN)){
			expandAction();
		}
	}

	private void expandAction(){
		FStepModel step = (FStepModel)this.getModel();
		if(step.isExpand()){
			step.setExpand(false);
			step.getRoot().hideAllChildVsb();
			step.getRoot().hide();
			step.collapse();
			this.refreshAll();
		}else{
			step.setExpand(true);
			step.getRoot().showAllChildVsb();
			step.getRoot().show();
			step.expand();
			this.refreshAll();
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		super.propertyChange(evt);
		String pName = evt.getPropertyName();
		if (evt.getPropertyName().equals(FNode.PROP_LOCATION)){
			if(((FStepModel)this.getModel()).getLocation().x <((FSubTransModel)((SubTransModelPart)this.getParent()).getModel()).getLocation().x){
				Point loc = new Point(50,((FStepModel)this.getModel()).getLocation().y);
				((FStepModel)this.getModel()).setLocation(loc);
			}
			((SubTransModelPart)this.getParent()).refresh();
		}
		if(pName.equals(FStepModel.PRO_FIGURE)){
			this.refreshVisuals();
		}
		if(pName.equals(FStepModel.PRO_CHILD)){
			this.refreshChildren();  // important!!!
			this.refreshAll();
		}
		if(pName.equals(FStepModel.PROP_SIZE)){
			this.refreshChildren();  // important!!!
			this.refreshAll();
		}
	}

	protected IFigure createFigure() {
		return new StepFigure((FStepModel)this.getModel());
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new StepEditPolicy());
	}

	public void activate() {
		super.activate();
		if (isActive()) {
			return;
		}
		((FNode) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		if (!isActive()) {
			return;
		}
		((FNode) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
		refreshSubTransModel();
		StepFigure nf = (StepFigure) this.getFigure();
		FStepModel node = (FStepModel) getModel();
		Point loc = node.getLocation();
		Dimension size = node.getSize();
		Rectangle rectangle = new Rectangle(loc, size);
		nf.setName(((FNode) this.getModel()).getName());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, nf, rectangle);
		node.setRootLocation(nf.getLocation());

	}

	public void refreshSubTransModel(){
		this.getParent().refresh();
	}

	public void refreshAll(){
		this.refresh();
		TreeItemPart part = null;
		for(int i = 0; i < this.getChildren().size(); i++){
			part = (TreeItemPart)this.getChildren().get(i);
			part.refreshAll();
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List getModelChildren() {

		return ((FStepModel)getModel()).getChildren();
	}

	protected TreeItemModel getStepRoot() {
		return (TreeItemModel) ((FStepModel)getModel()).getRoot();
	}

	protected List getModelSourceConnections() {
		if(((FStepModel)this.getModel()).isExpand()
				|| ((SubTransModelPart)this.getParent()).getState())
			return Collections.EMPTY_LIST;
		return getStepRoot().getAllOutgoings();
	}

	protected List getModelTargetConnections() {
		if(((FStepModel)this.getModel()).isExpand()
				|| ((SubTransModelPart)this.getParent()).getState())
			return Collections.EMPTY_LIST;
		return getStepRoot().getAllIncomings();
	}

	//*******************************
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new BorderAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new BorderAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new BorderAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new BorderAnchor(getFigure());
	}
}