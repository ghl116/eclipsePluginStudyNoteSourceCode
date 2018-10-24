package com.plugindev.addressbook.editors.actions;


import org.eclipse.jface.action.Action;

import com.plugindev.addressbook.editors.AddressFormEditor;

public class SaveAction extends Action {
private final AddressFormEditor editor;
	public SaveAction(AddressFormEditor editor){
		super();
		this.editor = editor;
	}
	public void run(){
		System.out.println();
	}
}
