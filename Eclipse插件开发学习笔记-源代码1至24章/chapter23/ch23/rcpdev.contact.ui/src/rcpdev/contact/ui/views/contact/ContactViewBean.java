package rcpdev.contact.ui.views.contact;

import java.util.List;
import java.util.Vector;

import rcpdev.common.ui.databinding.AbstractBean;

public class ContactViewBean extends AbstractBean {

	private List allContacts;

	public static final String ALL_CONTACTS = "ContactViewBean.allContacts";

	public List getAllContacts() {
		return allContacts;
	}

	public ContactViewBean() {
		super();
		allContacts = new Vector();
	}

	public void setAllContacts(List allContacts) {
		this.allContacts = allContacts;
		firePropertyChange(ALL_CONTACTS, null, allContacts);
	}

}
