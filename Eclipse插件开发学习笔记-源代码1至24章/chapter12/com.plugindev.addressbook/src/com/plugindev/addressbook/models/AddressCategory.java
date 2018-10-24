package com.plugindev.addressbook.models;

import java.util.HashMap;

import org.eclipse.swt.graphics.Image;

import com.plugindev.addressbook.util.ImageCache;
import com.plugindev.addressbook.util.ImageKeys;

public abstract class AddressCategory implements Comparable{
	private final String categoryName;
	private final int priority;
	private static final HashMap<String, AddressCategory> categoryMap = 
		new HashMap<String, AddressCategory>();
	public AddressCategory( String categoryName, int priority)
	{
		this.categoryName = categoryName;
		this.priority = priority;
	}
	public static final AddressCategory UNKNOWN = 
		new AddressCategory( "δ����", 0)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
			ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_UNKNOWN));
		}
	};
	public static final AddressCategory ORDINARY = 
		new AddressCategory("��ͨ", 1)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_ORDINARY));		}
	};
	public static final AddressCategory MATE = 
		new AddressCategory("ͬ��", 2)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_MATE));
		}
	};
	public static final AddressCategory BUSINESS = 
		new AddressCategory("��ҵ", 3)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_BUSINESS));
		}
	};
	public static final AddressCategory FRIENDS =
		new AddressCategory("����", 4)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_FRIENDS));
		}
	};

	public static final AddressCategory FAMILY = 
		new AddressCategory("��ͥ", 5)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_FAMILY));
		}
	};
	public static final AddressCategory VIP = 
		new AddressCategory("VIP", 6)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_UNKNOWN));
		}
	};
	public static final AddressCategory TEACHER = 
		new AddressCategory("ʦ��", 7)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_TEACHER));
		}
	};
	public static final AddressCategory LOVER = 
		new AddressCategory("����", 8)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_LOVER));
		}
	};

	////���Ͳ���
	private static final AddressCategory [] TYPES = {
		UNKNOWN,
		ORDINARY,
		MATE,
		BUSINESS,
		FRIENDS,
		FAMILY,
		VIP,
		TEACHER,
		LOVER,
	};

	public static AddressCategory[] getTypes(){
		return TYPES;
	}
	public static HashMap<String, AddressCategory> getCategoryMap(){
		if(categoryMap.isEmpty())
		{
			categoryMap.put("δ����", UNKNOWN);
			categoryMap.put("��ͨ", ORDINARY);
			categoryMap.put("ͬ��", MATE);
			categoryMap.put("��ҵ", BUSINESS);
			categoryMap.put("����", FRIENDS);
			categoryMap.put("��ͥ", FAMILY);
			categoryMap.put("VIP", VIP);
			categoryMap.put("ʦ��", TEACHER);
			categoryMap.put("����", LOVER);
		}
		return categoryMap;
	}
	public String getCategoryName()
	{
		return categoryName;
	}
	public int compareTo(Object obj){
		return this.priority
		-((AddressCategory)obj).priority;
	}
	public static AddressCategory getCategoryByName(String name){
		for(int i = 0; i <TYPES.length; i++){
			if(TYPES[i].getCategoryName() == name)
				return TYPES[i];
		}
		return TYPES[0];
	}
	public abstract Image getImage();
}
