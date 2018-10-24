package rcpdev.common.ui.javabeans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import rcpdev.common.core.utils.DateUtils;
import rcpdev.common.core.utils.MiscUtils;
import rcpdev.common.ui.lang.Messages;

public class CalendarComposite extends Composite {

	private CLabel monthLabel;

	private Button prevMonthButton;

	private Button nextMonthButton;

	private CLabel[] titleLabel;

	private CLabel[][] dateLabel;

	private HashMap<Date, CLabel> dateMap;

	private DateFormat format = new SimpleDateFormat("yyyy MMM"); //$NON-NLS-1$

	private static final String[] TITLES = new String[] {
			Messages.getString("CalendarComposite.su"), Messages.getString("CalendarComposite.mo"), Messages.getString("CalendarComposite.tu"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			Messages.getString("CalendarComposite.we"), Messages.getString("CalendarComposite.th"), Messages.getString("CalendarComposite.fr"), Messages.getString("CalendarComposite.sa") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	public static final String COLOR_TODAY = "colorCurrentDate"; //$NON-NLS-1$

	private Color SELECTED_FG = Display.getCurrent().getSystemColor(
			SWT.COLOR_WHITE);

	private Color SELECTED_BG = Display.getCurrent().getSystemColor(
			SWT.COLOR_BLUE);

	private Font sysFont = Display.getCurrent().getSystemFont();

	private Font sysBoldFont;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public CalendarComposite(Composite parent, int style) {
		super(parent, style);

		FontData[] fontData = sysFont.getFontData();
		fontData[0].setStyle(SWT.BOLD);
		sysBoldFont = new Font(Display.getCurrent(), fontData[0]);

		dateMap = new HashMap<Date, CLabel>();
		JFaceResources.getColorRegistry().put(COLOR_TODAY,
				new RGB(250, 214, 148));

		setBackgroundMode(SWT.INHERIT_FORCE);

		prevMonthButton = new Button(this, SWT.ARROW | SWT.FLAT | SWT.LEFT);
		prevMonthButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(getNavDate());
				cal.add(Calendar.MONTH, -1);
				setNavDate(cal.getTime());
				// initCalendar(getNavDate());
			}
		});

