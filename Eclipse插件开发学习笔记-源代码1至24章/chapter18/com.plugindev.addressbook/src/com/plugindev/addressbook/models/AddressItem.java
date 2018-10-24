/*
 * 引用在第十二章（视图）第十页
 */


package com.plugindev.addressbook.models;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.views.properties.IPropertySource;

public class AddressItem implements IAdaptable{
	private String name;
	private String address;
	private AddressCategory category;
/*	private class AddressInfo{
		String cellPhone;
		String homePhone;
		String officePhone;
		String email;
		String mailBox;
		String apartment;
		String workPlace;
		String other;
	}*/
	
	static AddressItem[] NONE = new AddressItem[]{};

	public AddressItem(String name)
	{
		setName(name);
	}
	public AddressItem(String name, AddressCategory category)
	{
		setName(name);
		setCategory(category);
	}
	public AddressItem(String name, String address, AddressCategory category)
	{
		setName(name);
		setAddress(address);
		setCategory(category);
	}
	public String getAddress() {
		return this.address;
	}

	public AddressCategory getCategory() {
		return this.category;
	}

	public String getName() {
		return this.name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCategory(AddressCategory category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//为属性视图提供支持
	public Object getAdapter(Class adapter) {
		if(adapter == IPropertySource.class)
			return new AddressItemPropertySource(this);
		return null;
	}

}
