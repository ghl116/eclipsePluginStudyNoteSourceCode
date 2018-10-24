package com.example.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.example.model.FStepModel;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class ChangeNodeConstraintCommand extends Command {

	private FStepModel node;

	private Point oldPos;

	private Point newPos;

	private Dimension oldSize;

	private Dimension newSize;

	public void setLocation(Point p) {
		oldPos = this.node.getLocation();
		this.newPos = p;
	}

	public void setDimension(Dimension d) {
		oldSize = this.node.getSize();
		this.newSize = d;
	}

	public void setNode(FStepModel node) {
		this.node = node;
	}

	public void execute() {
		node.setLocation(newPos);
		/*if (!(node instanceof SubTransModel && (((SubTransModel) node).isCollapsed())))
            node.setSize(newSize);*/
	}

	public String getLabel() {
		return "";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		this.node.setLocation(oldPos);
		//this.node.setSize(newSize);
	}
}