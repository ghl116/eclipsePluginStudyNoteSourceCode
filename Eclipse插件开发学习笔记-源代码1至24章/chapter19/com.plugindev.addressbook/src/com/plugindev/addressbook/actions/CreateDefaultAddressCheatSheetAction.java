package com.plugindev.addressbook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

import com.plugindev.addressbook.wizards.NewAddressItemWizard;
import com.plugindev.addressbook.wizards.NewAddressItemWizardPage;

public class CreateDefaultAddressCheatSheetAction extends Action 
implements ICheatSheetAction{

	public void run(String[] params, ICheatSheetManager manager) {
		// TODO 自动生成方法存根
		String addressName = params[0];
		
		NewAddressItemWizard wizard = new NewAddressItemWizard();
		wizard.init(PlatformUI.getWorkbench(), null);
		WizardDialog dialog = 
			new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
		dialog.create();
		if (addressName != null) {
			NewAddressItemWizardPage page = (NewAddressItemWizardPage) wizard.getStartingPage();
			page.setName(addressName);
			page.setCategory("未分类");
			page.setName(addressName);
			page.updatePageComplete();
		}

		dialog.open();
		this.notifyResult(dialog.getReturnCode()==Dialog.OK);
	}

}
