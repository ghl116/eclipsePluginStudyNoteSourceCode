package book.ch2;

	import org.eclipse.swt.SWT;
	import org.eclipse.swt.events.*;
	import org.eclipse.swt.widgets.*;

	public class HelloSWT extends Shell {
		private static Text text;
		private static Button swtButton;
		private static Button swingButton;
		private static Button awtButton;
		private static Group group;
		private static Button button;
		private static Label benefitOfSwtLabel;
		private static List list;

		public static void main(String[] args) {
			Display display = Display.getDefault();
			final Shell shell = new Shell(display);
			shell.setText("Hello SWT");
			shell.setSize(260, 283);
			shell.open();

			text = new Text(shell, SWT.BORDER);
			text.setText("SWT是Eclipse平台使用的图形工具箱");
			text.setBounds(10, 8, 230, 35);

			list = new List(shell, SWT.BORDER);
			list.setItems(new String[] { 
					"使用操作系统本地控件", 
					"提供一套平台无关的API",
					"GUI程序的运行速度快", 
					"更多更多....." });
			list.setBounds(10, 68, 232, 82);

			benefitOfSwtLabel = new Label(shell, SWT.NONE);
			benefitOfSwtLabel.setText("SWT的优点:");
			benefitOfSwtLabel.setBounds(10, 49, 90, 15);

			group = new Group(shell, SWT.NONE);
			group.setText("你使用过哪些图形工具箱?");
			group.setBounds(10, 159, 230, 47);

			awtButton = new Button(group, SWT.CHECK);
			awtButton.setText("AWT");
			awtButton.setBounds(10, 20, 54, 18);

			swingButton = new Button(group, SWT.CHECK);
			swingButton.setText("Swing");
			swingButton.setBounds(70, 22, 60, 15);

			swtButton = new Button(group, SWT.CHECK);
			swtButton.setBounds(136, 22, 62, 15);
			swtButton.setText("SWT");

			button = new Button(shell, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(final SelectionEvent e) {
					MessageBox msgBox = 
						new MessageBox(shell, SWT.ICON_INFORMATION);
					msgBox.setMessage("Hello SWT!");
					msgBox.open();
				}
			});
			button.setText("按一下按钮,向SWT说Hello");
			button.setBounds(10, 214, 227, 25);

			shell.layout();

			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		}
	
}
