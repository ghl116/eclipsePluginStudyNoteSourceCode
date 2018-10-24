package rcpdev.todo.ui.todolist.views.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;

import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.model.series.TodoSeries;
import rcpdev.todo.core.storage.TodoFacadeException;
import rcpdev.todo.core.storage.TodoFacadeFactory;
import rcpdev.todo.ui.todolist.actions.CreateTodoEditorAction;

public class EditTodoItemJob extends ItemOperationJob {

	public static final String NAME = "Editing Todo Items...";

	public EditTodoItemJob() {
		super(NAME);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			final int editType;
			TodoItem editTarget = null;
			RunDialogAction dialogRun = new RunDialogAction();
			if (target.getSeries() != null || target instanceof TodoSeries) {
				Display.getDefault().syncExec(dialogRun);
				editType = dialogRun.getEditType();
				switch (editType) {
				case SelectModifyTypeDialog.EDIT_NONE:
					return Status.CANCEL_STATUS;
				case SelectModifyTypeDialog.EDIT_ITEM:
					if (target instanceof TodoSeries) {
						// Create new TodoItem and edit it
						TodoItem item = new TodoItem();
						item.copy(target);
						item.setDate(date);
						((TodoSeries) target).addItem(item);
						item = TodoFacadeFactory.getInstance().getFacade()
								.addTodoItem(item);
						editTarget = item;
					} else
						editTarget = target;
					break;
				case SelectModifyTypeDialog.EDIT_SERIES:
					editTarget = target instanceof TodoSeries ? target : target
							.getSeries();
					break;
				}
			} else
				editTarget = getTarget();
			return runWithItem(editTarget);
		} catch (TodoFacadeException e) {
			return null;
		}
	}

	protected IStatus runWithItem(TodoItem item) {
		CreateTodoEditorAction createEditorAction = new CreateTodoEditorAction();
		createEditorAction.setItem(item);
		Display.getDefault().asyncExec(createEditorAction);
		return Status.OK_STATUS;
	}
}
