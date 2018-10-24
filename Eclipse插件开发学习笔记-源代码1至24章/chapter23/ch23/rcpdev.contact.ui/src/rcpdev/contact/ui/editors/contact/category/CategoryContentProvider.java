package rcpdev.contact.ui.editors.contact.category;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import rcpdev.contact.ui.editors.contact.category.CategoryBean.CategoryItem;

public class CategoryContentProvider implements IStructuredContentProvider,
		PropertyChangeListener, ICheckStateListener {

	private CheckboxTableViewer viewer;

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof CategoryBean) {
			CategoryBean bean = (CategoryBean) inputElement;
			return bean.getCategories().toArray();
		}
		return null;
	}

	public void dispose() {

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (viewer instanceof CheckboxTableViewer) {
			this.viewer = ((CheckboxTableViewer) viewer);
			this.viewer.addCheckStateListener(this);
		}
		if (oldInput != null && oldInput instanceof CategoryBean)
			((CategoryBean) oldInput).removePropertyChangeListener(this);
		if (newInput != null && newInput instanceof CategoryBean)
			((CategoryBean) newInput).addPropertyChangeListener(this);
	}

	/**
	 * Change table according to the model change.
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (CategoryBean.CHECK_ITEM.equals(evt.getPropertyName())) {
			Object item = evt.getSource();
			viewer.setChecked(item, (Boolean) evt.getNewValue());
		}
		if (CategoryBean.ADD_ITEM.equals(evt.getPropertyName())) {
			CategoryItem item = (CategoryItem) evt.getNewValue();
			viewer.add(item);
			viewer.setChecked(item, item.isSelected());
		}
		if (CategoryBean.REMOVE_ITEM.equals(evt.getPropertyName())) {
			viewer.remove(evt.getOldValue());
		}

	}

	/**
	 * Change model according to the table change.
	 */
	public void checkStateChanged(CheckStateChangedEvent event) {
		((CategoryItem) event.getElement()).setSelected(event.getChecked());
	}

}
