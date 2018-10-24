package com.example.parts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import com.example.figures.BorderAnchor;
import com.example.figures.MappingConnection;
import com.example.policies.ConnectionEditPolicy;

/**
 * TODO 
 * @2006-12-30
 * @author xuli
 */
public class ConnectionPart extends AbstractConnectionEditPart {
	private BorderAnchor sourceAnchor;
	private BorderAnchor targetAnchor;

	protected IFigure createFigure() {
		PolylineConnection conn = new MappingConnection();
		conn.setTargetDecoration(new PolygonDecoration());
		conn.setConnectionRouter(new BendpointConnectionRouter());
		return conn;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ConnectionEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
	}

	protected void refreshVisuals() {

	}
	public void setSelected(int value) {
		super.setSelected(value);
		if (value != EditPart.SELECTED_NONE)
			((PolylineConnection) getFigure()).setLineWidth(2);
		else
			((PolylineConnection) getFigure()).setLineWidth(1);
	}
	/*****************************/
	public BorderAnchor getSourceAnchor() {
		return sourceAnchor;
	}

	public void setSourceAnchor(BorderAnchor sourceAnchor) {
		this.sourceAnchor = sourceAnchor;
	}

	public BorderAnchor getTargetAnchor() {
		return targetAnchor;
	}

	public void setTargetAnchor(BorderAnchor targetAnchor) {
		this.targetAnchor = targetAnchor;
	}
}