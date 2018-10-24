package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class UsingGridLayout {

	public static void main(String[] args) {
		Display display = Display.getDefault();

		Shell shell = new Shell();
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		shell.setLayout(gridLayout);
		shell.setText("Hello SWT");
		shell.setSize(200, 200);
		shell.open();

		final Label userNameLabel = new Label(shell, SWT.NONE);
		userNameLabel.setText("User Name:");

		final Text userNameText = new Text(shell, SWT.BORDER);
		userNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		final Label addressLabel = new Label(shell, SWT.NONE);
		addressLabel.setText("Address:");

		final Text addressText = new Text(shell, SWT.BORDER);
		addressText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Button okButton = new Button(shell, SWT.NONE);
		final GridData gridData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1);
		gridData.widthHint = 80;
		gridData.heightHint = 25;
		gridData.exclude = true;
		okButton.setLayoutData(gridData);
		okButton.setText("OK");
		new Label(shell, SWT.NONE);
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}
