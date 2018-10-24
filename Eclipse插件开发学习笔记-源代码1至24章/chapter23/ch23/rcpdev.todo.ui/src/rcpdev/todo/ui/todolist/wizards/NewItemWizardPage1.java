package rcpdev.todo.ui.todolist.wizards;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import rcpdev.common.core.utils.StringUtils;
import rcpdev.todo.ui.lang.Messages;
import rcpdev.todo.ui.todolist.common.beans.todoitem.TodoItemBasicInfoComposite;
import rcpdev.todo.ui.todolist.common.beans.todoitem.TodoItemBean;

public class NewItemWizardPage1 extends WizardPage implements
		PropertyChangeListener {

	public static final String PAGE_NAME = "itemPage1"; //$NON-NLS-1$

	private TodoItemBean bean;

	private TodoItemBasicInfoComposite content;

	/**
	 * Create the wizard
	 */
	public NewItemWizardPage1(TodoItemBean bean) {
		super(PAGE_NAME);
		setTitle(Messages.getString("TodoItemBasicInfoComposite.title")); //$NON-NLS-1$
		setDescription(Messages.getString("TodoItemBasicInfoComposite.desc")); //$NON-NLS-1$
		this.bean = bean;
		bean.addPropertyChangeListener(this);
	}

	/**
	 * Create contents of the wizard
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		content = new TodoItemBasicInfoComposite(parent, SWT.NONE);
		setControl(content);
		content.setBean(bean);
	}

	@Override
	public boolean isPageComplete() {
		return !StringUtils.isEmpty(bean.getSubject());
	}

	@Override
	public IWizardPage getNextPage() {
		if (bean.getType() == TodoItemBean.TYPE_SINGLE)
			return null;
		return super.getNextPage();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (TodoItemBean.ATTR_SUBJECT.equals(evt.getPropertyName())) {
			if (!StringUtils.isEmpty((String) evt.getOldValue())
					&& StringUtils.isEmpty((String) evt.getNewValue()))
				setErrorMessage(Messages
						.getString("NewItemWizardPage1.error_empty_subject")); //$NON-NLS-1$
			else
				setErrorMessage(null);
		}
		if (TodoItemBean.ATTR_START_TIME.equals(evt.getPropertyName())
				|| TodoItemBean.ATTR_STOP_TIME.equals(evt.getPropertyName())) {
			if (bean.getStartTime() > bean.getStopTime())
				setErrorMessage(Messages.getString("NewItemWizardPage1.error_start_late_than_stop")); //$NON-NLS-1$
			else
				setErrorMessage(null);
		}
		getWizard().getContainer().updateButtons();
	}

}
