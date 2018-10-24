package com.example.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.example.model.FConnection;
import com.example.model.TreeItemModel;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class CreateConnectionCommand extends Command {
	
	protected FConnection connection;

	protected TreeItemModel source;

	protected TreeItemModel target;

	public void setSource(TreeItemModel source) {
		this.source = source;
	}

	/*public void setConnection(FConnection connection) {
		this.connection = connection;
	}*/

	public void setTarget(TreeItemModel target) {
		this.target = target;
	}

	//------------------------------------------------------------------------
	// Overridden from Command

	public void execute() {
		connection = new FConnection(source, target);
	}

	public String getLabel() {
		return "";
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
		
		return true;
	}
}