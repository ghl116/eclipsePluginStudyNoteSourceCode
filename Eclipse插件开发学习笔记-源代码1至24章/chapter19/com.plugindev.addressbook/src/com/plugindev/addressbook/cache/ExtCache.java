package com.plugindev.addressbook.cache;

import java.util.HashSet;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;

public class ExtCache implements IRegistryChangeListener {

	private static final String PID = "com.plugindev.addressbook";

	private static final String PT_ID = PID + "." + "messages";

	private final HashSet extensions = new HashSet();

	public void startup() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IExtensionPoint pt = reg.getExtensionPoint(PT_ID);
		IExtension[] ext = pt.getExtensions();
		for (int i = 0; i < ext.length; i++)
			extensions.add(ext[i]);

		reg.addRegistryChangeListener(this);
	}

	public void registryChanged(IRegistryChangeEvent event) {
		IExtensionDelta[] deltas = event.getExtensionDeltas(PID, PT_ID);
		for (int i = 0; i < deltas.length; i++) {
			if (deltas[i].getKind() == IExtensionDelta.ADDED)
				extensions.add(deltas[i].getExtension());
			else
				extensions.remove(deltas[i].getExtension());
		}
	}

	public void shutdown() {
		extensions.clear();
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		reg.removeRegistryChangeListener(this);
	}

}
