package rcpdev.contact.core.persistence;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

public class ContactFacadeFactory {

	public static ContactFacadeFactory getInstance() {
		if (instance == null)
			instance = new ContactFacadeFactory();
		return instance;
	}

	private static ContactFacadeFactory instance;

	private ContactFacadeFactory() {

	}

	public IContactFacade getFacade() {
		// TODO Enable caching and pooling in coming release
		return createFacade();
	}

	/**
	 * Create an <code>StorageFacade</code> implementation according to the
	 * extension.
	 * 
	 * @return <code>StorageFacade</code> instance
	 */
	protected IContactFacade createFacade() {
		IExtensionPoint point = Platform.getExtensionRegistry()
				.getExtensionPoint("rcpdev.contact.core.persistence");
		IExtension[] extensions = point.getExtensions();
		if (extensions.length == 0)
			return null;
		IExtension extension = extensions[0];
		IConfigurationElement[] elements = extension.getConfigurationElements();
		try {
			return (IContactFacade) elements[0]
					.createExecutableExtension("class");
		} catch (Exception e) {
			return null;
		}
	}
}
