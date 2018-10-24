package com.example.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.example.model.FTransModel;
import com.example.policies.TranModelLayoutEditPolicy;


/**
 * TODO 
 * @2007-1-18
 * @author xuli
 */
public class TransModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	protected List getModelChildren() {
		return ((FTransModel) this.getModel()).getChildren();
	}

	public void activate() {
		super.activate();
		((FTransModel) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((FTransModel) getModel()).removePropertyChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (FTransModel.PRO_CHILD.equals(prop))
			refreshChildren();
		if (FTransModel.PROP_CHANGE.equals(prop))
			refreshChildren();
	}

	protected IFigure createFigure() {
		Figure figure = new FreeformLayer();
		figure.setLayoutManager(new FreeformLayout());
		return figure;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new TranModelLayoutEditPolicy());
	}

}
