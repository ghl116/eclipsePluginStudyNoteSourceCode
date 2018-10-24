package com.plugindev.addressbook.dialogs;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.plugindev.addressbook.models.AddressCategory;

public class AddressViewFilterDialog extends Dialog {

	private String namePattern;
	private Collection<AddressCategory> selectedCategories;
	private Text namePatternField;
	private Map categoryFields;
	
	public AddressViewFilterDialog(Shell parentShell,String namePattern,
		AddressCategory[] selectedCategories) {
		super(parentShell);
		this.namePattern = namePattern;
		this.selectedCategories = new HashSet<AddressCategory>();
		for(int i = 0; i < selectedCategories.length; i++){
			this.selectedCategories.add(selectedCategories[i]);
		}
	}
	protected Control createDialogArea(Composite parent){
		Composite container = (Composite) super.createDialogArea(parent);
	      final GridLayout gridLayout = new GridLayout();
	      gridLayout.numColumns = 2;
	      container.setLayout(gridLayout);
	   
	      final Label filterLabel = new Label(container, SWT.NONE);
	      filterLabel.setLayoutData(new GridData(GridData.BEGINNING,
	            GridData.CENTER, false, false, 2, 1));
	      filterLabel.setText("输入一个名字过滤器 (* = 任何字符串, "
	         + "? = 任何单个字符)" 
	         + "\n或者置空以不选择任何过滤器:");
	   
	      final Label nameLabel = new Label(container, SWT.NONE);
	      nameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
	            false, false));
	      nameLabel.setText("名字:");
	   
	      namePatternField = new Text(container, SWT.BORDER);
	      namePatternField.setLayoutData(new GridData(GridData.FILL,
	            GridData.CENTER, true, false));
	   
	   
	      final Label typesLabel = new Label(container, SWT.NONE);
	      typesLabel.setLayoutData(new GridData(GridData.BEGINNING,
	            GridData.CENTER, false, false, 2, 1));
	      typesLabel.setText("选择将要选择的AddressItem类别:");
	   
	      final Composite checkboxComposite = new Composite(container,
	            SWT.NONE);
	      final GridData gridData_1 = new GridData(GridData.FILL,
	            GridData.FILL, false, false, 2, 1);
	      gridData_1.horizontalIndent = 20;
	      checkboxComposite.setLayoutData(gridData_1);
	      final GridLayout typeCheckboxLayout = new GridLayout();
	      typeCheckboxLayout.numColumns = 2;
	      checkboxComposite.setLayout(typeCheckboxLayout);

	      createCheckboxes(checkboxComposite);
	      initContent();
	      return container;
	}
	private void createCheckboxes(Composite parent){
		categoryFields = new HashMap();
		AddressCategory[] allCategories = AddressCategory.getTypes();
	      for (int i = 0; i < allCategories.length; i++) {
	         final AddressCategory category = allCategories[i];
	         final Button button = new Button(parent, SWT.CHECK);
	         button.setText(category.getCategoryName());
	         categoryFields.put(category, button);
	         button.addSelectionListener(new SelectionAdapter() {
	            public void widgetSelected(SelectionEvent e) {
	               if (button.getSelection())
	            	   selectedCategories.add(category);
	               else
	            	   selectedCategories.remove(category);
	            }
	         });
	      }
	}
	private void initContent(){
	      namePatternField.setText(namePattern != null ? namePattern : "");
	      namePatternField.addModifyListener(new ModifyListener() {
	         public void modifyText(ModifyEvent e) {
	            namePattern = namePatternField.getText();
	         }
	      });
	   
	      AddressCategory[] allCategories = AddressCategory.getTypes();
	      for (int i = 0; i < allCategories.length; i++) {
	    	  AddressCategory eachType = allCategories[i];
	         Button button = (Button) categoryFields.get(eachType);
	         button.setSelection(selectedCategories.contains(eachType));
	      }
	}
	protected void configureShell(Shell newShell){
		super.configureShell(newShell);
		newShell.setText("\"地址本\"视图过滤");
	}
	public String getNamePattern(){
		return this.namePattern;
	}
	public AddressCategory[] getSelectedCategories(){
		return this.selectedCategories.toArray(
				new AddressCategory[selectedCategories.size()]);
	}

}
