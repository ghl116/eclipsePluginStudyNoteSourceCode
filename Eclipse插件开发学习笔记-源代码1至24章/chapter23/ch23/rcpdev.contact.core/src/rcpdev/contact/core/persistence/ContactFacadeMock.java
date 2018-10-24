package rcpdev.contact.core.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import rcpdev.common.core.utils.DateUtils;
import rcpdev.contact.core.model.contact.Contact;

/**
 * Dummy StorageFacade
 * 
 * Use this to debug and develop ui part when storage part is not ready.<br>
 * Provider searching function only.
 * 
 * @author Harper
 * 
 */
public class ContactFacadeMock implements IContactFacade {

	Vector<Contact> persons = new Vector<Contact>();

	Contact person1;

	Contact person2;

	public ContactFacadeMock() {
		person1 = new Contact();
		person1.setFullName("Jiang, Harper");
		person1.setCompany("OOCL");
		person1.setOid("0");
		Vector cat1 = new Vector();
		cat1.add("家庭");
		cat1.add("工作");
		person1.setCategory(cat1);

		person1.setBirthday(DateUtils.truncToDay(new Date()));

		person2 = new Contact();
		person2.setFullName("Yang, Debbie");
		person2.setCompany("Genesys");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		person2.setBirthday(DateUtils.truncToDay(cal.getTime()));
		person2.setOid("1");
		Vector cat2 = new Vector();
		cat2.add("家庭");
		person2.setCategory(cat2);

		persons.add(person1);
		persons.add(person2);

	}

	public Contact createContact(Contact contact) throws ContactFacadeException {
		return contact;
	}

	public List<Contact> searchContacts() throws ContactFacadeException {
		// TODO Auto-generated method stub
		return persons;
	}

	public Contact updateContact(Contact contact) throws ContactFacadeException {
		return contact;
	}

	public List<Contact> birthdayInMonth(Date date) throws ContactFacadeException {
		return persons;
	}

	public List<String> getCategories() throws ContactFacadeException {
		Vector<String> v = new Vector<String>();
		v.add("家庭");
		v.add("工作");
		return v;
	}

	public List<Contact> getContactByCategory(String category)
			throws ContactFacadeException {
		Vector ccat1 = new Vector();
		ccat1.add(person1);
		ccat1.add(person2);

		Vector ccat2 = new Vector();
		ccat2.add(person1);
		if ("家庭".equals(category))
			return ccat1;
		if ("工作".equals(category))
			return ccat2;
		return null;
	}

	public List<Contact> searchContacts(String name, String company,
			String category) throws ContactFacadeException {
		return null;
	}

	public void setCategories(List<String> newCates) throws ContactFacadeException {
		// TODO Auto-generated method stub
		
	}

	public void init() throws ContactFacadeException {
		// TODO Auto-generated method stub
		
	}

	public void removeContact(Contact contact) throws ContactFacadeException {
		// TODO Auto-generated method stub
		return;
	}

	public void dispose() throws ContactFacadeException {
		// TODO Auto-generated method stub
		
	}

	

}
