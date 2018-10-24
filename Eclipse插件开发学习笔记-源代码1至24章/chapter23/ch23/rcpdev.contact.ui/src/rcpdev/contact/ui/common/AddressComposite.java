package rcpdev.contact.ui.common;

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
import rcpdev.contact.core.model.contact.Address;
import rcpdev.contact.ui.common.lang.Messages;

public class AddressComposite extends ModelComposite {

	private Text detailText;

	private Text cityText;

	private Text stateText;

	private Text countryText;

	private Text zipCodeText;

	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public AddressComposite(Composite parent, int style) {
		super(parent, style);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 10;
		gridLayout.marginRight = 3;
		gridLayout.marginBottom = 5;
		gridLayout.marginTop = 5;
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		gridLayout.numColumns = 2;
		setLayout(gridLayout);

		toolkit.createLabel(this, Messages.getString("AddressComposite.zipCode"), SWT.NONE); //$NON-NLS-1$

		zipCodeText = toolkit.createText(this, null);
		final GridData zipCodeGridData = new GridData(SWT.FILL, SWT.CENTER,
				true, false);
		zipCodeGridData.heightHint = 15;
		zipCodeText.setLayoutData(zipCodeGridData);

		toolkit.createLabel(this, Messages.getString("AddressComposite.country"), SWT.NONE); //$NON-NLS-1$

		countryText = toolkit.createText(this, null, SWT.NONE);
		countryText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		toolkit.createLabel(this, Messages.getString("AddressComposite.state"), SWT.NONE); //$NON-NLS-1$

		stateText = toolkit.createText(this, null, SWT.NONE);
		stateText
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		toolkit.createLabel(this, Messages.getString("AddressComposite.city"), SWT.NONE); //$NON-NLS-1$

		cityText = toolkit.createText(this, null, SWT.NONE);
		cityText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Label detailLabel = toolkit.createLabel(this, Messages.getString("AddressComposite.detailAddress"), //$NON-NLS-1$
				SWT.NONE);
		detailLabel
				.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));

		detailText = toolkit.createText(this, null, SWT.WRAP | SWT.MULTI);
		final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		gridData.heightHint = 60;
		detailText.setLayoutData(gridData);

		toolkit.paintBordersFor(this);
		//
		initBindingManager();
	}

	protected void initBindingManager() {
		model = createEmptyModel();
		manager = new BindingManager(model);
		manager.addBinding(new TextBinding("country", countryText)); //$NON-NLS-1$
		manager.addBinding(new TextBinding("state", stateText)); //$NON-NLS-1$
		manager.addBinding(new TextBinding("city", cityText)); //$NON-NLS-1$
		manager.addBinding(new TextBinding("postalCode", zipCodeText)); //$NON-NLS-1$
		manager.addBinding(new TextBinding("detail", detailText)); //$NON-NLS-1$
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public Address getAddress() {
		return (Address) getModel();
	}

	@Override
	protected IAdaptable createEmptyModel() {
		return new Address();
	}

}
