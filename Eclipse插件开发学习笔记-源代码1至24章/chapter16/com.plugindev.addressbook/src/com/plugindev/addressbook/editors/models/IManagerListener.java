package com.plugindev.addressbook.editors.models;

public interface IManagerListener {
	String ADDED="__added"; //$NON-NLS-1$
	String REMOVED="__removed"; //$NON-NLS-1$
	String CHANGED = "__changed"; //$NON-NLS-1$
	
	//object-改动的AddressList;type-事件类型;itemType-修改的类型（文本或选择框）;更改的值所对应的键名称
	void managerChanged(Object object, String type, String itemType, String key);
}