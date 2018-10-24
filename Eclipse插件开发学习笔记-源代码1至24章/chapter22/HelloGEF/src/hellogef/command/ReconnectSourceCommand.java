package hellogef.command;

import hellogef.model.ConnectionModel;
import hellogef.model.NodeModel;

import org.eclipse.gef.commands.Command;

public class ReconnectSourceCommand extends Command {
	private ConnectionModel connection;

	private NodeModel newSource;

	private NodeModel oldSource;

	private NodeModel target;

	//setters
	public void setConnection(ConnectionModel connection) {
		this.connection = connection;
		this.target=this.connection.getTarget();
		this.oldSource=this.connection.getSource();
	}

	public void setSource(NodeModel source) {
		this.newSource = source;
	}

	public void execute() {
		oldSource.removeOutput(connection);
		newSource.addOutput(connection);
		connection.setSource(newSource);
	}

	public String getLabel() {
		return "Reconnect Source";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		newSource.removeOutput(connection);
		oldSource.addOutput(connection);
		connection.setSource(oldSource);
	}
}