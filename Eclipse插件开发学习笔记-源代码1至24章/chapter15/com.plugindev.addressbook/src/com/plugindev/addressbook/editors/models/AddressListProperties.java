package com.plugindev.addressbook.editors.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.plugindev.addressbook.util.Messages;

public class AddressListProperties implements IDetailsPage {

	protected IManagedForm mform;
	private AddressList input;
	private Button [][] choices;
	private Text[] texts;
	private Map<Text, String> textMap;
	private Map<Button, String> buttonMap;
	
	private Section s1;
	
	//在13.7节加入该字段
	private boolean updated;
	//结束在13.7节的加入
	
	private static final String RTEXT_DATA =
			"<form><p>本项内容用来设置每个人员的个人信息，"+
			"这个信息将会在其他两个页面中以不同的方式显示</p>"+
			"<p>如果有问题请联系作者<b>张鹏</b>.</p>"+
			"<li>email:<a>nemo.zhp@gmail.com</a></li>"+
			"<li>网址：http://www.blogjava.net/nemo-zhp</li>"+
			"</form>";
	public AddressListProperties() {
	}

	public void initialize(IManagedForm mform) {
		this.mform = mform;
		this.textMap = new HashMap<Text, String>();
		this.buttonMap = new HashMap<Button, String>();
	}

	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);
	}
	protected void createSection(Composite parent,
			ArrayList<String> stringKeys, Map<String, Object[]>choiceKeysMap)
	{
		FormToolkit toolkit = mform.getToolkit();
		s1 = toolkit.createSection(parent, Section.DESCRIPTION|Section.TITLE_BAR);
		s1.marginWidth = 10;
		
		TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		s1.setLayoutData(td);
		
		Composite client = toolkit.createComposite(s1);
		GridLayout glayout = new GridLayout();
		glayout.marginWidth = glayout.marginHeight = 0;
		glayout.numColumns = 2;
		client.setLayout(glayout);
		
		createChoiceItem(toolkit, client, choiceKeysMap);
		createTextItem(toolkit, client, stringKeys);
		
		FormText rtext = toolkit.createFormText(parent, true);
		rtext.setText(RTEXT_DATA, true, false);
		td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		rtext.setLayoutData(td);
		
		toolkit.paintBordersFor(s1);
		s1.setClient(client);
	}
	private void createTextItem(
			FormToolkit toolkit,Composite parent, ArrayList<String> textKeys){
		texts = new Text[textKeys.size()];
		Iterator iter = textKeys.iterator();
		for(int i = 0; iter.hasNext(); i++){
			final String key = (String)iter.next();
			toolkit.createLabel(parent, key);
			texts[i] = toolkit.createText(parent, "");
			GridData gd = new GridData(GridData.FILL_HORIZONTAL|GridData.VERTICAL_ALIGN_BEGINNING);
			gd.widthHint = 10;
			texts[i].setLayoutData(gd);
			textMap.put(texts[i], key);
			texts[i].addModifyListener(new ModifyListener(){
				public void modifyText(ModifyEvent e){
					//在13.7节加入
					if(updated == true)
						return;
					//结束在13.7节的加入
					Text text = (Text)e.getSource();
					if(input != null)
						input.setStringValue(textMap.get(text), text.getText());
				}
			});
			createSpacer(toolkit, parent, 2);
		}
	}
	private void createChoiceItem(
			FormToolkit toolkit, Composite parent, Map<String, Object[]> choiceKeysMap){
		//有keys.size()个选择项
		Iterator iter = choiceKeysMap.keySet().iterator();
		choices = new Button[choiceKeysMap.size()][];
		
		//遍历choiceContentsItems，
		//为每个choiceItem创建一个标签和多个选项按钮
		//并且为每个choiceContentsItem添加一个选择监听器
		for(int i = 0;iter.hasNext(); i++){
			GridData gd;
			SelectionListener choiceListener = new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					//在13.7节加入
					if(updated == true)
						return;
					//结束在13.7节的加入
					Button button = (Button)e.getSource();
					Integer value = (Integer)e.widget.getData();
					if (input!=null) {
						input.setIntValue(buttonMap.get(button), value);
					}
				}
			};
			String key = (String)iter.next();
			
			//在此处创建Label
			Label label = toolkit.createLabel(parent, key + ": ");
			GridData gdata = new GridData(GridData.FILL_HORIZONTAL);
			gdata.horizontalSpan = 2;
			label.setLayoutData(gdata);
			
			Object[] choiceStrings = (Object[])choiceKeysMap.get(key);
			choices[i]= new Button[choiceStrings.length];
			
			//为选择按钮添加选择监听器，
			//在同一个choiceContentItem中的一组选择按钮对应相同的选择监听器。
			for(int j = 0; j < choices[i].length; j++)
			{
				choices[i][j] = toolkit.createButton(parent, (String)choiceStrings[j], SWT.RADIO);
				choices[i][j].setData(new Integer(j));
				buttonMap.put(choices[i][j], key);
				choices[i][j].addSelectionListener(choiceListener);
				gd = new GridData();
				gd.horizontalSpan = 1;
				choices[i][j].setLayoutData(gd);
			}
			createSpacer(toolkit, parent, 2);
		}
	}
	
	protected void createSpacer(FormToolkit toolkit, Composite parent, int span) {
		Label spacer = toolkit.createLabel(parent, ""); //$NON-NLS-1$
		GridData gd = new GridData();
		gd.horizontalSpan = span;
		spacer.setLayoutData(gd);
	}
	protected void update() {
		//在13.7节加入
		if(updated == false)
			updated = true;
		//结束在13.7节的加入
		
		//更新模型
		for(int i = 0; i < choices.length; i++)
			for(int j = 0; j < choices[i].length; j++)
			{
				choices[i][j].setSelection(input!= null && input.getIntValue(buttonMap.get(choices[i][j]))== j);
			}
		for(int i = 0; i < texts.length; i++)
		{
			String key = textMap.get(texts[i]);
			texts[i].setText(input!=null && input.getStringValue(key)!= null?input.getStringValue(key):"");
		}
		
		//在13.7节加入
		updated = false;
		//结束在13.7节的加入
	}
	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection)selection;
		if (ssel.size()==1) {
			input = (AddressList)ssel.getFirstElement();
			/*
			 * 在第十五章加入下面的代码，将文本和描述从setContent()中固定实现变为随着选择的不同动态改变
			 * 修复当改变AddressList时，DetailsPart的标题和描述不随之改变的BUG
			 */
			s1.setText(input.getName());
			s1.setDescription(input.getDescription());
		}
		else
			input = null;
		update();
	}
	public void commit(boolean onSave) {
	}
	public void setFocus() {
		choices[0][0].setFocus();
	}
	public void dispose() {
	}
	public boolean isDirty() {
		return false;
	}
	public boolean isStale() {
		return false;
	}
	public void refresh() {
		update();
	}
	public boolean setFormInput(Object input) {
		return false;
	}

}
