package com.plugindev.addressbook.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.plugindev.addressbook.Activator;
import com.plugindev.addressbook.editors.models.IManagerListener;
import com.plugindev.addressbook.editors.models.SimpleFormEditorInput;
import com.plugindev.addressbook.preferences.PreferenceConstants;

//��13.7��ʵ����IManagerListener�ӿ�
public class AddressFormEditor extends FormEditor implements IManagerListener{
/*	private StyledText text;*/

	private boolean isPageModified;
	
	private boolean sourceChanged;
	
	//���ҳ��
	private MasterDetailsPage mdPage;
	private PageWithSubPages pwsPage;
	private SourcePage sourcePage;
	
	//�ڵ�ʮ��������
	private final IPropertyChangeListener propertyChangeListener = new IPropertyChangeListener() {
		   public void propertyChange(PropertyChangeEvent event) {
		      if (event.getProperty().equals(PreferenceConstants.P_EDITOR_EDIT_BOOL) || 
		    		  event.getProperty().equals(PreferenceConstants.P_EDITOR_PWSP_BOOL) || 
		    		  event.getProperty().equals(PreferenceConstants.P_EDITOR_SOURCE_BOOL))
		         updatePages();
		   }
		};
	//�����ڵ�ʮ���µ�����
	
	public AddressFormEditor() {
	}
	protected void addPages() {
		try {
			//��16�¸ı�öδ���
			mdPage = new MasterDetailsPage(this);
			pwsPage = new PageWithSubPages(this);
			sourcePage = new SourcePage(this);
			addPage(mdPage);
			addPage(pwsPage);
			addPage(sourcePage);
			
/*			addPage(new MasterDetailsPage(this));
			addPage(new PageWithSubPages(this));
			addPage(new SourcePage(this));*/
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		updateTitle();
		
		//��13.7���м���˶δ���
		((SimpleFormEditorInput)getEditorInput()).getManager().addManagerListener(this);
		//������13.7���д˶δ���ļ���
		
		
		//�ڵ�ʮ��������
		updatePages();
		Activator.getDefault().getPluginPreferences().
		addPropertyChangeListener(propertyChangeListener);
		//�����ڵ�ʮ���µ�����
		
	}
	void updateTitle(){
		IEditorInput input = getEditorInput();
		setPartName(input.getName());
		setTitleToolTip(input.getToolTipText());
	}
	protected FormToolkit createToolkit(Display display) {
		// Create a toolkit that shares colors between editors.
		return new FormToolkit(Activator.getDefault().getFormColors(
				display));
	}
	

	@Override
	public void doSave(IProgressMonitor monitor) {
		//��13.7�ڼ���˶δ���
		if(isPageModified){
			//�ڵ�ʮ�����޸��˸����
			((SimpleFormEditorInput)getEditorInput()).getManager().saveDescriptions();
			sourceChanged = true;
		}
		isPageModified = false;
		if(this.getActivePage() == 2)
		{
			SourcePage page= (SourcePage)getSelectedPage();
			page.loadSource();
			sourceChanged = false;
		}
		editorDirtyStateChanged();
		updateTitle();
		//������13.7�ڶԴ˶δ���ļ���
	}
	public boolean isDirty(){
		//��13.7���м���˶δ���
		return isPageModified||super.isDirty();
		//������13.7���д˶δ���ļ���
	}
	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	public void pageChange(int newPageIndex){
		//��13.7���м���˶δ���
		switch(newPageIndex){
		case 0:
			break;
		case 1:
			if(isPageModified)
			{
				PageWithSubPages page = (PageWithSubPages)getSelectedPage();
				page.updateSelection();
			}
			break;
		case 2:
			SourcePage page = (SourcePage)getSelectedPage();
			if(sourceChanged == true){
				page.loadSource();
				sourceChanged = false;
			}
			break;
		}
		super.pageChange(newPageIndex);
		//������13.7���д˶δ���ļ���
		
		//��13.8���м���˶δ���
		 IEditorActionBarContributor contributor = 
	         getEditorSite().getActionBarContributor();
	      if (contributor instanceof AddressFormEditorContributor)
	         ((AddressFormEditorContributor) contributor)
	            .setActivePage(this, newPageIndex);
	      //������13.8���д˶δ���ļ���
	}

	public void dispose(){
		/*��ʱʵ�֣�����13.7С�ڸ���
		 * ((SimpleFormEditorInput)getEditorInput()).getManager().saveDescriptions();
		 * */
		
		//�ڵ�ʮ��������
		Activator.getDefault().getPluginPreferences().
		removePropertyChangeListener(propertyChangeListener);
		//�����ڵ�ʮ���µ�����
		
		super.dispose();
	}
	public int getActivePage(){
		return super.getActivePage();
	}
	/*
	 * ��13.7��ʵ����managerChanged()����
	 * @see com.plugindev.addressbook.editors.models.IManagerListener#managerChanged(java.lang.Object[], java.lang.String, java.lang.String, java.lang.String)
	 */
	public void managerChanged(Object object, String type, String itemType, String key) {
		if(isPageModified == false){
			isPageModified = true;
			editorDirtyStateChanged();
		}
	}
	
	//�ڵ�ʮ�������Ӹ÷�����������Ӧ���ñ༭����ص���ѡ��ĸ���
	public void updatePages(){
		Preferences prefs = Activator
	     .getDefault().getPluginPreferences();
		boolean showPWSPage = prefs.getBoolean(
				PreferenceConstants.P_EDITOR_PWSP_BOOL);
		boolean showSourcePage = prefs.getBoolean(
				PreferenceConstants.P_EDITOR_SOURCE_BOOL);
		FormPage localPwsPage = (FormPage)findPage("pageWithSub");
		FormPage localSourcePage = (FormPage)findPage("source");
		if(showPWSPage == false && localPwsPage != null)
		{
			removePage(localPwsPage.getIndex());
		}
		else if(showPWSPage == true && localPwsPage == null)
		{
			if(localSourcePage != null)
				removePage(localSourcePage.getIndex());
			pwsPage = new PageWithSubPages(this);
			sourcePage = new SourcePage(this);
			try {
				addPage(pwsPage);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
		if(showSourcePage == false && localSourcePage != null){
			removePage(localSourcePage.getIndex());
		}
		else if(showSourcePage == true && localSourcePage == null){
			sourcePage = new SourcePage(this);
			try {
				addPage(sourcePage);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}
}
