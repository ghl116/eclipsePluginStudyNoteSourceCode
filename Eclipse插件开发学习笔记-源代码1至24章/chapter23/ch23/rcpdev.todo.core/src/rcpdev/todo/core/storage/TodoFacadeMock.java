package rcpdev.todo.core.storage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import rcpdev.common.core.mediator.Mediator;
import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.model.series.TodoSeries;

public class TodoFacadeMock implements ITodoFacade {

	private Vector<TodoItem> storage;

	private int count;

	public TodoFacadeMock() {
		super();
		storage = new Vector<TodoItem>();
		addPropertyChangeListener(Mediator.getInstance());
	}

	public TodoItem addTodoItem(TodoItem item) {
		item.setId(String.valueOf(count++));
		storage.add(item);
		getDelegate().firePropertyChange(FACADE_UPDATED, null, item);
		return item;
	}

	public TodoItem updateTodoItem(TodoItem item) {
		if (storage.contains(item)) {
			storage.set(storage.indexOf(item), item);
		}
		getDelegate().firePropertyChange(FACADE_UPDATED, null, item);
		return item;
	}

	public List<TodoItem> findTodoItemByDate(Date date) {
		Vector<TodoItem> items = new Vector<TodoItem>();
		for (int i = 0; i < storage.size(); i++) {
			TodoItem item = storage.get(i).fitDate(date);
			if (item != null && !items.contains(item))
				items.add(item);
		}
		return items;
	}

	public boolean removeTodoItem(TodoItem item) {
		try {
			storage.remove(item);
			getDelegate().firePropertyChange(FACADE_UPDATED, item, null);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private PropertyChangeSupport delegate;

	protected PropertyChangeSupport getDelegate() {
		if (delegate == null)
			delegate = new PropertyChangeSupport(this);
		return delegate;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getDelegate().addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		getDelegate().removePropertyChangeListener(listener);
	}

	public TodoSeries findBirthdaySeries(String subject, String content)
			throws TodoFacadeException {
		return null;
	}

	public void init() {
	}

	public void dispose() throws TodoFacadeException {
	}
}
