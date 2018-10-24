package hellogef.control;

import hellogef.model.NodeModel;
import hellogef.policy.NodeDirectEditPolicy;
import hellogef.policy.NodeEditPolicy;
import hellogef.policy.NodeGraphicalNodeEditPolicy;
import hellogef.view.NodeFigure;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

public class NodePart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {

	protected DirectEditManager manager;

	public void performRequest(Request req) {
		if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
			if (manager == null) {
				NodeFigure figure = (NodeFigure) getFigure();
				manager = new NodeDirectEditManager(this, TextCellEditor.class, new NodeCellEditorLocator(figure));
			}
			manager.show();
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(NodeModel.PROP_LOCATION))
			refreshVisuals();
		else if (evt.getPropertyName().equals(NodeModel.PROP_NAME))
			refreshVisuals();
		else if (evt.getPropertyName().equals(NodeModel.PROP_INPUTS))
			refreshTargetConnections();
		else if (evt.getPropertyName().equals(NodeModel.PROP_OUTPUTS))
			refreshSourceConnections();
	}

	protected IFigure createFigure() {
		return new NodeFigure();
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new NodeDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
	}

	public void activate() {
		if (isActive()) {
			return;
		}
		
		((NodeModel) getModel()).addPropertyChangeListener(this);
		super.activate();
	}

	public void deactivate() {
		if (!isActive()) {
			return;
		}
		
		((NodeModel) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	protected void refreshVisuals() {
		NodeModel node = (NodeModel) getModel();
		Point loc = node.getLocation();
		Dimension size = new Dimension(150, 40);
		Rectangle rectangle = new Rectangle(loc, size);
		((NodeFigure) this.getFigure()).setName(((NodeModel) this.getModel()).getName());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	//------------------------------------------------------------------------
	// Abstract methods from NodeEditPart

	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	protected List getModelSourceConnections() {
		return ((NodeModel) this.getModel()).getOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return ((NodeModel) this.getModel()).getIncomingConnections();
	}

}