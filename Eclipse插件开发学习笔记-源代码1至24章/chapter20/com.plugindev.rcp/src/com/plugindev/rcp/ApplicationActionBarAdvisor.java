package com.plugindev.rcp;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	private IWorkbenchAction helpAction;

	private IWorkbenchAction welcomeAction;

	protected void makeActions(IWorkbenchWindow window) {
		helpAction = ActionFactory.HELP_CONTENTS.create(window);
		register(helpAction);

		welcomeAction = ActionFactory.INTRO.create(window);
		register(welcomeAction);

	}

	protected void fillMenuBar(IMenuManager menuBar) {
		try {

			menuBar
					.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

			IMenuManager menuManager = new MenuManager(
					Messages
							.getString("ApplicationActionBarAdvisor.helpMenu.label"), "helpMenu"); //$NON-NLS-1$ //$NON-NLS-2$
			menuManager.add(new GroupMarker("helpContentGroup")); //$NON-NLS-1$
			menuManager.appendToGroup("helpContentGroup", helpAction); //$NON-NLS-1$
			menuManager.appendToGroup("helpContentGroup", welcomeAction);//$NON-NLS-1$

			menuBar.add(menuManager);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
