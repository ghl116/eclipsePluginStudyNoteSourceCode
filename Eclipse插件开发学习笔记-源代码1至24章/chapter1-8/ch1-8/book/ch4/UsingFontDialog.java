package book.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;

public class UsingFontDialog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();

		final Shell shell = new Shell(display);
		shell.setSize(200, 100);
		shell.open();

		Button button = new Button(shell, SWT.PUSH);
		button.setBounds(10, 10, 100, 20);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FontDialog fd = new FontDialog(shell);
				FontData data = fd.open();
				
				RGB fontColor = fd.getRGB();
				if (data != null) {
					Font font = new Font(Display.getDefault(), data);
					Color color = new Color(Display.getDefault(), fontColor);
					// 使用字体和颜色信息显示文字
					color.dispose();
					font.dispose();
				}

			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
