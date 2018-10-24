package com.plugindev.addressbook.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import com.plugindev.addressbook.Activator;
import com.swtdesigner.ResourceManager;

public class AddressRootPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * Create the preference page
	 */
	public AddressRootPreferencePage() {
		super();
	}

	/**
	 * Create contents of the preference page
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		final Label label = new Label(container, SWT.NONE);
		label.setBackgroundImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/preferences/contact.jpg"));
		label.setBounds(0, 0, 400, 230);

		final Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("地址本插件" + "\n  版本1.0.0Beta 于 2007年7月1日编译通过。"
						+"\n   "+"目的是为了全面演示Eclipse插件开发的功能。"
						+"\n   "+"由于专注于演示，所以功能较为简单，请读者见谅。"
				        +"\n   "+"作者：张鹏");
		label_1.setBounds(10, 234, 309, 72);

		final Label zhangpengcsebuaaeducnLabel = new Label(container, SWT.NONE);
		zhangpengcsebuaaeducnLabel.setText("如有意见，请发邮件至：zhangpeng@cse.buaa.edu.cn"
				+"\n我们共同探讨改进，不胜感激！");
		zhangpengcsebuaaeducnLabel.setBounds(135, 340, 283, 40);
		//
		return container;
	}

	/**
	 * Initialize the preference page
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
		noDefaultAndApplyButton();
	}

}
