package com.plugindev.addressbook.views;


import java.util.Comparator;
import java.util.Iterator;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.*;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.action.*;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;

import com.plugindev.addressbook.Activator;
import com.plugindev.addressbook.models.AddressItem;
import com.plugindev.addressbook.models.AddressManager;
import com.plugindev.addressbook.preferences.PreferenceConstants;
import com.plugindev.addressbook.util.ImageCache;
import com.plugindev.addressbook.util.ImageKeys;
import com.plugindev.addressbook.actions.AddAddressAction;
import com.plugindev.addressbook.actions.AddressViewerFilterAction;
import com.plugindev.addressbook.actions.DeleteAddressAction;
import com.plugindev.addressbook.editors.models.SimpleFormEditorInput;


public class AddressView extends ViewPart implements ISelectionListener{
	private static TableViewer viewer;
/*	private Action action1;
	private Action action2;*/
	private Action doubleClickAction;
	private AddressViewerSorter sorter;
	
	//���ӵĲ���
	private DeleteAddressAction deleteAction;
	private AddAddressAction addAction;
	private AddressViewerFilterAction filterAction;
	
	
	private TableColumn nameColumn;
	TableColumn categoryColumn;
	
	private IMemento memento;
	
	//�ڵ�ʮ��������
	private final IPropertyChangeListener propertyChangeListener = new IPropertyChangeListener() {
		   public void propertyChange(PropertyChangeEvent event) {
		      if (event.getProperty().equals(
		    		  PreferenceConstants.P_VIEW_COMBO))
		         updateColumnNumbers();
		   }
		};
	//�����ڵ�ʮ���µ�����

	/**
	 * The constructor.
	 */
	public AddressView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		//����viewer
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | 
				SWT.V_SCROLL | SWT.FULL_SELECTION);
		
		//���ñ�����ʾ����
		final Table table = viewer.getTable();
