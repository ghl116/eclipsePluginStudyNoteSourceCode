package com.plugindev.addressbook.editors.models;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public 	class MasterContentProvider implements IStructuredContentProvider {
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof SimpleFormEditorInput) {
			/*				SimpleFormEditorInput input = (SimpleFormEditorInput) page
			.getEditor().getEditorInput();*/
			SimpleFormEditorInput input = (SimpleFormEditorInput)inputElement;
			return input.getManager().getContents();
		}
		return new Object[0];
	}
	public void dispose() {
	}
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}
}
