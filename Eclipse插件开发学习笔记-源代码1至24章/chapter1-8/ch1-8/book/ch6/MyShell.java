package book.ch6;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MyShell {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Label nameLabel = null;
	private Text nameText = null;
	private Label addressLabel = null;
	private Text addressText = null;
	private Button button = null;
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 2;
		sShell = new Shell();
		sShell.setText("Shell");
		sShell.setLayout(gridLayout1);
		sShell.setSize(new Point(263, 191));
		nameLabel = new Label(sShell, SWT.NONE);
		nameLabel.setText("Name");
		nameText = new Text(sShell, SWT.BORDER);
		addressLabel = new Label(sShell, SWT.NONE);
		addressLabel.setText("Address");
		addressText = new Text(sShell, SWT.BORDER);
		Label filler = new Label(sShell, SWT.NONE);
		button = new Button(sShell, SWT.NONE);
		button.setText("button");
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				System.out.println("widgetSelected()"); // TODO Auto-generated Event stub widgetSelected()
			}
		});
	}
}
