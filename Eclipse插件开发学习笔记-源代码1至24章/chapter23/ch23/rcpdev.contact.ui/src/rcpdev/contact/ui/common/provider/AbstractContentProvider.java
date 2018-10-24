package rcpdev.contact.ui.common.provider;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public abstract class AbstractContentProvider implements
		IStructuredContentProvider, PropertyChangeListener {

	protected Viewer viewer;

	protected Object input;

	public void dispose() {

	}

	public Viewer getViewer() {
		return viewer;
	}

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}

	public Object getInput() {
		return input;
	}

	public void setInput(Object input) {
		this.input = input;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		setViewer(viewer);
		setInput(newInput);
		if (oldInput != null && oldInput instanceof IAdaptable) {
			PropertyChangeSupport pcs = (PropertyChangeSupport) ((IAdaptable) oldInput)
					.getAdapter(PropertyChangeSupport.class);
			if (pcs != null)
				pcs.removePropertyChangeListener(this);
		}
		if (newInput != null && newInput instanceof IAdaptable) {
			PropertyChangeSupport pcs = (PropertyChangeSupport) ((IAdaptable) newInput)
					.getAdapter(PropertyChangeSupport.class);
			if (pcs != null)
				pcs.addPropertyChangeListener(this);
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		getViewer().refresh();
	}

}
