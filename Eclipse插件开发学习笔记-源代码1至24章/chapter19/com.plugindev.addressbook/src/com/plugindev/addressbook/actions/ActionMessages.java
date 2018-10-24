package com.plugindev.addressbook.actions;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ActionMessages {
	private static final String BUNDLE_NAME = "com.plugindev.addressbook.actions.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private ActionMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
