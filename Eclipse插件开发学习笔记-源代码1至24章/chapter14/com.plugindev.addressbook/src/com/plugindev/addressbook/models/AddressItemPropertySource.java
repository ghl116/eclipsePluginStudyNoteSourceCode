package com.plugindev.addressbook.models;


import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class AddressItemPropertySource implements IPropertySource {
	protected AddressItem addressItem;
	
	protected static final String PROPERTY_CATEGORY = "com.plugindev.addressbook.properties.category";
	protected static final String PROPERTY_NAME = "com.plugindev.addressbook.properties.name";
	private String[] categoryNames;
	private int property;
	private String name;
	
	private ComboBoxPropertyDescriptor categoryPropertyDescriptor;
	private TextPropertyDescriptor namePropertyDescriptor;
	
	public AddressItemPropertySource(AddressItem item)
	{
		super();
		this.addressItem = item;
		initProperties();
	}
	private void initProperties(){
		if(categoryNames == null)
		{
			categoryNames = new String[9];
			AddressCategory[] categories = AddressCategory.getTypes();
			for(int i = 0; i < categories.length; i++)
				categoryNames[i] = categories[i].getCategoryName();
		}
		// TODO 自动生成方法存根
		for(int i = 0; i < categoryNames.length; i++)
		{
			if(categoryNames[i].equals(addressItem.getCategory().getCategoryName()))
				property = i;
		}
		name = addressItem.getName();
	}

	public Object getEditableValue() {

		return addressItem;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO 自动生成方法存根
		categoryPropertyDescriptor = new ComboBoxPropertyDescriptor(PROPERTY_CATEGORY, 
				"类别", categoryNames);
		
		categoryPropertyDescriptor.setLabelProvider(new LabelProvider(){
			public String getText(Object element){
				Integer item = (Integer)element;
				return categoryNames[item.intValue()];
			}});
		categoryPropertyDescriptor.setCategory("地址元素信息");
		
		namePropertyDescriptor = new TextPropertyDescriptor(PROPERTY_NAME,"姓名");
		namePropertyDescriptor.setLabelProvider(new LabelProvider(){
			public String getText(Object element){
				return (String)element;
			}
		});
		namePropertyDescriptor.setCategory("地址元素信息");
		
		
		return new IPropertyDescriptor[]{categoryPropertyDescriptor, namePropertyDescriptor};
	}

	public Object getPropertyValue(Object id) {
		// TODO 自动生成方法存根
		if(id.equals(PROPERTY_CATEGORY))
			return new Integer(property);
		if(id.equals(PROPERTY_NAME))
			return name;
		return null;
	}

	public boolean isPropertySet(Object id) {
		return false;
	}

	public void resetPropertyValue(Object id) {
	}

	public void setPropertyValue(Object id, Object value) {
		hookPropertyChanged(id, value);
		if(id.equals(PROPERTY_CATEGORY))
			property = ((Integer)value).intValue();
		if(id.equals(PROPERTY_NAME))
			name = (String)value;
	}
	public void hookPropertyChanged(Object id,Object newValue){
		if(id.equals(PROPERTY_CATEGORY))
		{
			addressItem.setCategory(AddressCategory.getCategoryMap().
					get(categoryNames[((Integer)newValue).intValue()]));
			AddressManager.getManager().propertyChange(new PropertyChangeEvent(
					this.addressItem, "Category", property,newValue));
		}
		else if(id.equals(PROPERTY_NAME)){
			addressItem.setName((String)newValue);
			AddressManager.getManager().propertyChange(new PropertyChangeEvent(
					this.addressItem, "Name", name, newValue));
		}
	}

}
