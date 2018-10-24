package hellogef.control;

import hellogef.model.NodeModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

public class NodeTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {

	public NodeTreeEditPart(Object model) {
		super(model);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshVisuals();
	}

	public void activate() {
		super.activate();
		((NodeModel) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((NodeModel) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals() {
		setWidgetText(((NodeModel) getModel()).getName());
	}
}