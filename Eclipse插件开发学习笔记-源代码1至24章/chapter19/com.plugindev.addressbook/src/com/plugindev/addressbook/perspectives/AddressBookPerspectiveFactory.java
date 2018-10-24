package com.plugindev.addressbook.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class AddressBookPerspectiveFactory implements IPerspectiveFactory {
	private static final String ADDRESS_VIEW_ID = 
		"com.plugindev.addressbook.views.AddressView";
	private static final String ADDRESS_ACTION_ID =
		"com.plugindev.addressbook.actionSet";
	public void createInitialLayout(IPageLayout layout) {
		//获得编辑器区域
		String editorArea = layout.getEditorArea();
		
		//将地址本视图放入编辑器区域的左侧
		layout.addView(ADDRESS_VIEW_ID, 
				IPageLayout.LEFT, 0.25f, editorArea);
		
		//在编辑器区域底部创建一个包布局（IFolderLayout）
		IFolderLayout bottom = layout.createFolder("bottom", 
				IPageLayout.BOTTOM, 0.66f, editorArea);
		
		//将属性视图放入bottom中
		bottom.addView(IPageLayout.ID_PROP_SHEET);
		//bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addPlaceholder(IPageLayout.ID_PROP_SHEET);
		//将地址本操作集加入透视图
		layout.addActionSet(ADDRESS_ACTION_ID);
	}
}
