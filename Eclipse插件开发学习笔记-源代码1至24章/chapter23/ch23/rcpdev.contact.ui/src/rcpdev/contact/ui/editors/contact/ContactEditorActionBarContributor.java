package rcpdev.contact.ui.editors.contact;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.part.EditorActionBarContributor;

public class ContactEditorActionBarContributor extends
		EditorActionBarContributor {

	IWorkbenchAction saveAction;

	@Override
	public void init(IActionBars bars, IWorkbenchPage page) {
		IWorkbenchWindow window = page.getWorkbenchWindow();
		saveAction = ActionFactory.SAVE.create(window);

		super.init(bars, page);
	}

	@Override
	public void contributeToCoolBar(ICoolBarManager coolBarManager) {
		coolBarManager.add(saveAction);
	}

	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(saveAction);
	}
}
