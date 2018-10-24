package rcpdev.contact.persistence.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import rcpdev.contact.persistence.Activator;

public class MySQLPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * Create the preference page
	 */
	public MySQLPreferencePage() {
		super(FieldEditorPreferencePage.GRID);
		setDescription("Configurate the connection to mysql server");
		setTitle("MySql Connection");
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	/**
	 * Create contents of the preference page
	 */
	@Override
	protected void createFieldEditors() {
		{
			final StringFieldEditor constrField = new StringFieldEditor(
					PreferenceConstants.P_MYSQL_CONSTR, "Connection String",
					getFieldEditorParent());
			addField(constrField);
		}

		{
			final StringFieldEditor usernameField = new StringFieldEditor(
					PreferenceConstants.P_MYSQL_USERNAME, "User Name",
					getFieldEditorParent());
			addField(usernameField);
		}

		{
			final StringFieldEditor passwordField = new StringFieldEditor(
					PreferenceConstants.P_MYSQL_PASSWORD, "Password",
					getFieldEditorParent());
			addField(passwordField);
		}
		// Create the field editors
	}

	/**
	 * Initialize the preference page
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}

}
