package com.plugindev.addressbook.editors;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.LabelRetargetAction;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.part.EditorActionBarContributor;

public class AddressFormEditorContributor extends
		EditorActionBarContributor {
	private LabelRetargetAction retargetRemoveAction;
	
	public AddressFormEditorContributor(){
		retargetRemoveAction = new LabelRetargetAction(
				ActionFactory.DELETE.getId(), "É¾³ý");
	}
	public void init(IActionBars bars, IWorkbenchPage page){
		super.init(bars, page);
		page.addPartListener(retargetRemoveAction);
	}
	public void contributeToMenu(IMenuManager manager){
		IMenuManager mgr = new MenuManager("µØÖ·±à¼­Æ÷");
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, mgr);
		mgr.add(retargetRemoveAction);
	}
	
	private static final String[] WORKBENCH_ACTION_IDS ={
		ActionFactory.DELETE.getId()
	};
	public void setActiveEditor(IEditorPart part){
		AddressFormEditor editor = (AddressFormEditor)part;
		setActivePage(editor, editor.getActivePage());
	}
	public void setActivePage(AddressFormEditor editor, int pageIndex){
		IActionBars actionBars = getActionBars();
		if(actionBars != null){
			switch(pageIndex){
			case 0:
				MasterDetailsPage page = (MasterDetailsPage)editor.findPage("masterDetail");
				if(page != null)
					hookGlobalTableActions(page, actionBars);
				break;
			}
		}
		actionBars.updateActionBars();
	}
/*	public void updateActionBars(){
		IActionBars actionBars = getActionBars();
		if(actionBars != null)
		actionBars.updateActionBars();
	}*/

	public void hookGlobalTableActions(MasterDetailsPage page, 
			IActionBars actionBars){
		for(int i = 0; i < WORKBENCH_ACTION_IDS.length; i++){
			actionBars.setGlobalActionHandler(WORKBENCH_ACTION_IDS[i],
					page.getTableAction(WORKBENCH_ACTION_IDS[i]));
		}
	}
	   /**
	    * Contributes to the given tool bar.
	    */
	   public void contributeToToolBar(IToolBarManager manager) {
	      manager.add(new Separator());
	      manager.add(retargetRemoveAction);
	   }
}
