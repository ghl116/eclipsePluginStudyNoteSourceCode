package com.plugindev.addressbook.actions;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.plugindev.addressbook.Activator;
import com.plugindev.addressbook.editors.models.SimpleFormEditorInput;
import com.plugindev.addressbook.models.AddressItem;
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
		/*
		 * �ڵ�ʮ����ʱ���Ӹöδ��룬��ɾ��AddressView�еĵ�ַԪ��ʱ��
		 * ��ر���Ӧ�ı༭����ͬʱɾ�����Ӧ���ļ���
		 */
		AddressItem[] items = view.getSelectedAddresses();

		for(int i = 0; i < items.length; i++){
			IWorkbenchPage page = PlatformUI.getWorkbench().
			getActiveWorkbenchWindow().getActivePage();
			IEditorPart editor = page.findEditor(new SimpleFormEditorInput(items[i].getName()));
			if(editor != null){
				page.closeEditor(editor, false);
			}
			String fileName = items[i].getName();
			IPath path = Activator.getDefault().getStateLocation().
			append(fileName +".addr");
			File file = path.toFile();
			if(file.exists())
				file.delete();
		}
		/*
		 * ��ʮ�������ӵĴ������
		 */
		AddressManager.getManager().removeAddresses(
				items);
		
	}
}
