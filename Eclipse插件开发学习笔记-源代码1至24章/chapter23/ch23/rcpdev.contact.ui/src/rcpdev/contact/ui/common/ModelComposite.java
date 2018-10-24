package rcpdev.contact.ui.common;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.widgets.Composite;

import rcpdev.common.ui.databinding.BindingManager;

public abstract class ModelComposite extends Composite {

	protected BindingManager manager;

	protected IAdaptable model;

	public ModelComposite(Composite parent, int style) {
		super(parent, style);
	}

	public BindingManager getManager() {
		return manager;
	}
	
	public IAdaptable getModel() {
		return model;
	}

	public void setModel(IAdaptable model) {
		if (model == null)
			manager.setBean(createEmptyModel());
		else
			manager.setBean(model);
		this.model = manager.getBean();
	}

	protected abstract IAdaptable createEmptyModel();

}
