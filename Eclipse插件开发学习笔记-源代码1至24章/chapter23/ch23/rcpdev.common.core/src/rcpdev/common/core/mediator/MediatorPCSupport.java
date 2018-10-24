package rcpdev.common.core.mediator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MediatorPCSupport extends PropertyChangeSupport implements
		IMediatorSender {

	public MediatorPCSupport(Object sourceBean) {
		super(sourceBean);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2618854772391584878L;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PropertyChangeListener[] listeners = getPropertyChangeListeners();
		for (int i = 0; i < listeners.length; i++)
			if (listeners[i].equals(listener))
				return;
		super.addPropertyChangeListener(listener);
	}

}
