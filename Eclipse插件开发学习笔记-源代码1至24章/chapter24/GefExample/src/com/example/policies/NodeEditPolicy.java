package com.example.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.example.commands.DeleteNodeCommand;
import com.example.model.FSubTransModel;
import com.example.model.FTransModel;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class NodeEditPolicy extends ComponentEditPolicy{

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteNodeCommand deleteCommand=new DeleteNodeCommand();
		deleteCommand.setTransModel((FTransModel)getHost().getParent().getModel());
		deleteCommand.setNode((FSubTransModel)getHost().getModel());

		return deleteCommand;
	}
}
