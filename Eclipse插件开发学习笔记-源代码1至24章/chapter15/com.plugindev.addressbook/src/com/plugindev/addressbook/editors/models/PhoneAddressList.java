package com.plugindev.addressbook.editors.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.plugindev.addressbook.util.Messages;

/**
 * @author nemo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PhoneAddressList extends AddressList {

	//存储电话项的名称，为不同的地址项（AddressItem）固定不变的部分
	public static ArrayList <String> stringKeys;
/*	private Map<String, PhonePropertyItem> phoneMap;*/
	public static final String TYPE_PHONE = "phoneList";
	/**
	 * @param name
	 */
	public PhoneAddressList(String name, ArrayList items, int sequence) {
		super(name, items, new ArrayList(), sequence);
		if(stringKeys == null){
			stringKeys = new ArrayList<String>();
			for(int i = 0; i < items.size(); i++){
				TextItemContents item = (TextItemContents)items.get(i);
				stringKeys.add(item.getKey());
			}
		}
		setType(TYPE_PHONE);
	}
	@Override
	public Map getChoiceKeysMap() {
		return new HashMap();
	}
	@Override
	public ArrayList getStringKeys() {
		return stringKeys;
	}
	@Override
	public String getDescription() {
		return Messages.ADDR_LIST_PHONE_DESC;
	}
}