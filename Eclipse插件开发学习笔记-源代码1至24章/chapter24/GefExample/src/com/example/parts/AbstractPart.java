package com.example.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.example.model.FNode;

/**
 * TODO 
 * @2006-12-30
 * @author xuli
 */
public abstract class AbstractPart  extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(FNode.PROP_LOCATION))
			refreshVisuals();
		else if (evt.getPropertyName().equals(FNode.PROP_NAME)){
			refreshVisuals();
		}
		else if (evt.getPropertyName().equals(FNode.PROP_INPUTS))
			refreshTargetConnections();
		else if (evt.getPropertyName().equals(FNode.PROP_OUTPUTS))
			refreshSourceConnections();
	}

	protected void createEditPolicies() {

	}

	public void activate() {
		if (isActive()) {
			return;
		}
		((FNode) getModel()).addPropertyChangeListener(this);
		super.activate();
	}

	public void deactivate() {
		if (!isActive()) {
			return;
		}
		((FNode) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	protected void refreshVisuals() {

	}

}