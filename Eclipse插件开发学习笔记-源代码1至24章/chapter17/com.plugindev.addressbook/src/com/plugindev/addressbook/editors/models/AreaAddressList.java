package com.plugindev.addressbook.editors.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.plugindev.addressbook.util.Messages;

public class AreaAddressList extends AddressList {

	//�洢�ص�������ƣ�Ϊ��ͬ�ĵ�ַ�AddressItem���̶�����Ĳ���
	public static ArrayList <String> stringKeys;
	public static final String TYPE_AREA = "positionList";
	/**
	 * @param name
	 */
	public AreaAddressList(String name, ArrayList items, int sequence) {
		super(name, items, new ArrayList(), sequence);
		if(stringKeys == null)
		{
			stringKeys = new ArrayList<String>();
			for(int i = 0; i < items.size(); i++){
				TextItemContents item = (TextItemContents)items.get(i);
				stringKeys.add(item.getKey());
			}
		}
		setType(TYPE_AREA);
	}
	@Override
	public Map getChoiceKeysMap() {
		// TODO �Զ����ɷ������
		return new HashMap();
	}
	@Override
	public ArrayList getStringKeys() {
		// TODO �Զ����ɷ������
		return stringKeys;
	}
	@Override
	public String getDescription() {
		// TODO �Զ����ɷ������
		return Messages.ADD_LIST_AREA_DESC;
	}
}
