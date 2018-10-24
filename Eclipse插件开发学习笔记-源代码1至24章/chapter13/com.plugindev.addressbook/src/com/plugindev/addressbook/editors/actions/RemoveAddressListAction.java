package com.plugindev.addressbook.editors.actions;
/**
 * 在13.8节中加入此类
 */
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.editor.FormEditor;

import com.plugindev.addressbook.editors.AddressFormEditor;
import com.plugindev.addressbook.editors.models.AddressList;
import com.plugindev.addressbook.editors.models.AddressListManager;
import com.plugindev.addressbook.editors.models.SimpleFormEditorInput;

public class RemoveAddressListAction extends Action {
	private final AddressFormEditor editor;
	private final TableViewer viewer;
	private ISelectionChangedListener listener = 
		new ISelectionChangedListener(){
		public void selectionChanged(SelectionChangedEvent e)
		{
			ISelection selection = e.getSelection();
			setEnabled(!e.getSelection().isEmpty());
		}
	};
	public RemoveAddressListAction(FormEditor editor, TableViewer viewer,
			String text, ImageDescriptor imageDescriptor){
		super(text, imageDescriptor);
		this.editor = (AddressFormEditor)editor;
		this.viewer = viewer;
		setEnabled(false);
		viewer.addSelectionChangedListener(listener);
	}
	public void run(){
		AddressListManager manager = ((SimpleFormEditorInput)editor.getEditorInput()).getManager();
		ISelection sel = viewer.getSelection();
		Table table = viewer.getTable();
		table.setRedraw(false);
		try{
			AddressList list = (AddressList)((IStructuredSelection)sel).getFirstElement();
			manager.remove(list, true);
		}
		finally{
			table.setRedraw(true);
			viewer.refresh();
		}
	}
}
