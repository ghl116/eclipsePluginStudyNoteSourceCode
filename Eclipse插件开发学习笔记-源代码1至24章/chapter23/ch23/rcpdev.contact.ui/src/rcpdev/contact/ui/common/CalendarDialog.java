package rcpdev.contact.ui.common;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class CalendarDialog extends Dialog {

	public static final String[] titles = new String[] { "S", "M", "T", "W",
			"T", "F", "S" };

//	private Label dummyLabel;

	private Button cancelButton;

	private Button okButton;

	private Composite bottomComposite;

//	private TableColumn satColumn;
//
//	private TableColumn friColumn;
//
//	private TableColumn thrColumn;
//
//	private TableColumn monColumn;
//
//	private TableColumn tueColumn;
//
//	private TableColumn wedColumn;
//
//	private TableColumn sunColumn;

	private Table calendarTable;

	private Composite topComposite;

	private Spinner yearSpinner;

	private Combo monthCombo;

	protected Object result;

	protected Shell shell;

	private ModifyAdapter modifier = new ModifyAdapter();

	/**
	 * Create the dialog
	 * 
	 * @param parent
	 * @param style
	 */
	public CalendarDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * 
	 * @param parent
	 */
	public CalendarDialog(Shell parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Open the dialog
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		final GridLayout mainGridLayout = new GridLayout();
		shell.setLayout(mainGridLayout);
		shell.setSize(188, 234);
		shell.setText("Calendar");

		topComposite = new Composite(shell, SWT.NONE);
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		final GridLayout topGridLayout = new GridLayout();
		topGridLayout.marginWidth = 0;
		topGridLayout.numColumns = 2;
		topComposite.setLayout(topGridLayout);

		monthCombo = new Combo(topComposite, SWT.READ_ONLY);
		monthCombo.setItems(new String[] { "January", "Febrary", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December" });
		monthCombo.addModifyListener(modifier);

		yearSpinner = new Spinner(topComposite, SWT.READ_ONLY | SWT.BORDER);
		yearSpinner.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		final GridData gridData_2 = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		yearSpinner.addModifyListener(modifier);
		yearSpinner.setLayoutData(gridData_2);

		calendarTable = new Table(shell, SWT.SINGLE | SWT.BORDER);
		calendarTable.setCapture(true);
		calendarTable.setHeaderVisible(true);
		calendarTable
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		calendarTable.addMouseListener(new MouseAdapter());

		for (int i = 0; i < 7; i++) {
			TableColumn column = new TableColumn(calendarTable, SWT.NONE);
			column.setMoveable(false);
			column.setResizable(false);
			column.setText(titles[i]);
			column.setWidth(24);
		}

		for (int j = 0; j < 6; j++)
			new TableItem(calendarTable, SWT.NONE);

		bottomComposite = new Composite(shell, SWT.NONE);
		bottomComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false));
		final GridLayout bottomGridLayout = new GridLayout();
		bottomGridLayout.numColumns = 3;
		bottomComposite.setLayout(bottomGridLayout);

		okButton = new Button(bottomComposite, SWT.NONE);
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				result = theDate;
				shell.dispose();
			}
		});
		final GridData gridData = new GridData(SWT.CENTER, SWT.CENTER, true,
				false);
		gridData.widthHint = 65;
		okButton.setLayoutData(gridData);
		okButton.setText("OK");

		cancelButton = new Button(bottomComposite, SWT.NONE);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				result = null;
				shell.dispose();
			}
		});
		final GridData gridData_1 = new GridData(SWT.CENTER, SWT.CENTER, true,
				false);
		gridData_1.widthHint = 65;
		cancelButton.setLayoutData(gridData_1);
		cancelButton.setText("Cancel");

		if (theDate == null)
			theDate = new Date();
		initData(theDate);
		//
	}

	private int maxDayInMonth(int month) {
		Calendar cal = Calendar.getInstance();
		if (month == Calendar.DECEMBER)
			month = Calendar.JANUARY;
		else
			month += 1;
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	private void initData(Date date) {
		theDate = date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int maxDays = maxDayInMonth(calendar.get(Calendar.MONTH));
		calendarTable.clearAll();
		for (int i = 1; i <= maxDays; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, i);
			int dayInWeek = calendar.get(Calendar.DAY_OF_WEEK);
			int col = dayInWeek - 1;
			int weekInMonth = calendar.get(Calendar.WEEK_OF_MONTH);
			int row = weekInMonth - 1;
			// if(i>15 && row == 0)
			// row += calendar.getMaximum(Calendar.WEEK_OF_MONTH);
			calendarTable.getItem(row).setText(col, String.valueOf(i));
		}
		calendar.setTime(date);
		int dayInWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int col = dayInWeek - 1;
		int weekInMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		int row = weekInMonth - 1;
		changeSelection(row, col);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		yearSpinner.removeModifyListener(modifier);
		yearSpinner.setValues(year, 1900, 2100, 0, 1, 1);
		yearSpinner.addModifyListener(modifier);
		monthCombo.removeModifyListener(modifier);
		monthCombo.select(month);
		monthCombo.addModifyListener(modifier);
	}

	private void changeSelection(int row, int col) {
		Color white = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
		Color black = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
		Color sel = Display.getCurrent().getSystemColor(
				SWT.COLOR_LIST_SELECTION);
		if (selected != null) {
			calendarTable.getItem(selected.x).setBackground(selected.y, white);
			calendarTable.getItem(selected.x).setForeground(selected.y, black);
		} else
			selected = new Point(0, 0);
		calendarTable.deselectAll();
		selected.x = row;
		selected.y = col;
		calendarTable.getItem(row).setBackground(col, sel);
		calendarTable.getItem(row).setForeground(col, white);
		Calendar cal = Calendar.getInstance();
		cal.setTime(theDate);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(calendarTable.getItem(
				row).getText(col)));
		theDate = cal.getTime();
	}

	private Point selected;

	public Date theDate;

	protected class ModifyAdapter implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			try {
				int year = yearSpinner.getSelection();
				int month = monthCombo.getSelectionIndex();
				int day = Integer.parseInt(calendarTable.getItem(selected.x)
						.getText(selected.y));
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH, month);
				cal.set(Calendar.DAY_OF_MONTH, day);
				initData(cal.getTime());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	protected class MouseAdapter implements MouseListener {

		public void mouseDoubleClick(MouseEvent e) {
		}

		public void mouseDown(MouseEvent e) {
			if (calendarTable.getItem(new Point(5, e.y)) == null)
				return;
			int row = calendarTable.indexOf(calendarTable.getItem(new Point(5,
					e.y)));
			int col = calColumn(e.x);
			if (calendarTable.getItem(row).getText(col) == null
					|| calendarTable.getItem(row).getText(col).equals(""))
				return;
			changeSelection(row, col);
		}

		public void mouseUp(MouseEvent e) {

		}

		protected int calColumn(int x) {
			int j = 0;
			int sum = 0, oldsum = 0;
			for (j = 0; j < calendarTable.getColumnCount(); j++) {
				oldsum = sum;
				sum += calendarTable.getColumn(j).getWidth();
				if (oldsum <= x && x <= sum)
					return j;
			}
			return -1;
		}
	}

}
