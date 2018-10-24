package com.example.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.example.model.FNode;

/**
 * TODO 
 * @2007-1-26
 * @author xuli
 */
public class MoveNodeCommand extends Command {
	private FNode node;

	private Point oldPos;

	private Point newPos;

	public void setLocation(Point p) {
		this.newPos = p;
	}

	public void setNode(FNode node) {
		this.node = node;
	}

	public void execute() {
		oldPos = this.node.getLocation();
		node.setLocation(newPos);
	}

	public String getLabel() {
		return "";
	}

	public void redo() {
		this.node.setLocation(newPos);
	}

	public void undo() {
		this.node.setLocation(oldPos);
	}
}