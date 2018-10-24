package com.plugindev.addressbook.editors.models;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.plugindev.addressbook.util.ImageCache;
import com.plugindev.addressbook.util.ImageKeys;

public class MasterLabelProvider extends LabelProvider
implements ITableLabelProvider {
	public String getColumnText(Object obj, int index) {
		return obj.toString();
	}
	public Image getColumnImage(Object obj, int index) {
		if (obj instanceof BasicAddressList) {
			return ImageCache.getInstance().getImage(ImageKeys.getImageDescriptor(ImageKeys.IMG_SCROL_BASIC));
		}
		if (obj instanceof PhoneAddressList) {
			return ImageCache.getInstance().getImage(ImageKeys.getImageDescriptor(ImageKeys.IMG_SCROL_PHONE));
		}
		if(obj instanceof AreaAddressList){
			return ImageCache.getInstance().getImage(ImageKeys.getImageDescriptor(ImageKeys.IMG_SCROL_AREA));
		}
		else
			return ImageCache.getInstance().getImage(ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_UNKNOWN));
	}
}
