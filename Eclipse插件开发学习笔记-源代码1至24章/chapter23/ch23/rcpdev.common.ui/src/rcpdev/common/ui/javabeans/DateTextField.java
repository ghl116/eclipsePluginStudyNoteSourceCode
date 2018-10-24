package rcpdev.common.ui.javabeans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import rcpdev.common.ui.databinding.ControlBinding;

public class DateTextField extends Composite implements PropertyChangeListener {

	private Text text;

	private CalendarShell calendarShell;

	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private Date date;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public DateTextField(Composite parent, int style) {
		super(parent, style);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		gridLayout.numColumns = 2;
		setLayout(gridLayout);

		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		text
				.setBackground(Display.getCurrent().getSystemColor(
						SWT.COLOR_WHITE));

		final Button button = new Button(this, SWT.ARROW | SWT.DOWN);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int textHeight = text.getSize().y;
				Point location = Display.getCurrent().map(text, null, 0,
						textHeight);
				getCalendarShell().setLocation(location);
				if (date != null)
					getCalendarShell().getCalendar().setDate(date);
				getCalendarShell().setVisible(true);
			}
		});

		//
		setDate(new Date());

		getCalendarShell().setVisible(false);
		getCalendarShell().getCalendar().addPropertyChangeListener(this);
	}

	public CalendarShell getCalendarShell() {
		if (calendarShell == null || calendarShell.isDisposed()) {
			calendarShell = new CalendarShell(getShell());
			calendarShell.pack();
		}
		return calendarShell;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	private static final class CalendarShell extends Shell {

		private CalendarComposite calendar;

		public CalendarShell(Shell parent) {
			super(parent, SWT.NONE);
			setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
			setLayout(new GridLayout());

			Button button = new Button(this, SWT.NONE);
			button.setText("x");
			button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					setVisible(false);
				}
			});
			button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
					false));
			calendar = new CalendarComposite(this, SWT.NONE);

		}

		public CalendarComposite getCalendar() {
			return calendar;
		}

		@Override
		public void checkSubclass() {
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (CalendarComposite.CALENDAR_DATE.equals(evt.getPropertyName())) {
			getCalendarShell().setVisible(false);
			setDate((Date) evt.getNewValue());
		}
	}

	public class DateTextFieldBinding extends ControlBinding {

		public DateTextFieldBinding(String attribute) {
			super();
			setAttribute(attribute);
			setControl(text);
		}

		public void setControl(Control control) {
			super.setControl(control);
			text.addListener(SWT.Modify, this);
		}

		public void setValue(Object value) {
			this.value = value;
			text.removeListener(SWT.Modify, this);
			setDate((Date) value);
			text.addListener(SWT.Modify, this);
		}

		public void handleEvent(Event event) {
			if (SWT.Modify == event.type) {
				Object oldVal = getValue();
				value = getDate();
				firePropertyChange(new PropertyChangeEvent(this, BINDING_VAL,
						oldVal, value));
			}
		}

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		text.setText(format.format(date));
	}
}
