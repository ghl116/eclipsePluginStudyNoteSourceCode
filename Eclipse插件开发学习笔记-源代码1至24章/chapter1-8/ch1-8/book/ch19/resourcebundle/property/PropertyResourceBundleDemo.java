package book.ch19.resourcebundle.property;

import java.util.ResourceBundle;

public class PropertyResourceBundleDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("resourcebundle.property.Messages");
		System.out.println(bundle.getString("s1"));
	}

}
