package hellogef.factory;

import org.eclipse.gef.requests.CreationFactory;

public class ElementFactory implements CreationFactory {

	private Object template;

	public ElementFactory(Object template) {
		this.template = template;
	}

	public Object getNewObject() {
		try {
			return ((Class) template).newInstance();
		}
		catch (Exception e) {
			return null;
		}
	}

	public Object getObjectType() {
		return template;
	}
}