package com.example.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.example.commands.CreateNodeCommand;
import com.example.commands.MoveNodeCommand;
import com.example.model.FNode;
import com.example.model.FSubTransModel;
import com.example.model.FTransModel;
import com.example.parts.SubTransModelPart;

/**
 * TODO 
 * @2007-1-26
 * @author xuli
 */
public class TranModelLayoutEditPolicy extends XYLayoutEditPolicy {

	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}

	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		if (!(child instanceof SubTransModelPart))
			return null;
		if (!(constraint instanceof Rectangle))
			return null;
		MoveNodeCommand cmd = new MoveNodeCommand();
		cmd.setNode((FNode) child.getModel());
		cmd.setLocation(((Rectangle) constraint).getLocation());
		return cmd;

	}

	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObject() instanceof FSubTransModel) {
			CreateNodeCommand cmd = new CreateNodeCommand();
			cmd.setDiagram((FTransModel) getHost().getModel());
			cmd.setNode((FNode) request.getNewObject());
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			cmd.setLocation(constraint.getLocation());
			return cmd;
		}
		return null;
	}

	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}
}