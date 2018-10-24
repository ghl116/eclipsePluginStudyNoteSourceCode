package rcpdev.common.core.mediator;

import java.beans.PropertyChangeListener;

public interface IMediatorSender {
	public void addPropertyChangeListener(PropertyChangeListener listener);

	public void removePropertyChangeListener(PropertyChangeListener listener);
}
