package rcpdev.contact.ui.common.mp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.TreeMap;

import org.eclipse.swt.widgets.Event;

import rcpdev.common.core.utils.StringUtils;
import rcpdev.common.ui.databinding.ControlBinding;

public class MultiPageBlock implements PropertyChangeListener {

	public static final String SEL_CHANGE = "selChange";

	public static final String VAL_CHANGE = "valChange";

	private IMasterPart masterPart;

	private IDetailsPart detailsPart;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public MultiPageBlock(IMasterPart masterPart, IDetailsPart detailsPart) {
		this.masterPart = masterPart;
		this.detailsPart = detailsPart;
		masterPart.getDelegate().addPropertyChangeListener(this);
		detailsPart.getDelegate().addPropertyChangeListener(this);
		//
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (SEL_CHANGE.equals(evt.getPropertyName())) {
			detailsPart.changePage((Integer) evt.getNewValue());
		}
		if (VAL_CHANGE.equals(evt.getPropertyName())) {
			Object[] change = (Object[]) evt.getNewValue();
			int index = (Integer) change[0];
			Object value = change[1];
			masterPart.valueChanged(index, value != null
					&& !StringUtils.isEmpty(value.toString()));
		}
	}

	public class BlockBinding extends ControlBinding implements
			PropertyChangeListener {

		public BlockBinding(String attribute) {
			super();
			setAttribute(attribute);
			detailsPart.getDelegate().addPropertyChangeListener(this);
		}

		public void handleEvent(Event event) {

		}

		public void propertyChange(PropertyChangeEvent evt) {
			TreeMap oldValue = (TreeMap) getValue();
			TreeMap newValue;
			if (oldValue != null) {
				newValue = (TreeMap) oldValue.clone();
			} else {
				newValue = new TreeMap();
			}
			if (VAL_CHANGE.equals(evt.getPropertyName())) {
				String key = masterPart.getItems()[masterPart.getSelection()];
				Object val = ((Object[]) evt.getNewValue())[1];
				newValue.put(key, val);
				this.value = newValue;
				firePropertyChange(new PropertyChangeEvent(this,
						ControlBinding.BINDING_VAL, oldValue, newValue));
			}
		}

		@Override
		public void setValue(Object newValue) {
			this.value = newValue;
			TreeMap newMap = (TreeMap) newValue;
			String[] items = masterPart.getItems();
			detailsPart.getDelegate().removePropertyChangeListener(this);
			if (newMap == null) {
				for (int i = 0; i < items.length; i++)
					detailsPart.setValue(i, null);
			} else
				for (int i = 0; i < items.length; i++) {
					if (newMap.containsKey(items[i])) {
						masterPart.valueChanged(i, true);
						detailsPart.setValue(i, newMap.get(items[i]));
					}
				}
			detailsPart.getDelegate().addPropertyChangeListener(this);
		}
	}
}
