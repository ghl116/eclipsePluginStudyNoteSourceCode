package hellogef.policy;

import hellogef.command.DeleteNodeCommand;
import hellogef.model.Diagram;
import hellogef.model.NodeModel;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

public class NodeEditPolicy extends ComponentEditPolicy{

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteNodeCommand deleteCommand=new DeleteNodeCommand();
		deleteCommand.setDiagram((Diagram)getHost().getParent().getModel());
		deleteCommand.setNode((NodeModel)getHost().getModel());
		return deleteCommand;
	}
}
