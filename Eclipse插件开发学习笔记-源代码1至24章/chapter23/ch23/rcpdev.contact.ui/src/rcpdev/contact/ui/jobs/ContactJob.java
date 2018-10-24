package rcpdev.contact.ui.jobs;

import org.eclipse.core.runtime.jobs.Job;

import rcpdev.contact.core.model.contact.Contact;

public abstract class ContactJob extends Job {

	public ContactJob(String name) {
		super(name);
	}

	private Contact editorContent;

	public Contact getEditorContent() {
		return editorContent;
	}

	public void setEditorContent(Contact editorContent) {
		this.editorContent = editorContent;
	}

}
