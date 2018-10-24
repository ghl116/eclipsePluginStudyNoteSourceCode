package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class UsingMenu {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(200, 200);
		shell.setText("Menu Demo");

		Image menu1 = new Image(Display.getDefault(), UsingMenu.class
				.getResourceAsStream("menu1.gif"));
		Image menu2 = new Image(Display.getDefault(), UsingMenu.class
				.getResourceAsStream("menu2.gif"));
		
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);

		MenuItem fileMenuItem = new MenuItem(bar, SWT.CASCADE);
		fileMenuItem.setImage(menu1);
		fileMenuItem.setText("File");

		MenuItem otherMenuItem = new MenuItem(bar, SWT.PUSH);
		otherMenuItem.setText("Other");

		final Menu fileMenu = new Menu(fileMenuItem);
		fileMenuItem.setMenu(fileMenu);

		final MenuItem openMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
		openMenuItem.setText("Open");
		openMenuItem.setImage(menu1);
		
		final Menu openMenu = new Menu(openMenuItem);
		openMenuItem.setMenu(openMenu);
		
		final MenuItem openDirMenuItem = new MenuItem(openMenu,SWT.PUSH);
		openDirMenuItem.setText("Directory");
		
		final MenuItem openFileMenuItem = new MenuItem(openMenu,SWT.PUSH);
		openFileMenuItem.setText("File");
		

		final MenuItem exitMenuItem = new MenuItem(fileMenu, SWT.NONE);
		exitMenuItem.setText("Exit");
		exitMenuItem.setImage(menu2);

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		menu1.dispose();
		menu2.dispose();
		display.dispose();
	}

}
