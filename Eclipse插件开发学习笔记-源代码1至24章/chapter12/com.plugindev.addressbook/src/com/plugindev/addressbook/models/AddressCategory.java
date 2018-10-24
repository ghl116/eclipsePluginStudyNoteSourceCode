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
		new AddressCategory( "未分类", 0)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
			ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_UNKNOWN));
		}
	};
	public static final AddressCategory ORDINARY = 
		new AddressCategory("普通", 1)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_ORDINARY));		}
	};
	public static final AddressCategory MATE = 
		new AddressCategory("同事", 2)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_MATE));
		}
	};
	public static final AddressCategory BUSINESS = 
		new AddressCategory("商业", 3)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_BUSINESS));
		}
	};
	public static final AddressCategory FRIENDS =
		new AddressCategory("朋友", 4)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_FRIENDS));
		}
	};

	public static final AddressCategory FAMILY = 
		new AddressCategory("家庭", 5)
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
		new AddressCategory("师长", 7)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_TEACHER));
		}
	};
	public static final AddressCategory LOVER = 
		new AddressCategory("伴侣", 8)
	{
		public Image getImage()
		{
			return ImageCache.getInstance().getImage(
					ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_LOVER));
		}
	};

	////类型查找
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
			categoryMap.put("未分类", UNKNOWN);
			categoryMap.put("普通", ORDINARY);
			categoryMap.put("同事", MATE);
			categoryMap.put("商业", BUSINESS);
			categoryMap.put("朋友", FRIENDS);
			categoryMap.put("家庭", FAMILY);
			categoryMap.put("VIP", VIP);
			categoryMap.put("师长", TEACHER);
			categoryMap.put("伴侣", LOVER);
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
