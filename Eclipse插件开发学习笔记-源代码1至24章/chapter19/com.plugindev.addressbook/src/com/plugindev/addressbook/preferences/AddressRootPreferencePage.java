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
		label_1.setText("��ַ�����" + "\n  �汾1.0.0Beta �� 2007��7��1�ձ���ͨ����"
						+"\n   "+"Ŀ����Ϊ��ȫ����ʾEclipse��������Ĺ��ܡ�"
						+"\n   "+"����רע����ʾ�����Թ��ܽ�Ϊ�򵥣�����߼��¡�"
				        +"\n   "+"���ߣ�����");
		label_1.setBounds(10, 234, 309, 72);

		final Label zhangpengcsebuaaeducnLabel = new Label(container, SWT.NONE);
		zhangpengcsebuaaeducnLabel.setText("����������뷢�ʼ�����zhangpeng@cse.buaa.edu.cn"
				+"\n���ǹ�ͬ̽�ָĽ�����ʤ�м���");
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
