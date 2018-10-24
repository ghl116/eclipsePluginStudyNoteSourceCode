package com.plugindev.addressbook.wizards;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.plugindev.addressbook.editors.models.AddressList;
import com.plugindev.addressbook.editors.models.MasterContentProvider;
import com.plugindev.addressbook.editors.models.SimpleFormEditorInput;
import com.plugindev.addressbook.models.AddressCategory;
import com.plugindev.addressbook.models.AddressItem;

public class EditListsConfigWizardPage extends WizardPage {

	   private CheckboxTableViewer checkboxTableViewer;
	   private AddressItem item;
	   private SimpleFormEditorInput input;
	   private Object[] checked;
	   
	public EditListsConfigWizardPage(){
		super("选择编辑列表");
		setTitle("设置编辑列表");
		setDescription("从系统默认的编辑列表项中选择需要的编辑列表");
	}
	public void createControl(Composite parent) {
		// TODO 自动生成方法存根
	      Composite container = new Composite(parent, SWT.NULL);
	      container.setLayout(new FormLayout());
	      setControl(container);

	      checkboxTableViewer =
	         CheckboxTableViewer.newCheckList(container, SWT.BORDER);
	      checkboxTableViewer.setContentProvider(
	         new MasterContentProvider());
	      checkboxTableViewer.setLabelProvider(
	         new TableViewerLabelProvider());
	      final Table table = checkboxTableViewer.getTable();
	      final FormData formData = new FormData();
	      formData.bottom = new FormAttachment(100, 0);
	      formData.right = new FormAttachment(100, 0);
	      formData.top = new FormAttachment(0, 0);
	      formData.left = new FormAttachment(0, 0);
	      table.setLayoutData(formData);
	      table.setHeaderVisible(true);

	      final TableColumn tableColumn =
	         new TableColumn(table, SWT.NONE);
	      tableColumn.setWidth(200);
	      tableColumn.setText("列表名称");

	      final TableColumn tableColumn_1 =
	         new TableColumn(table, SWT.NONE);
	      tableColumn_1.setWidth(250);
	      tableColumn_1.setText("列表描述");
	   }

	   /**
	    * Update the content before becoming visible.
	    */
	public void setVisible(boolean visible) {
		if (visible) {
			AddressCategory category = ((NewAddressItemWizardPage)getPreviousPage())
			.getSelectedAddressCategory();
			String peopleName = ((NewAddressItemWizardPage)getPreviousPage())
			.getSelectedName();
			item = new AddressItem(peopleName, category);
			input = new SimpleFormEditorInput(item.getName());
			checkboxTableViewer.setInput(input);
			checkboxTableViewer.setAllChecked(true);
		}
		super.setVisible(visible);
	}

	   /**
	    * Return the currently selected strings.
	    */
	   public AddressList[] getSelection() {
		  checked = checkboxTableViewer.getCheckedElements();
	      int count = checked.length;
	      AddressList[] extracted = new AddressList[count];
	      System.arraycopy(checked, 0, extracted, 0, count);
	      return extracted;
	   }
	   public AddressItem getAddressItem(){
		   return item;
	   }
	   public SimpleFormEditorInput getEditorInput(){
		   return input;
	   }
}
