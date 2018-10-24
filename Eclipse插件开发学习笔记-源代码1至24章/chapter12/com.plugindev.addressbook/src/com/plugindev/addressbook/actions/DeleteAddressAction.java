package com.plugindev.addressbook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import com.plugindev.addressbook.models.AddressManager;
import com.plugindev.addressbook.views.AddressView;

public class DeleteAddressAction extends Action {
	private AddressView view;
	public DeleteAddressAction(AddressView view, String text,
			ImageDescriptor imageDescriptor){
		super(text, imageDescriptor);
		this.view = view;
	}
	public void run()
	{
		AddressManager.getManager().removeAddresses(
				view.getSelectedAddresses());
	}
}
