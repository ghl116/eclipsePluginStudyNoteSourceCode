package com.plugindev.addressbook.views;


import java.util.Comparator;
import java.util.Iterator;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.plugindev.addressbook.Activator;
import com.plugindev.addressbook.actions.AddAddressAction;
import com.plugindev.addressbook.actions.AddressViewerFilterAction;
import com.plugindev.addressbook.actions.DeleteAddressAction;
import com.plugindev.addressbook.editors.models.SimpleFormEditorInput;
import com.plugindev.addressbook.models.AddressItem;
import com.plugindev.addressbook.models.AddressManager;
import com.plugindev.addressbook.preferences.PreferenceConstants;
import com.plugindev.addressbook.util.ImageCache;
import com.plugindev.addressbook.util.ImageKeys;


public class AddressView extends ViewPart implements ISelectionListener{
	private static TableViewer viewer;
/*	private Action action1;
	private Action action2;*/
	private Action doubleClickAction;
	private AddressViewerSorter sorter;
	
	//增加的操作
	private DeleteAddressAction deleteAction;
	private AddAddressAction addAction;
	private AddressViewerFilterAction filterAction;
	
	
	private TableColumn nameColumn;
	TableColumn categoryColumn;
	
	private IMemento memento;
	
	//在第十六章增加
	private final IPropertyChangeListener propertyChangeListener = new IPropertyChangeListener() {
		   public void propertyChange(PropertyChangeEvent event) {
		      if (event.getProperty().equals(
		    		  PreferenceConstants.P_VIEW_COMBO))
		         updateColumnNumbers();
		   }
		};
	//结束在第十六章的增加

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
		//创建viewer
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | 
				SWT.V_SCROLL | SWT.FULL_SELECTION);
		
		//设置表格的显示界面
		final Table table = viewer.getTable();
/*		TableColumn indexColumn = new TableColumn(table, SWT.LEFT);
		indexColumn.setText("");
		indexColumn.setWidth(10);*/
		
		nameColumn = new TableColumn(table, SWT.LEFT);
		nameColumn.setText("姓名");
		nameColumn.setImage(ImageCache.getInstance().getImage(
				ImageKeys.getImageDescriptor(ImageKeys.IMAGE_PEOPLE)));
		//nameColumn.setWidth(100);
		
		categoryColumn = new TableColumn(table, SWT.LEFT);
		categoryColumn.setText("类别");
		categoryColumn.setImage(ImageCache.getInstance().getImage(
				ImageKeys.getImageDescriptor(ImageKeys.IMAGE_CATEGORY)));
		//categoryColumn.setWidth(100);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//初始化viewer
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
		
		//添加排序器
		createTableSorter();
		
		
		//为视图添加行为
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		hookKeybordActions();
		
		//共享视图查看器中的内容
		getViewSite().setSelectionProvider(viewer);
		
		//在页面中添加该侦听器
		getSite().getPage().addSelectionListener(this);
		
		//在第十六章增加
		updateColumnNumbers();
		Activator.getDefault().getPluginPreferences().
			addPropertyChangeListener(propertyChangeListener);
		//结束在第十六章的增加
		
		//在第十七章增加
		setHelpContextIDs();
		//addHelpButtonToToolBar();
		//结束在第十七章的增加
	}
	
	/*
	 * 在第十六章增加此方法，用来更新显示的列的数量
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
	//设置排序器
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
		//为持久化UI状态提供支持
		if(memento != null)
			filterAction.init(memento);
		//增加操作
		manager.add(filterAction);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		
		//增加操作
		manager.add(addAction);
		manager.add(new Separator());
		manager.add(deleteAction);
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
/*		manager.add(action1);
		manager.add(action2);*/
		
		//增加操作
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
				//为Editor增加的操作
			}
		};
		
		//增加的操作
		ImageDescriptor deleteImage = ImageKeys.getImageDescriptor(
				ImageKeys.IMG_TOOL_DELETE);
		deleteAction = new DeleteAddressAction(this, "删除", deleteImage);
		deleteAction.setDisabledImageDescriptor(
				ImageKeys.getImageDescriptor(ImageKeys.IMG_TOOL_DISABLEDELETE));
		
		ImageDescriptor addImage = ImageKeys.getImageDescriptor(ImageKeys.IMG_TOOL_ADD);
		addAction = new AddAddressAction("添加", addImage);
		
		//第十七章加入——为addAction添加帮助监听
		addAction.setHelpListener(new HelpListener(){
			public void helpRequested(HelpEvent e) {
				PlatformUI.getWorkbench().getHelpSystem().
				displayHelp("com.plugindev.addressbook.view_addaction");
			}
		});
		//结束第十七章的加入
		
		ImageDescriptor filterImage = ImageKeys.getImageDescriptor(ImageKeys.IMG_TOOL_FILTER);
		
		filterAction = new AddressViewerFilterAction(viewer, "过滤...", filterImage);
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
			"地址本视图",
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
		//待添加
		
	}
	public void dispose(){
		getViewSite().getWorkbenchWindow().
			getSelectionService().removeSelectionListener(this);
		
		//在第十六章增加
		Activator.getDefault().getPluginPreferences().
		removePropertyChangeListener(propertyChangeListener);
		//结束在第十六章的增加
		
		super.dispose();
	}
	public void saveState(IMemento memento){
		super.saveState(memento);
		sorter.saveState(memento);
		filterAction.saveState(memento);
	}
	/*
	 * 在第十七章增加该方法
	 */
	private void setHelpContextIDs(){
		PlatformUI.getWorkbench().getHelpSystem().
		setHelp(viewer.getControl(), 
				"com.plugindev.addressbook.address_view");
		//for actions
		PlatformUI.getWorkbench().getHelpSystem().
		setHelp(deleteAction, "com.plugindev.addressbook.view_deleteaction");
		//添加操作使用另外一种方式实现
/*		PlatformUI.getWorkbench().getHelpSystem().
		setHelp(addAction, "com.plugindev.addressbook.view_addaction");*/
		PlatformUI.getWorkbench().getHelpSystem().
		setHelp(filterAction, "com.plugindev.addressbook.view_filteraction");
	}
	/*
	 * 在第十七章中加入此方法
	 */
	private void addHelpButtonToToolBar(){
		Action helpAction = new Action(){
			public void run(){
				PlatformUI.getWorkbench().getHelpSystem().displayHelpResource("/com.plugindev.addressbook/html/基本用户界面/视图.htm");
			}
		};
		helpAction.setToolTipText("打开地址本视图帮助");
		helpAction.setImageDescriptor(ImageKeys.getImageDescriptor(ImageKeys.IMG_HELP));
		getViewSite().getActionBars().getToolBarManager().add(helpAction);
	}
	public void init(IViewSite site, IMemento memento)throws PartInitException
	{
		super.init(site, memento);
		this.memento = memento;
	}
}