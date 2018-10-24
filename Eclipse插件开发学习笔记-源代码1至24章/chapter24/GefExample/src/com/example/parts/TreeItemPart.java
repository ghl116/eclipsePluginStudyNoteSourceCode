package com.example.parts;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.example.figures.BorderAnchor;
import com.example.figures.TreeItemFigure;
import com.example.model.FNode;
import com.example.model.FStepModel;
import com.example.model.TreeItemModel;
import com.example.policies.NodeGraphicalNodeEditPolicy;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class TreeItemPart extends AbstractPart {

	public void performRequest(Request req) {
		if(req.getType().equals(RequestConstants.REQ_OPEN)){
			expandAction();
		}
	}

	private void expandAction(){
		TreeItemModel m = (TreeItemModel)this.getModel();
		if(m.getExpand()== TreeItemModel.ITEM_EXPAND){
			m.setExpand(TreeItemModel.ITEM_COLLAPSED);
			m.hideAllChildVsb();
			((FStepModel)m.findRoot().getParent()).refreshRegion();
			StepPart step = (StepPart)this.findStepPart();
			if(step != null){
				step.refreshAll();
			}
		}else{
			if(m.getExpand() == TreeItemModel.ITEM_COLLAPSED){
				m.setExpand(TreeItemModel.ITEM_EXPAND);
				//m.setAllChildVsb(true);
				m.showAllChildVsb();
				((FStepModel)m.findRoot().getParent()).refreshRegion();
				StepPart step = (StepPart)this.findStepPart();
				if(step != null){
					step.refreshAll();
				}
			}
		}
	}

	public EditPart findStepPart(){
		EditPart step = this.getParent();
		if(step instanceof StepPart){
			return step;
		}else{
			if(step instanceof TreeItemPart){
				return ((TreeItemPart)step).findStepPart();
			}
		}
		return null;
	}

	public void activate() {
		super.activate();
		if (isActive()) {
			return;
		}
	}

	public void deactivate() {
		super.deactivate();
		if (!isActive()) {
			return;
		}
	}

	protected IFigure createFigure() {
		return new TreeItemFigure((TreeItemModel)this.getModel());
	}

	public IFigure getFigure() {
		return super.getFigure();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(FNode.PROP_LOCATION))
			refreshVisuals();
		else if (evt.getPropertyName().equals(FNode.PROP_NAME))
			refreshItemName();
		else if (evt.getPropertyName().equals(FNode.PROP_INPUTS))
			refreshTargetConnections();
		else if (evt.getPropertyName().equals(FNode.PROP_OUTPUTS))
			refreshSourceConnections();
	}
	protected void createEditPolicies() {
		TreeItemModel model = (TreeItemModel)this.getModel();
		if(model.getType() == TreeItemModel.TYPE_COLUMN){
			installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
		}
	}
	
	protected List getModelChildren() {
		return ((TreeItemModel)getModel()).getChildren();
	}

	protected void refreshVisuals() {
		TreeItemFigure ti = (TreeItemFigure) this.getFigure();

		TreeItemModel node = (TreeItemModel) getModel();
		Point loc = node.getLocation();
		Dimension size = node.refreshRegion();
		ti.setName(node.getName());
		Rectangle rectangle = new Rectangle(loc, size);
		ti.setBounds(rectangle);
	}

	private void refreshItemName(){
		TreeItemFigure ti = (TreeItemFigure) this.getFigure();
		TreeItemModel node = (TreeItemModel) getModel();
		ti.setName(node.getName());
	}
	
	public void refreshAll(){
		refreshVisuals();
		refreshSourceConnections();
		refreshTargetConnections();
		TreeItemPart part = null;
		for(int i = 0; i < this.getChildren().size(); i++){
			part = (TreeItemPart)this.getChildren().get(i);
			part.refreshAll();
		}
	}
//	------------------------------------------------------------------------
	// Abstract methods from NodeEditPart

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

	protected TreeItemModel getSubject() {
		return (TreeItemModel) getModel();
	}

	protected List getModelSourceConnections() {
		return getSubject().getShowOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return getSubject().getShowIncomingConnections();
	}

}
