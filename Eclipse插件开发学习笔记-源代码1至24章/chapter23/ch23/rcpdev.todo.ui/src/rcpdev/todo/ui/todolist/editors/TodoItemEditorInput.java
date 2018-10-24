package rcpdev.todo.ui.todolist.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import rcpdev.todo.core.model.TodoItem;

public class TodoItemEditorInput implements IEditorInput {

	private TodoItem item;

	public TodoItemEditorInput(TodoItem item) {
		super();
		this.item = item;
	}

	public TodoItemEditorInput() {
		super();
	}

	public TodoItem getItem() {
		return item;
	}

	public void setItem(TodoItem item) {
		this.item = item;
	}

	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return "Edit TodoItem";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return "Edit TodoItem";
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}
