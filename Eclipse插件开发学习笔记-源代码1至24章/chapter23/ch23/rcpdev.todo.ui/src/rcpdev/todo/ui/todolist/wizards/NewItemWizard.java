package rcpdev.todo.ui.todolist.wizards;

import org.eclipse.jface.wizard.Wizard;

import rcpdev.common.core.utils.StringUtils;
import rcpdev.todo.ui.todolist.common.beans.todoitem.TodoItemBean;
import rcpdev.todo.ui.todolist.views.jobs.CreateTodoItemJob;

public class NewItemWizard extends Wizard {

	private TodoItemBean bean;

	public TodoItemBean getBean() {
		if (bean == null)
			bean = new TodoItemBean();
		return bean;
	}

	@Override
	public void addPages() {

		addPage(new NewItemWizardPage1(getBean()));
		addPage(new NewItemWizardPage2(getBean()));
	}

	@Override
	public boolean canFinish() {
		return (bean.getType() == TodoItemBean.TYPE_SINGLE && !StringUtils
				.isEmpty((String) bean.getSubject()))
				|| NewItemWizardPage2.PAGE_NAME.equals(getContainer()
						.getCurrentPage().getName());
	}

	@Override
	public boolean performFinish() {
		CreateTodoItemJob createJob = new CreateTodoItemJob();
		createJob.setItem(bean.extractItem());
		createJob.schedule();
		try {
			createJob.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
	}
}
