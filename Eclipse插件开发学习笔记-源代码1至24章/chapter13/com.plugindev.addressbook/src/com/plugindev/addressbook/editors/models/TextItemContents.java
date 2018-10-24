package com.plugindev.addressbook.editors.models;

public class TextItemContents{
	
	String key;
	String value;
	public TextItemContents(String key, String value){
		this.key = key;
		this.value = value;
	}
	public String getKey(){
		return this.key;
	}
	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}
}
