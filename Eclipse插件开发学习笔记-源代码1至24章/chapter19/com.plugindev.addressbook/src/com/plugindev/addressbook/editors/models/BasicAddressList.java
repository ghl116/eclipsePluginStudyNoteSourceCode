package com.plugindev.addressbook.editors.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.plugindev.addressbook.util.Messages;

public class BasicAddressList extends AddressList {

	//记录TextContentsItem的键名称
	public static ArrayList <String> stringKeys;
	//记录ChoiceContentsItem的键名称和其每个整数值所代表的含义
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
		//在书中未加入此语句。
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
		// TODO 自动生成方法存根
		return Messages.ADDR_LIST_BASIC_DESC;
	}
}