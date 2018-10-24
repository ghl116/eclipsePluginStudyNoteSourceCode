package rcpdev.contact.ui.editors.contact.category;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class CategoryDialog extends Dialog {
	private CategoryComposite categoryComposite;

	/**
	 * Create the dialog
	 * 
	 * @param parentShell
	 */
	public CategoryDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout());

		categoryComposite = new CategoryComposite(container, SWT.NONE);
		categoryComposite.setLayout(new GridLayout());
		//
		return container;
	}

	public CategoryComposite getCategoryComposite() {
		return categoryComposite;
	}

	public void setCategoryComposite(CategoryComposite categoryComposite) {
		this.categoryComposite = categoryComposite;
	}

	/**
	 * Create contents of the button bar
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(240, 300);
	}

	public void setInput(Object input) {

	}
}
