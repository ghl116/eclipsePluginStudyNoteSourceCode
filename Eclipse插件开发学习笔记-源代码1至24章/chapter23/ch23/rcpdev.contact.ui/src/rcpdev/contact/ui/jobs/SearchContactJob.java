package rcpdev.contact.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.core.persistence.ContactFacadeException;
import rcpdev.contact.core.persistence.ContactFacadeFactory;
import rcpdev.contact.ui.Activator;
import rcpdev.contact.ui.views.search.SearchBean;
import rcpdev.contact.ui.views.search.SearchContactView;

public class SearchContactJob extends Job {

	public static final String NAME = "Searching Contact...";

	public SearchContactJob() {
		super(NAME);
		setRule(StorageRule.getInstance());
		setPriority(Job.SHORT);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		List<Contact> searchResult = null;
		monitor.beginTask("Search Contact", 2);
		try {
			if (getSearch() == null)
				throw new NullPointerException("Search Bean not set");
			searchResult = ContactFacadeFactory
					.getInstance()
					.getFacade()
					.searchContacts(getSearch().getName(),
							getSearch().getCompany(), getSearch().getCategory());
		} catch (ContactFacadeException e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID, 0, e
					.getDescription(), e);
		} catch (Exception e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID, 0, e
					.getMessage(), e);
		}
		monitor.worked(1);

		final List<Contact> result = searchResult;
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				SearchContactView view = (SearchContactView) page
						.findView(SearchContactView.ID);
				if(view != null) {
					view.getBean().setResult(result);
				}
			}
		});

		monitor.done();
		return Status.OK_STATUS;
	}

	private SearchBean search;

	public SearchBean getSearch() {
		return search;
	}

	public void setSearch(SearchBean search) {
		this.search = search;
	}

}
