package com.plugindev.addressbook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;

import com.plugindev.addressbook.views.AddressViewerCategoryFilter;

public class AddressViewerFilterAction extends Action {
	private final Shell shell;
	private final AddressViewerCategoryFilter categoryFilter;
	public AddressViewerFilterAction(
			StructuredViewer viewer, String text, ImageDescriptor imageDescriptor)
	{
		super(text, imageDescriptor);
		shell = viewer.getControl().getShell();
		categoryFilter = new AddressViewerCategoryFilter(viewer);
	}
	
	public void run(){
		InputDialog dialog = 
			new InputDialog(
					shell,"������", "����һ��������� "
					+System.getProperty("line.seperator")
					+"�����ÿ�",
					categoryFilter.getPattern(),
					null);
		if(dialog.open() == InputDialog.OK)
			categoryFilter.setPattern(dialog.getValue().trim());
	}
	
	//�������״̬
	public void saveState(IMemento memento){
		categoryFilter.saveState(memento);
	}
	//��ñ���Ĺ���״̬
	public void init(IMemento memento){
		categoryFilter.init(memento);
	}
}
