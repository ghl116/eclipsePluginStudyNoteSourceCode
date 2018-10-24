package rcpdev.todo.persistence.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import com.swtdesigner.ResourceManager;
import rcpdev.todo.persistence.Activator;

public class TodoPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * Create the preference page
	 */
	public TodoPreferencePage() {
		super();
	}

	/**
	 * Create contents of the preference page
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout());

		final Label imageLabel = new Label(container, SWT.NONE);
		imageLabel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "images/logo.JPG"));
		imageLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Label todoPluginWrittenLabel = new Label(container, SWT.NONE);
		todoPluginWrittenLabel.setText("To-Do plugin written by Harper Jiang");

		final Label contactMeaLink = new Label(container, SWT.NONE);
		contactMeaLink.setText("Contact me : harper.jiang@gmail.com");
		//
		return container;
	}

	/**
	 * Initialize the preference page
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}

}
