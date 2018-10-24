package com.example.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.example.commands.DeleteConnectionCommand;
import com.example.model.FConnection;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class ConnectionEditPolicy extends ComponentEditPolicy{

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		FConnection conn=(FConnection)getHost().getModel();
		DeleteConnectionCommand cmd=new DeleteConnectionCommand();
		cmd.setConnection(conn);
		cmd.setSource(conn.getSource());
		cmd.setTarget(conn.getTarget());
		return cmd;
	}
}
