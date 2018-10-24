package com.example.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.stackview.CommandStackInspectorPage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.example.dnd.DiagramTemplateTransferDropTargetListener;
import com.example.model.FTransModel;
import com.example.parts.PartFactory;
import com.example.tools.PaletteFactory;

/**
 * TODO 
 * @2006-12-28
 * @author xuli
 */
public class PracticeEditor extends GraphicalEditorWithFlyoutPalette{
	private FTransModel transmodel = null;
	private PaletteRoot paletteRoot;
	private ActionRegistry m_action_registry;
	private KeyHandler m_shared_key_handler;
	public static String path = null;

	public PracticeEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();
		getGraphicalViewer().setRootEditPart(root);
		getGraphicalViewer().setEditPartFactory(new PartFactory());
	}

	protected void initializeGraphicalViewer() {
		getGraphicalViewer().setContents(this.transmodel);
		getGraphicalViewer().addDropTargetListener(new DiagramTemplateTransferDropTargetListener(getGraphicalViewer()));
	}

	public void doSave(IProgressMonitor monitor) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			createOutputStream(out);
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.setContents(
					new ByteArrayInputStream(out.toByteArray()), 
					true,  // keep saving, even if IFile is out of sync with the Workspace
					false, // dont keep history
					monitor); // progress monitor
			getCommandStack().markSaveLocation();
			firePropertyChange(IEditorPart.PROP_DIRTY);
		} catch (CoreException ce) { 
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void createOutputStream(OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(this.transmodel);
		oos.close();
	}

	public void doSaveAs() {
	}

	public boolean isDirty() {
		return getCommandStack().isDirty();
	}

	public boolean isSaveAsAllowed() {
		return false;
	}

	protected void setInput(IEditorInput input) {
		super.setInput(input);
		try {
			IFile file = ((IFileEditorInput) input).getFile();
			ObjectInputStream in = new ObjectInputStream(file.getContents());
			this.transmodel = (FTransModel) in.readObject();
			in.close();
			setPartName(file.getName());
		} catch (IOException e) { 
			handleLoadException(e); 
		} catch (CoreException e) { 
			handleLoadException(e); 
		} catch (ClassNotFoundException e) { 
			handleLoadException(e); 
		}
	}

	private void handleLoadException(Exception e) {
		
		this.transmodel = new FTransModel();
	}

	protected PaletteRoot getPaletteRoot() {
		if (this.paletteRoot == null) {
			this.paletteRoot = PaletteFactory.createPalette();
		}
		return this.paletteRoot;
	}

	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(getEditDomain()) {
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
			}
		};
	}

	protected FlyoutPreferences getPalettePreferences() {
		return new FlyoutPreferences() {
			public int getDockLocation() {
				return  PositionConstants.WEST;
			}
			public void setDockLocation(int location) { 

			}
			public int getPaletteState() {
				return FlyoutPaletteComposite.STATE_PINNED_OPEN;
			}
			public int getPaletteWidth() {
				return 150;
			}
			public void setPaletteState(int state) {

			}
			public void setPaletteWidth(int width) {

			}
		};
	}

	public Object getAdapter(Class type) {
		if (type == CommandStackInspectorPage.class)
			return new CommandStackInspectorPage(getCommandStack());
		if (type == ActionRegistry.class)
			return getActionRegistry();
		if (type == ZoomManager.class)
			return getGraphicalViewer().getProperty(ZoomManager.class.toString());

		return super.getAdapter(type);
	}

	public void commandStackChanged(EventObject event) {
		if (isDirty()) {
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
		super.commandStackChanged(event);

	}
}

