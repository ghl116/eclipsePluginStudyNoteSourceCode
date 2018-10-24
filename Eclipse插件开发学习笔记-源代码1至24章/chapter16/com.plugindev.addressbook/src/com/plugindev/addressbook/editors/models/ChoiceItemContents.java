package com.plugindev.addressbook.editors.models;

import java.util.ArrayList;

//单个choice Item
//需要记录：1.名称 2.button数量 3.每个button的名字
public class ChoiceItemContents {
	private String key;
	private int value;
	private ArrayList<String> choices;

	public ChoiceItemContents(String key, String[] choices, int value){
		this.key = key;
		this.value = value;
		this.choices = new ArrayList<String>();
		for(int i = 0; i < choices.length; i++)
		{
			this.choices.add(choices[i]);
		}
	}
	public String getKey(){
		return key;
	}
	public int getValue(){
		return value;
	}
	public Object[] getChoices(){
		return choices.toArray();
	}
	public void addChoice(String choice){
		choices.add(choice);
	}
	public void removeChoice(String choice){
		choices.remove(choice);
	}
	public void setValue(int value){
		this.value = value;
	}
}
