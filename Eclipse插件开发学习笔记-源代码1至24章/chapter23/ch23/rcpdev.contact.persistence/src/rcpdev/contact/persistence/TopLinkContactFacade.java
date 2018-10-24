package rcpdev.contact.persistence;

import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

import oracle.toplink.exceptions.DatabaseException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.queryframework.DeleteObjectQuery;
import oracle.toplink.queryframework.ReadAllQuery;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.sessions.UnitOfWork;
import rcpdev.common.core.mediator.Mediator;
import rcpdev.common.core.utils.StringUtils;
import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.core.model.support.Category;
import rcpdev.contact.core.persistence.ContactFacadeException;
import rcpdev.contact.core.persistence.IContactFacade;
import rcpdev.contact.persistence.core.TopLinkContactFacadeException;
import rcpdev.contact.persistence.core.session.TopLinkSessionProvider;

public class TopLinkContactFacade implements IContactFacade {

	private PropertyChangeSupport delegate;

	public TopLinkContactFacade() {
		super();
		delegate = new PropertyChangeSupport(this);
		delegate.addPropertyChangeListener(Mediator.getInstance());
	}

	public void init() throws ContactFacadeException {

	}

	public void dispose() {
		delegate.removePropertyChangeListener(Mediator.getInstance());
	}

	public PropertyChangeSupport getDelegate() {
		return delegate;
	}

	public Contact createContact(Contact contact) throws ContactFacadeException {
		try {
			UnitOfWork uow = TopLinkSessionProvider.getInstance().getSession()
					.acquireUnitOfWork();
			// uow.assignSequenceNumber(contact);
			uow.registerNewObject(contact);
			uow.commit();
			delegate.firePropertyChange(ADD_CONTACT, null, contact);
			return contact;
		} catch (DatabaseException e) {
			throw new TopLinkContactFacadeException(e);
		}
	}

	public List<String> getCategories() throws ContactFacadeException {
		try {
			ReadAllQuery raq = new ReadAllQuery(Category.class);
			UnitOfWork uow = TopLinkSessionProvider.getInstance().getSession()
					.acquireUnitOfWork();
			Vector result = (Vector) uow.executeQuery(raq);
			Vector<String> res = new Vector<String>();
			for (int i = 0; i < result.size(); i++) {
				Category category = (Category) result.get(i);
				res.add(category.getValue());
			}
			uow.release();
			return res;
		} catch (DatabaseException e) {
			throw new TopLinkContactFacadeException(e);
		}
	}

	public List<Contact> searchContacts() throws ContactFacadeException {
		try {
			ReadAllQuery raq = new ReadAllQuery(Contact.class);
			raq.addAscendingOrdering("fullName");
			raq.addAscendingOrdering("company");
			UnitOfWork uow = TopLinkSessionProvider.getInstance().getSession()
					.acquireUnitOfWork();
			List<Contact> result = (List<Contact>) uow.executeQuery(raq);
			uow.release();
			return result;
		} catch (DatabaseException e) {
			throw new TopLinkContactFacadeException(e);
		}
	}

	public List<Contact> searchContacts(String name, String company,
			String category) throws ContactFacadeException {
		try {
			ExpressionBuilder builder = new ExpressionBuilder();
			Expression exp = builder;
			if (!StringUtils.isEmpty(name))
				exp = exp.and(builder.get("fullName").likeIgnoreCase(
						"%" + name + "%"));
			if (!StringUtils.isEmpty(company))
				exp = exp.and(builder.get("company").likeIgnoreCase(
						"%" + company + "%"));
			if (!StringUtils.isEmpty(category))
				exp = exp.and(builder.anyOf("category").equal(category));
			ReadAllQuery query = new ReadAllQuery(Contact.class, exp);
			UnitOfWork uow = TopLinkSessionProvider.getInstance().getSession()
					.acquireUnitOfWork();
			List<Contact> result = (List<Contact>) uow.executeQuery(query);
			uow.release();
			return result;
		} catch (DatabaseException e) {
			throw new TopLinkContactFacadeException(e);
		}
	}

	public Contact updateContact(Contact contact) throws ContactFacadeException {
		try {
			UnitOfWork uow = TopLinkSessionProvider.getInstance().getSession()
					.acquireUnitOfWork();
			Contact workCopy = (Contact) uow.readObject(contact);
			workCopy.copy(contact);
			TreeMap newMap = new TreeMap();
			Iterator aI = workCopy.getAddresses().entrySet().iterator();
			while (aI.hasNext()) {
				Entry entry = (Entry) aI.next();
				newMap
						.put(entry.getKey(), uow.registerObject(entry
								.getValue()));
			}
			workCopy.setAddresses(newMap);
			uow.commit();
			delegate.firePropertyChange(UPDATE_CONTACT, null, contact);
			return workCopy;
		} catch (DatabaseException e) {
			throw new TopLinkContactFacadeException(e);
		}
	}

	public void setCategories(List<String> newCates)
			throws ContactFacadeException {
		try {
			UnitOfWork uow = TopLinkSessionProvider.getInstance().getSession()
					.acquireUnitOfWork();
			uow.executeNonSelectingCall(new SQLCall(
					"delete from support_category"));
			uow.commit();
			uow = TopLinkSessionProvider.getInstance().getSession()
					.acquireUnitOfWork();
			for (int i = 0; i < newCates.size(); i++) {
				Category cate = new Category();
				cate.setValue(newCates.get(i));
				uow.registerNewObject(cate);
			}
			uow.commit();
			delegate.firePropertyChange(FACADE_UPDATED, null, null);
		} catch (DatabaseException e) {
			throw new TopLinkContactFacadeException(e);
		}
	}

	public void removeContact(Contact contact) throws ContactFacadeException {
		try {
			UnitOfWork uow = TopLinkSessionProvider.getInstance().getSession()
					.acquireUnitOfWork();
			Object clone = uow.readObject(contact);
			uow.executeQuery(new DeleteObjectQuery(clone));
			uow.commit();
			delegate.firePropertyChange(REMOVE_CONTACT, contact, null);
		} catch (DatabaseException e) {
			throw new TopLinkContactFacadeException(e);
		}
	}

}
