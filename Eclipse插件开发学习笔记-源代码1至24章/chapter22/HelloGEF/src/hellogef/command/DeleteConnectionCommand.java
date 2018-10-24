package hellogef.command;

import hellogef.model.ConnectionModel;
import hellogef.model.NodeModel;

import org.eclipse.gef.commands.Command;

public class DeleteConnectionCommand extends Command {

	private NodeModel source;

	private NodeModel target;

	private ConnectionModel connection;

	//Setters
	public void setConnection(ConnectionModel connection) {
		this.connection = connection;
	}

	public void setSource(NodeModel source) {
		this.source = source;
	}

	public void setTarget(NodeModel target) {
		this.target = target;
	}

	public void execute() {
		source.removeOutput(connection);
		target.removeInput(connection);
		connection.setSource(null);
		connection.setTarget(null);
	}

	public String getLabel() {
		return "Delete Connection";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		connection.setSource(source);
		connection.setTarget(target);
		source.addOutput(connection);
		target.addInput(connection);
	}
}