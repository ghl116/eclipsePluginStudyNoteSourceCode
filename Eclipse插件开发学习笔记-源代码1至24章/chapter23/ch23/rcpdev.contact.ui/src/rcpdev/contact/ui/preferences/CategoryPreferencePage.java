package rcpdev.contact.ui.preferences;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import rcpdev.contact.ui.jobs.SaveCategoryJob;

public class CategoryPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private List list;

	private CategoryBean bean;

	public CategoryPreferencePage() {

	}

	public CategoryPreferencePage(String title) {
		super(title);
	}

	public CategoryPreferencePage(String title, ImageDescriptor image) {
		super(title, image);

	}

	@Override
	protected Control createContents(Composite parent) {
		// parent.setLayout(new FillLayout());
		Composite container = new Composite(parent, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);

		bean = new CategoryBean();

		final ListViewer listViewer = new ListViewer(container, SWT.BORDER);
		list = listViewer.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		listViewer.setContentProvider(new CategoryContentProvider());
		listViewer.setLabelProvider(new CategoryLabelProvider());
		listViewer.setInput(bean);

		final Composite buttonComposite = new Composite(container, SWT.NONE);
		buttonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false,
				false));
		buttonComposite.setLayout(new GridLayout());

		final Button addButton = new Button(buttonComposite, SWT.NONE);
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				InputDialog dialog = new InputDialog(getShell());
				if (dialog.open() == Dialog.OK) {
					String value = dialog.getBean().getValue();
					bean.addCategory(value);
				}
			}
		});
		addButton
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		addButton.setText("Add");

		final Button removeButton = new Button(buttonComposite, SWT.NONE);
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				String[] selected = list.getSelection();
				for (int i = 0; i < selected.length; i++)
					bean.removeCategory(selected[i]);
			}
		});
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		removeButton.setText("Remove");

		final Button editButton = new Button(buttonComposite, SWT.NONE);
		editButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				InputDialog dialog = new InputDialog(getShell());
				dialog.create();
				String[] selected = list.getSelection();
				if (selected.length == 0)
					return;
				String oldVal = selected[0];
				dialog.getBean().setValue(oldVal);
				if (dialog.open() == Dialog.OK) {
					String value = dialog.getBean().getValue();
					bean.updateCategory(oldVal, value);
				}
			}
		});
		editButton
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		editButton.setText("Edit");

		return container;
	}

	public void init(IWorkbench workbench) {

	}

	@Override
	public boolean performOk() {
		SaveCategoryJob job = new SaveCategoryJob();
		job.setNewCategories(bean.getCategories());
		job.schedule();
		try {
			job.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return super.performOk();
	}
}
