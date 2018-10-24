package rcpdev.contact.ui.editors.contact;

import org.eclipse.swt.widgets.Composite;

public class DetailComposite extends Composite {

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public DetailComposite(Composite parent, int style) {
		super(parent, style);
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

}
