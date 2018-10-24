package book.ch7.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserStructure {

	public static final String ADD_USER = "addUser";

	public static final String REMOVE_USER = "removeUser";

	private User president;

	public UserStructure(User president) {
		this.president = president;
		delegate = new PropertyChangeSupport(this);
	}
	
	public User getPresident() {
		return president;
	}

	/**
	 * 在parentPath所指定的User上添加一个Underling
	 */
	public void add(int[] parentPath, User newUser) {
		User parent = findUser(parentPath);
		if (parent != null && !parent.getUnderlings().contains(newUser)) {
			if (parent.getUnderlings().add(newUser)) {
				newUser.setManager(parent);
				firePropertyChange(new PropertyChangeEvent(this, ADD_USER,
						null, new Object[] { parent, newUser }));
			}
		}
	}

	/**
	 * 丛树结构上移除一个由path指定的User
	 */
	public void remove(int[] path) {
		User theUser = findUser(path);
		if (theUser != null
				&& theUser.getManager().getUnderlings().remove(theUser)) {
			theUser.setManager(null);
			firePropertyChange(new PropertyChangeEvent(this, REMOVE_USER, null,
					theUser));
		}

	}

	/**
	 * 在树结构中定位一个User
	 */
	protected User findUser(int[] path) {
		try {
			User current = president;
			for (int i = 0; i < path.length; i++) {
				current = (User) current.getUnderlings().get(path[i]);
			}
			return current;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	private PropertyChangeSupport delegate;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(listener);
	}

	public void firePropertyChange(PropertyChangeEvent evt) {
		delegate.firePropertyChange(evt);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(listener);
	}

}
