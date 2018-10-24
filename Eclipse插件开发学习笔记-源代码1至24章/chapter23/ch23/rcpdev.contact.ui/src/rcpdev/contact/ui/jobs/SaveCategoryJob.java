package rcpdev.contact.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

public class SaveCategoryJob extends Job {

	public static final String NAME = "Saving Category...";

	public SaveCategoryJob() {
		super(NAME);
		setPriority(Job.SHORT);
		setRule(StorageRule.getInstance());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	private List newCategories;

	public List getNewCategories() {
		return newCategories;
	}

	public void setNewCategories(List newCategory) {
		this.newCategories = newCategory;
	}

}
