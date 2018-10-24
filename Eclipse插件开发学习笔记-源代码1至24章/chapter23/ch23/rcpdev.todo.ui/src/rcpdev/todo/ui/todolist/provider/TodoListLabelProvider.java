package rcpdev.todo.ui.todolist.provider;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.ui.todolist.utils.TimeConverter;

public class TodoListLabelProvider implements ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Integer) {
			switch (columnIndex) {
			case 0:
				return TimeConverter.convert((Integer)element);
			case 1:
			case 2:
			default:
				return "";
			}
		}
		if (element instanceof TodoItem) {
			TodoItem item = (TodoItem) element;
			switch (columnIndex) {
			case 0:
				return item.getSubject();
			case 1:
			case 2:
			default:
				return "";
			}
		}
		return null;
	}

	public void addListener(ILabelProviderListener listener) {

	}

	public void dispose() {

	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
	}

}
