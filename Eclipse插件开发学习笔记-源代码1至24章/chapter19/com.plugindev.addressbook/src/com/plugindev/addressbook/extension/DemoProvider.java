package com.plugindev.addressbook.extension;

public class DemoProvider implements IMessageProvider {

	public String getMessage() {
		return "这是扩展提供的欢迎信息";
	}

	public String getTitle() {
		return "欢迎信息";
	}

}
