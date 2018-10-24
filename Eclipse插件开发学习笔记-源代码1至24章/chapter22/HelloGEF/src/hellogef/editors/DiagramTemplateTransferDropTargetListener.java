package hellogef.editors;

import hellogef.factory.ElementFactory;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

public class DiagramTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	/**
	 * @param viewer
	 */
	public DiagramTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	protected CreationFactory getFactory(Object template) {
		return new ElementFactory(template);
	}
}