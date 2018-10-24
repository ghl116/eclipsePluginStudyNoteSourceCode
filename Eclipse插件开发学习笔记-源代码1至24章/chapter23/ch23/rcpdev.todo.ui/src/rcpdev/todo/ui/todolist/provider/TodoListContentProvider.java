package rcpdev.todo.ui.todolist.provider;

import java.beans.PropertyChangeEvent;
import java.util.Vector;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;

import rcpdev.common.ui.viewers.AbstractContentProvider;
import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.ui.todolist.views.TodoListBean;

public class TodoListContentProvider extends AbstractContentProvider implements
		ITreeContentProvider {

	public Object[] getElements(Object inputElement) {
		TodoListBean bean = (TodoListBean) getInput();
		return bean.getItemsMap().keySet().toArray();
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Integer) {
			TodoListBean bean = (TodoListBean) getInput();
			return bean.getItemsMap().get(parentElement).toArray();
		}
		return null;
	}

	public Object getParent(Object element) {
		if (element instanceof TodoItem)
			return ((TodoItem) element).getStartTime();
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof Integer) {
			TodoListBean bean = (TodoListBean) getInput();
			Vector<TodoItem> items = bean.getItemsMap().get(element);
			return items != null && items.size() != 0;
		}
		return false;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		super.propertyChange(evt);
		((TreeViewer) getViewer()).expandAll();
	}
}
