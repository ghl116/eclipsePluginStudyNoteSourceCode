package com.plugindev.addressbook.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.IMemento;

import com.plugindev.addressbook.models.AddressCategory;
import com.plugindev.addressbook.models.AddressItem;
import com.plugindev.addressbook.util.StringMatcher;

public class AddressViewerCategoryFilter extends ViewerFilter {

	//保存在IMemento中的标记名称
/*	private static final String TAG_PATTERN = "pattern";
	private static final String TAG_TYPE = "CategoryFilterInfo";*/
	private static final String TAG_CATE_ID = "categoryId";
	private static final String TAG_CATE = "CategoryFilterInfo";
	
	   
	private final StructuredViewer viewer;
/*	private String pattern = "";
	private StringMatcher matcher;*/
	private HashSet categories;
	   
	public AddressViewerCategoryFilter(StructuredViewer viewer) {
		this.viewer = viewer;
	}

/*	public String getPattern() {
		return pattern;
	}*/

/*	public void setPattern(String newPattern) {
		boolean filtering = matcher != null;
		if (newPattern != null && newPattern.trim().length() > 0) {
			pattern = newPattern;
			matcher = new StringMatcher(pattern, true, false);
			if (!filtering)
				viewer.addFilter(this);
			else
	            viewer.refresh();
		}
		else {
			pattern = "";
			matcher = null;
			if (filtering)
				viewer.removeFilter(this);
		}
	}
	//保存过滤状态
	public void saveState(IMemento memento) {
		if (pattern.length() == 0)
			return;
		IMemento mem = memento.createChild(TAG_TYPE);
			mem.putString(TAG_PATTERN, pattern);
		}
	//获得保存的过滤状态
	public void init(IMemento memento) {
		IMemento mem = memento.getChild(TAG_TYPE);
		if (mem == null)
			return;
		setPattern(mem.getString(TAG_PATTERN));
	}*/
	public AddressCategory[] getCategories() {
		if (categories == null)
			return AddressCategory.getTypes();
		return (AddressCategory[]) categories.toArray(new AddressCategory[categories.size()]);
		   }

   public void setCategories(AddressCategory[] selectedTypes) {
      AddressCategory[] allCategories = AddressCategory.getTypes();
      boolean filtering = categories != null;
      if (selectedTypes.length < allCategories.length) {
    	  categories = new HashSet();
         categories.addAll(Arrays.asList(selectedTypes));
         if (!filtering)
            viewer.addFilter(this);
         else
            viewer.refresh();
      }
      else {
         categories = null;
         if (filtering)
            viewer.removeFilter(this);
      }
   }

   public boolean select(Viewer viewer, Object parentElement, Object element) {
      return categories == null
         || categories.contains(((AddressItem) element).getCategory());
   }
   
   public void saveState(IMemento memento) {
      if (categories == null)
         return;
      IMemento mem = memento.createChild(TAG_CATE);
      int index = 0;
      for (Iterator iter = categories.iterator(); iter.hasNext();) {
         mem.putString(
            TAG_CATE_ID + index, 
            ((AddressCategory) iter.next()).getCategoryName());
         index++;
      }
   }

   public void init(IMemento memento) {
      AddressCategory[] allTypes = AddressCategory.getTypes();
      IMemento mem = memento.getChild(TAG_CATE);
      if (mem == null) {
         setCategories(allTypes);
         return;
      }
      List someTypes = new ArrayList(allTypes.length);
      int index = 0;
      while (true) {
         String eachId = mem.getString(TAG_CATE_ID + index);
         if (eachId == null)
            break;
         AddressCategory eachCategory = AddressCategory.getCategoryByName(eachId);
         if (eachCategory != null)
            someTypes.add(eachCategory);
         index++;
      }
      setCategories((AddressCategory[]) someTypes
            .toArray(new AddressCategory[someTypes.size()]));
   }
}
