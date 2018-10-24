package com.example.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.example.image.ImageConstants;
import com.example.tools.STextPropertyDescriptor;
import com.example.tools.StepFactory;

/**
 * TODO 
 * @2007-1-13
 * @author xuli
 */
public class FStepModel extends FNode {

	private boolean expand = true;

	protected String icon = ImageConstants.STEP_DB_INPUT_ICON; //$NON-NLS-1$

	protected TreeItemModel basicRoot = null;

	private Dimension headSize = new Dimension(149,29);

	private Dimension tempSize = null;

	final public static String PROP_TYPE = "TYPE"; //$NON-NLS-1$

	final public static String PROP_ERROR_HANDLER = "ERROR_HANDLER"; //$NON-NLS-1$

	final public static String TYPE_DBINPUT = "DATABASE_INPUT"; //$NON-NLS-1$

	final public static String TYPE_DBOUTPUT = "DATABASE_OUTPUT"; //$NON-NLS-1$

	final public static String TYPE_TRANSFORM = "TRANSFORM"; //$NON-NLS-1$

	public FStepModel(){
		initModel();
	}

	private void initModel(){

		if(this.basicRoot != null)
			this.removeChild(this.basicRoot);
		this.basicRoot = inintTreeItem();
		Dimension reg = this.basicRoot.refreshRegion().getCopy();
		this.setSize(reg);
		this.addChild(this.basicRoot);
	}

	private TreeItemModel inintTreeItem(){
		TreeItemModel root = new TreeItemModel();
		root.setIfroot(true);
		root.setName("database");
		TreeItemModel table = new TreeItemModel();
		table.setName("table");
		table.setExpand(TreeItemModel.ITEM_EXPAND);
		table.setType(TreeItemModel.TYPE_TABLE);
		TreeItemModel column = null;
		for(int i = 0; i < 7; i++){
			column = new TreeItemModel();
			column.setName("column_"+i);
			column.setExpand(TreeItemModel.ITEM_NOCHILD);
			column.setType(TreeItemModel.TYPE_COLUMN);
			table.addChild(column);
		}
		root.addChild(table);
		return root;
	}

	public String getIcon(){
		return icon;
	}

	public void setRootLocation(Point p){
		basicRoot.setLocation(p);
	}

	public void refreshRegion(){
		setSize(basicRoot.refreshRegion());
		setLocation(this.getLocation());

	}

	public TreeItemModel getRoot(){
		return basicRoot;
	}

	public void removeAllConnections(){
		this.basicRoot.removeAllConnections();

	}
	/***************************************/

	public void setLocation(Point p) {
		super.setLocation(p);
	}
	public void collapse(){
		if(this.tempSize == null)
			this.tempSize = new Dimension(0,0);
		this.tempSize.height = this.getSize().height - this.headSize.height;
		this.tempSize.width = this.getSize().width - this.headSize.width;
		this.setSize(new Dimension(0,0));
	}

	public void expand(){
		if(this.tempSize != null){
			this.setSize(this.tempSize);
		}
	}
	/********************************************/
	protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new STextPropertyDescriptor(PROP_NAME, "name"),
			new STextPropertyDescriptor(PROP_DESCRIPTION, "description")};

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

	public void setSize(Dimension d) {

		if(149 < d.width){
			this.size.width = d.width;
		}else{
			this.size.width = headSize.width;
		}
		this.size.height = d.height+headSize.height;

		firePropertyChange(PROP_SIZE, null, d);
	}

	public boolean isExpand() {
		return expand;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}

}
