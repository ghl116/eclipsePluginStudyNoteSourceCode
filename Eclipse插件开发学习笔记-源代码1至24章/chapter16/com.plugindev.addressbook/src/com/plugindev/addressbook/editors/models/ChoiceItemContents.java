package com.plugindev.addressbook.editors.models;

import java.util.ArrayList;

//����choice Item
//��Ҫ��¼��1.���� 2.button���� 3.ÿ��button������
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
