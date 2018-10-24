package rcpdev.contact.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

import rcpdev.contact.ui.actions.contact.CreateContactEditorAction;

public class EditContactJob extends ContactJob {

	public static final String NAME = "Edit Contact";

	public EditContactJob() {
		super(NAME);
		setPriority(Job.INTERACTIVE);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		assert getEditorContent() != null;
		Display.getDefault().asyncExec(
				new CreateContactEditorAction(getEditorContent()));
		return Status.OK_STATUS;
	}

}
