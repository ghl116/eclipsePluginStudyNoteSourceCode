package rcpdev.common.core.utils;

import java.lang.reflect.Field;

import org.apache.commons.jxpath.JXPathContext;

public class BeanAccess {

	public static Object get(Object bean, String attr) {
		JXPathContext context = JXPathContext.newContext(bean);
		return context.getValue(attr);
	}

	public static void set(Object bean, String attr, Object value) {
		JXPathContext context = JXPathContext.newContext(bean);
		context.setValue(attr, value);
	}

	public static void setDirect(Object bean, String attr, Object value) {
		try {
			Field field = bean.getClass().getDeclaredField(attr);
			field.setAccessible(true);
			field.set(bean, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
