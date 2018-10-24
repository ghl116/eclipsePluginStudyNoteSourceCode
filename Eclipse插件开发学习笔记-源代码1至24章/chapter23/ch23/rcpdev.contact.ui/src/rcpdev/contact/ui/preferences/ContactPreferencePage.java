package rcpdev.contact.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import rcpdev.contact.ui.Activator;

import com.swtdesigner.ResourceManager;

public class ContactPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * Create the preference page
	 */
	public ContactPreferencePage() {
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

		final Label contactPluginWrittenLabel = new Label(container, SWT.NONE);
		contactPluginWrittenLabel.setText("Contact Manager plugin written by Harper Jiang");

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
