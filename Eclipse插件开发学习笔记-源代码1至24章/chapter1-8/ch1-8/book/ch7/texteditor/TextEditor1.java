/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package book.ch7.texteditor;

/*
 * Setting the font style, foreground and background colors of StyledText
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class TextEditor1 {

public static void main(String[] args) {
	Display display = new Display();
	Shell shell = new Shell(display);
	shell.setLayout(new FillLayout());
	
	Font tahoma = new Font(display,"Tahoma",16,SWT.BOLD);
	Font courierNew = new Font(display,"Courier New",12,SWT.ITALIC);
	Font arial = new Font(display,"Arial",14,SWT.NORMAL);
	
	StyledText text = new StyledText (shell, SWT.BORDER);
	text.setText("The quick brown fox jumps over the lazy dog.");
	StyleRange style1 = new StyleRange();
	style1.start = 0;
	style1.length = 9;
	style1.font = tahoma;
	text.setStyleRange(style1);
	
	StyleRange style2 = new StyleRange();
	style2.start = 10;
	style2.length = 15;
	style2.font = courierNew;
	style2.foreground = display.getSystemColor(SWT.COLOR_BLUE);
	style2.rise = 5;
	style2.strikeout = true;
	text.setStyleRange(style2);
	
	StyleRange style3 = new StyleRange();
	style3.start = 26;
	style3.length = 14;
	style3.font = arial;
	style3.underline = true;
	style3.foreground = display.getSystemColor(SWT.COLOR_RED);
	style3.background = display.getSystemColor(SWT.COLOR_YELLOW);
	text.setStyleRange(style3);
	
	shell.pack();
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	tahoma.dispose();
	courierNew.dispose();
	arial.dispose();
	display.dispose();
}
}