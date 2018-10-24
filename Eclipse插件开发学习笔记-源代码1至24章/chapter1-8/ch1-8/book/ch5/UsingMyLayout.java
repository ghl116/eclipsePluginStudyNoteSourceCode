package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class UsingMyLayout {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setText("My Layout");
		MyLayout layout = new MyLayout();
		layout.margin = 2;
		layout.spacing = 10;
		shell.setLayout(layout);
//		shell.setSize(200,200);
		
		Label label = new Label(shell,SWT.BORDER);
		label.setText("This is a Label");
		MyData labelData = new MyData();
		labelData.ratio = 20;
		label.setLayoutData(labelData);
		
		Text text = new Text(shell,SWT.BORDER);
		text.setText("This is a text");
		MyData textData = new MyData();
		textData.ratio = 30;
		text.setLayoutData(textData);
		
		Button button = new Button(shell,SWT.NONE);
		button.setText("This is a button");
		MyData buttonData = new MyData();
		buttonData.ratio = 50;
		button.setLayoutData(buttonData);
		
		
		shell.pack();
		shell.open();
		shell.layout();
		
		while(!shell.isDisposed())
			if(!display.readAndDispatch())
				display.sleep();
		display.dispose();		
	}
}
