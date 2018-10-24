package hellogef.policy;

import hellogef.command.CreateNodeCommand;
import hellogef.command.MoveNodeCommand;
import hellogef.control.NodePart;
import hellogef.model.Diagram;
import hellogef.model.NodeModel;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {

	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}

	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		if (!(child instanceof NodePart))
			return null;
		if (!(constraint instanceof Rectangle))
			return null;

		MoveNodeCommand cmd = new MoveNodeCommand();
		cmd.setNode((NodeModel) child.getModel());
		cmd.setLocation(((Rectangle) constraint).getLocation());
		return cmd;

	}

	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObject() instanceof NodeModel) {
			CreateNodeCommand cmd = new CreateNodeCommand();
			cmd.setDiagram((Diagram) getHost().getModel());
			cmd.setNode((NodeModel) request.getNewObject());
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