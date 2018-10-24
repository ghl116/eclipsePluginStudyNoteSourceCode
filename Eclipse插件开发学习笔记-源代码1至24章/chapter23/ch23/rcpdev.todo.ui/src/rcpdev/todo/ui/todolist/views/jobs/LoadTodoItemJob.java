package rcpdev.todo.ui.todolist.views.jobs;

import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import rcpdev.common.core.utils.DateUtils;
import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.storage.TodoFacadeException;
import rcpdev.todo.core.storage.TodoFacadeFactory;
import rcpdev.todo.ui.Activator;
import rcpdev.todo.ui.todolist.views.TodoListView;

public class LoadTodoItemJob extends Job {

	public static final String NAME = "Loading To-Do List";

	private Date date;

	public LoadTodoItemJob() {
		super(NAME);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = DateUtils.truncToDay(date);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		// Retrieve Data from Facade
		List<TodoItem> items;
		try {
			items = TodoFacadeFactory.getInstance().getFacade()
					.findTodoItemByDate(date);
		} catch (TodoFacadeException e) {
			items = null;
			e.printStackTrace();
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, e
					.getDescription(), e);
		}
		// Update UI
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		final List<TodoItem> finalItems = items;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				TodoListView view = (TodoListView) PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage().findView(
								TodoListView.ID);
				if (view != null) {
					view.getBean().removeAllItems();
					if (finalItems != null)
						for (int i = 0; i < finalItems.size(); i++) {
							view.getBean().addItem(finalItems.get(i));
						}
				}
			}
		});

		return Status.OK_STATUS;
	}

}
