package rcpdev.contact.ui.editors.contact;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.EditorPart;

import rcpdev.common.core.mediator.Mediator;
import rcpdev.contact.core.model.contact.Contact;
import rcpdev.contact.core.persistence.IContactFacade;
import rcpdev.contact.ui.common.ModelComposite;
import rcpdev.contact.ui.editors.lang.Messages;
import rcpdev.contact.ui.jobs.SaveContactJob;

public class ContactEditor extends EditorPart implements PropertyChangeListener {

	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	public static final String ID = "rcpdev.contact.ui.editors.ContactEditor"; //$NON-NLS-1$

	private ModelComposite generalInfoComposite;

	private Contact bean;

	private IEditorPart thisEditor;

	/**
	 * Create contents of the editor part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		final ScrolledForm form = toolkit.createScrolledForm(container);
		final Composite body = form.getBody();
		body.setLayout(new GridLayout());
		toolkit.paintBordersFor(body);

		final CTabFolder tabFolder = new CTabFolder(body, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		toolkit.adapt(tabFolder, true, true);

		final CTabItem generalInfoTabItem = new CTabItem(tabFolder, SWT.NONE);
		generalInfoTabItem.setText(Messages
				.getString("ContactDetailEditor.tab1")); //$NON-NLS-1$

		generalInfoComposite = new GeneralInfoComposite(tabFolder, SWT.NONE);
		toolkit.adapt(generalInfoComposite);
		toolkit.paintBordersFor(generalInfoComposite);
		generalInfoTabItem.setControl(generalInfoComposite);

		final CTabItem detailTabItem = new CTabItem(tabFolder, SWT.NONE);
		detailTabItem.setText(Messages.getString("ContactDetailEditor.tab2")); //$NON-NLS-1$

		final Composite detailComposite = toolkit.createComposite(tabFolder,
				SWT.NONE);
		toolkit.paintBordersFor(detailComposite);
		detailTabItem.setControl(detailComposite);
		tabFolder.setSelection(generalInfoTabItem);

		form.setText(Messages.getString("ContactDetailEditor.title")); //$NON-NLS-1$
		//
		initManager();
	}

	protected void initManager() {
		ContactEditorInput input = (ContactEditorInput) getEditorInput();
		Contact person = input.getPerson();
		bean = person;
		bean.addPropertyChangeListener(this);
		generalInfoComposite.setModel(bean);
	}
	
	protected void disposeManager() {
		bean.removePropertyChangeListener(this);
		generalInfoComposite.getManager().setBean(null);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(input.getName());
		Mediator.getInstance().addPropertyChangeListener(this);
		thisEditor = this;
	}

	public void dispose() {
		super.dispose();
		Mediator.getInstance().removePropertyChangeListener(this);
		disposeManager();
		thisEditor = null;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			ContactEditorInput input = (ContactEditorInput) getEditorInput();
			SaveContactJob job = new SaveContactJob();
			job.setContact(bean);
			job.setAdd(input.isAdd());
			job.schedule();
			try {
				job.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			isDirty = false;
			firePropertyChange(PROP_DIRTY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void setFocus() {

	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public boolean isDirty() {
		return isDirty;
	}

	private boolean isDirty = false;

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource().equals(bean)) {
			boolean oldDirty = isDirty;
			isDirty = true;
			if (!oldDirty)
				firePropertyChange(PROP_DIRTY);
			return;
		}
		// if (evt.getSource().equals(CacheManager.getCache())) {
		if (IContactFacade.ADD_CONTACT.equals(evt.getPropertyName())
				|| IContactFacade.UPDATE_CONTACT.equals(evt.getPropertyName())) {
			final Contact contact = (Contact) evt.getNewValue();
			final Contact myContact = ((ContactEditorInput) getEditorInput())
					.getPerson();
			if (!contact.equals(myContact))
				return;
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run() {
					setInput(new ContactEditorInput(contact));
					initManager();
					setPartName(getEditorInput().getName());
				}
			});
		}
		if (IContactFacade.REMOVE_CONTACT.equals(evt.getPropertyName())) {
			final Contact contact = (Contact) evt.getOldValue();
			final Contact myContact = ((ContactEditorInput) getEditorInput())
					.getPerson();
			if (!contact.equals(myContact))
				return;
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run() {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().closeEditor(thisEditor, false);
				}
			});

		}
		// }
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (PropertyChangeListener.class.equals(adapter))
			return this;
		return super.getAdapter(adapter);
	}
}
