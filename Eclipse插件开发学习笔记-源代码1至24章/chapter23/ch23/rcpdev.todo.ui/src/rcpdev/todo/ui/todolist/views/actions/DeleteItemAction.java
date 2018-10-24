package rcpdev.todo.ui.todolist.views.actions;

import org.eclipse.jface.viewers.TreeViewer;

import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.ui.Activator;
import rcpdev.todo.ui.todolist.views.jobs.DeleteTodoItemJob;

import com.swtdesigner.ResourceManager;

public class DeleteItemAction extends ItemOperationAction {

	public DeleteItemAction(TreeViewer viewer) {
		super("deleteItemAction", "Delete Item", viewer);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator
				.getDefault(), "icons/todo_delete_item.gif"));
		setId("deleteItemAction");
	}

	@Override
	public void runWithItem(TodoItem item) {
		DeleteTodoItemJob deleteJob = new DeleteTodoItemJob();
		deleteJob.setTarget(item);
		deleteJob.schedule();
	}
}
