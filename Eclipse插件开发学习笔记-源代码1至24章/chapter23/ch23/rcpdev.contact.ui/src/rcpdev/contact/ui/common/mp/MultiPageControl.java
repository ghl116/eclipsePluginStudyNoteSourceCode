package rcpdev.contact.ui.common.mp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Constructor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import rcpdev.common.core.utils.StringUtils;

public class MultiPageControl extends Composite implements IDetailsPart,
		Listener {

	private Control[] controls;

	private StackLayout stackLayout;

	private int selection = 0;

	private static final Class[] args = new Class[] { Composite.class,
			Integer.TYPE };

	public static final int[] EVENT_TYPE = new int[] { SWT.Modify };

	private static final String KEY_INDEX = "keyIndex";

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public MultiPageControl(Composite parent, int style,
			Class<? extends Control> controlClass, int count) {
		super(parent, style);

//		FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		stackLayout = new StackLayout();
		stackLayout.marginHeight = 2;
		stackLayout.marginWidth = 1;
		setLayout(stackLayout);

		controls = new Control[count];
		try {
			Constructor cons = controlClass.getConstructor(args);
			for (int i = 0; i < count; i++) {
				controls[i] = (Control) cons.newInstance(new Object[] { this,
						SWT.NONE });
				controls[i].setData(KEY_INDEX, i);
				for (int j = 0; j < EVENT_TYPE.length; j++)
					controls[i].addListener(EVENT_TYPE[j], this);
			}
			stackLayout.topControl = controls[0];
			layout();

		} catch (Exception e) {
			e.printStackTrace();
		}
		//
	}

	public void changeSelection(int index) {
		selection = index;
		stackLayout.topControl = controls[index];
		layout();
	}

	public int getSelection() {
		return selection;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public void changePage(int newIndex) {
		changeSelection(newIndex);
	}

	public Control[] getControls() {
		return controls;
	}

	public void handleEvent(Event event) {
		int index = (Integer) event.widget.getData(KEY_INDEX);
		if (SWT.Modify == event.type && event.widget instanceof Text)
			firePropertyChange(new PropertyChangeEvent(this,
					MultiPageBlock.VAL_CHANGE, null, new Object[] { index,
							((Text) event.widget).getText() }));
	}

	private PropertyChangeSupport delegate = new PropertyChangeSupport(this);

	public PropertyChangeSupport getDelegate() {
		return delegate;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(listener);
	}

	public void firePropertyChange(PropertyChangeEvent evt) {
		delegate.firePropertyChange(evt);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(listener);
	}

	protected boolean isEmpty(Control c) {
		if (c instanceof Text && !StringUtils.isEmpty(((Text) c).getText()))
			return true;
		return false;
	}

	public void setValue(int index, Object value) {
		Control control = controls[index];
		if (control instanceof Text) {
			((Text) control).setText(value == null ? "" : value.toString());
		}
	}
}
