package book.ch19.resourcebundle.list;

import java.util.ListResourceBundle;

public class Messages_zh_CN extends ListResourceBundle {
	protected Object[][] getContents() {
		return new Object[][] { 
				{ "s1", "确定" }, 
				{ "s2", "取消" }, 
				{ "s3", "重试" } 
		};
	}
}
