package com.example.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.example.model.FConnection;
import com.example.model.FStepModel;
import com.example.model.FSubTransModel;
import com.example.model.FTransModel;
import com.example.model.TreeItemModel;

/**
 * TODO 
 * @2006-12-30
 * @author xuli
 */
public class PartFactory implements EditPartFactory {
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof FTransModel)
			part = new TransModelPart();
		else if (model instanceof FConnection) 
			part = new ConnectionPart();
		else if (model instanceof FSubTransModel)
			part = new SubTransModelPart();
		else if (model instanceof TreeItemModel)
			part = new TreeItemPart();
		else
			part = new StepPart();
		part.setModel(model);
		return part;
	}
}