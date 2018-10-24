package rcpdev.common.ui.javabeans.text;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

public class TextVerifier {

	public static final int TYPE_NORMAL = 0;

	public static final int TYPE_INTEGER = 1;

	public static final int TYPE_DOUBLE = 2;

	public TextVerifier(Text control, int maxLength, int type) {
		control.setTextLimit(maxLength);
		switch (type) {
		case TYPE_INTEGER:
			control.addVerifyListener(new IntCheck());
			break;
		case TYPE_DOUBLE:
			control.addVerifyListener(new DoubleCheck());
			break;
		default:
			
		}
	}

	private class IntCheck implements VerifyListener {
		public void verifyText(VerifyEvent e) {
			try {
				String existed = ((Text) e.widget).getText();
				Integer.parseInt(calTobeString(existed, e.start, e.end,
						e.text));
			} catch (Exception ex) {
				e.doit = false;
			}
		}
	}

	private class DoubleCheck implements VerifyListener {
		public void verifyText(VerifyEvent e) {
			try {
				String existed = ((Text) e.widget).getText();
				Double.parseDouble(calTobeString(existed, e.start, e.end,
						e.text));
			} catch (Exception ex) {
				e.doit = false;
			}
		}
	}
	
	private static final String calTobeString(String existed, int start,
			int end, String text) {
		StringBuffer sb = new StringBuffer();
		if (start == end) {// Insert new data
			sb.append(existed);
			sb.append(text);
		} else {
			sb.append(existed.substring(0, start));
			sb.append(text);
			sb.append(existed.substring(end, existed.length()));
		}
		return sb.toString();
	}
}
