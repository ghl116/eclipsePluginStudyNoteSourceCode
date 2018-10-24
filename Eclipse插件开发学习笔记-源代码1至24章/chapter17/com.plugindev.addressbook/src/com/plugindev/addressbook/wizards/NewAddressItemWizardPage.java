package com.plugindev.addressbook.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import com.plugindev.addressbook.models.AddressCategory;
import com.plugindev.addressbook.models.AddressItem;
import com.plugindev.addressbook.models.AddressManager;
import com.plugindev.addressbook.util.ImageKeys;

public class NewAddressItemWizardPage extends WizardPage {

	private Combo combo;
	private Text text;
	private AddressCategory selectedCategory;
	private String name;
	
	/**
	 * Create the wizard
	 */
	public NewAddressItemWizardPage() {
		super("新建地址项");
		setTitle("创建地址元素");
		setDescription("创建一个新的地址元素");
		setImageDescriptor(ImageKeys.
				getImageDescriptor(ImageKeys.IMG_WIZARD_NEW));
	}

	/**
	 * Create contents of the wizard
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		//
		setControl(container);

		final Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		label.setText("为将要添加的地址元素创建名称。");

		final Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		label_1.setText("设定元素名称：");

		text = new Text(container, SWT.BORDER);
		final GridData gd_text = new GridData(SWT.CENTER, SWT.CENTER, true, false);
		gd_text.widthHint = 117;
		text.setLayoutData(gd_text);
		text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				name = text.getText();
				updatePageComplete();
			}
		});

		final Label label_2 = new Label(container, SWT.NONE);
		label_2.setLayoutData(new GridData());
		label_2.setText("为要添加的地址元素指定类别。");
		new Label(container, SWT.NONE);

		final Label label_3 = new Label(container, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		label_3.setText("选择元素类别：");

		combo = new Combo(container, SWT.NONE);
		final GridData gd_combo = new GridData(SWT.CENTER, SWT.CENTER, false, false);
		gd_combo.widthHint = 96;
		combo.setLayoutData(gd_combo);
		combo.addSelectionListener(new SelectionListener(){
			public void widgetDefaultSelected(SelectionEvent e) {}
			public void widgetSelected(SelectionEvent e) {
				Combo combo = (Combo)e.getSource();
				String str = combo.getText();
				selectedCategory = AddressCategory.getCategoryByName(str);
				updatePageComplete();
			}
		});
		
		AddressCategory[] allCategories = AddressCategory.getTypes();
		
		for(int i = 0; i <allCategories.length; i++)
		{
			String categoryName = allCategories[i].getCategoryName();
			combo.add(categoryName);
		}
		/*
		 * 在第17章增加下面两个判断，来为cheatsheet提供支持
		 */
		if(this.name != null){
			text.setText(this.name);
			text.setEditable(false);
		}
		if(this.selectedCategory != null){
			combo.setText(this.selectedCategory.getCategoryName());
			combo.setEnabled(false);
		}
		updatePageComplete();
	}
	public AddressCategory getSelectedAddressCategory(){
		return this.selectedCategory;
	}
	public String getSelectedName(){
		return this.name;
	}
	public void updatePageComplete(){
		setPageComplete(false);
		AddressManager manager = AddressManager.getManager();
		if(name == null || name.equals("")){
			setMessage(null);
			setErrorMessage("请输入要创建的地址元素的名称");
			return;
		}
		AddressItem[] items = manager.getAddresses();
		for(int i = 0; i < items.length; i++){
			if(items[i].getName().equals(name))
			{
				setMessage(null);
				setMessage("此名称已存在，请设置另外的名称！",WARNING);
				return;
			}
		}
		if(selectedCategory == null){
			setMessage(null);
			setErrorMessage("请选择要创建的地址元素的类别");
			return;
		}
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	/*
	 * 在第17章增加下面两个方法，来为cheatsheet提供支持
	 */
	public void setName(String name){
		this.name = name;
		text.setText(this.name);
		text.setEditable(false);
	}
	public void setCategory(String categoryName){
		this.selectedCategory = AddressCategory.getCategoryByName(categoryName);
		combo.setText(this.selectedCategory.getCategoryName());
		combo.setEnabled(false);
	}
}
