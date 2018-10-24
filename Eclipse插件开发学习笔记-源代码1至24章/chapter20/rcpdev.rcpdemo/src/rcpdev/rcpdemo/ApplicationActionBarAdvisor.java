package rcpdev.rcpdemo;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	private IWorkbenchAction aboutAction;

	protected void makeActions(IWorkbenchWindow window) {
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		menuBar.add(aboutAction);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		
	}

}
