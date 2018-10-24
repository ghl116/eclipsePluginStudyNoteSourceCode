package rcpdev.todo.core.storage;

public abstract class TodoFacadeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8807898364418575484L;

	public TodoFacadeException(Throwable cause) {
		super(cause);
	}

	public abstract String getDescription();
	
	public abstract int getErrorCode();
}
