package com.plugindev.addressbook.extension;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

public class ShowMessageAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;

	public void dispose() {
		this.window = null;
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IExtensionPoint ep = reg
				.getExtensionPoint("com.plugindev.addressbook.messages");
		IExtension[] extensions = ep.getExtensions();

		for (int i = 0; i < extensions.length; i++) {
			IExtension ext = extensions[i];
			IConfigurationElement ce = ext.getConfigurationElements()[0];
			if (!"impl".equals(ce.getName()))
				return;
			
			try {
				String name = ce.getAttribute("name");
				Object obj = ce.createExecutableExtension("class");
				IMessageProvider provider = (IMessageProvider) obj;
				MessageDialog.openInformation(window.getShell(), "From " + name
						+ ":" + provider.getTitle(), provider.getMessage());
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {

	}

}
