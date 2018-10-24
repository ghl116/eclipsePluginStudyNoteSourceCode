package com.example.model;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.example.tools.STextPropertyDescriptor;


/**
 * TODO 
 * @2007-1-13
 * @author xuli
 */
public class FSubTransModel extends FNode {

	final public static String PROP_PRIORITY = "PRIORITY"; //$NON-NLS-1$

	public static String PROP_COLLAPSED = "COLLAPSED"; //$NON-NLS-1$

	protected boolean collapsed = false;

	protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new STextPropertyDescriptor(PROP_NAME, "name"),
			new STextPropertyDescriptor(PROP_DESCRIPTION, "description")};

	public FSubTransModel(){
		initModel();

	}

	private void initModel(){

		setSize(new Dimension(800, 300));
		setName("数据交换流程");
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}
	public Object getPropertyValue(Object id) {
		if (PROP_NAME.equals(id))
			return getName();
		if (PROP_DESCRIPTION.equals(id))
			return getDescription();
		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		if (PROP_NAME.equals(id))
			setName((String) value);
		if (PROP_DESCRIPTION.equals(id))
			setDescription((String) value);
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
		firePropertyChange(PROP_COLLAPSED, new Boolean(collapsed));
	}

	public boolean isCollapsed(){
		return this.collapsed;
	}

	public void addFChildOnly(FElement child){
		child.setFtransmodel(this.ftransmodel);
		getChildren().add(child);
		child.setParent(this);
		this.fireChildenChange(child);
	}
}