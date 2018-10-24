package rcpdev.application;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction preferencesAction;
    private IWorkbenchAction saveAction;
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
		{
			saveAction = ActionFactory.SAVE.create(window);
			register(saveAction);
		}
		{
			preferencesAction = ActionFactory.PREFERENCES.create(window);
			register(preferencesAction);
		}
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	final MenuManager optionsMenu = new MenuManager("Options");
    	menuBar.add(optionsMenu);

    	optionsMenu.add(preferencesAction);
    }
	protected void fillCoolBar(ICoolBarManager coolBar) {
		final ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT);
		coolBar.add(toolBarManager);

		toolBarManager.add(saveAction);
	}
    
}
