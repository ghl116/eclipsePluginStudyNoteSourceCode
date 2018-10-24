package rcpdev.contact.ui.actions.contact;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import rcpdev.contact.core.model.contact.Contact;

public abstract class ContactAction extends Action implements Runnable {

	private TableViewer viewer;

	public ContactAction(String name) {
		super(name);
	}

	public TableViewer getViewer() {
		return viewer;
	}

	public void setViewer(TableViewer viewer) {
		this.viewer = viewer;
	}

	public Contact getSelectedContact() {
		try {
			Contact selected = (Contact) ((StructuredSelection) viewer
					.getSelection()).getFirstElement();
			if (selected == null) {
				Shell shell = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell();
				MessageBox box = new MessageBox(shell, SWT.OK
						| SWT.ICON_WARNING);
				box.setText("Select a Contact");
				box.setMessage("Please select a contact to operate on");
				box.open();
			}
			return (Contact)selected.clone();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}

	}
}
