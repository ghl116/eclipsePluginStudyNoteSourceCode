package com.example.tools;

import org.eclipse.gef.requests.SimpleFactory;

import com.example.model.FStepModel;

public class StepFactory extends SimpleFactory {

	public String step_name = null;
	public StepFactory(Class aClass,String name) {
		super(aClass);
		step_name = name;
	}

	public Object getNewObject() {
		FStepModel step = (FStepModel)super.getNewObject();
		if(step_name != null)
			step.setName(step_name);
		return step;
	}
}
