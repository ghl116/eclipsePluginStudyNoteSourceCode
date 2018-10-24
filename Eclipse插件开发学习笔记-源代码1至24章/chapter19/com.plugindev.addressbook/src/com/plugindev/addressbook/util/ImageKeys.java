package com.plugindev.addressbook.util;

import org.eclipse.jface.resource.ImageDescriptor;

import com.plugindev.addressbook.Activator;

public final class ImageKeys {
	public static final String IMAGE_CATEGORY = "icons/category.gif";
	public static final String IMAGE_PEOPLE = "icons/people.gif";
	
	//类别图像
	public static final String IMG_CAT_BUSINESS = "icons/category/business.gif";
	public static final String IMG_CAT_FAMILY = "icons/category/family.gif";
	public static final String IMG_CAT_FRIENDS = "icons/category/friends.gif";
	public static final String IMG_CAT_LOVER = "icons/category/lover.gif";
	public static final String IMG_CAT_MATE = "icons/category/mate.gif";
	public static final String IMG_CAT_ORDINARY = "icons/category/ordinary.gif";
	public static final String IMG_CAT_VIP = "icons/category/vip.gif";
	public static final String IMG_CAT_UNKNOWN = "icons/category/unknown.gif";
	public static final String IMG_CAT_TEACHER = "icons/category/teacher.gif";
	
	//操作图像
	public static final String IMG_TOOL_ADD = "icons/actions/add.gif";
	public static final String IMG_TOOL_DELETE = "icons/actions/delete.gif";
	public static final String IMG_TOOL_DISABLEDELETE = "icons/actions/delete_dis.gif";
	public static final String IMG_TOOL_FILTER = "icons/actions/filter.gif";
	
	//编辑器相关
	public static final String IMG_FORM_BG = "icons/editors/form_banner.gif"; //$NON-NLS-1$
	public static final String IMG_HORIZONTAL = "icons/editors/th_horizontal.gif"; //$NON-NLS-1$
	public static final String IMG_VERTICAL = "icons/editors/th_vertical.gif"; //$NON-NLS-1$
	
	//ScrollBlock图标
	public static final String IMG_SCROL_BASIC = "icons/editors/basic.gif";
	public static final String IMG_SCROL_PHONE = "icons/editors/phone.gif";
	public static final String IMG_SCROL_AREA = "icons/editors/area.gif";
	public static final String IMG_SCROL_CONTACT = "icons/editors/contact.gif";
	
	//向导相关
	public static final String IMG_WIZARD_NEW = "icons/wizards/new_wiz.gif";
	
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return Activator.getImageDescriptor(path);
	}
}
