package rcpdev.contact.ui.common;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

import rcpdev.contact.ui.common.mp.IMasterPart;
import rcpdev.contact.ui.common.mp.MultiPageBlock;

public class DropdownLink extends Composite implements IMasterPart {

	private static final String KEY_INDEX = "KEY_INDEX";

	final Hyperlink contentLink;

	final Button dropdownButton;

	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	private final Menu popMenu;

	private final Composite composite;

	private int selIndex = 0;

	private String[] items;
	
	private boolean[] checked;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public DropdownLink(Composite parent, int style) {
		super(parent, style);
		composite = this;
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		gridLayout.numColumns = 2;
		setLayout(gridLayout);

		contentLink = toolkit.createHyperlink(this, "", SWT.NONE);
		contentLink.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		dropdownButton = toolkit.createButton(this, "New Forms Button",
				SWT.ARROW | SWT.DOWN);
		dropdownButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				Rectangle buttonRange = contentLink.getBounds();
				popMenu.setLocation(composite.toDisplay(buttonRange.x,
						buttonRange.y + buttonRange.height + 3));
				for(int i = 0 ; i < checked.length ; i++)
					popMenu.getItem(i).setSelection(checked[i]);
				popMenu.setVisible(true);
			}
		});

		popMenu = new Menu(this);

		//
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public Hyperlink getcontentLink() {
		return contentLink;
	}

	public Button getDropdownButton() {
		return dropdownButton;
	}

	public Menu getPopMenu() {
		return popMenu;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public String getText() {
		return contentLink.getText();
	}

	public void setText(String text) {
		contentLink.setText(text);
	}

	public void setItems(String[] items) {
		this.items = items;
		this.checked = new boolean[items.length];
		for (int i = 0; i < items.length; i++) {
			MenuItem item = new MenuItem(popMenu, SWT.CHECK);
			item.setText(items[i]);
			item.setData(KEY_INDEX, i);
			selIndex = 0;
			contentLink.setText(items[0]);
			item.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent event) {
					MenuItem item = (MenuItem) event.widget;
					int curSel = (Integer) item.getData(KEY_INDEX);
					int oldVal = selIndex;
					selIndex = curSel;
					if (curSel != oldVal) {
						contentLink.setText(item.getText());
						composite.layout();
						firePropertyChange(new PropertyChangeEvent(composite,
								SEL_INDEX, oldVal, curSel));
					}
				}
			});
		}
	}

	private PropertyChangeSupport delegate = new PropertyChangeSupport(this);

	public static final String SEL_INDEX = MultiPageBlock.SEL_CHANGE;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate.addPropertyChangeListener(listener);
	}

	public void firePropertyChange(PropertyChangeEvent evt) {
		delegate.firePropertyChange(evt);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate.removePropertyChangeListener(listener);
	}

	public PropertyChangeSupport getDelegate() {
		return delegate;
	}

	public int getSelection() {
		return selIndex;
	}

	public String[] getItems() {
		return items;
	}

	public void valueChanged(int index,boolean checked) {
		this.checked[index] = checked;
	}

}
