package rcpdev.common.ui.databinding.demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import rcpdev.common.ui.databinding.BindingManager;
import rcpdev.common.ui.databinding.IBinding;
import rcpdev.common.ui.databinding.TextBinding;

public class DemoShell {

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display, SWT.SHELL_TRIM);
			final BindingManager manager = new BindingManager(new DemoBean());

			shell.setText("SWT Application");
			shell.setSize(300, 200);
			GridLayout layout = new GridLayout();
			layout.numColumns = 2;
			shell.setLayout(layout);

			new Label(shell, SWT.NONE).setText("User ID");
			Text userIdText = new Text(shell, SWT.BORDER);
			userIdText.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));

			new Label(shell, SWT.NONE).setText("User Name");
			Text userNameText = new Text(shell, SWT.BORDER);
			userNameText.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));

			Button button = new Button(shell, SWT.NONE);
			button.setText("Print Content");
			button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					DemoBean bean = (DemoBean)manager.getBean();
					System.out.println(bean.getUserId());
					System.out.println(bean.getUserName());
				}
			});

			IBinding idBinding = new TextBinding("userId", userIdText);
			manager.addBinding(idBinding);
			IBinding nameBinding = new TextBinding("userName", userNameText);
			manager.addBinding(nameBinding);
			manager.loadAll();

			shell.open();
			shell.layout();

			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
