package book.ch2;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.DecoratedField;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.TextControlCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class FieldHelper {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setSize(400,200);
		shell.setLayout(new GridLayout());

		DecoratedField field = new DecoratedField(shell, SWT.BORDER,
				new TextControlCreator());
		Text text = (Text) field.getControl();
		text.setText("some text");

		// field.getLayoutControl().setBounds(10,10,100,60);
		Image image = ImageDescriptor.createFromFile(FieldHelper.class, "image.bmp")
				.createImage();
		// setImage(image);
		JFaceResources.getImageRegistry().put("test", image);

		FieldDecorationRegistry.getDefault().registerFieldDecoration("a",
				"Description", image);
		FieldDecoration deco = FieldDecorationRegistry.getDefault()
				.getFieldDecoration("a");
		deco = new FieldDecoration(image,"Description");

		field.addFieldDecoration(deco, SWT.BOTTOM | SWT.RIGHT, false);

		int a = IDialogConstants.ENTRY_FIELD_WIDTH
		+ FieldDecorationRegistry.getDefault()
		.getMaximumDecorationWidth();
//		GridData data = new GridData(200, SWT.DEFAULT);
//		field.getLayoutControl().setLayoutData(data);

		shell.open();
		shell.layout();
//		 shell.pack();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		image.dispose();
		display.dispose();
	}
}
