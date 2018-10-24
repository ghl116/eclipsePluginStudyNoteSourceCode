package rcpdev.common.ui.databinding;

import java.beans.PropertyChangeListener;

public interface IBinding {

	public static final String BINDING_VAL = "bindingVal";

	public String getAttribute();

	public Object getValue();

	public void setValue(Object value);

	public void addPropertyChangeListener(PropertyChangeListener listener);

	public void removePropertyChangeListener(PropertyChangeListener listener);
}
