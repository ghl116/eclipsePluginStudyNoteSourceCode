package rcpdev.contact.ui.views.search;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import rcpdev.common.ui.databinding.BindingManager;
import rcpdev.common.ui.databinding.TextBinding;
import rcpdev.contact.ui.common.ModelComposite;

public class SearchComposite extends ModelComposite {
	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	private Text companyText;

	private Text nameText;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public SearchComposite(Composite parent, int style) {
		super(parent, style);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		setLayout(gridLayout);

		toolkit.createLabel(this, "Name:", SWT.NONE);

		nameText = toolkit.createText(this, null, SWT.NONE);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		toolkit.createLabel(this, "Company:", SWT.NONE);

		companyText = toolkit.createText(this, null, SWT.NONE);
		companyText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		//
		initManager();
	}

	protected void initManager() {
		model = new SearchBean();
		manager = new BindingManager(model);
		manager.addBinding(new TextBinding("name", nameText));
		manager.addBinding(new TextBinding("company", companyText));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	protected IAdaptable createEmptyModel() {
		return new SearchBean();
	}

}
