package rcpdev.application;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView("rcpdev.todo.ui.TodoListView", IPageLayout.RIGHT, 0.5f,
				layout.getEditorArea());
		layout.addView("rcpdev.todo.ui.CalendarView", IPageLayout.TOP, 0.5f,
				"rcpdev.todo.ui.TodoListView");
		layout.addView("rcpdev.contact.ui.views.contactView", IPageLayout.TOP,
				0.4f, layout.getEditorArea());

	}
}
