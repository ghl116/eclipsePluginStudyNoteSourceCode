package com.plugindev.addressbook.example;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

public class ViewExampleAction implements IViewActionDelegate {
	private IWorkbenchPart targetPart;

	public void init(IViewPart view) {
		// TODO �Զ����ɷ������
		this.targetPart = view;
	}

	public void run(IAction action) {
		// TODO �Զ����ɷ������
		MessageDialog.openInformation(targetPart.getSite().getShell(),
				"��Ϣ", "��ͼʾ��������ѡ��");
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO �Զ����ɷ������

	}

}
