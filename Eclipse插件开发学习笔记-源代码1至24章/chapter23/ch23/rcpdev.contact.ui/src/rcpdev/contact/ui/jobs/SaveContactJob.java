package rcpdev.contact.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.core.persistence.ContactFacadeException;
import rcpdev.contact.core.persistence.IContactFacade;
import rcpdev.contact.core.persistence.ContactFacadeFactory;
import rcpdev.contact.ui.Activator;

public class SaveContactJob extends Job {

	public static final String NAME = "Saving Contact...";

	private Contact contact;

	private boolean isAdd;

	public SaveContactJob() {
		super(NAME);
		setRule(StorageRule.getInstance());
		setPriority(Job.SHORT);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		assert getContact() != null;
		try {
			IContactFacade facade = ContactFacadeFactory.getInstance()
					.getFacade();
			List<Contact> existed = facade.searchContacts(getContact()
					.getFullName(), null, null);
			if (existed != null && existed.size() > 0
					&& !existed.contains(getContact()))
				return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0,
						"A contact with this name already exists", null);
			if (isAdd())
				facade.createContact(contact);
			else
				facade.updateContact(getContact());
		} catch (ContactFacadeException e) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, e
					.getDescription(), e);
		}
		return Status.OK_STATUS;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

}
