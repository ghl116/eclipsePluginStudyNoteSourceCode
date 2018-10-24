package com.plugindev.addressbook.editors.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.ui.IMemento;

import com.plugindev.addressbook.Activator;
import com.plugindev.addressbook.util.Messages;

public class AddressListManager {
	private ArrayList<IManagerListener> managerListeners;
	private ArrayList<AddressList> objects;
	private String peopleName;
	private boolean initFlag = false;
/*	private static MYXMLMemento memento;*/
	
	
	public AddressListManager(String name) {
		managerListeners = new ArrayList<IManagerListener>();
		this.peopleName = name;
		initialize();
	}
	public void addManagerListener(IManagerListener listener) {
		if (!managerListeners.contains(listener))
			managerListeners.add(listener);
	}
	public void removeManagerListener(IManagerListener listener) {
		managerListeners.remove(listener);
	}
	public void fireManagerChanged(Object object, String type, String itemType, String property) {
		for (int i = 0; i < managerListeners.size(); i++) {
			((IManagerListener) managerListeners.get(i)).managerChanged(object,
					type, itemType, property);
		}
	}
	public Object[] getContents() {
		return objects.toArray();
	}
	private void initialize() {
		objects = new ArrayList<AddressList>();

		//在13.6小节加入，从文件中获得MYXMLMemento模型
		FileReader reader = null;
		try{
			reader = new FileReader(getDescriptionFile());
			MYXMLMemento memento = MYXMLMemento.createReadRoot(reader);
			loadDescriptions(memento);
		}catch(FileNotFoundException e)
		{
			//to be added
			initFlag = true;
		}catch(Exception e)
		{
			e.printStackTrace();
			//to be added
		}finally{
			try{
				if(reader != null)
					reader.close();
			}
			catch(IOException e){
				//to be added
			}
		}
		if(initFlag == true){
		//13.6小节前加入
			ArrayList<TextItemContents> initPhoneItems = new ArrayList<TextItemContents>();
			ArrayList<TextItemContents> initPositionItems = new ArrayList<TextItemContents>();
			ArrayList<TextItemContents> initBasicStringItems = new ArrayList<TextItemContents>();
			ArrayList<ChoiceItemContents> initBasicChoiceItems = new ArrayList<ChoiceItemContents>();
			
			initBasicStringItems.add(new TextItemContents(Messages.P_BASIC_AGE, ""));
			initBasicStringItems.add(new TextItemContents(Messages.P_BASIC_PROFESSION, ""));
			initBasicChoiceItems.add(new ChoiceItemContents(Messages.P_BASIC_GENDER,
					new String[]{Messages.P_BASIC_GENDER_F, Messages.P_BASIC_GENDER_M}, 0));
			
			initPhoneItems.add(new TextItemContents(Messages.P_PHONE_HOME,""));
			initPhoneItems.add(new TextItemContents(Messages.P_PHONE_OFFICE,""));
			initPhoneItems.add(new TextItemContents(Messages.P_PHONE_CEL,""));
			initPhoneItems.add(new TextItemContents(Messages.P_PHONE_OTHER,""));
			
			initPositionItems.add(new TextItemContents(Messages.P_POS_HOME,""));
			initPositionItems.add(new TextItemContents(Messages.P_POS_OFFICE,""));
			initPositionItems.add(new TextItemContents(Messages.P_POS_OTHER,""));

			add(new BasicAddressList(Messages.ADDR_LIST_BASIC,initBasicStringItems, initBasicChoiceItems, 0), false);
			add(new PhoneAddressList(Messages.ADDR_LIST_PHONE, initPhoneItems,1), false);
			add(new AreaAddressList(Messages.ADD_LIST_AREA, initPositionItems,2), false);
			//13.6小节加入
			initFlag = false;
		}
	}
	public void add(AddressList obj, boolean notify) {
		AddressList list = obj;
		objects.add(list);
		obj.setManager(this);
		if (notify)
			fireManagerChanged(obj, IManagerListener.ADDED, "",""); //$NON-NLS-1$
	}
	public void remove(AddressList obj, boolean notify) {
			objects.remove(obj);
			obj.setManager(null);

		if (notify)
			fireManagerChanged(obj, IManagerListener.REMOVED, "",""); //$NON-NLS-1$
	}
	public void removeAll(){
		objects.removeAll(objects);
	}
	/*
	 * 在13.6小节加入，通过人名获得相应的xml文件
	 */
	public File getDescriptionFile(){
		
		//在第十五章改变文件类型后缀为.addr，以方便区别地址信息描述文件和其他文件。
		return Activator.getDefault().getStateLocation().
		append(peopleName +".addr").toFile();
	}
	
