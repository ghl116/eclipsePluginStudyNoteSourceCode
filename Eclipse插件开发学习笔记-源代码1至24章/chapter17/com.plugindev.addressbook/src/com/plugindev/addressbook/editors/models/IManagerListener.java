package com.plugindev.addressbook.editors.models;

public interface IManagerListener {
	String ADDED="__added"; //$NON-NLS-1$
	String REMOVED="__removed"; //$NON-NLS-1$
	String CHANGED = "__changed"; //$NON-NLS-1$
	
	//object-�Ķ���AddressList;type-�¼�����;itemType-�޸ĵ����ͣ��ı���ѡ���;���ĵ�ֵ����Ӧ�ļ�����
	void managerChanged(Object object, String type, String itemType, String key);
}