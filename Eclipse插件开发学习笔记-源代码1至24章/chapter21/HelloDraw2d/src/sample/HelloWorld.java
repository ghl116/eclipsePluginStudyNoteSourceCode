package sample;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class HelloWorld {

	public static void main(String args[]){

		Shell shell = new Shell();  
		shell.open();
		shell.setText("Draw2d Hello World");
		LightweightSystem lws = new LightweightSystem(shell);   
		IFigure label = new Label("Hello World");
		lws.setContents(label);

		Display display = Display.getDefault();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ())
				display.sleep ();
		}
	}
}
