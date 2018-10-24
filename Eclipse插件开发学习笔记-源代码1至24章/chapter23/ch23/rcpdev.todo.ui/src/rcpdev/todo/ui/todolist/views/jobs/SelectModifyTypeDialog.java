package rcpdev.todo.ui.todolist.views.jobs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SelectModifyTypeDialog extends Dialog {
	public static final int EDIT_NONE = 0;

	public static final int EDIT_ITEM = 1;

	public static final int EDIT_SERIES = 2;

	private int editType;

	/**
	 * Create the dialog
	 * 
	 * @param parentShell
	 */
	public SelectModifyTypeDialog(Shell parentShell) {
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

		final Label promptLabel = new Label(container, SWT.NONE);
		promptLabel
				.setText("You are about to modify an item in a series,\n please choose the action you want to perform");

		final Button editThisOccurenceButton = new Button(container, SWT.RADIO);
		editThisOccurenceButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				editType = EDIT_ITEM;
			}
		});
		editThisOccurenceButton.setLayoutData(new GridData());
		editThisOccurenceButton.setText("Modify this occurence");

		final Button editTheSeriesButton = new Button(container, SWT.RADIO);
		editTheSeriesButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				editType = EDIT_SERIES;
			}
		});
		editTheSeriesButton.setLayoutData(new GridData());
		editTheSeriesButton.setText("Modify the series");

		//
		return container;
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
		return new Point(400, 300);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Select Edit Type");
	}

	public int getEditType() {
		return editType;
	}

	public void setEditType(int editType) {
		this.editType = editType;
	}

}
