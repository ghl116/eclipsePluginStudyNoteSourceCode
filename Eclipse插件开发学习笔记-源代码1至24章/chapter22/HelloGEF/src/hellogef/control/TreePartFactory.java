package hellogef.control;

import hellogef.model.Diagram;
import hellogef.model.NodeModel;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

public class TreePartFactory implements EditPartFactory{

	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Diagram) {
			return new DiagramTreeEditPart(model);
		}
		else if (model instanceof NodeModel) {
			return new NodeTreeEditPart(model);
		}
		else {
			return null;
		}
	}
}
