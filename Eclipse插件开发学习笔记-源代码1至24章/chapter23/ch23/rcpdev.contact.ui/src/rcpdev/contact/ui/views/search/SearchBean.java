package rcpdev.contact.ui.views.search;

import rcpdev.common.ui.databinding.AbstractBean;

public class SearchBean extends AbstractBean {
	private String company;

	private String name;

	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
