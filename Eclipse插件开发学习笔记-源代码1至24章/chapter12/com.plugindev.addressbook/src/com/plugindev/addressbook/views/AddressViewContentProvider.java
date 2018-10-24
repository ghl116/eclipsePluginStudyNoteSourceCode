package com.plugindev.addressbook.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

import com.plugindev.addressbook.models.AddressItem;
import com.plugindev.addressbook.models.AddressManager;
import com.plugindev.addressbook.models.AddressManagerEvent;
import com.plugindev.addressbook.models.AddressManagerListener;

public class AddressViewContentProvider implements IStructuredContentProvider,
		AddressManagerListener {
	private TableViewer viewer;
	private AddressManager manager;

	public Object[] getElements(Object inputElement) {
		// TODO �Զ����ɷ������
		return manager.getAddresses();
	}

	public void dispose() {
		// TODO �Զ����ɷ������

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO �Զ����ɷ������
		this.viewer = (TableViewer)viewer;
		if(manager != null)
			manager.removeAddressManagerListener(this);
		manager = (AddressManager)newInput;
		if(manager != null)
		{
			manager.addAddressManagerListener(this);
		}
	}

	public void addressesChanged(AddressManagerEvent event) {
		// TODO �Զ����ɷ������
		viewer.getTable().setRedraw(false);
		try{
			viewer.remove(event.getItemsRemoved());
			viewer.add(event.getItemsAdded());
			viewer.update(event.getItemsModified(), null);
		}
		finally{
			viewer.getTable().setRedraw(true);
		}
	}

}
