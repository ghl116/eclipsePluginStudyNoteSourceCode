package com.plugindev.addressbook.editors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.plugindev.addressbook.Activator;
import com.plugindev.addressbook.util.ImageCache;
import com.plugindev.addressbook.util.ImageKeys;
import com.plugindev.addressbook.util.Messages;

public class SourcePage extends FormPage {
	private String peopleName;
	private Text text;
	public SourcePage(FormEditor editor){
		super(editor, "source",Messages.PAGE_NAME_SOURCE);
		this.peopleName = editor.getEditorInput().getName();
	}
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText(Messages.PAGE_NAME_SOURCE);
		form.setBackgroundImage(ImageCache.getInstance().getImage(ImageKeys.getImageDescriptor(ImageKeys.IMG_FORM_BG)));
		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		form.getBody().setLayout(layout);
		createFormTextSection(form, toolkit);
	}
	
	private void createFormTextSection(final ScrolledForm form, FormToolkit toolkit) {
		Section section =
			toolkit.createSection(
				form.getBody(),
				Section.TWISTIE | Section.DESCRIPTION);
		section.setActiveToggleColor(
			toolkit.getHyperlinkGroup().getActiveForeground());
		section.setToggleColor(
			toolkit.getColors().getColor(FormColors.SEPARATOR));
		toolkit.createCompositeSeparator(section);
		text = toolkit.createText(section, "源代码", SWT.WRAP|SWT.FLAT|SWT.READ_ONLY);
		loadSource();
		section.setClient(text);

		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(false);
			}
		});

		section.setText("显示源代码");
		section.setDescription("显示源代码文件中的内容");
		TableWrapData td = new TableWrapData();
		td.align = TableWrapData.FILL;
		td.grabHorizontal = true;
		section.setLayoutData(td);
		td = new TableWrapData();
		td.align = TableWrapData.CENTER;
		text.setLayoutData(td);
		text.setFont(new Font(text.getDisplay(), new FontData("Tahoma", 9, SWT.NORMAL)));
		text.getBorderWidth();
	}
	/*
	 * 在13.6小节加入，从文件中读取源代码
	 */
	public void loadSource() {
		if(text == null)
			return;
		File file = Activator.getDefault().getStateLocation().
		append(peopleName +".addr").toFile();
		if(file.exists() == false){
			text.setText("");
			return;
		}
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String temp = reader.readLine();

			while(temp != null)
			{
				if(temp.contains("<list") || temp.contains("</list>"))
					buffer.append("\t");
				else if(temp.contains("<textItem"))
					buffer.append("\t\t");
				else if(temp.contains("<choiceItem")|| temp.contains("</choiceItem>"))
					buffer.append("\t\t");
				else if(temp.contains("<choiceString"))
					buffer.append("\t\t\t");
				buffer.append(temp);
				buffer.append("\n");
				temp = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}finally{
			text.setText(buffer.toString());
		}
	}
}
