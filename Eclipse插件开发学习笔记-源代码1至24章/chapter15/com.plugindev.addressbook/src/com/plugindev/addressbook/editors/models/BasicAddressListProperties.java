package com.plugindev.addressbook.editors.models;


import org.eclipse.swt.widgets.*;

public class BasicAddressListProperties extends AddressListProperties {

	public BasicAddressListProperties() {
		super();
	}
	public void createContents(Composite parent) {
		super.createContents(parent);
		createSection(parent, BasicAddressList.stringKeys, BasicAddressList.choiceKeysMap);
	}
}