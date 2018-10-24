package book.ch7.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

public class UserTreeContentProvider implements ITreeContentProvider,
		PropertyChangeListener {

	public Object[] getChildren(Object parentElement) {
		return ((User) parentElement).getUnderlings().toArray();
	}

	public Object getParent(Object element) {
		return ((User) element).getManager();
	}

	public boolean hasChildren(Object element) {
		Vector underlings = ((User) element).getUnderlings();
		return !(underlings == null || underlings.size() == 0);
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof UserStructure)
			return new Object[] { ((UserStructure) inputElement).getPresident() };
		return new Object[0];
	}

	private TreeViewer viewer;

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TreeViewer) viewer;
		if (oldInput != null)
			((UserStructure) oldInput).removePropertyChangeListener(this);
		if (newInput != null)
			((UserStructure) newInput).addPropertyChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (UserStructure.ADD_USER.equals(evt.getPropertyName())) { 
			Object[] values = (Object[])evt.getNewValue();
			viewer.add(values[0], values[1]);
		}
		if (UserStructure.REMOVE_USER.equals(evt.getPropertyName())) {
			viewer.remove(evt.getNewValue());
		}
	}
	
	public void dispose() {

	}
}
