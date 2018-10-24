package com.example.commands;

import org.eclipse.gef.commands.Command;

import com.example.model.FSubTransModel;
import com.example.model.FTransModel;

/**
 * TODO 
 * @2007-1-26
 * @author xuli
 */
public class DeleteNodeCommand extends Command {

	private FTransModel transmodel;

	private FSubTransModel sub;

	//private int index;

	public void setTransModel(FTransModel transmodel) {
		this.transmodel = transmodel;
	}

	public void setNode(FSubTransModel sub) {
		this.sub = sub;
	}

	//------------------------------------------------------------------------
	// Overridden from Command

	public void execute() {
		
		transmodel.removeChild(sub);
	}

	public String getLabel() {
		return "";
	}

	public void redo() {
		execute();
	}

	public void undo() {

		transmodel.addChild(sub);
	}
}