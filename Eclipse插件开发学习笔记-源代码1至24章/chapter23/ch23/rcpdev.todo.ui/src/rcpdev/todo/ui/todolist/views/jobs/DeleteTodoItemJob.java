package rcpdev.todo.ui.todolist.views.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;

import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.storage.TodoFacadeException;
import rcpdev.todo.core.storage.TodoFacadeFactory;
import rcpdev.todo.ui.Activator;

public class DeleteTodoItemJob extends ItemOperationJob {

	public static final String NAME = "Deleting TodoItem...";

	public DeleteTodoItemJob() {
		super(NAME);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		final int editType;
		TodoItem editTarget = null;
		RunDialogAction dialogRun = new RunDialogAction();
		if (target.getSeries() != null) {
			Display.getDefault().syncExec(dialogRun);
			editType = dialogRun.getEditType();
			switch (editType) {
			case SelectModifyTypeDialog.EDIT_NONE:
				return Status.CANCEL_STATUS;
			case SelectModifyTypeDialog.EDIT_ITEM:
				editTarget = target;
				break;
			case SelectModifyTypeDialog.EDIT_SERIES:
				editTarget = target.getSeries();
				break;
			}
		} else
			editTarget = getTarget();
		return runWithItem(editTarget);
	}

	protected IStatus runWithItem(TodoItem item) {
		try {
			TodoFacadeFactory.getInstance().getFacade().removeTodoItem(item);
			return Status.OK_STATUS;
		} catch (TodoFacadeException e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID, 0, e
					.getDescription(), e);
		}
	}

}
