package rcpdev.todo.persistence.core;

import rcpdev.todo.core.storage.TodoFacadeException;
import rcpdev.todo.persistence.lang.Messages;

public class TopLinkTodoFacadeException extends TodoFacadeException {

	private int errorCode;

	public TopLinkTodoFacadeException(int errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3250837457255673851L;

	private static final int ERROR_AUTHENTICATION = 1045;

	@Override
	public String getDescription() {
		switch (errorCode) {
		case ERROR_AUTHENTICATION:
			return Messages.getString("TopLinkTodoFacadeException.error_authentication"); //$NON-NLS-1$
		}
		return null;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
