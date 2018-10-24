package rcpdev.contact.ui.editors.contact;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.ui.editors.lang.Messages;

public class ContactEditorInput implements IEditorInput {

	private Contact person;

	private boolean add;

	public ContactEditorInput() {
		person = new Contact();
		add = true;
	}

	public ContactEditorInput(Contact person) {
		super();
		setPerson(person);
		add = false;
	}

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	public Contact getPerson() {
		return person;
	}

	public void setPerson(Contact person) {
		this.person = person;
	}

	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		if (!add)
			return Messages.getString("ContactDetailEditorInput.title_edit") + person.getFullName(); //$NON-NLS-1$
		return Messages.getString("ContactDetailEditorInput.title_new"); //$NON-NLS-1$
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return getName();
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}
