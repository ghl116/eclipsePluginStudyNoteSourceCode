package com.plugindev.addressbook.editors.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.plugindev.addressbook.util.Messages;

public class BasicAddressList extends AddressList {

	//��¼TextContentsItem�ļ�����
	public static ArrayList <String> stringKeys;
	//��¼ChoiceContentsItem�ļ����ƺ���ÿ������ֵ������ĺ���
	public static Map <String, Object[]>choiceKeysMap;
	
	public static final String TYPE_BASIC = "basicList";

	/**
	 * @param name
	 */
	public BasicAddressList(String name, ArrayList stringItems, ArrayList choiceItems, int sequence) {
		super(name, stringItems, choiceItems, sequence);
		if(stringKeys == null){
			stringKeys = new ArrayList<String>();
			for(int i = 0; i < stringItems.size(); i++){
				TextItemContents item = (TextItemContents)stringItems.get(i);
				stringKeys.add(item.getKey());
			}
		}
		if(choiceKeysMap == null){
			choiceKeysMap = new HashMap<String, Object[]>();
			for(int i = 0; i < choiceItems.size();i++){
				ChoiceItemContents item = (ChoiceItemContents)choiceItems.get(i);
				choiceKeysMap.put(item.getKey(), item.getChoices());
			}
		}
		//������δ�������䡣
		setType(TYPE_BASIC);
	}

	@Override
	public ArrayList getStringKeys() {
		return stringKeys;
	}

	@Override
	public Map getChoiceKeysMap() {
		return choiceKeysMap;
	}

	@Override
	public String getDescription() {
		// TODO �Զ����ɷ������
		return Messages.ADDR_LIST_BASIC_DESC;
	}
}