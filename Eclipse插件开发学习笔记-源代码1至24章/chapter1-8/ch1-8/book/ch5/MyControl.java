package book.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class MyControl extends Composite {
	private Button button;

	private Text text;

	private int count = 0;

	public MyControl(Composite parent, int style) {
		super(parent, style);
		setLayout(null);
		// ��Composite�д���Text
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setBounds(5, 5, 50, 20);
		text.setText(String.valueOf(count));
		// ��Composite�д���Button
		button = new Button(this, SWT.NONE);
		button.setBounds(60, 5, 50, 20);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				/*
				 * ��Button�����ʱ,������������1, ����Text���ı���ɼ�������ֵ
				 */
				text.setText(String.valueOf(++count));
			}
		});
		button.setText("button");
	}
}
