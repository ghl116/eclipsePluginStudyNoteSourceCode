package com.plugindev.addressbook.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.XMLMemento;

import com.plugindev.addressbook.Activator;

public class AddressManager implements IPropertyChangeListener{
	private static final String TAG_ADDRESSES = "Addresses";
	private static final String TAG_ADDRESS = "AddressItem";
	private static final String TAG_NAME = "name";
	private static final String TAG_CATEGORY = "category";
	
	private static AddressManager manager;
	private Collection<AddressItem> addresses;
	private List listeners = new ArrayList();
	
	public static AddressManager getManager()
	{
		if(manager == null)
			manager = new AddressManager();
		return manager;
	}
	public AddressItem[] getAddresses(){
		if(addresses == null)
			loadAddresses();
		return (AddressItem[])addresses.toArray(
				new AddressItem[addresses.size()]);
	}
	public void addAddresses(AddressItem[] items){
		if(addresses == null)
			loadAddresses();
		if(addresses.addAll(Arrays.asList(items)))
		{
			fireAddressesChanged(items, AddressItem.NONE, AddressItem.NONE);
		}
	}
	public void removeAddresses(AddressItem[] items)
	{
		if(addresses == null)
			loadAddresses();
		if(addresses.removeAll(Arrays.asList(items)))
		{
			fireAddressesChanged(AddressItem.NONE, items, AddressItem.NONE);
		}
	}
	private void loadAddresses(){
		//临时实现,用来查看视图的显示效果——读取文件
		addresses = new HashSet(20);
/*		AddressItem item1 = new AddressItem("Nemo", AddressCategory.FAMILY);
		AddressItem item2 = new AddressItem("Dingding", AddressCategory.VIP);
		AddressItem item3 = new AddressItem("Flysky", AddressCategory.FRIENDS);
		AddressItem item4 = new AddressItem("Rainny", AddressCategory.UNKNOWN);
		AddressItem item5 = new AddressItem("Brighter", AddressCategory.ORDINARY);
		AddressItem item6 = new AddressItem("Snow", AddressCategory.LOVER);
		AddressItem item7 = new AddressItem("Denny", AddressCategory.BUSINESS);
		AddressItem item8 = new AddressItem("Lily", AddressCategory.TEACHER);
		addresses.add(item1);
		addresses.add(item2);
		addresses.add(item3);
		addresses.add(item4);
		addresses.add(item5);
		addresses.add(item6);
		addresses.add(item7);
		addresses.add(item8);*/
		
		//在12.7.2小节加入该段代码
		FileReader reader = null;
		try{
			reader = new FileReader(getAddressesFile());
			loadAddresses(XMLMemento.createReadRoot(reader));
		}catch(FileNotFoundException e)
		{
			//to be added
		}catch(Exception e)
		{
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
		/*end*/
	}
	private File getAddressesFile() {
		return Activator.getDefault().getStateLocation().
			append("Addresses.xml").toFile();
	}
	private void loadAddresses(XMLMemento memento)
	{
		IMemento[] children = memento.getChildren(TAG_ADDRESS);
		for(int i = 0; i < children.length; i++)
		{
			AddressItem item = createNewAddressItem(children[i].getString(TAG_NAME),
					children[i].getString(TAG_CATEGORY));
			if(item != null)
				addresses.add(item);
		}
	}
	public AddressItem createNewAddressItem(String name, String categoryName) {
		AddressCategory category = AddressCategory.getCategoryMap().get(categoryName);
		return new AddressItem(name, category);
		}
	public void saveAddresses(){
		if(addresses == null)
			return;
		XMLMemento memento = XMLMemento.createWriteRoot(TAG_ADDRESSES);
		saveAddresses(memento);
		FileWriter writer = null;
		try{
			writer = new FileWriter(getAddressesFile());
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
	public void saveAddresses(IMemento memento){
		Iterator iter = addresses.iterator();
		while(iter.hasNext()){
			AddressItem item = (AddressItem)iter.next();
			IMemento child = memento.createChild(TAG_ADDRESS);
			child.putString(TAG_NAME, item.getName());
			child.putString(TAG_CATEGORY, item.getCategory().getCategoryName());
		}
	}
	public void addAddressManagerListener(AddressManagerListener listener)
	{
		listeners.add(listener);
	}
	public void removeAddressManagerListener(AddressManagerListener listener)
	{
		listeners.remove(listener);
	}
	private void fireAddressesChanged(AddressItem[] itemsAdded,
			AddressItem[] itemsRemoved, AddressItem[] itemsModified)
	{
		AddressManagerEvent event =
			new AddressManagerEvent(
					this, itemsAdded, itemsRemoved, itemsModified);
		for(Iterator iter = listeners.iterator();iter.hasNext();)
		{
			((AddressManagerListener)iter.next()).addressesChanged(event);
		}
	}
	public void propertyChange(PropertyChangeEvent event) {
		
		fireAddressesChanged(AddressItem.NONE, AddressItem.NONE,
				new AddressItem[]{(AddressItem)event.getSource()});
	}
}
