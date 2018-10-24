package hellogef.factory;

import hellogef.model.NodeModel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;

public class PaletteFactory {
	public static PaletteRoot createPalette() {
		PaletteRoot paletteRoot = new PaletteRoot();
		paletteRoot.addAll(createCategories(paletteRoot));
		return paletteRoot;
	}

	private static List createCategories(PaletteRoot root) {
		List categories = new ArrayList();
		categories.add(createControlGroup(root));
		categories.add(createComponentsDrawer());
		return categories;
	}

	private static PaletteContainer createControlGroup(PaletteRoot root) {
		PaletteGroup controlGroup = new PaletteGroup("控制工具");
		List entries = new ArrayList();
		ToolEntry tool = new SelectionToolEntry();
		entries.add(tool);
		root.setDefaultEntry(tool);
		tool = new ConnectionCreationToolEntry("连线", "创建连接节点的连线", null, null, null);
		entries.add(tool);
		controlGroup.addAll(entries);
		return controlGroup;
	}

	private static PaletteContainer createComponentsDrawer() {

		PaletteDrawer drawer = new PaletteDrawer("节点组");
		List entries = new ArrayList();
		ToolEntry tool = new CombinedTemplateCreationEntry("节点", "创建一个节点", NodeModel.class, new SimpleFactory(
				NodeModel.class), null, null);
		entries.add(tool);
		drawer.addAll(entries);
		return drawer;
	}
}