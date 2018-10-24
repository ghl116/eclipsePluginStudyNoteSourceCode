package rcpdev.todo.ui.todolist.common.beans.todoitem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import rcpdev.common.ui.databinding.AbstractBean;
import rcpdev.common.ui.databinding.ComboBinding2;
import rcpdev.common.ui.databinding.TextBinding;
import rcpdev.common.ui.javabeans.AbstractComposite;
import rcpdev.common.ui.javabeans.DateTextField;
import rcpdev.todo.ui.lang.Messages;
import rcpdev.todo.ui.todolist.utils.TimeConverter;

public class TodoItemBasicInfoComposite extends AbstractComposite {
	private Combo stopTimeCombo;

	private Combo startTimeCombo;

	private Combo itemTypeCombo;

	private Text contentText;

	private Text subjectText;

	private DateTextField dateTextField;

	public TodoItemBasicInfoComposite(Composite parent, int style) {
		super(parent, style);
	}

	public void editMode(boolean edit) {
		itemTypeCombo.setEnabled(!edit);
	}
	
	public void createControls() {
		Composite container = this;
		final GridLayout gridLayout = new GridLayout();
		container.setLayout(gridLayout);

		final Group timeGroup = new Group(container, SWT.NONE);
		timeGroup.setText(Messages
				.getString("TodoItemBasicInfoComposite.group_itemTime")); //$NON-NLS-1$
		timeGroup
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 4;
		timeGroup.setLayout(gridLayout_2);

		final Label dateLabel = new Label(timeGroup, SWT.NONE);
		dateLabel.setText(Messages
				.getString("TodoItemBasicInfoComposite.startDay")); //$NON-NLS-1$

		dateTextField = new DateTextField(timeGroup, SWT.NONE);
		dateTextField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		new Label(timeGroup, SWT.NONE);
		new Label(timeGroup, SWT.NONE);

		final Label startTimeLabel = new Label(timeGroup, SWT.NONE);
		startTimeLabel.setLayoutData(new GridData());
		startTimeLabel.setText(Messages
				.getString("TodoItemBasicInfoComposite.startTime")); //$NON-NLS-1$

		startTimeCombo = new Combo(timeGroup, SWT.READ_ONLY);
		startTimeCombo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		startTimeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		final Label stopTimeLabel = new Label(timeGroup, SWT.NONE);
		stopTimeLabel.setText(Messages
				.getString("TodoItemBasicInfoComposite.stopTime")); //$NON-NLS-1$

		stopTimeCombo = new Combo(timeGroup, SWT.READ_ONLY);
		stopTimeCombo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		stopTimeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		for (int i = 0; i < 48; i++) {
			String text = TimeConverter.convert(i);
			startTimeCombo.add(text);
			stopTimeCombo.add(text);
		}

		final Label selectItemTypeLabel = new Label(timeGroup, SWT.NONE);
		selectItemTypeLabel.setText(Messages
				.getString("TodoItemBasicInfoComposite.itemType")); //$NON-NLS-1$

		itemTypeCombo = new Combo(timeGroup, SWT.READ_ONLY);
		itemTypeCombo
				.setItems(new String[] {
						Messages
								.getString("TodoItemBasicInfoComposite.itemType_single"), Messages.getString("TodoItemBasicInfoComposite.itemType_series") }); //$NON-NLS-1$ //$NON-NLS-2$
		itemTypeCombo.select(0);

		new Label(timeGroup, SWT.NONE);
		new Label(timeGroup, SWT.NONE);

		final Group contentGroup = new Group(container, SWT.NONE);
		contentGroup.setText(Messages
				.getString("TodoItemBasicInfoComposite.group_itemContent")); //$NON-NLS-1$
		contentGroup
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 2;
		contentGroup.setLayout(gridLayout_1);

		final Label subjectLabel = new Label(contentGroup, SWT.NONE);
		subjectLabel.setText(Messages
				.getString("TodoItemBasicInfoComposite.itemSubject")); //$NON-NLS-1$

		subjectText = new Text(contentGroup, SWT.BORDER);
		subjectText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		final Label contentLabel = new Label(contentGroup, SWT.NONE);
		contentLabel
				.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		contentLabel.setText(Messages
				.getString("TodoItemBasicInfoComposite.itemContent")); //$NON-NLS-1$

		contentText = new Text(contentGroup, SWT.BORDER);
		contentText
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

		//
		initManager();
	}

	@Override
	protected void createBindings() {
		getManager().addBinding(
				new ComboBinding2(TodoItemBean.ATTR_TYPE, itemTypeCombo)); //$NON-NLS-1$
		getManager().addBinding(
				new TextBinding(TodoItemBean.ATTR_SUBJECT, subjectText)); //$NON-NLS-1$
		getManager().addBinding(new TextBinding("content", contentText)); //$NON-NLS-1$
		getManager().addBinding(
				dateTextField.new DateTextFieldBinding(
						TodoItemBean.ATTR_START_DATE)); //$NON-NLS-1$
		getManager()
				.addBinding(
						new ComboBinding2(TodoItemBean.ATTR_START_TIME,
								startTimeCombo));
		getManager().addBinding(
				new ComboBinding2(TodoItemBean.ATTR_STOP_TIME, stopTimeCombo));
	}

	@Override
	protected AbstractBean createEmptyBean() {
		return new TodoItemBean();
	}

}
