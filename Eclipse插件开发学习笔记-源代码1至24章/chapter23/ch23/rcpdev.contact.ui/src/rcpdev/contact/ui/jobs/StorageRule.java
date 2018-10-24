package rcpdev.contact.ui.jobs;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class StorageRule implements ISchedulingRule {

	public boolean contains(ISchedulingRule rule) {
		return rule.equals(this);
	}

	public boolean isConflicting(ISchedulingRule rule) {
		return rule.equals(this);
	}

	private static StorageRule instance;

	public static StorageRule getInstance() {
		if (instance == null)
			instance = new StorageRule();
		return instance;
	}

}
