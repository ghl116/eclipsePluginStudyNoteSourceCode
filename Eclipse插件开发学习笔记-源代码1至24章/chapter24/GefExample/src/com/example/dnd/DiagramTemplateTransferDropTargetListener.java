package com.example.dnd;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import com.example.tools.ElementFactory;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
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