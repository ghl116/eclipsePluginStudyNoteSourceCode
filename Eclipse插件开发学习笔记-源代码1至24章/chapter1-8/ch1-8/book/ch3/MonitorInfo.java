package book.ch3;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;

public class MonitorInfo {
	public static void main(String[] args) {
		Display disp = Display.getDefault();
		Monitor monitor = disp.getPrimaryMonitor();
		System.out.println(monitor.getClientArea());
		System.out.println(monitor.getBounds());
	}

}
