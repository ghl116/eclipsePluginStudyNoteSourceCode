package rcpdev.contact.ui.views.search.provider;

import rcpdev.contact.ui.common.provider.AbstractContentProvider;
import rcpdev.contact.ui.views.search.SearchResultBean;

public class SearchContactContentProvider extends AbstractContentProvider {

	public Object[] getElements(Object inputElement) {
		SearchResultBean input = (SearchResultBean) inputElement;
		if (input.getResult() == null)
			return new Object[] {};
		return input.getResult().toArray();
	}

}
