package rcpdev.todo.ui.todolist.views;

import java.util.Date;
import java.util.TreeMap;
import java.util.Vector;

import rcpdev.common.core.utils.DateUtils;
import rcpdev.common.ui.databinding.AbstractBean;
import rcpdev.todo.core.model.TodoItem;

public class TodoListBean extends AbstractBean {

	public static final String TODO_DATE = "date";

	private Date date;

	private TreeMap<Integer, Vector<TodoItem>> itemsMap;

	public TodoListBean() {
		setDate(new Date());
		itemsMap = new TreeMap<Integer, Vector<TodoItem>>();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date newDate) {
		newDate = DateUtils.truncToDay(newDate);
		Date oldDate = getDate();
		date = newDate;
		firePropertyChange(TODO_DATE, oldDate, date);
	}

	public static final String ADD_ITEM = "addItem";

	public static final String REMOVE_ALL_ITEMS = "removeAllItems";

	public void addItem(TodoItem item) {
		Vector<TodoItem> items = itemsMap.get(item.getStartTime());
		if (items == null) {
			items = new Vector<TodoItem>();
			itemsMap.put(item.getStartTime(), items);
		}
		items.add(item);
		firePropertyChange(ADD_ITEM, null, item);
	}

	public TreeMap<Integer, Vector<TodoItem>> getItemsMap() {
		return itemsMap;
	}

	public void removeAllItems() {
		itemsMap.clear();
		firePropertyChange(REMOVE_ALL_ITEMS, null, null);
	}

}
