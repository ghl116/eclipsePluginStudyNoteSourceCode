package rcpdev.todo.ui.todolist.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import rcpdev.todo.core.model.TodoItem;

public abstract class ItemOperationAction extends Action {

	private TreeViewer treeViewer;

	public ItemOperationAction(String id, String text, TreeViewer viewer) {
		super(text);
		setId(id);
		treeViewer = viewer;
	}

	public void run() {
		Object selected = ((TreeSelection) treeViewer.getSelection())
				.getFirstElement();
		if (selected instanceof TodoItem) {
			runWithItem(((TodoItem) selected));
		} else {
			try {
				SelectItemMessageBox msgBox = new SelectItemMessageBox(
						treeViewer.getTree().getShell());
				msgBox.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public abstract void runWithItem(TodoItem item);
}
