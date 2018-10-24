package com.plugindev.addressbook.views;


import java.util.Comparator;
import java.util.Iterator;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;

import com.plugindev.addressbook.models.AddressItem;
import com.plugindev.addressbook.models.AddressManager;
import com.plugindev.addressbook.util.ImageCache;
import com.plugindev.addressbook.util.ImageKeys;
import com.plugindev.addressbook.actions.AddAddressAction;
import com.plugindev.addressbook.actions.AddressViewerFilterAction;
import com.plugindev.addressbook.actions.DeleteAddressAction;


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
		nameColumn.setWidth(100);
		
		categoryColumn = new TableColumn(table, SWT.LEFT);
		categoryColumn.setText("类别");
		categoryColumn.setImage(ImageCache.getInstance().getImage(
				ImageKeys.getImageDescriptor(ImageKeys.IMAGE_CATEGORY)));
		categoryColumn.setWidth(100);
		
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
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};
		
		//增加的操作
		ImageDescriptor deleteImage = ImageKeys.getImageDescriptor(
				ImageKeys.IMG_TOOL_DELETE);
		deleteAction = new DeleteAddressAction(this, "删除", deleteImage);
		deleteAction.setDisabledImageDescriptor(
				ImageKeys.getImageDescriptor(ImageKeys.IMG_TOOL_DISABLEDELETE));
		
		ImageDescriptor addImage = ImageKeys.getImageDescriptor(ImageKeys.IMG_TOOL_ADD);
		addAction = new AddAddressAction(this, "添加", addImage);
		
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
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"地址本视图",
			message);
	}

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