package com.plugindev.addressbook.util;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public class ImageCache {
	private final HashMap<ImageDescriptor, Image> imageMap = 
		new HashMap<ImageDescriptor, Image>();
	private static ImageCache instance;
	public static ImageCache getInstance(){
		if(instance == null)
			instance = new ImageCache();
		return instance;
	}
	public Image getImage(ImageDescriptor descriptor)
	{
		if(descriptor == null)
			return null;
		Image image = (Image)imageMap.get(descriptor);
		if(image == null)
		{
			image = descriptor.createImage();
			imageMap.put(descriptor, image);
		}
		return image;
	}
	public void dispose(){
		Iterator iter = imageMap.values().iterator();
		while(iter.hasNext())
			((Image)iter.next()).dispose();
		imageMap.clear();
	}
}
