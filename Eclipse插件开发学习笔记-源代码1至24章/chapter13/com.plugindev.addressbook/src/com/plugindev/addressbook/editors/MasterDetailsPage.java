package com.plugindev.addressbook.editors;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.editor.*;
import org.eclipse.ui.forms.widgets.*;

import com.plugindev.addressbook.util.ImageCache;
import com.plugindev.addressbook.util.ImageKeys;
import com.plugindev.addressbook.util.Messages;

public class MasterDetailsPage extends FormPage {
	private ScrolledPropertiesBlock block;
	public MasterDetailsPage(FormEditor editor) {
		super(editor, "masterDetail", Messages.PAGE_NAME_MASTERDETAIL); //$NON-NLS-1$ //$NON-NLS-2$
		block = new ScrolledPropertiesBlock(this);
	}
	protected void createFormContent(final IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();

		form.setText(Messages.PAGE_TITLE_MASTERDETAIL); //$NON-NLS-1$
		form.setBackgroundImage(ImageCache.getInstance().getImage(ImageKeys.getImageDescriptor(ImageKeys.IMG_FORM_BG)));
		block.createContent(managedForm);
	}
	/*
	 * 此方法在13.9节中加入
	 */
	public IAction getTableAction(String workbenchActionId){
		return block.getTableAction(workbenchActionId);
	}
}