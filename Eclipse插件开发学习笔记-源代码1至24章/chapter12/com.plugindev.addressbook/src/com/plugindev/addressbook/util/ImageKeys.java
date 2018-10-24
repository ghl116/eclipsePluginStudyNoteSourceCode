package com.plugindev.addressbook.util;

import org.eclipse.jface.resource.ImageDescriptor;

import com.plugindev.addressbook.Activator;

public final class ImageKeys {
	public static final String IMAGE_CATEGORY = "icons/category.gif";
	public static final String IMAGE_PEOPLE = "icons/people.gif";
	
	//Àà±ðÍ¼Ïñ
	public static final String IMG_CAT_BUSINESS = "icons/category/business.gif";
	public static final String IMG_CAT_FAMILY = "icons/category/family.gif";
	public static final String IMG_CAT_FRIENDS = "icons/category/friends.gif";
	public static final String IMG_CAT_LOVER = "icons/category/lover.gif";
	public static final String IMG_CAT_MATE = "icons/category/mate.gif";
	public static final String IMG_CAT_ORDINARY = "icons/category/ordinary.gif";
	public static final String IMG_CAT_VIP = "icons/category/vip.gif";
	public static final String IMG_CAT_UNKNOWN = "icons/category/unknown.gif";
	public static final String IMG_CAT_TEACHER = "icons/category/teacher.gif";
	
	//²Ù×÷Í¼Ïñ
	public static final String IMG_TOOL_ADD = "icons/actions/add.gif";
	public static final String IMG_TOOL_DELETE = "icons/actions/delete.gif";
	public static final String IMG_TOOL_DISABLEDELETE = "icons/actions/delete_dis.gif";
	public static final String IMG_TOOL_FILTER = "icons/actions/filter.gif";
	
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return Activator.getImageDescriptor(path);
	}
}
