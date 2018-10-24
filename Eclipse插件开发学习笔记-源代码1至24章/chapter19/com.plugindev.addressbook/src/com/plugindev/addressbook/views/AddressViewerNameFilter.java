package com.plugindev.addressbook.views;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.IMemento;

import com.plugindev.addressbook.models.AddressItem;
import com.plugindev.addressbook.util.StringMatcher;

public class AddressViewerNameFilter extends ViewerFilter {
   private static final String TAG_PATTERN = "pattern";
   private static final String TAG_TYPE = "NameFilterInfo";
   
   private final StructuredViewer viewer;
   private String pattern = "";
   private StringMatcher matcher;
   
   public AddressViewerNameFilter(StructuredViewer viewer) {
      this.viewer = viewer;
   }

   public String getPattern() {
      return pattern;
   }

   public void setPattern(String newPattern) {
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

   public boolean select(
      Viewer viewer,
      Object parentElement,
      Object element
   ) {
      return matcher.match(
         ((AddressItem) element).getName());
   }
   
   public void saveState(IMemento memento) {
      if (pattern.length() == 0)
         return;
      IMemento mem = memento.createChild(TAG_TYPE);
      mem.putString(TAG_PATTERN, pattern);
   }

   public void init(IMemento memento) {
      IMemento mem = memento.getChild(TAG_TYPE);
      if (mem == null)
         return;
      setPattern(mem.getString(TAG_PATTERN));
   }
}