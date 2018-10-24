package rcpdev.contact.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import rcpdev.contact.core.persistence.ContactFacadeException;
import rcpdev.contact.core.persistence.ContactFacadeFactory;
import rcpdev.contact.ui.Activator;

public class DeleteContactJob extends ContactJob {

	public static final String NAME = "Delete Contact";

	public DeleteContactJob() {
		super(NAME);
		setPriority(Job.INTERACTIVE);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		if (getEditorContent() == null)
			return new Status(Status.ERROR, Activator.PLUGIN_ID, 0,
					"Editor Content is null", new NullPointerException());
		try {
			ContactFacadeFactory.getInstance().getFacade().removeContact(
					getEditorContent());
		} catch (ContactFacadeException e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID, 0, e
					.getDescription(), e);
		} catch (Exception e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID, 0, e
					.getMessage(), e);
		}
		return Status.OK_STATUS;
	}
}
