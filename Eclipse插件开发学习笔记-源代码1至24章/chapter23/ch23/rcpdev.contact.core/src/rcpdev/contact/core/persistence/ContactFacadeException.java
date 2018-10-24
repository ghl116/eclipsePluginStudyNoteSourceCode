package rcpdev.contact.core.persistence;

public abstract class ContactFacadeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -991377753411890004L;

	public ContactFacadeException() {
		super();
	}

	public ContactFacadeException(Throwable cause) {
		super(cause);
	}

	public abstract String getDescription();
}
