package com.plugindev.addressbook.editors.models;

import java.util.HashMap;

import org.eclipse.swt.widgets.*;


public class PhoneAddressListItemProperties extends AddressListProperties{
	
	public PhoneAddressListItemProperties() {
	}

	public void createContents(Composite parent) {
		super.createContents(parent);
		this.createSection(parent, PhoneAddressList.stringKeys, new HashMap<String, Object[]>());
	}
}
