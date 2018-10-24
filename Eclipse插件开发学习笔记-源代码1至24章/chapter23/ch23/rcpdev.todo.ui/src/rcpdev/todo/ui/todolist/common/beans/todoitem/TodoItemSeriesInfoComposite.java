package rcpdev.todo.ui.todolist.common.beans.todoitem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import rcpdev.common.ui.databinding.AbstractBean;
import rcpdev.common.ui.databinding.ChecksBinding;
import rcpdev.common.ui.databinding.RadiosBinding;
import rcpdev.common.ui.databinding.TextBinding;
import rcpdev.common.ui.javabeans.AbstractComposite;
import rcpdev.common.ui.javabeans.DateTextField;
import rcpdev.todo.ui.lang.Messages;

public class TodoItemSeriesInfoComposite extends AbstractComposite {
	private Text byMonthDayText;

	private Text byMonthCountText;

	private Text byWeekCountText;

	private Text byDayCountText;

	private DateTextField toDayText;

	private DateTextField fromDayText;

	private Composite detailComposite;

	private Composite byDayComposite;

	private Composite byWeekComposite;

	private Composite byMonthComposite;

	private Button byDayButton;

	private Button byWeekButton;

	private Button byMonthButton;

	private Button toDayButton;

	private Button noStopButton;

	private Button byDayButton_1;

	private Button byDayButton_2;

	private Button byDayButton_3;

	private Button byWeekMonButton;

	private Button byWeekTueButton;

	private Button byWeekWedButton;

	private Button byWeekThuButton;

	private Button byWeekFriButton;

	private Button byWeekSatButton;

	private Button byWeekSunButton;

	public TodoItemSeriesInfoComposite(Composite parent, int style) {
		super(parent, style);
	}

