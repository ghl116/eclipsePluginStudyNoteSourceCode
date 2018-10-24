package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class UsingToolbar {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(273, 150);

		final ToolBar horToolBar = new ToolBar(shell, SWT.HORIZONTAL);
		horToolBar.setBounds(0, 0, 265,50);

		Image toolBarImage1 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar1.gif"));
		Image toolBarImage2 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar2.gif"));
		Image toolBarImage3 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar3.gif"));
		Image toolBarImage4 = new Image(display, UsingToolbar.class
				.getResourceAsStream("toolbar4.gif"));

		final ToolItem toolItem1 = new ToolItem(horToolBar, SWT.NONE);
		toolItem1.setImage(toolBarImage1);
		toolItem1.setToolTipText("ÌבÊ¾");
		toolItem1.setText("Item 1");

		final ToolItem toolItem2 = new ToolItem(horToolBar, SWT.CHECK);
		toolItem2.setImage(toolBarImage2);
		toolItem2.setText("Item 2");

		final ToolItem toolItem3 = new ToolItem(horToolBar, SWT.RADIO);
		toolItem3.setImage(toolBarImage3);
		toolItem3.setText("Item 3");

		final ToolItem toolItem4 = new ToolItem(horToolBar, SWT.RADIO);
		toolItem4.setImage(toolBarImage4);
		toolItem4.setText("Item 4");
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
