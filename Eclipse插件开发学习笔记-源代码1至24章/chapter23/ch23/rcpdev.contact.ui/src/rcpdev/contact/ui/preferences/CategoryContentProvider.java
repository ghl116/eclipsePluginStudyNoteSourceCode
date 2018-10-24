package rcpdev.contact.ui.preferences;

import rcpdev.contact.ui.common.provider.AbstractContentProvider;

public class CategoryContentProvider extends AbstractContentProvider {

	public Object[] getElements(Object inputElement) {
		return ((CategoryBean) inputElement).getCategories().toArray();
	}

}
