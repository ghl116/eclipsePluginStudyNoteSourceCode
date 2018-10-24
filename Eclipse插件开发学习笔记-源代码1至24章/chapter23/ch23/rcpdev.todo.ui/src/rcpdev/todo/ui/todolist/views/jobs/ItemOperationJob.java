package rcpdev.todo.ui.todolist.views.jobs;

import java.util.Date;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import rcpdev.todo.core.model.TodoItem;

public abstract class ItemOperationJob extends Job {

	protected TodoItem target;

	protected Date date;

	public ItemOperationJob(String name) {
		super(name);
	}

	public TodoItem getTarget() {
		return target;
	}

	public void setTarget(TodoItem target) {
		this.target = target;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	protected static final class RunDialogAction implements Runnable {

		private int editType;

		public void run() {
			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getShell();
			SelectModifyTypeDialog dialog = new SelectModifyTypeDialog(shell);
			dialog.create();
			dialog.getShell().pack();
			if (Dialog.OK == dialog.open()) {
				editType = dialog.getEditType();
			}
		}

		public int getEditType() {
			return editType;
		}
	}

}
