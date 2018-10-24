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
		 * 在第十五章时增加该段代码，当删除AddressView中的地址元素时，
		 * 会关闭相应的编辑器，同时删除相对应的文件。
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
		 * 第十五章增加的代码结束
		 */
		AddressManager.getManager().removeAddresses(
				items);
		
	}
}
