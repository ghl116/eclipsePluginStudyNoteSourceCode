package book.ch2;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ContentProposal {
	public static void main(String[] args) throws Exception{
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(400,200);
//		shell.setLayout(new GridLayout());

		Text myTextControl = new Text(shell,SWT.BORDER);
		myTextControl.setBounds(10,10,125,20);
		
		char[] autoActivationCharacters = new char[] { '#', '(' };
		KeyStroke keyStroke = KeyStroke.getInstance("Ctrl+/");
//		 assume that myTextControl has already been created in some way
		ContentProposalAdapter adapter = new ContentProposalAdapter(
			myTextControl, new TextContentAdapter(), 
			new SimpleContentProposalProvider(new String [] {"ProposalOne", "ProposalTwo", "ProposalThree"}),
			keyStroke, autoActivationCharacters);


		shell.open();
		shell.layout();
//		 shell.pack();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
