package com.plugindev.addressbook.editors.models;

public class SimpleFormEditorInput extends FormEditorInput {
	private AddressListManager manager;
	
	public SimpleFormEditorInput(String name) {
		super(name);
		manager = new AddressListManager(name);
	}
	//在写作中还未加入该方法
	public AddressListManager getManager() {
		return manager;
	}
}