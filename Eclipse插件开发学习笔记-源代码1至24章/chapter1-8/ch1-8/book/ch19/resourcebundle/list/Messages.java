package book.ch19.resourcebundle.list;

import java.util.ListResourceBundle;

public class Messages extends ListResourceBundle {

	protected Object[][] getContents() {
		return new Object[][] { 
				{ "s1", "OK" }, 
				{ "s2", "Cancel" }, 
				{ "s3", "Retry" } 
		};
	}

}
