package rcpdev.todo.persistence.core.session;

import java.sql.SQLException;

import oracle.toplink.exceptions.DatabaseException;
import oracle.toplink.logging.DefaultSessionLog;
import oracle.toplink.logging.SessionLog;
import oracle.toplink.sessions.DatabaseSession;
import rcpdev.todo.core.storage.TodoFacadeException;
import rcpdev.todo.persistence.core.TopLinkTodoFacadeException;
import rcpdev.todo.persistence.core.project.TodoProject;

public class TopLinkSessionProvider {

	private static TopLinkSessionProvider instance;

	public static TopLinkSessionProvider getInstance()
			throws TodoFacadeException {
		if (instance == null)
			instance = new TopLinkSessionProvider();
		return instance;
	}

	private DatabaseSession session;

	private TopLinkSessionProvider() throws TodoFacadeException {
		try {
			TodoProject project = new TodoProject();
			session = project.createDatabaseSession();
			SessionLog log = new DefaultSessionLog();
			log.setLevel(SessionLog.FINE);
			session.setSessionLog(log);
			session.login();
		} catch (DatabaseException e) {
			SQLException cause = (SQLException) e.getCause();
			throw new TopLinkTodoFacadeException(cause.getErrorCode(), e);
		}
	}

	public DatabaseSession getSession() {
		return session;
	}

	public void init() {
	}

	public void dispose() {
		session.logout();
	}
}
