package com.example.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * TODO 
 * @2007-1-13
 * @author xuli
 */
public abstract class FElement implements Cloneable, Serializable, IPropertySource  {

	final public static int STATE_EXPAND = 1;

	final public static int STATE_COLLAPSED = 2;

	final public static String PROP_CHANGE= "PROP_CHANGE"; //$NON-NLS-1$

	final public static String PROP_DESCRIPTION = "DESCRIPTION"; //$NON-NLS-1$

	final public static String PROP_NAME= "NAME"; //$NON-NLS-1$

	final public static String PRO_CHILD = "CHILD"; //$NON-NLS-1$

	final public static String PROP_EXT_PROPS = "EXT_PROPS"; //$NON-NLS-1$

	protected String name = "untitle"; //$NON-NLS-1$

	protected String description = ""; //$NON-NLS-1$

	protected List children= new ArrayList();

	protected FElement parent = null;

	protected FTransModel ftransmodel = null;

	protected String model_name = ""; //$NON-NLS-1$

	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue) {
		listeners.firePropertyChange(prop, old, newValue);
	}

	protected void firePropertyChange(String prop, Object newValue) {
		listeners.firePropertyChange(prop, null, newValue);
	}

	public void fireChildenChange(FElement child){
		this.listeners.firePropertyChange(PRO_CHILD,null,child);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		listeners = new PropertyChangeSupport(this);
	}

	/***********************************************/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (this.name.equals(name)) {
			return;
		}
		this.name = name;
		firePropertyChange(PROP_NAME, null, name);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		if (this.description.equals(description)) {
			return;
		}
		this.description = description;
		firePropertyChange(PROP_DESCRIPTION, null, description);
	}

	/***********************************************/
	// Abstract methods from IPropertySource
	public Object getEditableValue() {
		return this;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return null;
	}

	public Object getPropertyValue(Object id) {
		return null;
	}

	public void setPropertyValue(Object id, Object value) {
	}

	public boolean isPropertySet(Object id) {
		return true;
	}

	public void resetPropertyValue(Object id) {

	}

	/***********************************************/
	/**
	 * @return 返回 parent.
	 */
	public FElement getParent() {
		return parent;
	}
	/**
	 * @param parent 设置 parent 
	 */
	public void setParent(FElement parent) {
		this.parent = parent;
	}
	/**
	 * @return 返回 children.
	 */
	public List getChildren() {
		if(children == null) 
			children = new ArrayList();
		return children;
	}
	/**
	 * @param children 设置 children 
	 */
	public void setChildren(List children) {
		this.children = children;
	}

	public void addChild(FElement child){
		addChild(-1,child);
	}

	public void addChild(int index , FElement child){
		if(index == -1){
			getChildren().add(child);
		}else{
			getChildren().add(index,child);
		}
		child.setParent(this);
		child.setFtransmodel(this.ftransmodel);
		this.fireChildenChange(child);
	}

	public void removeChild(FElement child){
		child.setParent(null);
		getChildren().remove(child);
		this.fireChildenChange(child);
	}

	public FTransModel getFtransmodel() {
		return ftransmodel;
	}

	public void setFtransmodel(FTransModel ftransmodel) {
		this.ftransmodel = ftransmodel;
	}

	public String getModelName(){
		return model_name;
	}

}
