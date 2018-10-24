package com.example.tools;

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
import org.eclipse.jface.resource.ImageDescriptor;

import com.example.image.IconFactory;
import com.example.image.ImageConstants;
import com.example.model.FStepModel;
import com.example.model.FSubTransModel;

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
        PaletteGroup controlGroup = new PaletteGroup("工具");

        List entries = new ArrayList();
        ToolEntry tool = new SelectionToolEntry();
        entries.add(tool);
        root.setDefaultEntry(tool);

        tool = new ConnectionCreationToolEntry("映射工具", "创建映射", null, null, null);
        entries.add(tool);

        controlGroup.addAll(entries);
        return controlGroup;
    }

    private static PaletteContainer createComponentsDrawer() {

		PaletteDrawer drawer = new PaletteDrawer("节点");

		List entries = new ArrayList();

		ToolEntry tool = new CombinedTemplateCreationEntry("流程", "流程", FSubTransModel.class, new SimpleFactory(
				FSubTransModel.class), null, null);
		ImageDescriptor imageDescriptor = IconFactory.
		getImageDescriptor(ImageConstants.FRAME_LOGO);
		tool.setSmallIcon(imageDescriptor);
		tool.setLargeIcon(imageDescriptor);
		entries.add(tool);
		tool = new CombinedTemplateCreationEntry("数据库导入", "把数据导入数据库", FStepModel.class, new StepFactory(
				FStepModel.class,"数据库导入"), null, null);
		imageDescriptor = IconFactory.
		getImageDescriptor(ImageConstants.STEP_DB_INPUT_ICON);
		tool.setSmallIcon(imageDescriptor);
		tool.setLargeIcon(imageDescriptor);
		entries.add(tool);
		
		tool = new CombinedTemplateCreationEntry("数据库导出", "把数据导出数据库", FStepModel.class, new StepFactory(
				FStepModel.class,"数据库导出"), null, null);
		imageDescriptor = IconFactory.
		getImageDescriptor(ImageConstants.STEP_DB_INPUT_ICON);
		tool.setSmallIcon(imageDescriptor);
		tool.setLargeIcon(imageDescriptor);
		entries.add(tool);
		drawer.addAll(entries);
		imageDescriptor = IconFactory.
		getImageDescriptor(ImageConstants.FRAME_LOGO);
		drawer.setSmallIcon(imageDescriptor);
		drawer.setLargeIcon(imageDescriptor);
		return drawer;
    }
}