package com.plugindev.addressbook.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.plugindev.addressbook.Activator;

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
		IPreferenceStore store = Activator.getDefault()
				.getPreferenceStore();
/*		store.setDefault(PreferenceConstants.P_BOOLEAN, true);
		store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
		store.setDefault(PreferenceConstants.P_STRING,
				"Default value");*/
		//ÊÓÍ¼
		//PreferenceInitializer#initializeDefaultPreferences()
		store.setDefault(PreferenceConstants.P_VIEW_COMBO, "1");
		
		//±à¼­Æ÷
		store.setDefault(PreferenceConstants.P_EDITOR_EDIT_BOOL, true);
		store.setDefault(PreferenceConstants.P_EDITOR_PWSP_BOOL, true);
		store.setDefault(PreferenceConstants.P_EDITOR_SOURCE_BOOL, true);
		store.setDefault(PreferenceConstants.P_EDITOR_LAYOUT, "horizontal");
	}
}
