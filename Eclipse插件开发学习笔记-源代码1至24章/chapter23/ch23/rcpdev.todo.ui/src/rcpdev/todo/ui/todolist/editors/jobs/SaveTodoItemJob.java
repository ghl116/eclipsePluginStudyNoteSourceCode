package rcpdev.todo.ui.todolist.editors.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.storage.TodoFacadeException;
import rcpdev.todo.core.storage.TodoFacadeFactory;
import rcpdev.todo.ui.Activator;

public class SaveTodoItemJob extends Job {

	private static final String NAME = "Saving TodoItem...";

	public SaveTodoItemJob() {
		super(NAME);
	}

	private TodoItem itemToBeSaved;

	public TodoItem getItemToBeSaved() {
		return itemToBeSaved;
	}

	public void setItemToBeSaved(TodoItem itemToBeSaved) {
		this.itemToBeSaved = itemToBeSaved;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			TodoFacadeFactory.getInstance().getFacade().updateTodoItem(
					itemToBeSaved);
			return Status.OK_STATUS;
		} catch (TodoFacadeException e) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, "", e);
		}

	}

}
