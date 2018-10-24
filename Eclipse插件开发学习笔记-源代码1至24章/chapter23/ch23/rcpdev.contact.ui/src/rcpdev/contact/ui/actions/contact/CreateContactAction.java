package rcpdev.contact.ui.actions.contact;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import rcpdev.contact.ui.Activator;
import rcpdev.contact.ui.actions.lang.Messages;
import rcpdev.contact.ui.editors.contact.ContactEditor;
import rcpdev.contact.ui.editors.contact.ContactEditorInput;

import com.swtdesigner.ResourceManager;

public class CreateContactAction extends ContactAction {

	public static final String ID = "createContactAction"; //$NON-NLS-1$

	public static final String NAME = Messages
			.getString("CreateContactAction.name"); //$NON-NLS-1$

	public CreateContactAction() {
		super(NAME);
		setId(ID);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator
				.getDefault(), "icons/contact_new_item.gif")); //$NON-NLS-1$
	}

	@Override
	public void run() {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			IEditorInput input = new ContactEditorInput();
			page.openEditor(input, ContactEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
