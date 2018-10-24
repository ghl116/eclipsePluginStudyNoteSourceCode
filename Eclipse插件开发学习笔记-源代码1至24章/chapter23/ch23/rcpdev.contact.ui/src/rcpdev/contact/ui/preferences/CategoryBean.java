package rcpdev.contact.ui.preferences;

import java.util.List;
import java.util.Vector;

import rcpdev.common.ui.databinding.AbstractBean;

public class CategoryBean extends AbstractBean {

	private List categories;

	public CategoryBean() {
		super();
		categories = new Vector();
	}

	public List getCategories() {
		return (List) ((Vector) categories).clone();
	}

	public static final String ADD = "CategoryBean.add";

	public static final String REMOVE = "CategoryBean.remove";

	public static final String EDIT = "CategoryBean.edit";

	public void addCategory(String category) {
		categories.add(category);
		firePropertyChange(ADD, null, category);
	}

	public void removeCategory(String category) {
		categories.remove(category);
		firePropertyChange(REMOVE, category, null);
	}

	public void updateCategory(String oldCategory, String newCategory) {
		int oldIndex = categories.indexOf(oldCategory);
		if (!categories.contains(newCategory)) {
			categories.set(oldIndex, newCategory);
			firePropertyChange(EDIT, oldCategory, newCategory);
		}
	}
}
