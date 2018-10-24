package hellogef.model;

import hellogef.tools.STextPropertyDescriptor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class NodeModel extends AbstractModel {

	final public static String PROP_LOCATION = "LOCATION";

	final public static String PROP_NAME = "NAME";

	final public static String PROP_VISIBLE = "VISIBLE";

	final public static String PROP_INPUTS = "INPUTS";

	final public static String PROP_OUTPUTS = "OUTPUTS";

	protected Point location = new Point(0, 0);

	protected boolean visible = true;

	protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new STextPropertyDescriptor(PROP_NAME, "名称"),
			new STextPropertyDescriptor(PROP_VISIBLE, "是否可见") };

	public NodeModel(){
		this.name = "节点";
	}

	protected List outputs = new ArrayList();

	protected List inputs = new ArrayList();

	public void addInput(ConnectionModel connection) {
		this.inputs.add(connection);
		fireStructureChange(PROP_INPUTS, connection);
	}

	public void addOutput(ConnectionModel connection) {
		this.outputs.add(connection);
		fireStructureChange(PROP_OUTPUTS, connection);
	}

	public List getIncomingConnections() {
		return this.inputs;
	}

	public List getOutgoingConnections() {
		return this.outputs;
	}

	public void removeInput(ConnectionModel connection) {
		this.inputs.remove(connection);
		fireStructureChange(PROP_INPUTS, connection);
	}

	public void removeOutput(ConnectionModel connection) {
		this.outputs.remove(connection);
		fireStructureChange(PROP_OUTPUTS, connection);
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

	public Object getEditableValue() {
		return this;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	public Object getPropertyValue(Object id) {
		if (PROP_NAME.equals(id))
			return getName();
		if (PROP_VISIBLE.equals(id))
			return isVisible() ? "可见" : "不可见";
			return null;
	}

	public boolean isPropertySet(Object id) {
		return true;
	}

	public void resetPropertyValue(Object id) {

	}

	public void setPropertyValue(Object id, Object value) {
		if (PROP_NAME.equals(id))
			setName((String) value);
		if (PROP_VISIBLE.equals(id))
			setVisible(((Integer) value).intValue() == 0);
	}
}