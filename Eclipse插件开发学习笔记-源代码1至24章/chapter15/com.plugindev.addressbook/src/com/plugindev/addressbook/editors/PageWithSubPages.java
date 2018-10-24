package com.plugindev.addressbook.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.forms.*;
import org.eclipse.ui.forms.editor.*;
import org.eclipse.ui.forms.widgets.*;

import com.plugindev.addressbook.editors.models.AddressList;
import com.plugindev.addressbook.editors.models.ChoiceItemContents;
import com.plugindev.addressbook.editors.models.IManagerListener;
import com.plugindev.addressbook.editors.models.SimpleFormEditorInput;
import com.plugindev.addressbook.editors.models.TextItemContents;
import com.plugindev.addressbook.util.ImageCache;
import com.plugindev.addressbook.util.ImageKeys;
import com.plugindev.addressbook.util.Messages;
/**
 *
 */
public class PageWithSubPages extends FormPage implements IManagerListener{
	private FormEditor editor;
	private CTabFolder tabFolder;
	
	//在13.5节加入此字段
	//informations<页面名称, 页面内容>
	private Map<String, String> informations;
	
	private Text text;
	private Font font;
	
	public class TextSection {
		String text;
		public TextSection(String text) {
			this.text = text;
			}
		public void setText(String text){
			this.text = text;
		}
		public String getText(){
			return text;
		}
	}
	/**
	 * @param id
	 * @param title
	 */
	public PageWithSubPages(FormEditor editor) {
		super(editor, "pageWithSub", Messages.PAGE_NAME_PAGEWITHSUB); 
		this.editor = editor;
		
		//在13.5节加入此段代码
		informations = new HashMap<String, String>();
		//结束在13.5节加入的此段代码
	}
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText(Messages.PAGE_TITLE_PAGEWITHSUB); 
		form.setBackgroundImage(ImageCache.getInstance().getImage(ImageKeys.getImageDescriptor(ImageKeys.IMG_FORM_BG)));
		GridLayout layout = new GridLayout();
		layout.marginWidth = 10;
		form.getBody().setLayout(layout);
		tabFolder = new CTabFolder(form.getBody(), SWT.FLAT|SWT.TOP);
		toolkit.adapt(tabFolder, true, true);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 0;
		tabFolder.setLayoutData(gd);
		Color selectedColor = toolkit.getColors().getColor(FormColors.SEPARATOR);
		tabFolder.setSelectionBackground(new Color[] {selectedColor, toolkit.getColors().getBackground()}, new int[] {50});

		toolkit.paintBordersFor(tabFolder);
		
		createTabs(/*toolkit*/);
		createText(toolkit, form.getBody());
		
		tabFolder.setSelection(0);
		
		//在13.5节加入此段代码
		tabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateSelection();
			}
		});
		SimpleFormEditorInput input = (SimpleFormEditorInput)editor.getEditorInput();
		input.getManager().addManagerListener(this);
		
		Object[] contents = input.getManager().getContents();
		for(int i = 0; i < contents.length; i++){
			AddressList list = (AddressList)contents[i];
			informations.put(list.toString(),addressList2String(list));
		}
		updateSelection();
		//结束在13.5节加入的此段代码
	}
	
	/*
	 * 在13.5节加入此方法
	 */
	private String addressList2String(AddressList list){
		StringBuffer buffer = new StringBuffer();
		
		Map choiceMap = list.getChoiceContentMap();
		Map textContentMap = list.getTextContentMap();
		Iterator choiceIter = choiceMap.keySet().iterator();
		Iterator textContentIter = textContentMap.keySet().iterator();
		while(choiceIter.hasNext()){
			String key = (String)choiceIter.next();
			ChoiceItemContents item = (ChoiceItemContents)choiceMap.get(key);
			Object[] choiceStrings = item.getChoices();
			int choiceValue = item.getValue();
			buffer.append(key + ": " + choiceStrings[choiceValue] + "\n");
		}
		while(textContentIter.hasNext()){
			String key = (String)textContentIter.next();
			TextItemContents item = (TextItemContents)textContentMap.get(key); 
			buffer.append(key + ": " + item.getValue() + "\n");
		}
		return buffer.toString();
	}
	private void createTabs() {
		//加载Tab页，该Tab页根据MasterDetail页面中项的多少而增加
		Object[] contents = ((SimpleFormEditorInput)editor.getEditorInput()).getManager().getContents();
		for(int i = 0; i < contents.length; i++)
		{
			createTab(/*toolkit,*/contents[i].toString(), "");
		}
	}
	private void createText(FormToolkit toolkit, Composite parent) {
		Composite tabContent = toolkit.createComposite(parent);
		tabContent.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		tabContent.setLayout(layout);
		layout.numColumns = 2;
		layout.marginWidth = 0;
		GridData gd;
		text = toolkit.createText(tabContent, "", SWT.MULTI|SWT.WRAP); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 2;
		text.setLayoutData(gd);
		
		text.setEditable(false);
		
		Button fontButton = toolkit.createButton(tabContent, Messages.BUTTON_FONT, SWT.PUSH);
		gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 2;
		fontButton.setLayoutData(gd);
		/*
		 * 在15.2小节中加入该段代码
		 */
		fontButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setFont();
			}
		});
		/*
		 * 结束在15.2小节中加入的该段代码
		 */
	}
void setFont() {
	FontDialog fontDialog = new FontDialog(getSite().getShell());
	fontDialog.setFontList(text.getFont().getFontData());
	FontData fontData = fontDialog.open();
	if (fontData != null) {
		if (font != null)
			font.dispose();
		font = new Font(text.getDisplay(), fontData);
		text.setFont(font);
	}
}
	private void createTab(String title, String content) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);
		TextSection section = new TextSection(content);
		item.setText(title);
		item.setData(section);
	}
	
	/*
	 * 	在13.5节中加入此方法
	 */
	public void updateSelection() {
		if(tabFolder == null)
			return;
		CTabItem item = tabFolder.getSelection();
		TextSection section = (TextSection)item.getData();
		section.setText((String)informations.get(item.getText()));
		text.setText(section.getText());
	}
	/*
	 * 此方法在13.5节加入
	 * @see com.plugindev.addressbook.editors.models.IManagerListener#managerChanged(java.lang.Object[], java.lang.String, java.lang.String, java.lang.String)
	 */
	//object-改动的AddressList;type-事件类型;itemType-修改的类型（文本或选择框）;更改的值所对应的键名称
	public void managerChanged(Object object, String type, String itemType, String key) {
		AddressList list = (AddressList)object;
		if(type == IManagerListener.CHANGED)
			informations.put(list.toString(),addressList2String(list));
		//在13.8节加入此段代码
		if(type == IManagerListener.REMOVED)
		{
			tabFolder.setRedraw(false);
			CTabItem[] items = tabFolder.getItems();
			for(int j = 0; j < items.length;j++)
			{
				if(items[j].getText().equals(list.toString()))
					items[j].dispose();
			}
/*				CTabItem item = tabFolder.getItem(list.getSequence());
				item.dispose();*/
			informations.remove(list.toString());
			tabFolder.setRedraw(true);
		}
		//结束在13.8节加入的此段代码
		
		if(type == IManagerListener.ADDED)
		{
			tabFolder.setRedraw(false);
			informations.put(list.toString(), addressList2String(list));
			createTab(list.toString(), informations.get(list.toString()));
			tabFolder.setRedraw(true);
		}
	}
}