package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class UsingMessageBox_2 {

	public static void main(String[] args) {
		Display display = Display.getDefault();

		final Shell messageboxDemoShell = new Shell(display);
		messageboxDemoShell.setText("MessageBox Demo");
		messageboxDemoShell.setSize(224, 79);
		messageboxDemoShell.open();

		Button showTheMessageButton = new Button(messageboxDemoShell, SWT.PUSH);
		showTheMessageButton.setText("Show the Message Box");
		showTheMessageButton.setBounds(10, 10, 196, 24);
		showTheMessageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox mb = new MessageBox(messageboxDemoShell,
						SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
				mb.setText("Choose");
				mb.setMessage("Which Button will you choose?");

				MessageBox resBox = new MessageBox(messageboxDemoShell,
						SWT.ICON_INFORMATION | SWT.OK);
				resBox.setText("The result");
				int result = mb.open();
				if (SWT.OK == result) {// 如果用户点击的是OK
					resBox.setMessage("You have chosen OK button!");
				} else if(SWT.CANCEL == result){// 用户点击了Cancel或窗口的关闭按钮
					resBox.setMessage("You have chosen Cancel Button!");
				} 
				resBox.open();
			}
		});

		while (!messageboxDemoShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}
}
