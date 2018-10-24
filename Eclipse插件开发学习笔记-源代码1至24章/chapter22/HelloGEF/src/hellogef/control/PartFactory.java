package hellogef.control;

import hellogef.model.ConnectionModel;
import hellogef.model.Diagram;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

public class PartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof Diagram)
			part = new DiagramPart();
		else if (model instanceof ConnectionModel) 
			part = new ConnectionPart();
		else
			part = new NodePart();
		part.setModel(model);
		return part;
	}
}