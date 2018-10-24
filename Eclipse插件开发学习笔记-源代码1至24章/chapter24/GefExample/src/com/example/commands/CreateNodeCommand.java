package com.example.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.example.model.FNode;
import com.example.model.FTransModel;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class CreateNodeCommand extends Command {
	protected FTransModel diagram;

	protected FNode node;

	protected Point location;

	// setters

	public void setDiagram(FTransModel diagram) {
		this.diagram = diagram;
	}

	public void setNode(FNode node) {
		this.node = node;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public void execute() {
		if (this.location != null) {
			this.node.setLocation(this.location);
		}
		this.diagram.addChild(this.node);
	}

	public String getLabel() {
		return "";
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		diagram.removeChild(node);
	}
}