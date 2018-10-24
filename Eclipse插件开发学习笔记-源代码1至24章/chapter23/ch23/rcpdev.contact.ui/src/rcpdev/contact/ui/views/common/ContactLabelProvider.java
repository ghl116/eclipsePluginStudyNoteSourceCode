package rcpdev.contact.ui.views.common;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import rcpdev.contact.core.model.contact.Contact;

public class ContactLabelProvider implements ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Contact) {
			Contact contact = (Contact) element;
			switch (columnIndex) {
			case 0:
				return contact.getFullName();
			case 1:
				return contact.getCompany();
			case 2:
			case 3:
			}
		}
		if (element instanceof String)
			switch (columnIndex) {
			case 0:
				return element.toString();
			default:
				return null;
			}
		return null;
	}

	public void addListener(ILabelProviderListener listener) {

	}

	public void dispose() {

	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {

	}

}
