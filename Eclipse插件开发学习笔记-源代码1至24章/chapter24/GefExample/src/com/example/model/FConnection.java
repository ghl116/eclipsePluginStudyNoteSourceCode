package com.example.model;

import java.io.Serializable;

/**
 * TODO 
 * @2007-1-13
 * @author xuli
 */
public class FConnection extends FElement{

	protected FNode source = null;

	protected FNode target = null;

	public void setSource(FNode source) {
		this.source = source;
	}

	public void setTarget(FNode target) {
		this.target = target;
	}

	public FNode getTarget() {
		return this.target;
	}

	public FNode getSource() {
		return this.source;
	}

	/**
	 * @param source
	 * @param target
	 */
	public FConnection(FNode source, FNode target) {
		super();
		this.source = source;
		this.target = target;
		source.addOutput(this);
		target.addInput(this);
	}

	public void reConnect(){
		if(this.source != null && this.target!= null){
			source.removeOutput(this);
			source.addOutput(this);
			target.removeInput(this);
			target.addInput(this);
		}
	}
}