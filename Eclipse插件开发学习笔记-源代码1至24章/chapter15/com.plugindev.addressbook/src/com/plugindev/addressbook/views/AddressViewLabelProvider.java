package com.plugindev.addressbook.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.plugindev.addressbook.models.AddressItem;

public class AddressViewLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		// TODO 自动生成方法存根
		switch(columnIndex){
		//姓名
		case 0:
			return null;
		//类别
		case 1:
			return ((AddressItem)element).getCategory().getImage();
		default:
			return null;
		}
	}

	public String getColumnText(Object element, int columnIndex) {
		// TODO 自动生成方法存根
		
		switch(columnIndex){
		case 0:
			if(element != null)
				return ((AddressItem)element).getName();
			return "";
		case 1:
			if(element != null)
				return ((AddressItem)element).getCategory().getCategoryName();
			return "";
		default:
			return "";
		}
	}
}
