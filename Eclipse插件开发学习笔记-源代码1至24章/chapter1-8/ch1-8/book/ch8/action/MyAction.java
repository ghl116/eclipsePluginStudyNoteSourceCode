package book.ch8.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class MyAction extends Action {

	public static String ID = "MyAction";

	public MyAction() {
		super();
		setId(ID);
		setText("My Action");
		setToolTipText("This is My Action");
		setImageDescriptor(ImageDescriptor.createFromFile(this.getClass(),
				"toolbar1.gif"));
	}

	public void run() {
		System.out.println("My Action Run");
	}
}
