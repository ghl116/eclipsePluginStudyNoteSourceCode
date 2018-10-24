package rcpdev.contact.ui.preferences;

import rcpdev.common.ui.databinding.AbstractBean;

public class InputBean extends AbstractBean {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		firePropertyChange("value", oldValue, value);
	}

}
