package hellogef.command;

import hellogef.model.Diagram;
import hellogef.model.NodeModel;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

public class CreateNodeCommand extends Command {
	protected Diagram diagram;

	protected NodeModel node;

	protected Point location;

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public void setNode(NodeModel node) {
		this.node = node;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public void execute() {
		if (this.location != null) {
			this.node.setLocation(this.location);
		}
		this.diagram.addNode(this.node);
	}

	public String getLabel() {
		return "Create Node";
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		diagram.removeNode(node);
	}
}