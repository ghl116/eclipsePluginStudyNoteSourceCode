package rcpdev.todo.ui.todolist.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.EditorPart;

import rcpdev.todo.core.model.TodoItem;
import rcpdev.todo.ui.todolist.common.beans.todoitem.TodoItemBasicInfoComposite;
import rcpdev.todo.ui.todolist.common.beans.todoitem.TodoItemBean;
import rcpdev.todo.ui.todolist.common.beans.todoitem.TodoItemSeriesInfoComposite;
import rcpdev.todo.ui.todolist.editors.jobs.SaveTodoItemJob;

public class TodoItemEditor extends EditorPart implements
		PropertyChangeListener {

	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	public static final String ID = "rcpdev.todo.ui.todolist.editors.TodoItemEditor"; //$NON-NLS-1$

	private TodoItemBean bean;

	private TodoItemBasicInfoComposite basicComposite;

	private TodoItemSeriesInfoComposite seriesComposite;

	private Section editBasicInfomationSection;

	private Section editSeriesInfoSection;

	/**
	 * Create contents of the editor part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		toolkit.paintBordersFor(container);

		final ScrolledForm editTodoitemScrolledForm = toolkit
				.createScrolledForm(container);
		final Composite body = editTodoitemScrolledForm.getBody();
		final GridLayout gridLayout = new GridLayout();
		body.setLayout(gridLayout);
		toolkit.paintBordersFor(body);

		editBasicInfomationSection = toolkit.createSection(body,
				Section.TITLE_BAR | Section.COMPACT | Section.CLIENT_INDENT
						| Section.DESCRIPTION | Section.FOCUS_TITLE
						| Section.EXPANDED);
		editBasicInfomationSection.setLayoutData(new GridData(SWT.FILL,
				SWT.FILL, true, true));
		editBasicInfomationSection.setText("Edit Basic Infomation");

		basicComposite = new TodoItemBasicInfoComposite(
				editBasicInfomationSection, SWT.NONE);
		basicComposite.editMode(true);
		toolkit.adapt(basicComposite);
		toolkit.paintBordersFor(basicComposite);
		editBasicInfomationSection.setClient(basicComposite);

		editSeriesInfoSection = toolkit.createSection(body, Section.TITLE_BAR
				| Section.COMPACT | Section.DESCRIPTION | Section.FOCUS_TITLE
				| Section.EXPANDED | Section.TWISTIE);
		editSeriesInfoSection.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true));
		editSeriesInfoSection.setText("Edit Series Info");

		seriesComposite = new TodoItemSeriesInfoComposite(
				editSeriesInfoSection, SWT.NONE);
		toolkit.paintBordersFor(seriesComposite);
		toolkit.adapt(seriesComposite);
		editSeriesInfoSection.setClient(seriesComposite);

		editTodoitemScrolledForm.setText("Edit TodoItem");
		//

		TodoItemEditorInput input = (TodoItemEditorInput) getEditorInput();
		if (input.getItem() != null)
			bean = new TodoItemBean(input.getItem());
		else
			bean = new TodoItemBean();
		basicComposite.setBean(bean);
		seriesComposite.setBean(bean);
		bean.addPropertyChangeListener(this);
		if (TodoItemBean.TYPE_SINGLE == bean.getType()) {
			editSeriesInfoSection.setExpanded(false);
			editSeriesInfoSection.setEnabled(false);
		}
	}

	public void initManager() {
		
	}
	
	public void disposeManager() {
		
	}
	
	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}
	
	public void dispose() {
		super.dispose();
		disposeManager();
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		SaveTodoItemJob job = new SaveTodoItemJob();
		TodoItem item = bean.extractItem();
		TodoItemEditorInput input = (TodoItemEditorInput) getEditorInput();
		input.getItem().copy(item);
		job.setItemToBeSaved(input.getItem());
		job.schedule();
		try {
			job.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dirty = false;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	private boolean dirty = false;

	public void propertyChange(PropertyChangeEvent evt) {
		if (bean.equals(evt.getSource())) {
			dirty = true;
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void doSaveAs() {
	}

}
