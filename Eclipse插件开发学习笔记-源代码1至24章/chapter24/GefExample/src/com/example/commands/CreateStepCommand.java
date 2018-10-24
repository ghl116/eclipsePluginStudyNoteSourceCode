package com.example.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.example.model.FNode;
import com.example.model.FSubTransModel;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class CreateStepCommand extends Command {

	private FSubTransModel parent = null;
	private FNode child = null;
	private int index = -1;
	protected Point location;


	public void setLocation(Point location) {
		this.location = location;
	}

	public void execute() {
		Assert.isNotNull(parent);
		Assert.isNotNull(child);
		if (this.location != null) {
			this.child.setLocation(this.location);
		}
		parent.addChild(index,child);
	}

	public String getLabel() {
		return "";
	}

	public void redo() {
		this.execute();
	}

	public void undo() {
		Assert.isNotNull(parent);
		Assert.isNotNull(child);
		parent.removeChild(child);
	}
	/**
	 * @return ���� parent.
	 */
	 public FSubTransModel getParent() {
		return parent;
	}
	/**
	 * @param parent ���� parent 
	 */
	 public void setParent(FSubTransModel parent) {
		 this.parent = parent;
	 }
	 /**
	  * @return ���� child.
	  */
	 public FNode getChild() {
		 return child;
	 }
	 /**
	  * @param child ���� child 
	  */
	 public void setChild(FNode child) {
		 this.child = child;
	 }
	 /**
	  * @return ���� index.
	  */
	 public int getIndex() {
		 return index;
	 }
	 /**
	  * @param index ���� index 
	  */
	 public void setIndex(int index) {
		 this.index = index;
	 }
}