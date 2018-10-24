package hellogef.editors;

import hellogef.control.PartFactory;
import hellogef.control.TreePartFactory;
import hellogef.factory.PaletteFactory;
import hellogef.model.Diagram;

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
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.gef.ui.stackview.CommandStackInspectorPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;


public class HelloEditor extends GraphicalEditorWithFlyoutPalette {

	private Diagram diagram = new Diagram();

	private PaletteRoot paletteRoot;

	public Diagram getDiagram() {
		return this.diagram;
	}

	public HelloEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setRootEditPart(new MyRootEditPart());
		getGraphicalViewer().setEditPartFactory(new PartFactory());
	}

	protected void initializeGraphicalViewer() {
		getGraphicalViewer().setContents(this.diagram);
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
		oos.writeObject(this.diagram);
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
			this.diagram = (Diagram) in.readObject();
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
		
		this.diagram = new Diagram();
	}

	public Object getAdapter(Class type) {
		if (type == CommandStackInspectorPage.class)
			return new CommandStackInspectorPage(getCommandStack());
		if (type == ActionRegistry.class)
			return getActionRegistry();
		if (type == IContentOutlinePage.class)
			return new OutlinePage();
		return super.getAdapter(type);
	}
	
	public void commandStackChanged(EventObject event) {
		if (isDirty()) {
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
		super.commandStackChanged(event);

	}

	protected PaletteRoot getPaletteRoot() {
		if (this.paletteRoot == null) {
			this.paletteRoot = PaletteFactory.createPalette();
		}
		return this.paletteRoot;
	}

	class OutlinePage extends ContentOutlinePage {
		private Control outline;

		public OutlinePage() {
			super(new TreeViewer());
		}

		public void init(IPageSite pageSite) {
			super.init(pageSite);
			ActionRegistry registry = getActionRegistry();
			IActionBars bars = pageSite.getActionBars();
			String id = IWorkbenchActionConstants.UNDO;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = IWorkbenchActionConstants.REDO;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = IWorkbenchActionConstants.DELETE;
			bars.setGlobalActionHandler(id, registry.getAction(id));
			bars.updateActionBars();
		}

		public void createControl(Composite parent) {
			outline = getViewer().createControl(parent);
			getSelectionSynchronizer().addViewer(getViewer());
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new TreePartFactory());
			getViewer().setContents(getDiagram());
		}

		public Control getControl() {
			return outline;
		}

		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());
			super.dispose();
		}
	}

}


