package hellogef.command;

import hellogef.model.ConnectionModel;
import hellogef.model.NodeModel;

import java.util.List;

import org.eclipse.gef.commands.Command;

public class CreateConnectionCommand extends Command {

	protected ConnectionModel connection;

	protected NodeModel source;

	protected NodeModel target;

	public void setSource(NodeModel source) {
		this.source = source;
	}

	public void setConnection(ConnectionModel connection) {
		this.connection = connection;
	}

	public void setTarget(NodeModel target) {
		this.target = target;
	}

	//------------------------------------------------------------------------
	// Overridden from Command

	public void execute() {
		connection = new ConnectionModel(source, target);
	}

	public String getLabel() {
		return "Create Connection";
	}

	public void redo() {
		this.source.addOutput(this.connection);
		this.target.addInput(this.connection);
	}

	public void undo() {
		this.source.removeOutput(this.connection);
		this.target.removeInput(this.connection);
	}

	public boolean canExecute() {
		if (source.equals(target))
			return false;
		// Check for existence of connection already
		List connections = this.source.getOutgoingConnections();
		for (int i = 0; i < connections.size(); i++) {
			if (((ConnectionModel) connections.get(i)).getTarget().equals(target))
				return false;
		}
		return true;
	}
}