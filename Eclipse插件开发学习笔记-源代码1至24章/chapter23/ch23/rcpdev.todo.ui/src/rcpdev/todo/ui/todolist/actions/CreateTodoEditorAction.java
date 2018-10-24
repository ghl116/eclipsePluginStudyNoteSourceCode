package rcpdev.todo.ui.todolist.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.ui.todolist.editors.TodoItemEditor;
import rcpdev.todo.ui.todolist.editors.TodoItemEditorInput;

public class CreateTodoEditorAction extends Action implements Runnable {

	private TodoItem item;

	public CreateTodoEditorAction() {
		super();
	}

	public TodoItem getItem() {
		return item;
	}

	public void setItem(TodoItem item) {
		this.item = item;
	}

	@Override
	public void run() {
		if (item == null)
			throw new NullPointerException();
		TodoItemEditorInput input = new TodoItemEditorInput(item);
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().openEditor(input, TodoItemEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
