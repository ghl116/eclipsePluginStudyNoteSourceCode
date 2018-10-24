package rcpdev.todo.ui.todolist.views.actions;

import java.util.Date;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

import com.swtdesigner.ResourceManager;

import rcpdev.todo.ui.Activator;
import rcpdev.todo.ui.todolist.wizards.NewItemWizard;

public class NewItemAction extends Action {

	public NewItemAction() {
		super("New Item");
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator
				.getDefault(), "icons/todo_new_item.gif"));
		setId("newItemAction");
	}

	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void run() {
		if (date == null)
			throw new NullPointerException("Date in NewItemAction is null");
		try {
			NewItemWizard wizard = new NewItemWizard();
			wizard.getBean().setStartDate(getDate());
			WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), wizard);
			dialog.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
