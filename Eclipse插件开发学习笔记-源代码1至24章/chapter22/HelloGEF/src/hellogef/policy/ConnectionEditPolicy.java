package hellogef.policy;

import hellogef.command.DeleteConnectionCommand;
import hellogef.model.ConnectionModel;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

public class ConnectionEditPolicy extends ComponentEditPolicy{

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		ConnectionModel conn=(ConnectionModel)getHost().getModel();
		DeleteConnectionCommand cmd=new DeleteConnectionCommand();
		cmd.setConnection(conn);
		cmd.setSource(conn.getSource());
		cmd.setTarget(conn.getTarget());
		return cmd;
	}
}
