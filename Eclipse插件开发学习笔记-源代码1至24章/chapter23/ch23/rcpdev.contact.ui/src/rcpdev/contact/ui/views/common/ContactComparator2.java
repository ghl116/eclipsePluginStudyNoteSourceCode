package rcpdev.contact.ui.views.common;

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

public class ContactComparator2 extends ViewerComparator implements Listener {

	private static final int SORT_ASC = 0;

	private static final int SORT_DESC = 1;

	private static final int SORT_NONE = 2;

	private static final String SORT_KEY = "cc2SortKey";

	private TableViewer viewer;

	private TableColumn sortColumn;

	public ContactComparator2(TableViewer viewer) {
		this.viewer = viewer;
		Table table = viewer.getTable();
		int columnCount = table.getColumnCount();
		assert columnCount != 0;

		for (int i = 0; i < columnCount; i++) {
			table.getColumn(i).addListener(SWT.Selection, this);
			table.getColumn(i).setData(SORT_KEY, SORT_NONE);
		}
	}

	public int compare(Viewer viewer, Object e1, Object e2) {
		assert viewer instanceof TableViewer;
		
		Table table = ((TableViewer) viewer).getTable();
		ITableLabelProvider labelProvider = ((ITableLabelProvider) ((TableViewer) viewer)
				.getLabelProvider());
		if (sortColumn == null
				|| SORT_NONE == (Integer) sortColumn.getData(SORT_KEY))
			return 0;
		int columnIndex = table.indexOf(sortColumn);
		String a = labelProvider.getColumnText(e1, columnIndex);
		String b = labelProvider.getColumnText(e2, columnIndex);
		int result = StringUtils.compare(a, b);
		int dir = (Integer) sortColumn.getData(SORT_KEY);
		return dir == SORT_ASC ? result : -result;
	}

	public void handleEvent(Event event) {
		assert event.widget instanceof TableColumn;
		TableColumn column = (TableColumn) event.widget;
		int newStatus = ((Integer) column.getData(SORT_KEY) + 1) % 3;
		column.setData(SORT_KEY, newStatus);
		if (!column.equals(sortColumn)) {
			if (sortColumn != null)
				sortColumn.setData(SORT_KEY, SORT_NONE);
			sortColumn = column;
			viewer.getTable().setSortColumn(sortColumn);
		}
		int dir = 0;
		switch (newStatus) {
		case SORT_ASC:
			dir = SWT.UP;
			break;
		case SORT_DESC:
			dir = SWT.DOWN;
			break;
		case SORT_NONE:
			dir = SWT.NONE;
			break;
		}
		viewer.getTable().setSortDirection(dir);
		viewer.refresh();
	}

	public void dispose() {
		Table table = viewer.getTable();
		for (int i = table.getColumnCount() - 1; i >= 0; i--) {
			table.getColumn(i).removeListener(SWT.Selection, this);
		}
		table.setSortColumn(null);
	}
}
