package rcpdev.todo.ui.todolist.views.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.storage.TodoFacadeFactory;

public class CreateTodoItemJob extends Job {

	public static final String NAME = "Creating New To-Do Item...";

	private TodoItem item;

	public CreateTodoItemJob() {
		super(NAME);
		setUser(true);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		// Object creation
		try {
			TodoFacadeFactory.getInstance().getFacade().addTodoItem(item);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Status.OK_STATUS;
	}

	public TodoItem getItem() {
		return item;
	}

	public void setItem(TodoItem item) {
		this.item = item;
	}

}
