package rcpdev.contact.core.model.contact;

import rcpdev.contact.core.model.CommonObject;

public class Email extends CommonObject {
	private String type;

	private String content;

	private Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
