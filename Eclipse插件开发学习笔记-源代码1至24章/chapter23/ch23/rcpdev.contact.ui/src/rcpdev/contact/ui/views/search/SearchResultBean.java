package rcpdev.contact.ui.views.search;

import java.util.List;

import rcpdev.common.ui.databinding.AbstractBean;
import rcpdev.contact.core.model.contact.Contact;

public class SearchResultBean extends AbstractBean {
	private List<Contact> result;

	public static final String RESULT = "result";

	public List<Contact> getResult() {
		return result;
	}

	public void setResult(List<Contact> result) {
		this.result = result;
		firePropertyChange(RESULT, null, result);
	}

}
