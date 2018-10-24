package rcpdev.common.ui.databinding.demo;

import rcpdev.common.ui.databinding.AbstractBean;

public class DemoBean extends AbstractBean {

	private String userId;

	private String userName;

	public DemoBean() {
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		String oldId = getUserId();
		this.userId = userId;
		firePropertyChange("userId", oldId, userId);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		String oldName = getUserName();
		this.userName = userName;
		firePropertyChange("userName", oldName, userName);
	}

}
