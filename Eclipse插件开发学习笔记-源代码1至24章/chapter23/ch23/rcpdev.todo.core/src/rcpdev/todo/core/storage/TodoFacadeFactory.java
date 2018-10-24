package rcpdev.todo.core.storage;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

public class TodoFacadeFactory {

	private static TodoFacadeFactory instance;

	public static final TodoFacadeFactory getInstance() {
		if (instance == null) {
			instance = new TodoFacadeFactory();
		}
		return instance;
	}

	private ITodoFacade facade;

	public ITodoFacade getFacade() {
		return facade;
	}

	private TodoFacadeFactory() {
		try {
			IExtension extension = Platform.getExtensionRegistry()
					.getExtensionPoint("rcpdev.todo.core.facade")
					.getExtensions()[0];
			facade = (ITodoFacade) extension.getConfigurationElements()[0]
					.createExecutableExtension("class");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
