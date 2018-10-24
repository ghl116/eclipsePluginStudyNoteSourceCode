package com.example.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.example.tools.STextPropertyDescriptor;

/**
 * TODO 
 * @2007-1-13
 * @author xuli
 */
public class FTransModel extends FElement {

	final public static String PROP_AUTHOR = "AUTHOR"; //$NON-NLS-1$

	final public static String PROP_VERSION = "VERSION"; //$NON-NLS-1$

	final public static String PROP_LANGUAGE = "LANGUAGE"; //$NON-NLS-1$

	final public static String PROP_MODIFY_TIME = "MODIFY_TIME"; //$NON-NLS-1$

	final public static String PROP_CREATE_TIME = "CREATE_TIME"; //$NON-NLS-1$

	final public static String PROP_CONTEXTS = "CONTEXTS"; //$NON-NLS-1$

	private String author = "";

	protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new STextPropertyDescriptor(PROP_NAME, "name"),
			new STextPropertyDescriptor(PROP_DESCRIPTION, "description"),
			new STextPropertyDescriptor(PROP_AUTHOR, "author")
	};

	public FTransModel(){
		this.setName("未命名模板"); //$NON-NLS-1$
		this.setAuthor("somebody"); //$NON-NLS-1$
		this.setDescription("新建的模板"); //$NON-NLS-1$
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}
	public Object getPropertyValue(Object id) {
		if (PROP_NAME.equals(id))
			return getName();
		if (PROP_DESCRIPTION.equals(id))
			return getDescription();
		if (PROP_AUTHOR.equals(id))
			return this.getAuthor();

		return ""; //$NON-NLS-1$
	}

	public void setPropertyValue(Object id, Object value) {
		if (PROP_NAME.equals(id))
			setName((String) value);
		if (PROP_DESCRIPTION.equals(id))
			setDescription((String) value);
		if (PROP_AUTHOR.equals(id))
			this.setAuthor((String)value);
	}

	/**********************************************/

	public void setAuthor(String au) {
		this.author = au;
	}

	public String getAuthor() {
		return this.author;
	}

	public void addFChildOnly(FElement child){
		child.setFtransmodel(this.ftransmodel);
		for(Iterator iter = child.children.iterator();iter.hasNext();){
			FElement c = (FElement)iter.next();
			c.setFtransmodel(this.ftransmodel);
		}
		getChildren().add(child);
		child.setParent(this);
		this.fireChildenChange(child);
	}
}
