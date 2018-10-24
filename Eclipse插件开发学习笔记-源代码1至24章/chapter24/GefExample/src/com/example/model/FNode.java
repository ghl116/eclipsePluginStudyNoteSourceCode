package com.example.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * TODO 
 * @2007-1-13
 * @author xuli
 */
public abstract class FNode extends FElement {

	final public static String PRO_FIGURE = "FIGURE"; 

	final public static String PROP_LOCATION = "LOCATION"; 

	final public static String PROP_VISIBLE = "VISIBLE"; 

	final public static String PROP_INPUTS = "INPUTS"; 

	final public static String PROP_OUTPUTS = "OUTPUTS"; 

	final public static String PROP_SIZE = "SIZE"; 

	protected Dimension size = new Dimension(100, 150);

	protected Point location = new Point(0, 0);

	protected boolean visible = true;

	protected List outputs = new ArrayList();

	protected List inputs = new ArrayList();

	public void addInput(FConnection connection) {
		this.inputs.add(connection);
		fireConnectionChange(PROP_INPUTS, connection);
	}

	public void addOutput(FConnection connection) {
		this.outputs.add(connection);
		fireConnectionChange(PROP_OUTPUTS, connection);
	}

	public List getIncomingConnections() {
		return this.inputs;
	}

	public List getOutgoingConnections() {
		return this.outputs;
	}

	public void removeInput(FConnection connection) {
		this.inputs.remove(connection);
		fireConnectionChange(PROP_INPUTS, connection);
	}

	public void removeOutput(FConnection connection) {
		this.outputs.remove(connection);
		fireConnectionChange(PROP_OUTPUTS, connection);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		if (this.visible == visible) {
			return;
		}
		this.visible = visible;
		firePropertyChange(PROP_VISIBLE, null, Boolean.valueOf(visible));
	}

	public void setLocation(Point p) {
		if (this.location.equals(p)) {
			return;
		}
		this.location = p;
		firePropertyChange(PROP_LOCATION, null, p);
	}

	public Point getLocation() {
		return location;
	}

	public void setSize(Dimension d) {
		if (this.size.equals(d)) {
			return;
		}
		this.size = d;
		firePropertyChange(PROP_SIZE, null, d);
	}

	public Dimension getSize() {
		return size;
	}

	protected void fireConnectionChange(String prop, Object child) {
		listeners.firePropertyChange(prop, null, child);
	}

}
