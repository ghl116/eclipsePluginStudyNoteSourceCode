package com.plugindev.addressbook.editors;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.*;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.*;
import org.eclipse.ui.internal.IWorkbenchConstants;

import com.plugindev.addressbook.Activator;
import com.plugindev.addressbook.editors.actions.RemoveAddressListAction;
import com.plugindev.addressbook.editors.models.BasicAddressList;
import com.plugindev.addressbook.editors.models.BasicAddressListProperties;
import com.plugindev.addressbook.editors.models.MasterContentProvider;
import com.plugindev.addressbook.editors.models.MasterLabelProvider;
import com.plugindev.addressbook.editors.models.PhoneAddressList;
import com.plugindev.addressbook.editors.models.PhoneAddressListItemProperties;
import com.plugindev.addressbook.editors.models.AreaAddressList;
import com.plugindev.addressbook.editors.models.AreaAddressListProperties;
import com.plugindev.addressbook.preferences.PreferenceConstants;
import com.plugindev.addressbook.util.ImageKeys;
import com.plugindev.addressbook.util.Messages;
/**
 *
 */
public class ScrolledPropertiesBlock extends MasterDetailsBlock {
	private FormPage page;
	private TableViewer viewer;
	
	//在13.8节加入此字段
	private RemoveAddressListAction removeAction;
	private Action vaction;
	private Action haction;
	
	//在第十六章增加
	private final IPropertyChangeListener propertyChangeListener = new IPropertyChangeListener() {
		   public void propertyChange(PropertyChangeEvent event) {
		      if (event.getProperty().equals(PreferenceConstants.P_EDITOR_LAYOUT)){
		    	  updateLayout();
		      }
		   }
		};
	//结束在第十六章的增加
	
	public ScrolledPropertiesBlock(FormPage page) {
		this.page = page;
	}

	protected void createMasterPart(final IManagedForm managedForm,
			Composite parent) {
		//final ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION|Section.TITLE_BAR);
		section.setText(Messages.SCROL_BLOC_NAME); //$NON-NLS-1$
		section.setDescription(Messages.SCROL_BLOC_DESCRIP); //$NON-NLS-1$
		section.marginWidth = 10;
		section.marginHeight = 5;
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);
		Table t = toolkit.createTable(client, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		gd.verticalSpan = 2;
		t.setLayoutData(gd);
		toolkit.paintBordersFor(client);

		section.setClient(client);
		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);
		viewer = new TableViewer(t);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(spart, event.getSelection());
			}
		});
		viewer.setContentProvider(new MasterContentProvider());
		viewer.setLabelProvider(new MasterLabelProvider());
		viewer.setInput(page.getEditor().getEditorInput());
		
		//在13.8节加入此段代码
		createActions();
		createContextMenu();
		//结束在13.8节加入的此段代码
		
		Button b = toolkit.createButton(client, Messages.SCROL_BLOC_ADD, SWT.PUSH);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		b.setLayoutData(gd);
		b = toolkit.createButton(client, Messages.SCROL_BLOC_DELETE, SWT.PUSH);
		
		//在13.8节加入此段代码
		b.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				removeAction.run();
			}
		});
		//结束在13.8节加入的此段代码
		
		//在13.8节加入此段代码
		viewer.getTable().addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
				if(e.character == SWT.DEL)
					removeAction.run();
			}
			public void keyReleased(KeyEvent e){
			}
		});
		//结束在13.8节加入的此段代码
		
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.VERTICAL_ALIGN_BEGINNING);
		b.setLayoutData(gd);
	}
	/*
	 * 在13.8节加入此方法
	 */
	private void createActions(){
		ImageDescriptor removeImage = 
			PlatformUI.
			getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
		ImageDescriptor disableRemoveImage = 
			PlatformUI.
			getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED);
		removeAction = 
			new RemoveAddressListAction(
					page.getEditor(),viewer,"删除", removeImage);
		removeAction.setDisabledImageDescriptor(disableRemoveImage);
	}
	private void createContextMenu(){
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener(){
			public void menuAboutToShow(IMenuManager m){
				ScrolledPropertiesBlock.this.fillContextMenu(m);
			}
		});
		Table table = viewer.getTable();
		Menu menu = menuMgr.createContextMenu(table);
		table.setMenu(menu);
		page.getEditor().getSite().registerContextMenu(menuMgr, viewer);
	}
	/*
	 * 在13.8节加入此方法
	 */
	private void fillContextMenu(IMenuManager menuMgr){
		boolean isEmpty = viewer.getSelection().isEmpty();
		removeAction.setEnabled(!isEmpty);
		menuMgr.add(removeAction);
		menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	protected void createToolBarActions(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		haction = new Action("hor", Action.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				sashForm.setOrientation(SWT.HORIZONTAL);
				form.reflow(true);
			}
		};
		haction.setChecked(true);
		haction.setToolTipText(Messages.SCROL_BLOC_HORIZONTAL);
		haction.setImageDescriptor(ImageKeys.getImageDescriptor(ImageKeys.IMG_HORIZONTAL));

		vaction = new Action("ver", Action.AS_RADIO_BUTTON) {
			public void run() {
				sashForm.setOrientation(SWT.VERTICAL);
				form.reflow(true);
			}
		};
		vaction.setChecked(false);
		vaction.setToolTipText(Messages.SCROL_BLOC_VERTICAL);
		vaction.setImageDescriptor(ImageKeys.getImageDescriptor(ImageKeys.IMG_VERTICAL));
		form.getToolBarManager().add(haction);
		form.getToolBarManager().add(vaction);
		
		
		//在第十六章增加
		Activator.getDefault().getPluginPreferences().
			addPropertyChangeListener(propertyChangeListener);
		
		updateLayout();
		//结束在第十六章的增加
	}
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.registerPage(BasicAddressList.class, new BasicAddressListProperties());
		detailsPart.registerPage(PhoneAddressList.class, new PhoneAddressListItemProperties());
		detailsPart.registerPage(AreaAddressList.class, new AreaAddressListProperties());
	}
	/*
	 * 此方法在13.8节中加入
	 */
	public IAction getTableAction(String workbenchActionId){
		if(ActionFactory.DELETE.getId().equals(workbenchActionId))
			return removeAction;
		return null;
	}
	/*
	 * 此方法在第16章加入，根据首选项的值更新布局显示
	 */
	public void updateLayout(){
		Preferences prefs = Activator
	     .getDefault().getPluginPreferences();
		String layout = prefs.getString(
				PreferenceConstants.P_EDITOR_LAYOUT);
		if(layout.equals("vertical")){
			vaction.run();
			vaction.setChecked(true);
			haction.setChecked(false);
		}
		else if(layout.equals("horizontal")){
			haction.run();
			haction.setChecked(true);
			vaction.setChecked(false);
		}
	}
}