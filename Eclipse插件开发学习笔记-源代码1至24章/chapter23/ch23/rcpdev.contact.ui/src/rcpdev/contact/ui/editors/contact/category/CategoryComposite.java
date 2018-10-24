package rcpdev.contact.ui.editors.contact.category;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import rcpdev.common.core.utils.StringUtils;
import rcpdev.contact.ui.editors.contact.category.CategoryBean.CategoryItem;

public class CategoryComposite extends Composite {

	private Text text;

	private Table table;

	private CategoryBean model;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public CategoryComposite(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		setLayout(gridLayout);

		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Button button = new Button(this, SWT.NONE);
		button.setText("Add To List");

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (!StringUtils.isEmpty(text.getText())) {
					CategoryItem item = new CategoryItem(text.getText().trim());
					item.setSelected(true);
					model.addCategory(item);
				}
			}
		});

		final CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer
				.newCheckList(this, SWT.NONE);
		checkboxTableViewer.setContentProvider(new CategoryContentProvider());
		checkboxTableViewer.setLabelProvider(new CategoryTableLabelProvider());
		model = new CategoryBean();
		checkboxTableViewer.setInput(model);
		table = checkboxTableViewer.getTable();
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalSpan = 2;
		table.setLayoutData(data);
		//
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public CategoryBean getModel() {
		return model;
	}

	public void setModel(CategoryBean model) {
		this.model = model;
	}

}
