package rcpdev.todo.ui.todolist.views.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

public class MarkCurrentItemJob extends Job {

	public static final String NAME = "MarkCurrentItemJob";

	public MarkCurrentItemJob() {
		super(NAME);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
