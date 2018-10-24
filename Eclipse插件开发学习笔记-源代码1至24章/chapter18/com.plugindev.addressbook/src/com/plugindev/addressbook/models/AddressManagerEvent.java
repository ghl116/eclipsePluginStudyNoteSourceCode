package com.plugindev.addressbook.models;

import java.util.EventObject;

public class AddressManagerEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3593257924686815924L;

	private final AddressItem[] added;
	private final AddressItem[] removed;
	private final AddressItem[] modified;
	public AddressManagerEvent(AddressManager source,
		      AddressItem[] itemsAdded, AddressItem[] itemsRemoved, AddressItem[] itemsModified) 
	{
		super(source);
		added = itemsAdded;
		removed = itemsRemoved;
		modified = itemsModified;
	}
	public AddressItem[] getItemsAdded() 
	{
		return added;
	}
	public AddressItem[] getItemsRemoved() 
	{
		return removed;
	}
	public AddressItem[] getItemsModified()
	{
		return modified;
	}
}
