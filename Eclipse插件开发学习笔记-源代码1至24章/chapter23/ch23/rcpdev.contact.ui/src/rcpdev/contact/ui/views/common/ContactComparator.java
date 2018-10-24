package rcpdev.contact.ui.views.common;

import java.util.Stack;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import rcpdev.common.core.utils.StringUtils;

public class ContactComparator extends ViewerComparator implements Listener {

	private TableViewer viewer;

	private static final int SORT_ASC = 0;

	private static final int SORT_DESC = 1;

	private static final int SORT_NONE = 2;

	private static final String SORT_TEXT[] = new String[] { "Up", "Down", null };

	private static final String SORT_KEY = "ccSortKey";

	private static final String ORIGIN_NAME = "ccOriginName";

	private Stack<TableColumn> pushSequence;

	public ContactComparator(TableViewer viewer) {
		this.viewer = viewer;
		Table table = viewer.getTable();
		int columnCount = table.getColumnCount();
		assert columnCount != 0;
		pushSequence = new Stack<TableColumn>();
		for (int i = 0; i < columnCount; i++) {
			TableColumn column = table.getColumn(i);
			column.addListener(SWT.Selection, this);
			column.setData(SORT_KEY, SORT_NONE);
			column.setData(ORIGIN_NAME, column.getText());
		}
	}

	public int compare(Viewer viewer, Object e1, Object e2) {
		assert viewer instanceof TableViewer;
		
		Table table = ((TableViewer) viewer).getTable();
		ITableLabelProvider labelProvider = ((ITableLabelProvider) ((TableViewer) viewer)
				.getLabelProvider());
		int length = pushSequence.size();
		String[] attr1 = new String[length];
		String[] attr2 = new String[length];

		for (int i = length - 1; i >= 0; i--) {
			int columnIndex = table.indexOf(pushSequence.get(i));
			attr1[length - 1 - i] = labelProvider
					.getColumnText(e1, columnIndex);
			attr2[length - 1 - i] = labelProvider
					.getColumnText(e2, columnIndex);
		}
		for (int i = 0; i < length; i++) {
			int dir = (Integer) pushSequence.get(length - 1 - i).getData(
					SORT_KEY);
			int thisCompare = StringUtils.compare(attr1[i], attr2[i]);
			if (thisCompare != 0) {
				return (dir == SORT_ASC) ? thisCompare : -thisCompare;
			}
		}
		return 0;
	}

	public void handleEvent(Event event) {
		assert event.widget instanceof TableColumn;
		TableColumn column = (TableColumn) event.widget;
		int newStatus = ((Integer) column.getData(SORT_KEY) + 1) % 3;
		column.setData(SORT_KEY, newStatus);
		pushSequence.remove(column);
		column.setText((String) column.getData(ORIGIN_NAME));
		if (SORT_NONE != newStatus)
			pushSequence.push(column);
		int length = pushSequence.size();
		for (int i = length - 1; i >= 0; i--) {
			TableColumn tc = pushSequence.get(i);
			String originText = (String) tc.getData(ORIGIN_NAME);
			String dir = SORT_TEXT[(Integer) tc.getData(SORT_KEY)];
			tc.setText(originText + " " + (length - 1 - i) + " " + dir);
		}
		viewer.refresh();
	}

	public void dispose() {
		Table table = viewer.getTable();
		for (int i = table.getColumnCount() - 1; i >= 0; i--)
			table.getColumn(i).removeListener(SWT.Selection, this);
	}
}
