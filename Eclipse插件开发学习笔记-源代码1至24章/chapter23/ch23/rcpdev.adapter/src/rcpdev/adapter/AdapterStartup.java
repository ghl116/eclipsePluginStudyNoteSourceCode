package rcpdev.adapter;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IStartup;

public class AdapterStartup implements IStartup {

	public void earlyStartup() {

		MenuManager sampleMenu = new MenuManager("Sample &Menu", "sampleMenu");
		sampleMenu.add(new Separator("sampleGroup"));
	}

}
