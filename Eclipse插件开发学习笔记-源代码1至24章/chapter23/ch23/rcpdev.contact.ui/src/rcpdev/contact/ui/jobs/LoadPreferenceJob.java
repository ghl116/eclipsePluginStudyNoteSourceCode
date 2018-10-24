package rcpdev.contact.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

public class LoadPreferenceJob extends Job {

	public LoadPreferenceJob() {
		super(NAME);
		setRule(StorageRule.getInstance());
		setPriority(Job.SHORT);
	}

	public static final String NAME = "Loading Preference";

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
