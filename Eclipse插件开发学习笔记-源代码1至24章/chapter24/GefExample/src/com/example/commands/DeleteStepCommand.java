package com.example.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.example.model.FConnection;
import com.example.model.FStepModel;
import com.example.model.FSubTransModel;


/**
 * TODO 
 * @2007-1-26
 * @author xuli
 */
public class DeleteStepCommand extends Command {

	private FSubTransModel subtransmodel;

	private FStepModel step;

	private List inputCons = null;

	private List outputCons = null;

	//private int index;

	public void setSubTransModel(FSubTransModel transmodel) {
		this.subtransmodel = transmodel;
	}

	public void setNode(FStepModel step) {
		this.step = step;
	}

	//------------------------------------------------------------------------
	// Overridden from Command

	public void execute() {
		//index=transmodel.getChildren().indexOf(node);
		inputCons = step.getRoot().getAllIncomings();
		outputCons = step.getRoot().getAllOutgoings();
		step.removeAllConnections();
		subtransmodel.removeChild(step);
	}

	public String getLabel() {
		return "";
	}

	public void redo() {
		execute();
	}

	public void undo() {
		subtransmodel.addChild(step);
		reconnectIncomings();
		reconnectOutgoings();
	}

	private void reconnectIncomings(){
		FConnection con = null;
		for(Iterator iter = inputCons.iterator(); iter.hasNext();){
			con = (FConnection)iter.next();
			con.reConnect();
		}
	}

	private void reconnectOutgoings(){
		FConnection con = null;
		for(Iterator iter = outputCons.iterator(); iter.hasNext();){
			con = (FConnection)iter.next();
			con.reConnect();
		}
	}
}