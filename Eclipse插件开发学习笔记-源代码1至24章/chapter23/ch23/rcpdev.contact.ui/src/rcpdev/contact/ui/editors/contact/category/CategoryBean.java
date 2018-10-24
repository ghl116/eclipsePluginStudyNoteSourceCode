package rcpdev.contact.ui.editors.contact.category;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import rcpdev.common.ui.databinding.AbstractBean;

public class CategoryBean extends AbstractBean {

	public static final String ADD_ITEM = "addItem";

	public static final String REMOVE_ITEM = "removeItem";

	public static final String CHECK_ITEM = "checkItem";

	private Vector<CategoryItem> categories;

	protected Map<String, CategoryItem> categoryMap;

	public CategoryBean() {
		super();
		categories = new Vector<CategoryItem>();
		categoryMap = new HashMap<String, CategoryItem>();
	}

	public Vector<CategoryItem> getCategories() {
		return (Vector) categories.clone();
	}

	public void addCategory(CategoryItem item) {
		if (!categories.contains(item)) {
			categories.add(item);
			categoryMap.put(item.getCategory(), item);
			firePropertyChange(ADD_ITEM, null, item);
		}
	}

	public boolean removeCategory(CategoryItem item) {
		boolean result = categories.remove(item);
		if (result) {
			categoryMap.remove(item.getCategory());
			firePropertyChange(REMOVE_ITEM, item, null);
		}
		return result;
	}

	public void checkCategory(String categoryName, boolean check) {
		CategoryItem item = categoryMap.get(categoryName);
		if (item == null) {
			item = new CategoryItem(categoryName);
			item.setSelected(true);
			addCategory(item);
			return;
		}
		boolean oldVal = item.isSelected();
		if (check != oldVal) {
			item.setSelected(check);
			firePropertyChange(new PropertyChangeEvent(item, CHECK_ITEM,
					oldVal, check));
		}
	}

	public void refreshAll() {
		for (Iterator ci = categories.iterator(); ci.hasNext();) {
			CategoryItem item = (CategoryItem) ci.next();
			if (item.isSelected()) {
				firePropertyChange(new PropertyChangeEvent(item, CHECK_ITEM,
						false, true));
			}
		}
	}

	public List<String> getSelected() {
		Vector<String> selected = new Vector<String>();
		for (int i = 0; i < categories.size(); i++)
			if (categories.get(i).isSelected())
				selected.add(categories.get(i).getCategory());
		return selected;
	}

	public static final class CategoryItem {
		String category;

		boolean selected;

		public CategoryItem(String category) {
			super();
			setCategory(category);
			setSelected(false);
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		@Override
		public boolean equals(Object anotherItem) {
			if (anotherItem instanceof CategoryItem)
				return category.equals(((CategoryItem) anotherItem)
						.getCategory());
			return super.equals(anotherItem);
		}
	}
}
