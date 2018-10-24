package rcpdev.contact.ui.actions.contact;

import org.eclipse.jface.viewers.TableViewer;

import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.ui.Activator;
import rcpdev.contact.ui.actions.lang.Messages;
import rcpdev.contact.ui.jobs.DeleteContactJob;

import com.swtdesigner.ResourceManager;

public class DeleteContactAction extends ContactAction {
	public static final String ID = "createContactAction"; //$NON-NLS-1$

	public static final String NAME = Messages
			.getString("DeleteContactAction.name"); //$NON-NLS-1$

	public TableViewer viewer;

	public DeleteContactAction() {
		super(NAME);
		setId(ID);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator
				.getDefault(), "icons/contact_delete_item.gif")); //$NON-NLS-1$
	}

	public void run() {
		DeleteContactJob job = new DeleteContactJob();
		Contact contact = getSelectedContact();
		if (contact == null)
			return;
		job.setEditorContent(contact);
		job.schedule();
	}

}
