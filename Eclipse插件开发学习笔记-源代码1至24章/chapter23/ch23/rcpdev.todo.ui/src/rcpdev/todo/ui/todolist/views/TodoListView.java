package rcpdev.todo.ui.todolist.views;

import java.beans.PropertyChangeEvent;
import java.text.DateFormat;
import java.util.Date;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import rcpdev.common.core.mediator.IMediatorReceiver;
import rcpdev.common.core.mediator.Mediator;
import rcpdev.common.ui.databinding.BindingManager;
import rcpdev.common.ui.databinding.LabelBinding;
import rcpdev.common.ui.javabeans.CalendarComposite;
import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.core.storage.ITodoFacade;
import rcpdev.todo.ui.Activator;
import rcpdev.todo.ui.todolist.provider.TodoListContentProvider;
import rcpdev.todo.ui.todolist.provider.TodoListLabelProvider;
import rcpdev.todo.ui.todolist.views.actions.DeleteItemAction;
import rcpdev.todo.ui.todolist.views.actions.ItemOperationAction;
import rcpdev.todo.ui.todolist.views.actions.NewItemAction;
import rcpdev.todo.ui.todolist.views.jobs.EditTodoItemJob;
import rcpdev.todo.ui.todolist.views.jobs.LoadTodoItemJob;

import com.swtdesigner.ResourceManager;

public class TodoListView extends ViewPart implements IMediatorReceiver {

	private Action collapseAllAction;

	private Action expandAllAction;

	private TodoListBean bean;

	private TreeViewer treeViewer;

	private Tree tree;

	private Label dateLabel;

	public static final String ID = "rcpdev.todo.ui.TodoListView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		final GridLayout gridLayout = new GridLayout();
		container.setLayout(gridLayout);

		final Composite composite = new Composite(container, SWT.NONE);
		composite
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		composite.setLayout(new FillLayout());

		dateLabel = new Label(composite, SWT.NONE);
		dateLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));

		treeViewer = new TreeViewer(container, SWT.NONE);
		treeViewer.setContentProvider(new TodoListContentProvider());
		treeViewer.setLabelProvider(new TodoListLabelProvider());
		treeViewer.setInput(getBean());
		tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tree.setLinesVisible(false);

		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				TreeSelection selection = (TreeSelection) treeViewer
						.getSelection();
				Object element = selection.getFirstElement();
				if (element instanceof TodoItem) {
					EditTodoItemJob editJob = new EditTodoItemJob();
					editJob.setTarget((TodoItem) element);
					editJob.setDate(bean.getDate());
					editJob.schedule();
				}
			}
		});

		final TreeColumn column1 = new TreeColumn(tree, SWT.NONE);
		column1.setWidth(100);
		column1.setResizable(false);
		tree.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Tree t = (Tree) e.widget;
				column1.setWidth(t.getSize().x);
			}
		});

		//
		initializeManager();
		createActions();
		initializeToolBar();
		initializeMenu();

		LoadTodoItemJob job = new LoadTodoItemJob();
		job.setDate(getBean().getDate());
		job.schedule();
	}

	private void initializeManager() {
		BindingManager manager = new BindingManager(getBean());
		manager.addBinding(new LabelBinding("date", dateLabel) {
			public void setValue(Object value) {
				((Label) getControl()).setText(DateFormat.getDateInstance(
						DateFormat.FULL).format((Date) value));
			}
		});

		manager.loadAll();
	}

	private NewItemAction newItemAction;

	private DeleteItemAction deleteItemAction;

	private EditItemAction editItemAction;

	/**
	 * Create the actions
	 */
	private void createActions() {

		newItemAction = new NewItemAction();
		newItemAction.setDate(bean.getDate());

		deleteItemAction = new DeleteItemAction(treeViewer);
		editItemAction = new EditItemAction(treeViewer);

		expandAllAction = new Action("Expand All") {
			public void run() {
				treeViewer.expandAll();
			}
		};

		collapseAllAction = new Action("Collapse All") {
			public void run() {
				treeViewer.collapseAll();
			}
		};
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		@SuppressWarnings("unused")
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();

		toolbarManager.add(newItemAction);
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
		menuManager.add(expandAllAction);
		menuManager.add(collapseAllAction);

		MenuManager contextMenuManager = new MenuManager();
		contextMenuManager.add(newItemAction);
		contextMenuManager.add(editItemAction);
		contextMenuManager.add(deleteItemAction);
		Menu contextMenu = contextMenuManager.createContextMenu(tree);
		tree.setMenu(contextMenu);

	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.equals(IMediatorReceiver.class)) {
			return this;
		}
		return super.getAdapter(adapter);
	}

	public TodoListBean getBean() {
		if (bean == null)
			bean = new TodoListBean();
		return bean;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (CalendarComposite.CALENDAR_DATE.equals(evt.getPropertyName())) {
			Date newDate = (Date) evt.getNewValue();
			getBean().setDate(newDate);
			LoadTodoItemJob job = new LoadTodoItemJob();
			job.setDate(newDate);
			job.schedule();
			newItemAction.setDate(newDate);
		}
		if (ITodoFacade.FACADE_UPDATED.equals(evt.getPropertyName())) {
			LoadTodoItemJob job = new LoadTodoItemJob();
			job.setDate(getBean().getDate());
			job.schedule();
		}
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		Mediator.getInstance().addPropertyChangeListener(this);
	}

	@Override
	public void dispose() {
		super.dispose();
		Mediator.getInstance().removePropertyChangeListener(this);
	}

	protected class EditItemAction extends ItemOperationAction {

		public EditItemAction(TreeViewer viewer) {
			super("editItemAction", "Edit Item", viewer);
			setImageDescriptor(ResourceManager.getPluginImageDescriptor(
					Activator.getDefault(), "icons/todo_edit_item.gif"));
			setId("editItemAction");
		}

		@Override
		public void runWithItem(TodoItem item) {
			EditTodoItemJob editJob = new EditTodoItemJob();
			editJob.setTarget(item);
			editJob.setDate(bean.getDate());
			editJob.schedule();
		}
	}
}
