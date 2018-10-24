package rcpdev.todo.persistence;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import oracle.toplink.exceptions.DatabaseException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.sessions.DatabaseSession;
import oracle.toplink.sessions.UnitOfWork;
import rcpdev.common.core.mediator.Mediator;
import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.model.series.SeriesInfo;
import rcpdev.todo.core.model.series.TodoSeries;
import rcpdev.todo.core.storage.ITodoFacade;
import rcpdev.todo.core.storage.TodoFacadeException;
import rcpdev.todo.persistence.core.TopLinkTodoFacadeException;
import rcpdev.todo.persistence.core.session.TopLinkSessionProvider;

public class TopLinkTodoFacade implements ITodoFacade {

	private PropertyChangeSupport delegate;

	public PropertyChangeSupport getDelegate() {
		if (delegate == null)
			delegate = new PropertyChangeSupport(this);
		return delegate;
	}

	protected DatabaseSession getSession() throws TodoFacadeException {
		return TopLinkSessionProvider.getInstance().getSession();
	}

	public TopLinkTodoFacade() {
		super();
		addPropertyChangeListener(Mediator.getInstance());
	}

	public void init() throws TodoFacadeException {
		getSession().readAllObjects(TodoItem.class);
	}

	public void dispose() {
		removePropertyChangeListener(Mediator.getInstance());
	}

	public TodoItem addTodoItem(TodoItem item) throws TodoFacadeException {
		try {
			UnitOfWork uow = getSession().acquireUnitOfWork();
			uow.registerNewObject(item);
			if (item.getSeries() != null)
				item.setSeries((TodoSeries) uow
						.registerObject(item.getSeries()));
			if (item instanceof TodoSeries) {
				TodoSeries series = (TodoSeries) item;
				series.setInfo((SeriesInfo) uow
						.registerObject(series.getInfo()));
				uow.registerObject(((TodoSeries) item).getInfo());
			}
			uow.commit();
			getDelegate().firePropertyChange(ITodoFacade.FACADE_UPDATED, null,
					item);
			return item;
		} catch (DatabaseException e) {
			throw new TopLinkTodoFacadeException(e.getDatabaseErrorCode(), e);
		} catch (Exception e) {
			throw new TopLinkTodoFacadeException(-1, e);
		}
	}

	public List<TodoItem> findTodoItemByDate(Date date)
			throws TodoFacadeException {
		try {
			UnitOfWork uow = getSession().acquireUnitOfWork();
			List<TodoItem> items = (List<TodoItem>) uow
					.readAllObjects(TodoItem.class);
			Vector<TodoItem> rets = new Vector<TodoItem>();
			for (int i = 0; i < items.size(); i++) {
				TodoItem item = items.get(i).fitDate(date);
				if (item != null && !rets.contains(item))
					rets.add((TodoItem) item);
			}
			uow.release();
			return rets;
		} catch (DatabaseException e) {
			throw new TopLinkTodoFacadeException(e.getDatabaseErrorCode(), e);
		} catch (Exception e) {
			throw new TopLinkTodoFacadeException(-1, e);
		}
	}

	public boolean removeTodoItem(TodoItem item) throws TodoFacadeException {
		try {
			UnitOfWork uow = getSession().acquireUnitOfWork();
			item = (TodoItem) uow.registerExistingObject(item);
			uow.deleteObject(item);
			uow.commit();
			getDelegate().firePropertyChange(ITodoFacade.FACADE_UPDATED, null,
					item);
			return true;
		} catch (DatabaseException e) {
			throw new TopLinkTodoFacadeException(e.getDatabaseErrorCode(), e);
		} catch (Exception e) {
			throw new TopLinkTodoFacadeException(-1, e);
		}
	}

	public TodoItem updateTodoItem(TodoItem item) throws TodoFacadeException {
		try {
			UnitOfWork uow = getSession().acquireUnitOfWork();
			TodoItem clone = (TodoItem) uow.readObject(item);
			clone.copy(item);
			uow.commit();
			getDelegate().firePropertyChange(ITodoFacade.FACADE_UPDATED, null,
					item);
			return item;
		} catch (DatabaseException e) {
			throw new TopLinkTodoFacadeException(e.getDatabaseErrorCode(), e);
		} catch (Exception e) {
			throw new TopLinkTodoFacadeException(-1, e);
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getDelegate().addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		getDelegate().removePropertyChangeListener(listener);
	}

	public TodoSeries findBirthdaySeries(String subject, String content)
			throws TodoFacadeException {
		try {
			UnitOfWork uow = getSession().acquireUnitOfWork();

			ExpressionBuilder builder = new ExpressionBuilder();
			Expression exp = builder.get("subject").equal(subject).and(
					builder.get("content").equal(content));
			TodoSeries series = (TodoSeries) uow.readObject(TodoSeries.class,
					exp);
			uow.release();
			return series;
		} catch (DatabaseException e) {
			throw new TopLinkTodoFacadeException(e.getDatabaseErrorCode(), e);
		} catch (Exception e) {
			throw new TopLinkTodoFacadeException(-1, e);
		}
	}

}
