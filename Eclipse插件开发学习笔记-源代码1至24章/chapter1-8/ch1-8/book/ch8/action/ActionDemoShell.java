package book.ch8.action;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

public class ActionDemoShell extends Shell {

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		
			Display display = Display.getDefault();
			Shell shell = new Shell(display, SWT.SHELL_TRIM);
			shell.setSize(300,300);
			shell.setText("Action Demo");
			
			ToolBar toolBar = new ToolBar(shell,SWT.NONE);
			toolBar.setBounds(0,0,300,50);
			
			Menu menuBar = new Menu(shell,SWT.BAR);
			shell.setMenuBar(menuBar);
			
			MyAction action = new MyAction();
			
			ToolBarManager tmgr = new ToolBarManager(toolBar);
			tmgr.add(action);
			tmgr.update(true);
			
			MenuManager mmgr = new MenuManager("Demo","DemoMenu");
			mmgr.fill(menuBar, 0);
			mmgr.add(action);
			mmgr.update();
			
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
			display.dispose();
	}

}
