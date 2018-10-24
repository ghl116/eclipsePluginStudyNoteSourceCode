package rcpdev.contact.persistence.core.session;

import java.sql.SQLException;

import oracle.toplink.exceptions.DatabaseException;
import oracle.toplink.logging.DefaultSessionLog;
import oracle.toplink.logging.SessionLog;
import oracle.toplink.sessions.DatabaseSession;
import rcpdev.contact.core.persistence.ContactFacadeException;
import rcpdev.contact.persistence.core.TopLinkContactFacadeException;
import rcpdev.contact.persistence.core.project.ContactProject;

public class TopLinkSessionProvider {

	private static TopLinkSessionProvider instance;

	public static TopLinkSessionProvider getInstance()
			throws ContactFacadeException {
		if (instance == null)
			instance = new TopLinkSessionProvider();
		return instance;
	}

	private DatabaseSession session;

	private TopLinkSessionProvider() throws ContactFacadeException {
		try {
			ContactProject project = new ContactProject();
			session = project.createDatabaseSession();
			SessionLog log = new DefaultSessionLog();
			log.setLevel(SessionLog.FINE);
			session.setSessionLog(log);
			session.login();
		} catch (DatabaseException e) {
			SQLException cause = (SQLException) e.getCause();
			throw new TopLinkContactFacadeException(cause.getErrorCode(), e);
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
