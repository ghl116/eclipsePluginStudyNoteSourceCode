package com.example.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.example.commands.CreateConnectionCommand;
import com.example.model.TreeItemModel;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class NodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		CreateConnectionCommand command = (CreateConnectionCommand) request.getStartCommand();
		command.setTarget((TreeItemModel) getHost().getModel());
		return command;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CreateConnectionCommand command = new CreateConnectionCommand();
		command.setSource((TreeItemModel) getHost().getModel());
		request.setStartCommand(command);
		return command;
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		return null;
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		return null;
	}
}