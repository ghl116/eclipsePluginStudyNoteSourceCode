package hellogef.command;

import hellogef.model.Diagram;
import hellogef.model.NodeModel;

import org.eclipse.gef.commands.Command;

public class DeleteNodeCommand extends Command {
	private Diagram diagram;

	private NodeModel node;

	private int index;

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public void setNode(NodeModel node) {
		this.node = node;
	}

	//------------------------------------------------------------------------
	// Overridden from Command

	public void execute() {
		index=diagram.getNodes().indexOf(node);
		diagram.removeNode(node);
	}

	public String getLabel() {
		return "Delete Node";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		diagram.addNode(node);
	}
}