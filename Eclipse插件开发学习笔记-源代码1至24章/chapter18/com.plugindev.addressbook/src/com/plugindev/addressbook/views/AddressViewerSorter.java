package com.plugindev.addressbook.views;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IMemento;

public class AddressViewerSorter extends ViewerSorter {
	   private static final String TAG_DESENDING = "descending";
	   private static final String TAG_INDEX = "columnIndex";
	   private static final String TAG_CATEGORY = "category";
	   private static final String TAG_TRUE = "true";
	private class SortInfo{
		int columnIndex;
		Comparator comparator;
		boolean descending;
	}
	
	private TableViewer viewer;
	private SortInfo[] infos;
	public AddressViewerSorter(
			TableViewer viewer,
			TableColumn[] columns,
			Comparator[] comparators){
		this.viewer = viewer;
		infos = new SortInfo[columns.length];
		for(int i = 0; i < columns.length; i++)
		{
			infos[i] = new SortInfo();
			infos[i].columnIndex = i;
			infos[i].comparator = comparators[i];
			infos[i].descending = false;
	         createSelectionListener(columns[i], infos[i]);
	      }
	}
	   public int compare(Viewer viewer, Object address1, Object address2) 
	   {
	      for (int i = 0; i < infos.length; i++) 
	      {
	         int result = infos[i].comparator.compare(address1, address2);
	         if (result != 0) {
	            if (infos[i].descending)
	               return -result;
	            return result;
	         }
	      }
	      return 0;
	   }

	   private void createSelectionListener(final TableColumn column, final SortInfo info) {
	      column.addSelectionListener(new SelectionAdapter() {
	         public void widgetSelected(SelectionEvent e) {
	            sortUsing(info);
	         }
	      });
	   }

	   protected void sortUsing(SortInfo info) {
	      if (info == infos[0])
	         info.descending = !info.descending;
	      else {
	         for (int i = 0; i < infos.length; i++) {
	            if (info == infos[i]) {
	               System.arraycopy(infos, 0, infos, 1, i);
	               infos[0] = info;
	               info.descending = false;
	               break;
	            }
	         }
	      }
	      viewer.refresh();
	   }
	   public void saveState(IMemento memento) {
		      for (int i = 0; i < infos.length; i++) {
		         SortInfo info = infos[i];
		         IMemento mem = memento.createChild(TAG_CATEGORY);
		         mem.putInteger(TAG_INDEX, info.columnIndex);
		         if (info.descending)
		            mem.putString(TAG_DESENDING, TAG_TRUE);
		      }
		   }

		   public void init(IMemento memento) {
		      List newInfos = new ArrayList(infos.length);
		      IMemento[] mems = memento.getChildren(TAG_CATEGORY);
		      for (int i = 0; i < mems.length; i++) {
		         IMemento mem = mems[i];
		         Integer value = mem.getInteger(TAG_INDEX);
		         if (value == null)
		            continue;
		         int index = value.intValue();
		         if (index < 0 || index >= infos.length)
		            continue;
		         SortInfo info = infos[index];
		         if (newInfos.contains(info))
		            continue;
		         info.descending =
		            TAG_TRUE.equals(mem.getString(TAG_DESENDING));
		         newInfos.add(info);
		      }
		      for (int i = 0; i < infos.length; i++)
		         if (!newInfos.contains(infos[i]))
		            newInfos.add(infos[i]);
		      infos = (SortInfo[]) newInfos.toArray(
		         new SortInfo[newInfos.size()]);
		   }
}
