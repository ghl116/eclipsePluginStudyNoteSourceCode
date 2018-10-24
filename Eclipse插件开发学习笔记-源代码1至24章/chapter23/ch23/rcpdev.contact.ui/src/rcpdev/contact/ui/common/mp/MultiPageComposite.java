package rcpdev.contact.ui.common.mp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.TreeMap;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.forms.widgets.FormToolkit;

import rcpdev.common.ui.databinding.ControlBinding;
import rcpdev.contact.ui.common.DropdownLabel;
import rcpdev.contact.ui.common.ModelComposite;

public class MultiPageComposite extends Composite implements
		PropertyChangeListener {

	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	final DropdownLabel dropdownLabel;

	final Composite composite;

	private ModelComposite[] elements;

	private StackLayout stackLayout;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public MultiPageComposite(Composite parent, int style) {
		super(parent, style);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		setLayout(gridLayout);

		dropdownLabel = new DropdownLabel(this, SWT.NONE);
		toolkit.adapt(dropdownLabel);
		dropdownLabel.addPropertyChangeListener(this);

		composite = toolkit.createComposite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		stackLayout = new StackLayout();
		composite.setLayout(stackLayout);

		toolkit.paintBordersFor(composite);
		//
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setItems(String[] items,
			Class<? extends ModelComposite> compositeClass) {
		dropdownLabel.setItems(items);
		elements = new ModelComposite[items.length];
		Class[] args = new Class[] { Composite.class, Integer.TYPE };
		Object[] paras = new Object[] { composite, SWT.NONE };
		try {
			for (int i = 0; i < items.length; i++) {
				elements[i] = compositeClass.getConstructor(args).newInstance(
						paras);
			}
			stackLayout.topControl = elements[0];
			composite.layout();
			toolkit.paintBordersFor(composite);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (DropdownLabel.SEL_INDEX.equals(evt.getPropertyName())) {
			stackLayout.topControl = elements[(Integer) evt.getNewValue()];
			layout();
			composite.layout();
		}

	}

	public final class MPCoBinding extends ControlBinding implements
			PropertyChangeListener {

		public MPCoBinding(String attribute) {
			super();
			setAttribute(attribute);
			for (int i = 0; i < elements.length; i++) {
				PropertyChangeSupport pcs = (PropertyChangeSupport) elements[i]
						.getModel().getAdapter(PropertyChangeSupport.class);
				if (pcs != null)
					pcs.addPropertyChangeListener(this);
			}
		}

		public void handleEvent(Event event) {

		}

		public void propertyChange(PropertyChangeEvent evt) {
			TreeMap oldValue = (TreeMap) getValue();
			TreeMap newValue;
			if (oldValue != null)
				newValue = (TreeMap) oldValue.clone();
			else
				newValue = new TreeMap();

			newValue.put(
					dropdownLabel.getItems()[dropdownLabel.getSelection()], evt
							.getSource());
			value  = newValue;
			firePropertyChange(new PropertyChangeEvent(this,
					ControlBinding.BINDING_VAL, oldValue, newValue));
		}

		@Override
		public void setValue(Object o) {
			TreeMap newMap = (TreeMap) o;
			String[] items = dropdownLabel.getItems();

			if (newMap == null) {
				for (int i = 0; i < items.length; i++) {
					PropertyChangeSupport pcs = (PropertyChangeSupport) elements[i]
							.getModel().getAdapter(PropertyChangeSupport.class);
					pcs.removePropertyChangeListener(this);
					elements[i].setModel(null);
					pcs = (PropertyChangeSupport) elements[i].getModel()
							.getAdapter(PropertyChangeSupport.class);
					pcs.addPropertyChangeListener(this);
				}
				return;
			}
			this.value = o;
			for (int i = 0; i < items.length; i++) {
				if (newMap.containsKey(items[i])) {
					PropertyChangeSupport pcs = (PropertyChangeSupport) elements[i]
							.getModel().getAdapter(PropertyChangeSupport.class);
					pcs.removePropertyChangeListener(this);
					elements[i].setModel((IAdaptable) newMap.get(items[i]));
					pcs = (PropertyChangeSupport) elements[i].getModel()
							.getAdapter(PropertyChangeSupport.class);
					pcs.addPropertyChangeListener(this);
				}
			}
		}
	}
}
