package rcpdev.contact.ui.views.search;

import java.util.List;
import org.eclipse.jface.action.Action;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import rcpdev.common.ui.databinding.AbstractBean;
import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.ui.jobs.SearchContactJob;
import rcpdev.contact.ui.views.common.ContactLabelProvider;
import rcpdev.contact.ui.views.common.SelectionMonitor;
import rcpdev.contact.ui.views.search.provider.SearchContactContentProvider;
import rcpdev.contact.ui.actions.contact.EditContactAction;

public class SearchContactView extends ViewPart {

	private EditContactAction editContactAction;

	private Table table;

	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	public static final String ID = "rcpdev.contact.ui.views.search.SearchContactView"; //$NON-NLS-1$

	SearchResultBean bean;

	TableViewer tableViewer;

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		final Form searchForm = toolkit.createForm(container);
		final Composite body = searchForm.getBody();
		final GridLayout gridLayout_1 = new GridLayout();
		body.setLayout(gridLayout_1);
		toolkit.paintBordersFor(body);

		final Section searchSection = toolkit.createSection(body,
				Section.TITLE_BAR | Section.EXPANDED);
		searchSection.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false));
		searchSection.setText("Search Criteria");

		final SearchComposite searchComposite = new SearchComposite(
				searchSection, SWT.NONE);
		toolkit.adapt(searchComposite);
		toolkit.paintBordersFor(searchComposite);
		searchSection.setClient(searchComposite);

		final Button searchButton = toolkit.createButton(body, "Search",
				SWT.NONE);
		searchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				SearchContactJob job = new SearchContactJob();
				job.setSearch((SearchBean) searchComposite.getModel());
				job.schedule();
			}
		});
		searchButton.setLayoutData(new GridData(86, SWT.DEFAULT));

		final Section searchResult = toolkit.createSection(body,
				Section.TITLE_BAR | Section.EXPANDED);
		searchResult
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		searchResult.setText("Result");

		tableViewer = new TableViewer(searchResult, SWT.FULL_SELECTION);
		tableViewer.setContentProvider(new SearchContactContentProvider());
		tableViewer.setLabelProvider(new ContactLabelProvider());
		table = tableViewer.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent evt) {
				editContactAction.run();
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		searchResult.setLayout(new FillLayout());
		searchResult.setClient(table);
		toolkit.adapt(table, true, true);

		final TableColumn nameColumn = new TableColumn(table, SWT.NONE);
		nameColumn.setWidth(140);
		nameColumn.setText("Name");

		final TableColumn companyColumn = new TableColumn(table, SWT.NONE);
		companyColumn.setWidth(150);
		companyColumn.setText("Company");
		searchForm.setText("Search Contacts");
		//
		bean = new SearchResultBean();
		tableViewer.setInput(bean);
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions
	 */
	private void createActions() {

		editContactAction = new EditContactAction();
		((EditContactAction) editContactAction).setViewer(tableViewer);
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (AbstractBean.class.equals(adapter)) {
			return bean;
		}
		return null;
	}

	public SearchResultBean getBean() {
		return bean;
	}
}