	/*
	 * 在13.6小节加入，从MYXMLMemento模型中创建AddressList模型
	 */
	public void loadDescriptions(MYXMLMemento memento){
		IMemento[] listChildren = memento.getChildren("list");
		if(listChildren == null){
			initFlag = true;
			return;
		}
		for(int i = 0; i < listChildren.length; i++)
		{
			String name = listChildren[i].getString("name");
			String type = listChildren[i].getString("type");
			int sequence = listChildren[i].getInteger("sequence").intValue();
			IMemento[] textChildren = listChildren[i].getChildren("textItem");
			IMemento[] choiceChildren = listChildren[i].getChildren("choiceItem");
			ArrayList<TextItemContents> textList;
			ArrayList<ChoiceItemContents> choiceList;
			
			textList = new ArrayList<TextItemContents>();
			for(int j= 0; j < textChildren.length; j++){
				TextItemContents item = new TextItemContents(textChildren[j].getString("key"),
						textChildren[j].getString("value"));
				textList.add(item);
			}
			choiceList = new ArrayList<ChoiceItemContents>();
			for(int j = 0; j < choiceChildren.length; j++){
				String key = choiceChildren[j].getString("key");
				int value = choiceChildren[j].getInteger("value").intValue();
				IMemento[] choiceStringMemento = choiceChildren[j].getChildren("choiceString");
				String[] choiceStrings = new String[choiceStringMemento.length];
				for(int k = 0; k < choiceStringMemento.length; k++){
					choiceStrings[k] = choiceStringMemento[k].getString("name");
				}
				
				ChoiceItemContents item = new ChoiceItemContents(key, choiceStrings, value);
				choiceList.add(item);
			}
			if(type.equals(BasicAddressList.TYPE_BASIC))
			{
				BasicAddressList basicList = new BasicAddressList(name, textList, choiceList, sequence);
				add(basicList, false);
			}
			else if(type.equals(PhoneAddressList.TYPE_PHONE))
			{
				PhoneAddressList phoneList = new PhoneAddressList(name, textList, sequence);
				add(phoneList, false);
			}
			else if(type.equals(AreaAddressList.TYPE_AREA))
			{
				AreaAddressList areaList = new AreaAddressList(name, textList,sequence);
				add(areaList, false);
			}
		}
	}
	/*
	 * 在13.6小节加入，保存模型
	 */
	public void saveDescriptions(){
		if(objects == null)
			return;
		MYXMLMemento memento = MYXMLMemento.createWriteRoot("messageLists");
		saveDescriptions(memento);
		FileWriter writer = null;
		try{
			writer = new FileWriter(getDescriptionFile());
			memento.save(writer);
		}catch(IOException e){
			//to be added
		}finally{
			try{
				if(writer != null)
					writer.close();
			}catch(IOException e){
				//to be added
			}
		}
	}
	/*
	 * 在13.6小节加入，保存MYXMLMemento模型
	 */
	public void saveDescriptions(IMemento memento){
		Iterator iter = objects.iterator();
		for(int i = 0; iter.hasNext(); i++){
			AddressList list = (AddressList)iter.next();
			IMemento child = memento.createChild("list");
			child.putString("type", list.getType());
			child.putInteger("sequence", i);
			child.putString("name", list.getName());
			Object[] stringKeys = list.getStringKeys().toArray();
			for(int j = 0; j < stringKeys.length; j++){
				IMemento textElement = child.createChild("textItem");
				textElement.putString("key", (String)stringKeys[j]);
				textElement.putString("value", list.getStringValue((String)stringKeys[j]));
			}
			Map choiceKeysMap = list.getChoiceKeysMap();
			Object[] intKeys = choiceKeysMap.keySet().toArray();

			for(int j = 0; j < intKeys.length; j++){
				IMemento choiceElement = child.createChild("choiceItem");
				choiceElement.putString("key", (String)intKeys[j]);
				choiceElement.putInteger("value", list.getIntValue((String)intKeys[j]));
				Object[] choiceStrings = (Object[])choiceKeysMap.get(intKeys[j]);
				for(int k = 0; k < choiceStrings.length; k++){
					IMemento nameChild = choiceElement.createChild("choiceString");
					nameChild.putString("name", (String)choiceStrings[k]);
				}
			}
		}
	}
}

