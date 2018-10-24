package hellogef.policy;

import hellogef.command.CreateConnectionCommand;
import hellogef.command.ReconnectSourceCommand;
import hellogef.model.ConnectionModel;
import hellogef.model.NodeModel;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

public class NodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		CreateConnectionCommand command = (CreateConnectionCommand) request.getStartCommand();
		command.setTarget((NodeModel) getHost().getModel());
		return command;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CreateConnectionCommand command = new CreateConnectionCommand();
		command.setSource((NodeModel) getHost().getModel());
		request.setStartCommand(command);
		return command;
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ReconnectSourceCommand cmd = new ReconnectSourceCommand();
		cmd.setConnection((ConnectionModel)request.getConnectionEditPart().getModel());
		cmd.setSource((NodeModel)getHost().getModel());
		return cmd;
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		return null;
	}
}