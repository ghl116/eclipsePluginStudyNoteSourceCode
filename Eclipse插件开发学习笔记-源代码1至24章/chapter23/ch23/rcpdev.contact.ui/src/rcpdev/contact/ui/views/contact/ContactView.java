package rcpdev.contact.ui.views.contact;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import rcpdev.common.core.mediator.Mediator;
import rcpdev.contact.core.persistence.IContactFacade;
import rcpdev.contact.ui.actions.contact.CreateContactAction;
import rcpdev.contact.ui.actions.contact.DeleteContactAction;
import rcpdev.contact.ui.actions.contact.EditContactAction;
import rcpdev.contact.ui.jobs.LoadContactJob;
import rcpdev.contact.ui.views.common.ContactComparator;
import rcpdev.contact.ui.views.common.ContactComparator2;
import rcpdev.contact.ui.views.common.ContactLabelProvider;
import rcpdev.contact.ui.views.contact.provider.ContactContentProvider;
import rcpdev.contact.ui.views.lang.Messages;

public class ContactView extends ViewPart implements PropertyChangeListener {

	private EditContactAction editContactAction;

	private DeleteContactAction deleteContactAction;

	private CreateContactAction createContactAction;

	private Action multiColumnAction;

	private Action singleColumnAction;

	private Table table;

	private TableViewer tableViewer;

	public static final String ID = "rcpdev.contact.ui.views.contactView"; //$NON-NLS-1$

	ContactViewBean model;

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		final ScrolledForm form = toolkit.createScrolledForm(parent);
		final Composite body = form.getBody();
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		body.setLayout(gridLayout);

		tableViewer = new TableViewer(body, SWT.FULL_SELECTION);

		tableViewer.setLabelProvider(new ContactLabelProvider());
		tableViewer.setContentProvider(new ContactContentProvider());

		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				editContactAction.run();
			}
		});
		toolkit.adapt(table, true, true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final TableColumn nameColumn = new TableColumn(table, SWT.NONE);
		nameColumn.setWidth(150);
		nameColumn.setText(Messages.getString("ContactView.tableColumn0")); //$NON-NLS-1$

		final TableColumn companyColumn = new TableColumn(table, SWT.NONE);
		companyColumn.setWidth(150);
		companyColumn.setText(Messages.getString("ContactView.tableColumn1")); //$NON-NLS-1$

		tableViewer.setComparator(new ContactComparator2(tableViewer));

		//
		model = new ContactViewBean();
		tableViewer.setInput(model);

		createActions();
		initializeToolBar();
		initializeMenu();

		new LoadContactJob().schedule();
	}

	/**
	 * Create the actions
	 */
	private void createActions() {

		singleColumnAction = new Action(Messages
				.getString("ContactView.action_singleColumn"), //$NON-NLS-1$
				IAction.AS_RADIO_BUTTON) {
			public void run() {
				if (tableViewer.getComparator() instanceof ContactComparator2)
					return;
				((ContactComparator) tableViewer.getComparator()).dispose();
				tableViewer.setComparator(new ContactComparator2(tableViewer));
			}
		};
		singleColumnAction.setChecked(true);

		multiColumnAction = new Action(
				Messages.getString("ContactView.action_multiColumn"), IAction.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				if (tableViewer.getComparator() instanceof ContactComparator)
					return;
				((ContactComparator2) tableViewer.getComparator()).dispose();
				tableViewer.setComparator(new ContactComparator(tableViewer));
			}
		};

		createContactAction = new CreateContactAction();

		deleteContactAction = new DeleteContactAction();
		((DeleteContactAction) deleteContactAction).setViewer(tableViewer);

		editContactAction = new EditContactAction();
		((EditContactAction) editContactAction).setViewer(tableViewer);

		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		@SuppressWarnings("unused")
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();

		toolbarManager.add(createContactAction);

		toolbarManager.add(deleteContactAction);
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();

		final MenuManager sortMenuManager = new MenuManager(Messages
				.getString("ContactView.menu_sortType")); //$NON-NLS-1$
		menuManager.add(sortMenuManager);

		sortMenuManager.add(singleColumnAction);

		sortMenuManager.add(multiColumnAction);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (PropertyChangeListener.class.equals(adapter))
			return model;
		return super.getAdapter(adapter);
	}

	public ContactViewBean getModel() {
		return model;
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

	public void propertyChange(PropertyChangeEvent evt) {
		if (IContactFacade.ADD_CONTACT.equals(evt.getPropertyName())
				|| IContactFacade.UPDATE_CONTACT.equals(evt.getPropertyName())
				|| IContactFacade.REMOVE_CONTACT.equals(evt.getPropertyName())) {
			new LoadContactJob().schedule();
		}
	}

}
