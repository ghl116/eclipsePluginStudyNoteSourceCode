package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ToolBarCoolBar {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(300, 100);
		shell.setText("CoolBar Demo");

		Image toolBarImage1 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar1.gif"));
		Image toolBarImage2 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar2.gif"));
		Image toolBarImage3 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar3.gif"));
		Image toolBarImage4 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar4.gif"));

		final CoolBar coolBar = new CoolBar(shell, SWT.NONE);
		coolBar.setBounds(0, 0, 400, 30);

		final CoolItem coolItem1 = new CoolItem(coolBar, SWT.PUSH);
		final ToolBar toolBar1 = new ToolBar(coolBar, SWT.NONE);
		ToolItem item10 = new ToolItem(toolBar1, SWT.NONE);
		item10.setImage(toolBarImage1);
		ToolItem item11 = new ToolItem(toolBar1, SWT.NONE);
		item11.setImage(toolBarImage2);
		coolItem1.setControl(toolBar1);
		toolBar1.pack();
		coolItem1.setSize(coolItem1.computeSize(toolBar1.getSize().x, toolBar1
				.getSize().y));
		
		final CoolItem coolItem2 = new CoolItem(coolBar, SWT.PUSH);
		final ToolBar toolBar2 = new ToolBar(coolBar, SWT.NONE);
		ToolItem item20 = new ToolItem(toolBar2, SWT.NONE);
		item20.setImage(toolBarImage3);
		ToolItem item21 = new ToolItem(toolBar2, SWT.NONE);
		item21.setImage(toolBarImage4);
		coolItem2.setControl(toolBar2);
		toolBar2.pack();
		coolItem2.setSize(coolItem1.computeSize(toolBar2.getSize().x, toolBar2
				.getSize().y));

		final CoolItem newItemCoolItem = new CoolItem(coolBar, SWT.PUSH);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		toolBarImage1.dispose();
		toolBarImage2.dispose();
		toolBarImage3.dispose();
		toolBarImage4.dispose();
		display.dispose();
	}

}
