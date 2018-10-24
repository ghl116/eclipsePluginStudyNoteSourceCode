package rcpdev.contact.ui.views.contact.provider;

import rcpdev.contact.ui.common.provider.AbstractContentProvider;
import rcpdev.contact.ui.views.contact.ContactViewBean;

public class ContactContentProvider extends AbstractContentProvider {

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof ContactViewBean) {
			return ((ContactViewBean) inputElement).getAllContacts().toArray();
		}
		return null;
	}

}
