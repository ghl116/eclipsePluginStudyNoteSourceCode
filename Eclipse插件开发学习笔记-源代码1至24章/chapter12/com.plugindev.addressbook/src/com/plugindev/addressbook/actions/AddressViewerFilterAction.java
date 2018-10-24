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
					shell,"类别过滤", "输入一个类别名称 "
					+System.getProperty("line.seperator")
					+"或者置空",
					categoryFilter.getPattern(),
					null);
		if(dialog.open() == InputDialog.OK)
			categoryFilter.setPattern(dialog.getValue().trim());
	}
	
	//保存过滤状态
	public void saveState(IMemento memento){
		categoryFilter.saveState(memento);
	}
	//获得保存的过滤状态
	public void init(IMemento memento){
		categoryFilter.init(memento);
	}
}
