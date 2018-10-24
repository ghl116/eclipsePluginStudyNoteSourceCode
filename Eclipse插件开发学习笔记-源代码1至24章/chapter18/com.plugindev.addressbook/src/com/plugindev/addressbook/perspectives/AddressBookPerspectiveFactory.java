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
		//��ñ༭������
		String editorArea = layout.getEditorArea();
		
		//����ַ����ͼ����༭����������
		layout.addView(ADDRESS_VIEW_ID, 
				IPageLayout.LEFT, 0.25f, editorArea);
		
		//�ڱ༭������ײ�����һ�������֣�IFolderLayout��
		IFolderLayout bottom = layout.createFolder("bottom", 
				IPageLayout.BOTTOM, 0.66f, editorArea);
		
		//��������ͼ����bottom��
		bottom.addView(IPageLayout.ID_PROP_SHEET);
		//bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addPlaceholder(IPageLayout.ID_PROP_SHEET);
		//����ַ������������͸��ͼ
		layout.addActionSet(ADDRESS_ACTION_ID);
	}
}
