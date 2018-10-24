package com.example.commands;

import org.eclipse.gef.commands.Command;

import com.example.model.FConnection;
import com.example.model.FNode;

/**
 * TODO 
 * @2007-1-26
 * @author xuli
 */
public class DeleteConnectionCommand extends Command {

	private FNode source;

	private FNode target;

	private FConnection connection;

	//Setters
	public void setConnection(FConnection connection) {
		this.connection = connection;
	}

	public void setSource(FNode source) {
		this.source = source;
	}

	public void setTarget(FNode target) {
		this.target = target;
	}

	public void execute() {
		source.removeOutput(connection);
		target.removeInput(connection);
	}

	public String getLabel() {
		return "";
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