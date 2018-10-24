package hellogef.command;

import hellogef.model.NodeModel;

import org.eclipse.gef.commands.Command;

public class RenameNodeCommand extends Command {

	private NodeModel node;

	private String newName;

	private String oldName;

	public void setName(String name) {
		this.newName = name;
	}

	public void setNode(NodeModel node) {
		this.node = node;
	}

	public void execute() {
		oldName = this.node.getName();
		this.node.setName(newName);
	}

	public void redo() {
		node.setName(newName);
	}

	public void undo() {
		node.setName(oldName);
	}

	public String getLabel() {
		return "Rename Node";
	}
}