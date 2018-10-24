package rcpdev.contact.core.model.contact;

import rcpdev.contact.core.model.CommonObject;

public class Address extends CommonObject {

	private String type;

	private String country;

	private String state;

	private String city;

	private String detail;

	private String postalCode;

	private Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		setValue("city", city);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		setValue("country", country);
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		setValue("detail", detail);
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		setValue("postalCode", postalCode);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		setValue("state", state);
	}

}
