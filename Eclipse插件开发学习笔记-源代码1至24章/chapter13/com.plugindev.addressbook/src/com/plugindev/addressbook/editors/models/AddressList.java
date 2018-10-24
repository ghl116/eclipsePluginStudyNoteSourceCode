package com.plugindev.addressbook.editors.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.plugindev.addressbook.util.Messages;

/**
 *ʹ������ע���ķ�ʽ��ע�����п��ܵ�AddressList����
 */
public	abstract class AddressList {
	private String name;
	private int sequence;
	private String type;
	protected AddressListManager manager;
	
	//���ı���ļ����ƻ���ı���������Ϣ
	protected Map<String, TextItemContents> textContentMap;
	//��ѡ����ļ����ƻ��ѡ����������Ϣ
	protected Map<String, ChoiceItemContents> choiceContentMap;
	
	
	protected AddressList(String name, ArrayList stringItems, ArrayList choiceItems, int sequence) {
		this.name = name;
		this.sequence = sequence;
		if(textContentMap == null){
			textContentMap = new HashMap<String, TextItemContents>();
			for(int i = 0; i < stringItems.size(); i++){
				TextItemContents item = (TextItemContents)stringItems.get(i);
				textContentMap.put(item.getKey(), item);
			}
		}
		if(choiceContentMap == null){
			choiceContentMap = new HashMap<String, ChoiceItemContents>();
			for(int i = 0; i < choiceItems.size(); i++){
				ChoiceItemContents item = (ChoiceItemContents)choiceItems.get(i);
				choiceContentMap.put(item.getKey(), item);
			}
		}
	}

	public void setManager(AddressListManager manager) {
		this.manager = manager;
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return getName();
	}
	public void setName(String name) {
		this.name = name;
		manager.fireManagerChanged(this, IManagerListener.CHANGED, "", "");
	}
	public String getStringValue(String key){
		TextItemContents item = textContentMap.get(key);
		if(item != null)
			return (String)item.getValue();
		return "";
	}

	public void setStringValue(String key, String value) {
		// TODO �Զ����ɷ������
		TextItemContents item = textContentMap.get(key);
		item.setValue(value);
		manager.fireManagerChanged(this, 
				IManagerListener.CHANGED, Messages.ITEM_TYPE_TEXT,key);
	}
	public int getIntValue(String key) {
		// TODO �Զ����ɷ������
		ChoiceItemContents item = choiceContentMap.get(key);
		if(item != null)
			return item.getValue();
		return 0;
	}

	public void setIntValue(String key, int value) {
		// TODO �Զ����ɷ������
		ChoiceItemContents item = choiceContentMap.get(key);
		item.setValue(value);
		manager.fireManagerChanged(this, 
				IManagerListener.CHANGED, Messages.ITEM_TYPE_BUTTON, key);
	}

	public Map getTextContentMap(){
		return this.textContentMap;
	}
	public Map getChoiceContentMap(){
		return this.choiceContentMap;
	}
	public int getSequence(){
		return this.sequence;
	}
	public void setSequence(int sequence){
		this.sequence = sequence;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	abstract public ArrayList getStringKeys();
	abstract public Map getChoiceKeysMap();
	/*
	 * �ڵ�ʮ�������Ӵ˳��󷽷����޸����ı�AddressListʱ��DetailsPart�ı������������֮�ı��BUG
	 */
	abstract public String getDescription();
}