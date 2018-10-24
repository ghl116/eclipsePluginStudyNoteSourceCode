package com.example.image;

import java.io.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

import com.example.ui.ShowMessage;

/**
 * TODO 
 * @2006-12-31
 * @author xuli
 */
public class IconFactory
{
	/**
	 * This static function returns an ImageDescriptor for 
	 * a given file
	 */
	public static ImageDescriptor getImageDescriptor(String file_name)
	{
		return ImageDescriptor.createFromFile(IconFactory.class, file_name);
	}

	/**
	 * This function returns an Image loaded from 
	 * the given image file
	 */
	public static Image getImage(Widget widget, String file_name)
	{
		InputStream input = IconFactory.class.getResourceAsStream(file_name);
		Image image = null;

		try 
		{
			image= new Image(widget.getDisplay(), input); 
		} 
		catch (org.eclipse.swt.SWTException e) 
		{
			ShowMessage.printError("[icons] File not found: "+file_name);
			return new Image(widget.getDisplay(), 16, 16);
		}

		return image;
	}
}
