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

//在13.7节实现了IManagerListener接口
public class AddressFormEditor extends FormEditor implements IManagerListener{
/*	private StyledText text;*/

	private boolean isPageModified;
	
	private boolean sourceChanged;
	
	//获得页面
	private MasterDetailsPage mdPage;
	private PageWithSubPages pwsPage;
	private SourcePage sourcePage;
	
	//在第十六章增加
	private final IPropertyChangeListener propertyChangeListener = new IPropertyChangeListener() {
		   public void propertyChange(PropertyChangeEvent event) {
		      if (event.getProperty().equals(PreferenceConstants.P_EDITOR_EDIT_BOOL) || 
		    		  event.getProperty().equals(PreferenceConstants.P_EDITOR_PWSP_BOOL) || 
		    		  event.getProperty().equals(PreferenceConstants.P_EDITOR_SOURCE_BOOL))
		         updatePages();
		   }
		};
	//结束在第十六章的增加
	
	public AddressFormEditor() {
	}
	protected void addPages() {
		try {
			//在16章改变该段代码
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
		
		//在13.7节中加入此段代码
		((SimpleFormEditorInput)getEditorInput()).getManager().addManagerListener(this);
		//结束在13.7节中此段代码的加入
		
		
		//在第十六章增加
		updatePages();
		Activator.getDefault().getPluginPreferences().
		addPropertyChangeListener(propertyChangeListener);
		//结束在第十六章的增加
		
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
		//在13.7节加入此段代码
		if(isPageModified){
			//在第十六章修改了该语句
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
		//结束在13.7节对此段代码的加入
	}
	public boolean isDirty(){
		//在13.7节中加入此段代码
		return isPageModified||super.isDirty();
		//结束在13.7节中此段代码的加入
	}
	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	public void pageChange(int newPageIndex){
		//在13.7节中加入此段代码
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
		//结束在13.7节中此段代码的加入
		
		//在13.8节中加入此段代码
		 IEditorActionBarContributor contributor = 
	         getEditorSite().getActionBarContributor();
	      if (contributor instanceof AddressFormEditorContributor)
	         ((AddressFormEditorContributor) contributor)
	            .setActivePage(this, newPageIndex);
	      //结束在13.8节中此段代码的加入
	}

	public void dispose(){
		/*临时实现，将在13.7小节更改
		 * ((SimpleFormEditorInput)getEditorInput()).getManager().saveDescriptions();
		 * */
		
		//在第十六章增加
		Activator.getDefault().getPluginPreferences().
		removePropertyChangeListener(propertyChangeListener);
		//结束在第十六章的增加
		
		super.dispose();
	}
	public int getActivePage(){
		return super.getActivePage();
	}
	/*
	 * 在13.7节实现了managerChanged()方法
	 * @see com.plugindev.addressbook.editors.models.IManagerListener#managerChanged(java.lang.Object[], java.lang.String, java.lang.String, java.lang.String)
	 */
	public void managerChanged(Object object, String type, String itemType, String key) {
		if(isPageModified == false){
			isPageModified = true;
			editorDirtyStateChanged();
		}
	}
	
	//在第十六章增加该方法，用来响应跟该编辑器相关的首选项的更改
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
