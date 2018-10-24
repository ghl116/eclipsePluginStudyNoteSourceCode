package book.ch7.tree;

import java.util.Vector;

public class User {

	private String id;

	private String name;
	
	private User manager;
	
	private Vector underlings;
	
	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Vector getUnderlings() {
		if(underlings == null)
			underlings = new Vector();
		return underlings;
	}	
}
