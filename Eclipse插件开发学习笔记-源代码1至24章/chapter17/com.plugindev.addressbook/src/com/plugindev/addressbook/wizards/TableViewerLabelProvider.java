package com.plugindev.addressbook.wizards;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.plugindev.addressbook.editors.models.AddressList;
import com.plugindev.addressbook.editors.models.AreaAddressList;
import com.plugindev.addressbook.editors.models.BasicAddressList;
import com.plugindev.addressbook.editors.models.PhoneAddressList;
import com.plugindev.addressbook.util.ImageCache;
import com.plugindev.addressbook.util.ImageKeys;

public class TableViewerLabelProvider extends LabelProvider
implements ITableLabelProvider {

	public Image getColumnImage(Object obj, int index) {
		if(index == 0){
			if (obj instanceof BasicAddressList) {
				return ImageCache.getInstance().getImage(
						ImageKeys.getImageDescriptor(ImageKeys.IMG_SCROL_BASIC));
			}
			//省略了部分相似代码
			if (obj instanceof PhoneAddressList) {
				return ImageCache.getInstance().getImage(
						ImageKeys.getImageDescriptor(ImageKeys.IMG_SCROL_PHONE));
			}
			if(obj instanceof AreaAddressList){
				return ImageCache.getInstance().getImage(
						ImageKeys.getImageDescriptor(ImageKeys.IMG_SCROL_AREA));
			}
			else
				return ImageCache.getInstance().getImage(
						ImageKeys.getImageDescriptor(ImageKeys.IMG_CAT_UNKNOWN));
		}
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof AddressList){
			AddressList addressList = (AddressList)element;
			switch(columnIndex){
			case 0:
				return addressList.getName();
			case 1:
				return addressList.getDescription();
			default:
				return "";
			}
		}
		if(element == null)	return "<null>";
		return element.toString();

	}
}
