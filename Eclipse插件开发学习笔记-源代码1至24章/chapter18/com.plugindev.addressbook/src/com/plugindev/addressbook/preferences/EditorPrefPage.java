package com.plugindev.addressbook.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.plugindev.addressbook.Activator;

public class EditorPrefPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	
	private BooleanFieldEditor boolEditFieldEditor;
	private BooleanFieldEditor boolPWSPFieldEditor;
	private BooleanFieldEditor boolSourceFieldEditor;
	private RadioGroupFieldEditor radioAlignFieldEditor;

	/**
	 * Create the preference page
	 */
	public EditorPrefPage() {
		super(FieldEditorPreferencePage.FLAT);
		this.setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("设置编辑器的页面数量、\"编辑\"页面布局");
	}
	
	/**
	 * Create contents of the preference page
	 */
	@Override
	protected void createFieldEditors() {
		boolEditFieldEditor = new BooleanFieldEditor(PreferenceConstants.P_EDITOR_EDIT_BOOL, "显示\"编辑\"页面", getFieldEditorParent());
		addField(boolEditFieldEditor);
		boolPWSPFieldEditor = new BooleanFieldEditor(PreferenceConstants.P_EDITOR_PWSP_BOOL, "显示\"分页预览\"页面", getFieldEditorParent());
		addField(boolPWSPFieldEditor);
		boolSourceFieldEditor =new BooleanFieldEditor(PreferenceConstants.P_EDITOR_SOURCE_BOOL, "显示\"源代码\"页面", getFieldEditorParent());
		addField(boolSourceFieldEditor);
		radioAlignFieldEditor = new RadioGroupFieldEditor(PreferenceConstants.P_EDITOR_LAYOUT, "选择编辑器的默认排列方式", 1, 
				new String[][] {{"水平","horizontal"}, {"垂直", "vertical"}},
				getFieldEditorParent());
			addField(radioAlignFieldEditor);

		// Create the field editors
		
	}

	/**
	 * Initialize the preference page
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}
	public void checkState(){
		super.checkState();
		if(!isValid())
			return;
		if(!boolEditFieldEditor.getBooleanValue()){
			setErrorMessage("\"编辑\"页面必须存在！");
			setValid(false);
		}
		else{
			setErrorMessage(null);
			setValid(true);
		}
	}
	public void propertyChange(PropertyChangeEvent event){
		super.propertyChange(event);
		if(event.getProperty().equals(FieldEditor.VALUE)){
			if(event.getSource() == boolEditFieldEditor)
				checkState();
			if(event.getSource() == boolPWSPFieldEditor){
				if(boolPWSPFieldEditor.getBooleanValue()== true)
					setMessage("该设置生效有可能需要重启应用程序！", WARNING);
				else
					setMessage(null);
			}
			if(event.getSource() == boolSourceFieldEditor){
				if(boolSourceFieldEditor.getBooleanValue() == true)
					setMessage("该设置生效有可能需要重启应用程序！", WARNING);
				else
					setMessage(null);
			}
		}
	}
}
