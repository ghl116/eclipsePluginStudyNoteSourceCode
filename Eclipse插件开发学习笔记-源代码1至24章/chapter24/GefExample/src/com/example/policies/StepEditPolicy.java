package com.example.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.example.commands.DeleteStepCommand;
import com.example.model.FStepModel;
import com.example.model.FSubTransModel;

/**
 * TODO 
 * @2007-1-26
 * @author xuli
 */
public class StepEditPolicy extends ComponentEditPolicy {
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteStepCommand deleteCommand=new DeleteStepCommand();
		deleteCommand.setSubTransModel((FSubTransModel)getHost().getParent().getModel());
		deleteCommand.setNode((FStepModel)getHost().getModel());

		return deleteCommand;
	}
}
