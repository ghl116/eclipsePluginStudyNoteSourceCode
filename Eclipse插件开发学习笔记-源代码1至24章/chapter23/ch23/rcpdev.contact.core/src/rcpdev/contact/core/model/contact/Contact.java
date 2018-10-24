package rcpdev.contact.core.model.contact;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import rcpdev.contact.core.model.BizObject;

public class Contact extends BizObject {

	private String fullName;

	private Date birthday;

	private String gender;

	private List<String> category;

	private String company;

	private TreeMap<String, String> emails;

	private String webPage;

	private String im;

	private String blog;

	private TreeMap<String, String> phones;

	private TreeMap<String, Address> addresses;

	private String note;

	public Contact() {
		birthday = new Date();
	}

	public TreeMap<String, Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(TreeMap<String, Address> addresses) {
		Iterator ite = addresses.entrySet().iterator();
		while (ite.hasNext()) {
			Entry entry = (Entry) ite.next();
			Address addr = (Address) entry.getValue();
			addr.setContact(this);
			addr.setType(String.valueOf(entry.getKey()));
		}
		setValue("addresses", addresses);
	}

	public TreeMap<String, String> getPhones() {
		return phones;
	}

	public void setPhones(TreeMap<String, String> phones) {
		setValue("phones", phones);
	}

	public TreeMap<String, String> getEmails() {
		return emails;
	}

	public void setEmails(TreeMap<String, String> emails) {
		setValue("emails", emails);
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		setValue("birthday", birthday);
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		setValue("blog", blog);
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		setValue("category", category);
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		setValue("company", company);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		setValue("fullName", fullName);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		setValue("gender", gender);
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		setValue("im", im);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		setValue("note", note);
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		setValue("webPage", webPage);
	}

	public void copy(Contact target) {
		setBirthday(target.getBirthday());
		setAddresses(target.getAddresses());
		setBlog(target.getBlog());
		setCategory(target.getCategory());
		setEmails(target.getEmails());
		setFullName(target.getFullName());
		setGender(target.getGender());
		setIm(target.getIm());
		setNote(target.getNote());
		setPhones(target.getPhones());
		setWebPage(target.getWebPage());
	}
}
