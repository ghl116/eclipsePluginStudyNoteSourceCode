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
		setDescription("���ñ༭����ҳ��������\"�༭\"ҳ�沼��");
	}
	
	/**
	 * Create contents of the preference page
	 */
	@Override
	protected void createFieldEditors() {
		boolEditFieldEditor = new BooleanFieldEditor(PreferenceConstants.P_EDITOR_EDIT_BOOL, "��ʾ\"�༭\"ҳ��", getFieldEditorParent());
		addField(boolEditFieldEditor);
		boolPWSPFieldEditor = new BooleanFieldEditor(PreferenceConstants.P_EDITOR_PWSP_BOOL, "��ʾ\"��ҳԤ��\"ҳ��", getFieldEditorParent());
		addField(boolPWSPFieldEditor);
		boolSourceFieldEditor =new BooleanFieldEditor(PreferenceConstants.P_EDITOR_SOURCE_BOOL, "��ʾ\"Դ����\"ҳ��", getFieldEditorParent());
		addField(boolSourceFieldEditor);
		radioAlignFieldEditor = new RadioGroupFieldEditor(PreferenceConstants.P_EDITOR_LAYOUT, "ѡ��༭����Ĭ�����з�ʽ", 1, 
				new String[][] {{"ˮƽ","horizontal"}, {"��ֱ", "vertical"}},
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
			setErrorMessage("\"�༭\"ҳ�������ڣ�");
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
					setMessage("��������Ч�п�����Ҫ����Ӧ�ó���", WARNING);
				else
					setMessage(null);
			}
			if(event.getSource() == boolSourceFieldEditor){
				if(boolSourceFieldEditor.getBooleanValue() == true)
					setMessage("��������Ч�п�����Ҫ����Ӧ�ó���", WARNING);
				else
					setMessage(null);
			}
		}
	}
}
