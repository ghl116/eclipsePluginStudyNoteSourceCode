package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ShellBackgroundMode extends Shell {




	private Label label;
	private Text text;
	private Group inheriteModeGroup;

	private Button inherite_forceButton;

	private Button inherite_defaultButton;

	private Button inherite_noneButton;

	private Composite controlComposite;

	private Composite displayComposite;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ShellBackgroundMode shell = new ShellBackgroundMode(display, SWT.SHELL_TRIM);
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

	/**
	 * Create the shell
	 * 
	 * @param display
	 * @param style
	 */
	public ShellBackgroundMode(Display display, int style) {
		super(display, style);
		createContents();
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		setText("Mode Demo");
		setSize(178, 206);
		// setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
		// setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));
		final GridLayout gridLayout = new GridLayout();
		setLayout(gridLayout);

		displayComposite = new Composite(this, SWT.NONE);
		displayComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		displayComposite.setLayoutData(gridData);

		text = new Text(displayComposite, SWT.BORDER);
		text.setBounds(10, 10, 115, 20);
		

		label = new Label(displayComposite, SWT.NONE);
		label.setText("Label");
		label.setBounds(10, 36, 115, 20);
		
		controlComposite = new Composite(this, SWT.NONE);
		final GridLayout gridLayout_1 = new GridLayout();
		controlComposite.setLayout(gridLayout_1);
		controlComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		inheriteModeGroup = new Group(controlComposite, SWT.NONE);
		inheriteModeGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false));
		inheriteModeGroup.setText("Inherite Mode");
		inheriteModeGroup.setLayout(new GridLayout());

		inherite_noneButton = new Button(inheriteModeGroup, SWT.RADIO);
		inherite_noneButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				displayComposite.setBackgroundMode(SWT.INHERIT_NONE);
			}
		});
		inherite_noneButton.setSelection(true);
		inherite_noneButton.setText("INHERITE_NONE");

		inherite_defaultButton = new Button(inheriteModeGroup, SWT.RADIO);
		inherite_defaultButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				displayComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
			}
		});
		inherite_defaultButton.setText("INHERITE_DEFAULT");

		inherite_forceButton = new Button(inheriteModeGroup, SWT.RADIO);
		inherite_forceButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				displayComposite.setBackgroundMode(SWT.INHERIT_FORCE);
			}
		});
		inherite_forceButton.setText("INHERITE_FORCE");
		//
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
