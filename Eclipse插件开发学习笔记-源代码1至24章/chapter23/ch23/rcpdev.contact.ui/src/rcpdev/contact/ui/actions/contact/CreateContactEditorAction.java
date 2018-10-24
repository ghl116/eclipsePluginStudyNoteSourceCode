package rcpdev.contact.ui.actions.contact;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.ui.editors.contact.ContactEditor;
import rcpdev.contact.ui.editors.contact.ContactEditorInput;

public class CreateContactEditorAction extends Action implements Runnable {

	private Contact contact;

	public CreateContactEditorAction(Contact contact) {
		super();
		this.contact = contact;
	}

	public void run() {
		IEditorInput input = new ContactEditorInput(contact);
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			IEditorReference[] refs = page.getEditorReferences();
			for (int i = 0; i < refs.length; i++) {
				if (ContactEditor.ID.equals(refs[i].getId())) {
					ContactEditorInput existed = (ContactEditorInput) refs[i]
							.getEditorInput();
					if (contact.equals(existed.getPerson())) {
						page.activate(refs[i].getEditor(false));
						return;
					}
				}
			}
			page.openEditor(input, ContactEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