	public void createControls() {
		Composite container = this;
		final GridLayout gridLayout = new GridLayout();
		container.setLayout(gridLayout);

		final Group periodGroup = new Group(container, SWT.NONE);
		periodGroup.setText(Messages
				.getString("TodoItemSeriesInfoComposite.periodGroupText")); //$NON-NLS-1$
		periodGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 4;
		periodGroup.setLayout(gridLayout_1);

		final Label fromDayLabel = new Label(periodGroup, SWT.NONE);
		fromDayLabel.setText(Messages
				.getString("TodoItemSeriesInfoComposite.fromDay")); //$NON-NLS-1$

		fromDayText = new DateTextField(periodGroup, SWT.NONE);
		fromDayText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		toDayButton = new Button(periodGroup, SWT.RADIO);
		toDayButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.toDay")); //$NON-NLS-1$
		toDayButton
				.setData(RadiosBinding.DATA_KEY, TodoItemBean.STOP_TYPE_DATE);
		toDayButton.setSelection(true);

		toDayText = new DateTextField(periodGroup, SWT.NONE);
		toDayText
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		new Label(periodGroup, SWT.NONE);
		new Label(periodGroup, SWT.NONE);

		final Group periodTypeCombo = new Group(container, SWT.NONE);
		periodTypeCombo.setText(Messages
				.getString("TodoItemSeriesInfoComposite.periodType")); //$NON-NLS-1$
		periodTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true));
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 3;
		periodTypeCombo.setLayout(gridLayout_2);

		final Composite typeComposite = new Composite(periodTypeCombo, SWT.NONE);
		typeComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false,
				true));
		typeComposite.setLayout(new GridLayout());

		byDayButton = new Button(typeComposite, SWT.RADIO);
		byDayButton.setData(RadiosBinding.DATA_KEY,
				TodoItemBean.SERIES_TYPE_DAY);
		byDayButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				StackLayout layout = (StackLayout) detailComposite.getLayout();
				layout.topControl = byDayComposite;
				detailComposite.layout();
			}
		});
		byDayButton.setSelection(true);
		byDayButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byDay")); //$NON-NLS-1$

		byWeekButton = new Button(typeComposite, SWT.RADIO);
		byWeekButton.setData(RadiosBinding.DATA_KEY,
				TodoItemBean.SERIES_TYPE_WEEK);
		byWeekButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				StackLayout layout = (StackLayout) detailComposite.getLayout();
				layout.topControl = byWeekComposite;
				detailComposite.layout();
			}
		});
		byWeekButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byWeek")); //$NON-NLS-1$

		byMonthButton = new Button(typeComposite, SWT.RADIO);
		byMonthButton.setData(RadiosBinding.DATA_KEY,
				TodoItemBean.SERIES_TYPE_MONTH);
		byMonthButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				StackLayout layout = (StackLayout) detailComposite.getLayout();
				layout.topControl = byMonthComposite;
				detailComposite.layout();
			}
		});
		byMonthButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byMonth")); //$NON-NLS-1$

		final Label label = new Label(periodTypeCombo, SWT.SEPARATOR);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true));

		detailComposite = new Composite(periodTypeCombo, SWT.NONE);
		final StackLayout stackLayout = new StackLayout();
		detailComposite.setLayout(stackLayout);
		detailComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true));

		byDayComposite = new Composite(detailComposite, SWT.NONE);
		final GridLayout gridLayout_3 = new GridLayout();
		gridLayout_3.numColumns = 2;
		byDayComposite.setLayout(gridLayout_3);
		byDayComposite.setData(StackBinding.STACK_DATA,
				TodoItemBean.SERIES_TYPE_DAY);

		byDayButton_1 = new Button(byDayComposite, SWT.RADIO);
		byDayButton_1.setSelection(true);
		byDayButton_1.setData(RadiosBinding.DATA_KEY,
				TodoItemBean.BY_DAY_TYPE_COUNT);

		final Composite composite = new Composite(byDayComposite, SWT.NONE);
		final GridLayout gridLayout_4 = new GridLayout();
		gridLayout_4.marginWidth = 0;
		gridLayout_4.verticalSpacing = 0;
		gridLayout_4.marginHeight = 0;
		gridLayout_4.horizontalSpacing = 10;
		gridLayout_4.numColumns = 3;
		composite.setLayout(gridLayout_4);

		final Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData());
		label_2.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byDay_label1")); //$NON-NLS-1$

		byDayCountText = new Text(composite, SWT.BORDER);
		byDayCountText.setLayoutData(new GridData(30, SWT.DEFAULT));

		final Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData());
		label_1.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byDay_label2")); //$NON-NLS-1$

		byDayButton_2 = new Button(byDayComposite, SWT.RADIO);
		byDayButton_2.setData(RadiosBinding.DATA_KEY,
				TodoItemBean.BY_DAY_TYPE_WEEKDAY);

		final Label label_3 = new Label(byDayComposite, SWT.NONE);
		label_3.setText(Messages
				.getString("TodoItemSeriesInfoComposite.weekday")); //$NON-NLS-1$

		byDayButton_3 = new Button(byDayComposite, SWT.RADIO);
		byDayButton_3.setData(RadiosBinding.DATA_KEY,
				TodoItemBean.BY_DAY_TYPE_WEEKEND);

		final Label label_4 = new Label(byDayComposite, SWT.NONE);
		label_4.setText(Messages
				.getString("TodoItemSeriesInfoComposite.weekend")); //$NON-NLS-1$

		byWeekComposite = new Composite(detailComposite, SWT.NONE);
		final GridLayout gridLayout_5 = new GridLayout();
		gridLayout_5.numColumns = 3;
		byWeekComposite.setData(StackBinding.STACK_DATA,
				TodoItemBean.SERIES_TYPE_WEEK);
		byWeekComposite.setLayout(gridLayout_5);

		final Composite composite_1 = new Composite(byWeekComposite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1));
		final GridLayout gridLayout_6 = new GridLayout();
		gridLayout_6.marginWidth = 0;
		gridLayout_6.marginHeight = 0;
		gridLayout_6.horizontalSpacing = 0;
		gridLayout_6.numColumns = 3;
		composite_1.setLayout(gridLayout_6);

		final Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byWeek_label1")); //$NON-NLS-1$

		byWeekCountText = new Text(composite_1, SWT.BORDER);
		final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		gridData.widthHint = 30;
		byWeekCountText.setLayoutData(gridData);

		final Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byWeek_label2")); //$NON-NLS-1$

		byWeekSunButton = new Button(byWeekComposite, SWT.CHECK);
		byWeekSunButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false));
		byWeekSunButton.setData(ChecksBinding.DATA_INDEX_KEY, 0);
		byWeekSunButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.sun")); //$NON-NLS-1$
		byWeekMonButton = new Button(byWeekComposite, SWT.CHECK);
		byWeekMonButton.setData(ChecksBinding.DATA_INDEX_KEY, 1);
		byWeekMonButton.setLayoutData(new GridData());
		byWeekMonButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.mon")); //$NON-NLS-1$
		byWeekTueButton = new Button(byWeekComposite, SWT.CHECK);
		byWeekTueButton.setLayoutData(new GridData());
		byWeekTueButton.setData(ChecksBinding.DATA_INDEX_KEY, 2);
		byWeekTueButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.tue")); //$NON-NLS-1$
		byWeekWedButton = new Button(byWeekComposite, SWT.CHECK);
		byWeekWedButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.wed")); //$NON-NLS-1$
		byWeekWedButton.setData(ChecksBinding.DATA_INDEX_KEY, 3);
		byWeekThuButton = new Button(byWeekComposite, SWT.CHECK);
		byWeekThuButton.setData(ChecksBinding.DATA_INDEX_KEY, 4);
		byWeekThuButton.setLayoutData(new GridData());
		byWeekThuButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.thu")); //$NON-NLS-1$
		byWeekFriButton = new Button(byWeekComposite, SWT.CHECK);
		byWeekFriButton.setLayoutData(new GridData());
		byWeekFriButton.setData(ChecksBinding.DATA_INDEX_KEY, 5);
		byWeekFriButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.fri")); //$NON-NLS-1$
		byWeekSatButton = new Button(byWeekComposite, SWT.CHECK);
		byWeekSatButton.setText(Messages
				.getString("TodoItemSeriesInfoComposite.sat")); //$NON-NLS-1$
		byWeekSatButton.setData(ChecksBinding.DATA_INDEX_KEY, 6);
		noStopButton = new Button(periodGroup, SWT.RADIO);
		noStopButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 2, 1));
		noStopButton.setText(Messages.getString("TodoItemSeriesInfoComposite.noEndDay")); //$NON-NLS-1$
		noStopButton.setData(RadiosBinding.DATA_KEY,
				TodoItemBean.STOP_TYPE_NONE);
		byMonthComposite = new Composite(detailComposite, SWT.NONE);
		final GridLayout gridLayout_7 = new GridLayout();
		gridLayout_7.numColumns = 5;
		byMonthComposite.setData(StackBinding.STACK_DATA,
				TodoItemBean.SERIES_TYPE_MONTH);
		byMonthComposite.setLayout(gridLayout_7);

		final Label label_7 = new Label(byMonthComposite, SWT.NONE);
		label_7.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byMonth_label1")); //$NON-NLS-1$

		byMonthCountText = new Text(byMonthComposite, SWT.BORDER);
		byMonthCountText.setLayoutData(new GridData(30, SWT.DEFAULT));

		final Label label_8 = new Label(byMonthComposite, SWT.NONE);
		label_8.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byMonth_label2")); //$NON-NLS-1$

		byMonthDayText = new Text(byMonthComposite, SWT.BORDER);
		final GridData gridData_1 = new GridData(30, SWT.DEFAULT);
		byMonthDayText.setLayoutData(gridData_1);

		final Label label_9 = new Label(byMonthComposite, SWT.NONE);
		label_9.setText(Messages
				.getString("TodoItemSeriesInfoComposite.byMonth_label3")); //$NON-NLS-1$
		stackLayout.topControl = byDayComposite;

	}

	@Override
	protected void createBindings() {
		getManager().addBinding(
				new RadiosBinding(TodoItemBean.ATTR_SERIES_TYPE, new Button[] {
						byDayButton, byWeekButton, byMonthButton }));
		getManager().addBinding(
				fromDayText.new DateTextFieldBinding(
						TodoItemBean.ATTR_START_DATE));
		getManager()
				.addBinding(
						toDayText.new DateTextFieldBinding(
								TodoItemBean.ATTR_STOP_DATE));
		getManager().addBinding(
				new RadiosBinding(TodoItemBean.ATTR_STOP_TYPE, new Button[] {
						toDayButton, noStopButton }));
		getManager().addBinding(
				new RadiosBinding(TodoItemBean.ATTR_BY_DAY_TYPE, new Button[] {
						byDayButton_1, byDayButton_2, byDayButton_3 }));
		getManager()
				.addBinding(
						new TextBinding(TodoItemBean.ATTR_BY_DAY_COUNT,
								byDayCountText));
		getManager().addBinding(
				new TextBinding(TodoItemBean.ATTR_BY_WEEK_COUNT,
						byWeekCountText));
		getManager().addBinding(
				new TextBinding(TodoItemBean.ATTR_BY_MONTH_COUNT,
						byMonthCountText));
		getManager()
				.addBinding(
						new TextBinding(TodoItemBean.ATTR_BY_MONTH_DAY,
								byMonthDayText));
		getManager().addBinding(
				new ChecksBinding(TodoItemBean.ATTR_BY_WEEK_DATA, new Button[] {
						byWeekSunButton, byWeekMonButton, byWeekTueButton,
						byWeekWedButton, byWeekThuButton, byWeekFriButton,
						byWeekSatButton }));
		getManager()
				.addBinding(
						new StackBinding(TodoItemBean.ATTR_SERIES_TYPE,
								detailComposite));
	}

	@Override
	protected AbstractBean createEmptyBean() {
		return new TodoItemBean();
	}

}
