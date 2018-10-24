package rcpdev.common.ui.javabeans;

import org.eclipse.swt.widgets.Composite;

import rcpdev.common.ui.databinding.AbstractBean;
import rcpdev.common.ui.databinding.BindingManager;

public abstract class AbstractComposite extends Composite {

	private BindingManager manager;

	private AbstractBean bean;

	public AbstractComposite(Composite parent, int style) {
		super(parent, style);
		createControls();
		initManager();
	}

	public AbstractBean getBean() {
		return bean;
	}

	public void setBean(AbstractBean bean) {
		this.bean = bean;
		manager.setBean(bean);
		manager.loadAll();
	}

	public BindingManager getManager() {
		return manager;
	}

	public void setManager(BindingManager manager) {
		this.manager = manager;
	}

	protected abstract void createControls();

	protected void initManager() {
		manager = new BindingManager(createEmptyBean());
		createBindings();
	}

	protected abstract void createBindings();

	protected abstract AbstractBean createEmptyBean();
}
