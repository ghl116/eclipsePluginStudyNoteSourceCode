package rcpdev.contact.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import rcpdev.contact.core.persistence.ContactFacadeException;
import rcpdev.contact.core.persistence.ContactFacadeFactory;
import rcpdev.contact.ui.Activator;
import rcpdev.contact.ui.views.contact.ContactView;

public class LoadContactJob extends Job {

	private static final String NAME = "Loading Contacts...";

	public LoadContactJob() {
		super(NAME);
		setPriority(Job.SHORT);
		setRule(StorageRule.getInstance());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		List contacts = null;

		try {
			contacts = ContactFacadeFactory.getInstance().getFacade()
					.searchContacts();
		} catch (ContactFacadeException e) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, e
					.getDescription(), e);
		}
		// Update UI
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		final List finalContacts = contacts;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ContactView view = (ContactView) PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage().findView(
								ContactView.ID);
				if (view == null)
					return;
				view.getModel().setAllContacts(finalContacts);
			}
		});

		return Status.OK_STATUS;
	}

}
