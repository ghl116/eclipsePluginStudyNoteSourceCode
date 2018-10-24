package com.plugindev.addressbook.example;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ObjExampleAction implements IObjectActionDelegate {
	private IWorkbenchPart targetPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		//为代理设置活动部分,每次操作在上下文菜单中出现时被调用
		this.targetPart = targetPart;
	}

	public void run(IAction action) {
		// TODO 自动生成方法存根
		MessageDialog.openInformation(targetPart.getSite().getShell(),
				"消息", "对象示例操作被选中");
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO 自动生成方法存根
	}
}
