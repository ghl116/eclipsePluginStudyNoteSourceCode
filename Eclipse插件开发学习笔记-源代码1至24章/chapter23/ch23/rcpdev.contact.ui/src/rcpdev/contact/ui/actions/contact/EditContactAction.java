package rcpdev.contact.ui.actions.contact;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.ui.Activator;
import rcpdev.contact.ui.actions.lang.Messages;
import rcpdev.contact.ui.editors.contact.ContactEditor;
import rcpdev.contact.ui.editors.contact.ContactEditorInput;

import com.swtdesigner.ResourceManager;

public class EditContactAction extends ContactAction {
	public static final String ID = "editContactAction"; //$NON-NLS-1$

	public static final String NAME = Messages
			.getString("EditContactAction.name"); //$NON-NLS-1$

	public EditContactAction() {
		super(NAME);
		setId(ID);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator
				.getDefault(), "icons/contact_edit_item.gif")); //$NON-NLS-1$
	}

	public void run() {
		try {
			Contact contact = getSelectedContact();
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
			page.openEditor(new ContactEditorInput(contact), ContactEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