		monthLabel = new CLabel(this, SWT.FLAT);
		monthLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 5, 1));
		monthLabel.setAlignment(SWT.CENTER);
		monthLabel.setText("Month Label"); //$NON-NLS-1$

		nextMonthButton = new Button(this, SWT.ARROW | SWT.FLAT | SWT.RIGHT);
		nextMonthButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(getNavDate());
				cal.add(Calendar.MONTH, 1);
				setNavDate(cal.getTime());
				// initCalendar(getNavDate());
			}
		});

		titleLabel = new CLabel[7];
		dateLabel = new CLabel[7][6];

		final GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		gridLayout.numColumns = 7;
		setLayout(gridLayout);

		for (int i = 0; i < 7; i++) {
			CLabel label = new CLabel(this, SWT.FLAT);
			label.setText(TITLES[i]);
			label.setLayoutData(new GridData(20, 20));
			titleLabel[i] = label;
		}
		for (int i = 0; i < 42; i++) {
			CLabel label = new CLabel(this, SWT.FLAT);
			dateLabel[i % 7][i / 7] = label;
			label.setText(String.valueOf(i));
			MouseMonitor mm = new MouseMonitor();
			label.addMouseListener(mm);
			label.addMouseMoveListener(mm);
			label.setLayoutData(new GridData(20, 20));
		}

		Button todayButton = new Button(this, SWT.FLAT);
		todayButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(getDate());
				int oldMonth = cal.get(Calendar.MONTH);
				Date newDate = DateUtils.truncToDay(new Date());
				cal.setTime(newDate);
				int newMonth = cal.get(Calendar.MONTH);
				if (oldMonth == newMonth) {
					CLabel todayLabel = getLabelByDate(newDate);
					selectLabel(todayLabel);
					setDate(newDate);
					return;
				}
				setDate(newDate);
				initCalendar(newDate);
				firePropertyChange(CALENDAR_MONTH, oldMonth, newMonth);
			}
		});
		GridData todayButtonData = new GridData(
				GridData.HORIZONTAL_ALIGN_CENTER);
		todayButtonData.horizontalSpan = 7;
		todayButton.setLayoutData(todayButtonData);
		todayButton.setText(Messages.getString("CalendarComposite.today")); //$NON-NLS-1$

		Date date = DateUtils.truncToDay(new Date());
		setDate(date);
		initCalendar(getNavDate());
		//
	}

	private class MouseMonitor extends MouseAdapter implements
			MouseMoveListener {

		private boolean isNotDrag = false;

		public void mouseDown(MouseEvent e) {
			isNotDrag = true;
		}

		public void mouseUp(MouseEvent e) {
			if (!isNotDrag) {
				return;
			}
			CLabel target = (CLabel) e.widget;

			isNotDrag = false;

			Date theDate = (Date) target.getData(KEY_DATE);
			if (theDate != null)
				setDate(theDate);
		}

		public void mouseMove(MouseEvent e) {
			isNotDrag = false;
		}

	}

	public void selectLabel(CLabel target) {
		Color color;
		if (curLabel != null)
			if (!curLabel.equals(target) && target.getData(KEY_DATE) != null) {

				if ((color = (Color) curLabel.getData(KEY_OLD_BG)) != null)
					curLabel.setBackground(color);
				if ((color = (Color) curLabel.getData(KEY_OLD_FG)) != null)
					curLabel.setForeground(color);
			} else {
				return;
			}

		CLabel label = target;
		curLabel = label;
		label.setData(KEY_OLD_BG, label.getBackground());
		label.setData(KEY_OLD_FG, label.getForeground());
		label.setBackground(SELECTED_BG);
		label.setForeground(SELECTED_FG);
	}

	@Override
	public void dispose() {
		super.dispose();
		sysBoldFont.dispose();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private Date date;

	private Date navDate;

	private CLabel curLabel;

	public static final String CALENDAR_DATE = "calendar.date"; //$NON-NLS-1$

	public static final String CALENDAR_MONTH = "calendar.month"; //$NON-NLS-1$

	private String KEY_DATE = "keyDate"; //$NON-NLS-1$

	private String KEY_OLD_FG = "keyOldFg"; //$NON-NLS-1$

	private String KEY_OLD_BG = "keyOldBg"; //$NON-NLS-1$

	public void setDate(Date newDate) {
		if (newDate == null)
			throw new IllegalArgumentException(Messages
					.getString("CalendarComposite.error_null_date")); //$NON-NLS-1$
		newDate = DateUtils.truncToDay(newDate);
		Date oldDate = getDate();
		if (MiscUtils.equals(oldDate, newDate))
			return;
		date = newDate;
		Calendar cal = Calendar.getInstance();
		cal.setTime(newDate);
		setNavDate(newDate);
		if (!newDate.equals(oldDate)) {
			firePropertyChange(new PropertyChangeEvent(this, CALENDAR_DATE,
					oldDate, newDate));
		}
		selectLabel(getLabelByDate(newDate));
	}

	protected void setNavDate(Date newNavDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(newNavDate);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date oldNavDate = getNavDate();
		navDate = cal.getTime();
		firePropertyChange(CALENDAR_MONTH, oldNavDate, navDate);
		if (!MiscUtils.equals(oldNavDate, navDate))
			initCalendar(navDate);
	}

	protected Date getNavDate() {
		return navDate;
	}

	public void initCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		dateMap.clear();

		Date newDate = calendar.getTime();
		monthLabel.setText(format.format(newDate));

		for (int i = 0; i < 42; i++) {
			dateLabel[i % 7][i / 7].setText(""); //$NON-NLS-1$
			dateLabel[i % 7][i / 7].setData(KEY_DATE, null);
			dateLabel[i % 7][i / 7].setBackground((Color) null);
			dateLabel[i % 7][i / 7].setForeground((Color) null);
		}

		newDate = DateUtils.truncToDay(newDate);
		calendar.setTime(newDate);
		Date thisDate = getDate();
		Date today = DateUtils.truncToDay(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int month = calendar.get(Calendar.MONTH);

		int rowCount = 0;
		curLabel = null;
		while (month == calendar.get(Calendar.MONTH)) {
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			CLabel label = dateLabel[dayOfWeek - 1][rowCount];
			dateMap.put(calendar.getTime(), label);
			label.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
			label.setData(KEY_DATE, calendar.getTime());
			label.setBackground((Color) null);
			label.setForeground((Color) null);
			label.setFont(null);
			if (today.equals(calendar.getTime()))
				highlight(label);
			if (calendar.getTime().equals(thisDate)) {
				label.setData(KEY_OLD_BG, label.getBackground());
				label.setData(KEY_OLD_FG, label.getForeground());
				label.setBackground(SELECTED_BG);
				label.setForeground(SELECTED_FG);
			}

			if (calendar.getTime().equals(thisDate)) {
				curLabel = label;
			}
			if (Calendar.SATURDAY == dayOfWeek)
				rowCount++;
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
	}

	protected void changeColor(CLabel label) {
		Color oldBg = label.getBackground();
		Color oldFg = label.getForeground();
		label.setForeground((Color) label.getData(KEY_OLD_FG));
		label.setBackground((Color) label.getData(KEY_OLD_BG));
		label.setData(KEY_OLD_BG, oldBg);
		label.setData(KEY_OLD_FG, oldFg);
	}

	protected void highlight(CLabel label) {
		label.setBackground(JFaceResources.getColorRegistry().get(COLOR_TODAY));
	}

	public Date getDate() {
		return date;
	}

	public CLabel getLabelByDate(Date date) {
		return dateMap.get(date);
	}

	public void setLabelBold(Date date, boolean bold) {
		CLabel targetLabel = (CLabel) getLabelByDate(date);
		if (targetLabel != null)
			if (bold)
				targetLabel.setFont(sysBoldFont);
			else
				targetLabel.setFont(sysFont);
	}

	protected PropertyChangeSupport delegate = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(listener);
	}

	public void firePropertyChange(PropertyChangeEvent evt) {
		delegate.firePropertyChange(evt);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(listener);
	}

	public void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		delegate.firePropertyChange(propertyName, oldValue, newValue);
	}

}