/*		TableColumn indexColumn = new TableColumn(table, SWT.LEFT);
		indexColumn.setText("");
		indexColumn.setWidth(10);*/
		
		nameColumn = new TableColumn(table, SWT.LEFT);
		nameColumn.setText("����");
		nameColumn.setImage(ImageCache.getInstance().getImage(
				ImageKeys.getImageDescriptor(ImageKeys.IMAGE_PEOPLE)));
		nameColumn.setWidth(100);
		
		categoryColumn = new TableColumn(table, SWT.LEFT);
		categoryColumn.setText("���");
		categoryColumn.setImage(ImageCache.getInstance().getImage(
				ImageKeys.getImageDescriptor(ImageKeys.IMAGE_CATEGORY)));
		categoryColumn.setWidth(100);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//��ʼ��viewer
		viewer.setContentProvider(new AddressViewContentProvider());
		viewer.setLabelProvider(new AddressViewLabelProvider());
		viewer.setInput(AddressManager.getManager());
		viewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
		      public void selectionChanged(SelectionChangedEvent event)
		      {
		        IStructuredSelection selection =
		          (IStructuredSelection) event.getSelection();
		        IStatusLineManager statusline = getViewSite().getActionBars()
		        .getStatusLineManager();
		        Object obj = selection.getFirstElement();
		        if(obj == null)
		        	return;
		        if(obj instanceof AddressItem)
		        {
		        	AddressItem addressItem = (AddressItem)obj;
		        	statusline.setMessage(addressItem.getCategory().getImage(), 
		        			addressItem.getName()+" : "
		        			+addressItem.getCategory().getCategoryName());
		        }
		        else
		        	statusline.setMessage(obj.toString());
		      }
		});
		
		//���������
		createTableSorter();
		
		
		//Ϊ��ͼ�����Ϊ
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		hookKeybordActions();
		
		//������ͼ�鿴���е�����
		getViewSite().setSelectionProvider(viewer);
		
		//��ҳ������Ӹ�������
		getSite().getPage().addSelectionListener(this);
		
		//�ڵ�ʮ��������
		updateColumnNumbers();
		Activator.getDefault().getPluginPreferences().
			addPropertyChangeListener(propertyChangeListener);
		//�����ڵ�ʮ���µ�����
	}
	
	/*
	 * �ڵ�ʮ�������Ӵ˷���������������ʾ���е�����
	 */
	private void updateColumnNumbers() {
	      Preferences prefs = Activator
		     .getDefault().getPluginPreferences();
		   
	      String columnNum = prefs.getString(
	         PreferenceConstants.P_VIEW_COMBO);
	      if(columnNum.equals("0"))
	      {
	    	  nameColumn.setWidth(200);
	    	  categoryColumn.setWidth(0);
	      }
	      else if(columnNum.equals("1"))
	      {
	    	  nameColumn.setWidth(100);
	    	  categoryColumn.setWidth(100);
	      }

	   }
	//����������
	private void createTableSorter() {
		Comparator nameComparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((AddressItem) o1)
					.getName()
					.compareTo(
							((AddressItem) o2).getName());
			}
		};
		Comparator categoryComparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((AddressItem) o1)
					.getCategory()
					.compareTo(
							((AddressItem) o2).getCategory());
			}
		};
		sorter = new AddressViewerSorter(
				viewer,
				new TableColumn[] {
						nameColumn, categoryColumn},
				new Comparator[] {
						nameComparator, categoryComparator});
		if(memento != null)
			sorter.init(memento);
		viewer.setSorter(sorter);
	}
	  
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				AddressView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		//Ϊ�־û�UI״̬�ṩ֧��
		if(memento != null)
			filterAction.init(memento);
		//���Ӳ���
		manager.add(filterAction);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		
		//���Ӳ���
		manager.add(addAction);
		manager.add(new Separator());
		manager.add(deleteAction);
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
/*		manager.add(action1);
		manager.add(action2);*/
		
		//���Ӳ���
		manager.add(addAction);
		manager.add(deleteAction);
		
		deleteAction.setEnabled(false);
		viewer.addSelectionChangedListener(
				new ISelectionChangedListener(){
					public void selectionChanged(
							SelectionChangedEvent event){
						deleteAction.setEnabled(
								!event.getSelection().isEmpty());
					}
				});
	}

	private void hookKeybordActions()
	{
		viewer.getControl().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent event){
			handleKeyReleased(event);
		}
		});
	}
	protected void handleKeyReleased(KeyEvent event)
	{
		if(event.character == SWT.DEL && event.stateMask == 0) {
			deleteAction.run();
		}
	}
	private void makeActions() {
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				AddressItem obj = (AddressItem)((IStructuredSelection)selection).getFirstElement();
				IWorkbenchPage page = PlatformUI.getWorkbench().
										getActiveWorkbenchWindow().getActivePage();
				try {
					SimpleFormEditorInput input = new SimpleFormEditorInput(obj.getName());
					page.openEditor(input, "com.plugindev.addressbook.tableEditor");
				} catch (PartInitException e) {
					System.out.println(e);
				}
				//ΪEditor���ӵĲ���
			}
		};
		
		//���ӵĲ���
		ImageDescriptor deleteImage = ImageKeys.getImageDescriptor(
				ImageKeys.IMG_TOOL_DELETE);
		deleteAction = new DeleteAddressAction(this, "ɾ��", deleteImage);
		deleteAction.setDisabledImageDescriptor(
				ImageKeys.getImageDescriptor(ImageKeys.IMG_TOOL_DISABLEDELETE));
		
		ImageDescriptor addImage = ImageKeys.getImageDescriptor(ImageKeys.IMG_TOOL_ADD);
		addAction = new AddAddressAction("���", addImage);
		
		ImageDescriptor filterImage = ImageKeys.getImageDescriptor(ImageKeys.IMG_TOOL_FILTER);
		
		filterAction = new AddressViewerFilterAction(viewer, "����...", filterImage);
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
/*	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"��ַ����ͼ",
			message);
	}*/

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	public AddressItem[] getSelectedAddresses()
	{
		IStructuredSelection selection = 
			(IStructuredSelection)viewer.getSelection();
		AddressItem[] items = 
			new AddressItem[selection.size()];
		Iterator iter = selection.iterator();
		int index = 0;
		while(iter.hasNext())
			items[index++] = (AddressItem)iter.next();
		return items;
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		//�����
		
	}
	public void dispose(){
		getViewSite().getWorkbenchWindow().
			getSelectionService().removeSelectionListener(this);
		
		//�ڵ�ʮ��������
		Activator.getDefault().getPluginPreferences().
		removePropertyChangeListener(propertyChangeListener);
		//�����ڵ�ʮ���µ�����
		
		super.dispose();
	}
	public void saveState(IMemento memento){
		super.saveState(memento);
		sorter.saveState(memento);
		filterAction.saveState(memento);
	}
	public void init(IViewSite site, IMemento memento)throws PartInitException
	{
		super.init(site, memento);
		this.memento = memento;
	}
}