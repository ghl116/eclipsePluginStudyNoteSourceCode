package com.plugindev.addressbook.editors.models;

import java.util.HashMap;

import org.eclipse.swt.widgets.Composite;

public class AreaAddressListProperties extends AddressListProperties{

	
	public AreaAddressListProperties() {
	}

	public void createContents(Composite parent) {
		super.createContents(parent);
		this.createSection(parent, AreaAddressList.stringKeys, new HashMap<String, Object[]>());
	}
}
