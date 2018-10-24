package book.ch6;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class AControl extends Composite {

	private Text text = null;
	private Label label = null;
	private Button checkBox = null;
	public AControl(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		text = new Text(this, SWT.BORDER);
		text.setText("This");
		label = new Label(this, SWT.NONE);
		label.setText("is");
		checkBox = new Button(this, SWT.CHECK);
		checkBox.setText("My Control");
		this.setLayout(gridLayout);
		setSize(new Point(189, 53));
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
