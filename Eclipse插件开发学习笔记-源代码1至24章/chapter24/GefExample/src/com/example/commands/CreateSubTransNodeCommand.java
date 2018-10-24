package com.example.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.example.model.FNode;
import com.example.model.FSubTransModel;

/**
 * TODO 
 * @2007-1-26
 * @author xuli
 */
public class CreateSubTransNodeCommand extends Command {

	private FSubTransModel subject;

	private FNode node;

	private Point location;

	//Setters
	public void setStep(FNode node) {
		this.node = node;
	}

	public void setSubject(FSubTransModel subject) {
		this.subject = subject;
	}

	public void setLocation(Point location) {
		this.location = location;
	}


	//------------------------------------------------------------------------
	// Overridden from Command
	public void execute() {
		node.setName("subTransModel");
		node.setLocation(this.location);
		subject.addChild(node);
	}

	public String getLabel() {
		return "";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		subject.removeChild(node);
	}
}