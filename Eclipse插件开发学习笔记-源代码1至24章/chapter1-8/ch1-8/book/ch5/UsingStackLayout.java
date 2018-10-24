package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class UsingStackLayout {
	public static void main(String[] args) {
	 	Display display = new Display();
	 	Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
	 	
	 	final Composite parent = new Composite(shell, SWT.NONE);
	 	parent.setLayoutData(new GridData(GridData.FILL_BOTH));
	 	final StackLayout layout = new StackLayout();
	 	parent.setLayout(layout);
	 	final Button[] bArray = new Button[10];
	 	for (int i = 0; i < 10; i++) {
	 		bArray[i] = new Button(parent, SWT.PUSH);
	 		bArray[i].setText("Button "+i);
	 	}
	 	layout.topControl = bArray[0];
	 	
	 	Button b = new Button(shell, SWT.PUSH);
	 	b.setText("Show Next Button");
	 	final int[] index = new int[1];
	 	b.addListener(SWT.Selection, new Listener(){
	 		public void handleEvent(Event e) {
	 			index[0] = (index[0] + 1) % 10;
	 			layout.topControl = bArray[index[0]];
	 			parent.layout();
			}
	 	});
	 	
	 	shell.open();
	 	while (shell != null && !shell.isDisposed()) {
	 		if (!display.readAndDispatch())
	 			display.sleep(); 
	 	} 	
	 }

}
