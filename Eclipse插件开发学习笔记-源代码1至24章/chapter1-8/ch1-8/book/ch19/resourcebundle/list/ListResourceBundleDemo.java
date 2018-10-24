package book.ch19.resourcebundle.list;

import java.util.ResourceBundle;

public class ListResourceBundleDemo {

	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("rb.list.Messages");
		System.out.println(bundle.getString("s1"));
	}

}
