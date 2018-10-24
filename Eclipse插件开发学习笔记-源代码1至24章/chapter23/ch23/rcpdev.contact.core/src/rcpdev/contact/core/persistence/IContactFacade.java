package rcpdev.contact.core.persistence;

import java.util.List;

import rcpdev.contact.core.model.contact.Contact;

public interface IContactFacade {

	public static final String ADD_CONTACT = "IContactFacade.addContact";

	public static final String UPDATE_CONTACT = "IContactFacade.updateContact";

	public static final String REMOVE_CONTACT = "IContactFacade.removeContact";

	public static final String FACADE_UPDATED = "IContactFacade.facadeUpdated";

	public void init() throws ContactFacadeException;

	public void dispose() throws ContactFacadeException;
	
	public Contact createContact(Contact contact) throws ContactFacadeException;

	public void removeContact(Contact contact) throws ContactFacadeException;

	public Contact updateContact(Contact contact) throws ContactFacadeException;

	public List<Contact> searchContacts() throws ContactFacadeException;

	public List<Contact> searchContacts(String name, String company,
			String category) throws ContactFacadeException;

	public List<String> getCategories() throws ContactFacadeException;

	public void setCategories(List<String> newCates)
			throws ContactFacadeException;
}
