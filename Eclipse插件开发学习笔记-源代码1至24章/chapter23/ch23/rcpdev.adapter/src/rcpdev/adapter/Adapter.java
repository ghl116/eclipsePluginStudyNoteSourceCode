package rcpdev.adapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import rcpdev.adapter.job.AdapterJob;
import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.core.persistence.IContactFacade;

public class Adapter implements PropertyChangeListener {

	private final String TODO_PLUGIN_ID = "rcpdev.todo.core";

	private static Adapter instance;

	public static Adapter getInstance() {
		if (instance == null)
			instance = new Adapter();
		return instance;
	}

	private Adapter() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry
				.getExtensionPoint("org.eclipse.ui.actionSets");
		IExtension[] extensions = point.getExtensions();
		IExtension extension = registry.getExtension("myActions");
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (Platform.getPlugin(TODO_PLUGIN_ID) == null)
			return;
		int actionType;
		Contact target;
		if (IContactFacade.ADD_CONTACT.equals(evt.getPropertyName())) {
			actionType = AdapterJob.ACTION_ADD;
			target = (Contact) evt.getNewValue();
		} else if (IContactFacade.UPDATE_CONTACT.equals(evt.getPropertyName())) {
			actionType = AdapterJob.ACTION_UPDATE;
			target = (Contact) evt.getNewValue();
		} else if (IContactFacade.REMOVE_CONTACT.equals(evt.getPropertyName())) {
			actionType = AdapterJob.ACTION_REMOVE;
			target = (Contact) evt.getOldValue();
		} else {
			return;
		}
		AdapterJob job = new AdapterJob(actionType, target);
		job.schedule();
	}
}
