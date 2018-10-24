package rcpdev.contact.persistence.core;

import oracle.toplink.exceptions.DatabaseException;
import rcpdev.contact.core.persistence.ContactFacadeException;

public class TopLinkContactFacadeException extends ContactFacadeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4610045815010196080L;

	private int errorcode = -1;

	public TopLinkContactFacadeException(Exception e) {
		super(e);
		if (e instanceof DatabaseException)
			errorcode = ((DatabaseException) e).getDatabaseErrorCode();
	}

	public TopLinkContactFacadeException(int errorcode, Exception e) {
		super(e);
		this.errorcode = errorcode;
	}

	@Override
	public String getDescription() {
		switch (errorcode) {
		case 1:
			return null;
		default:
			return null;
		}

	}

}
