package rcpdev.todo.ui.calendar;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import rcpdev.common.core.mediator.IMediatorSender;
import rcpdev.common.core.mediator.Mediator;
import rcpdev.common.core.mediator.MediatorPCSupport;
import rcpdev.common.ui.javabeans.CalendarComposite;

public class CalendarView extends ViewPart implements PropertyChangeListener {

	public static final String ID = "rcpdev.todo.ui.CalendarView"; //$NON-NLS-1$

	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	private Form form;

	private CalendarComposite calendar;

	private Color bgColor;

	final CalendarView view = this;

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {

		bgColor = new Color(Display.getCurrent(), 239, 245, 248);

		form = toolkit.createForm(parent);
		Composite body = form.getBody();
		final GridLayout gridLayout = new GridLayout();
		body.setLayout(gridLayout);
		body.setBackground(bgColor);
		body.setBackgroundMode(SWT.INHERIT_FORCE);
		calendar = new CalendarComposite(body, SWT.NONE);
		calendar
				.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		calendar.addPropertyChangeListener(this);

		//
		createActions();
		initializeToolBar();
		initializeMenu();

	}

	// /**
	// * Add bold mark on calendar
	// *
	// */
	// protected void refreshCalendar() {
	// List<Date> dates = CacheManager.getCache().getBirthdayInMonth(
	// calendar.getDate());
	// for (int i = 0; i < dates.size(); i++)
	// calendar.setLabelBold(dates.get(i), true);
	// }

	/**
	 * Create the actions
	 */
	private void createActions() {
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		@SuppressWarnings("unused")
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		@SuppressWarnings("unused")
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();

	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	public void propertyChange(PropertyChangeEvent evt) {
		getDelegate().firePropertyChange(evt);
	}

	private MediatorPCSupport delegate;

	public MediatorPCSupport getDelegate() {
		if (delegate == null)
			delegate = new MediatorPCSupport(this);
		return delegate;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.equals(IMediatorSender.class)) {
			return getDelegate();
		}
		return super.getAdapter(adapter);
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		getDelegate().addPropertyChangeListener(Mediator.getInstance());
	}

	@Override
	public void dispose() {
		super.dispose();
		bgColor.dispose();
		getDelegate().removePropertyChangeListener(Mediator.getInstance());
	}
}
