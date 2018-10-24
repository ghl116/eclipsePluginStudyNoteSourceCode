package com.plugindev.addressbook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;

import com.plugindev.addressbook.dialogs.AddressViewFilterDialog;
import com.plugindev.addressbook.views.AddressViewerCategoryFilter;
import com.plugindev.addressbook.views.AddressViewerNameFilter;

public class AddressViewerFilterAction extends Action {
	private final Shell shell;
	private final AddressViewerCategoryFilter categoryFilter;
	private final AddressViewerNameFilter nameFilter;
	public AddressViewerFilterAction(
			StructuredViewer viewer, String text, ImageDescriptor imageDescriptor)
	{
		super(text, imageDescriptor);
		shell = viewer.getControl().getShell();
		categoryFilter = new AddressViewerCategoryFilter(viewer);
		nameFilter = new AddressViewerNameFilter(viewer);
	}
	
	public void run(){
		AddressViewFilterDialog dialog = new AddressViewFilterDialog(shell,
				nameFilter.getPattern(),
				categoryFilter.getCategories());
		if(dialog.open() != AddressViewFilterDialog.OK)
			return;
		nameFilter.setPattern(dialog.getNamePattern());
		categoryFilter.setCategories(dialog.getSelectedCategories());
	}
	
	//保存过滤状态
	public void saveState(IMemento memento){
		categoryFilter.saveState(memento);
		nameFilter.saveState(memento);
	}
	//获得保存的过滤状态
	public void init(IMemento memento){
		categoryFilter.init(memento);
		nameFilter.init(memento);
	}
}
