package com.example.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.example.commands.ChangeNodeConstraintCommand;
import com.example.commands.CreateStepCommand;
import com.example.model.FStepModel;
import com.example.model.FSubTransModel;
import com.example.parts.StepPart;

/**
 * TODO 
 * @2006-12-30
 * @author xuli
 */
public class SubTransLayoutEditPolicy extends XYLayoutEditPolicy {

	protected boolean isHorizontal() {
		return false;
	}

	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		if (!(child instanceof StepPart))
			return null;
		if (!(constraint instanceof Rectangle))
			return null;

		ChangeNodeConstraintCommand cmd = new ChangeNodeConstraintCommand();
		cmd.setNode((FStepModel) child.getModel());
		cmd.setLocation(((Rectangle) constraint).getLocation());
		cmd.setDimension(((Rectangle) constraint).getSize());
		return cmd;
	}

	protected Command createAddCommand(EditPart child, Object constraint) {

		return null;
	}

	protected Command getCreateCommand(CreateRequest request) {
		Object obj = request.getNewObject();
		if (!(obj instanceof FStepModel))
			return null;
		CreateStepCommand cmd = new CreateStepCommand();
		cmd.setParent((FSubTransModel) getHost().getModel());
		cmd.setChild((FStepModel) request.getNewObject());
		Rectangle constraint = (Rectangle) getConstraintFor(request);
		cmd.setLocation(constraint.getLocation());
		return cmd;
	}

	protected Command getDeleteDependantCommand(Request request) {

		return null;
	}
}
