package com.example.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import com.example.figures.StepFigure;
/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class NodeDirectEditPolicy extends DirectEditPolicy{

	protected Command getDirectEditCommand(DirectEditRequest request) {
		return null;
	}
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		((StepFigure) getHostFigure()).setName(value);
	}
}
