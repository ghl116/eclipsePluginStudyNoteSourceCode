package rcpdev.todo.ui.todolist.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import rcpdev.todo.ui.lang.Messages;
import rcpdev.todo.ui.todolist.common.beans.todoitem.TodoItemBean;
import rcpdev.todo.ui.todolist.common.beans.todoitem.TodoItemSeriesInfoComposite;

public class NewItemWizardPage2 extends WizardPage {

	public static final String PAGE_NAME = "itemPage2"; //$NON-NLS-1$

	private TodoItemBean bean;

	private TodoItemSeriesInfoComposite content;

	/**
	 * Create the wizard
	 */
	public NewItemWizardPage2(TodoItemBean bean) {
		super(PAGE_NAME);
		setTitle(Messages.getString("TodoItemSeriesInfoComposite.title")); //$NON-NLS-1$
		setDescription(Messages.getString("TodoItemSeriesInfoComposite.desc")); //$NON-NLS-1$
		this.bean = bean;
	}

	/**
	 * Create contents of the wizard
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		content = new TodoItemSeriesInfoComposite(parent, SWT.NONE);
		setControl(content);
		content.setBean(bean);
	}
}
