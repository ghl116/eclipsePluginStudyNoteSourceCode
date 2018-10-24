package com.plugindev.addressbook.example;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

public class ViewerExampleAction implements IViewActionDelegate {
	private IWorkbenchPart targetPart;
	
	public void init(IViewPart view) {
		this.targetPart = view;
	}

	public void run(IAction action) {
		MessageDialog.openInformation(targetPart.getSite().getShell(),
				"��Ϣ", "�鿴��ʾ��������ѡ��");
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
