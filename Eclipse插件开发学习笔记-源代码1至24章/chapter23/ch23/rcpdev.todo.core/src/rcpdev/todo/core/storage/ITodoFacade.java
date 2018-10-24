package rcpdev.todo.core.storage;

import java.util.Date;
import java.util.List;

import rcpdev.common.core.mediator.IMediatorSender;
import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.model.series.TodoSeries;

/**
 * This interface declares the method that persistence solution must provide.
 * The implementator should also implements interface
 * <code>IMediatorSender</code> and notify <code>Mediator</code> when change
 * occurs.
 * 
 * @author Harper Jiang
 * @since Todo Plug-in 1.0
 * @version 1.0
 * 
 */

public interface ITodoFacade extends IMediatorSender {

	public static final String FACADE_UPDATED = "facade.updated";

	public void init() throws TodoFacadeException;
	
	public void dispose() throws TodoFacadeException;

	public TodoItem addTodoItem(TodoItem item) throws TodoFacadeException;

	public TodoItem updateTodoItem(TodoItem item) throws TodoFacadeException;

	public boolean removeTodoItem(TodoItem item) throws TodoFacadeException;

	public List<TodoItem> findTodoItemByDate(Date date)
			throws TodoFacadeException;

	// This method is added to support backend query from Contact plug-in
	public TodoSeries findBirthdaySeries(String subject, String content)
			throws TodoFacadeException;
}
