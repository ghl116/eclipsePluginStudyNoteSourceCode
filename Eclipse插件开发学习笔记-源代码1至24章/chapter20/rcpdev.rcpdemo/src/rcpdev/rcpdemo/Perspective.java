package rcpdev.rcpdemo;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import rcpdev.rcpdemo.views.SampleView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView(SampleView.ID, SWT.LEFT, 0.5f, layout.getEditorArea());
	}
}
