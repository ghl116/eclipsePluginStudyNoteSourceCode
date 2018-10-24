package com.plugindev.addressbook.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.plugindev.addressbook.Activator;
import com.swtdesigner.preference.ComboFieldEditor;

public class ViewPrefPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	
	private ComboFieldEditor comboFieldEditor;

	/**
	 * Create the preference page
	 */
	public ViewPrefPage() {
		super(FieldEditorPreferencePage.GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("设置\"地址本视图\"显示的列");
	}

	/**
	 * Create contents of the preference page
	 */
	@Override
	protected void createFieldEditors() {
		{
			comboFieldEditor = new ComboFieldEditor(
					PreferenceConstants.P_VIEW_COMBO, 
					"选择显示的列：", 
					new String[][] {{"仅\"名称\"", "0"},
					{"\"名称\"和\"类别\"", "1"} }, 
					getFieldEditorParent());
			addField(comboFieldEditor);
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
