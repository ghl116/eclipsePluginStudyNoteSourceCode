package rcpdev.todo.persistence.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import rcpdev.todo.persistence.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_MYSQL_CONSTR,
				"jdbc:mysql://localhost:3306/todo");
		store.setDefault(PreferenceConstants.P_MYSQL_USERNAME, "root");
		store.setDefault(PreferenceConstants.P_MYSQL_PASSWORD, "passwd");
	}

}
