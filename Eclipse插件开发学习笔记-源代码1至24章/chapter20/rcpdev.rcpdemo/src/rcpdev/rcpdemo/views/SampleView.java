package rcpdev.rcpdemo.views;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;

public class SampleView extends ViewPart {

	public static final String ID = "rcpdev.rcpdemo.views.SampleView"; // TODO Needs to be whatever is mentioned in plugin.xml

	private Composite top = null;

	private Label label = null;

	@Override
	public void createPartControl(Composite parent) {
		top = new Composite(parent, SWT.NONE);
		top.setSize(300, 300);
		top.setLayout(new FillLayout());
		label = new Label(top, SWT.NONE);
		label.setBounds(200, 200, 200, 200);
		label.setText("This is a sample view_______");
		Button btn = new Button(top, SWT.None);
		btn.setText("---------");
	}

	@Override
	public void setFocus() {

	}

}
