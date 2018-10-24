package book.ch7.tree;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeColumn;

public class UsingTree {

	private Shell sShell = null;
	private Tree tree = null;

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new Shell();
		sShell.setLayout(new FillLayout());
		sShell.setText("Using Tree");
		sShell.setSize(new Point(300, 200));
		tree = new Tree(sShell, SWT.CHECK|SWT.MULTI);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
		treeColumn.setWidth(100);
		treeColumn.setText("Column 1");
		TreeColumn treeColumn1 = new TreeColumn(tree, SWT.NONE);
		treeColumn1.setWidth(100);
		treeColumn1.setText("Column 2");
		
		TreeItem root1 = new TreeItem(tree,SWT.NONE);
		root1.setText(new String[]{"Root 1","Root 1 Content"});
		TreeItem root2 = new TreeItem(tree,SWT.NONE);
		root2.setText(new String[]{"Root 2","Root 2 Content"});
		TreeItem child1 = new TreeItem(root1,SWT.NONE);
		child1.setText("Child 1");
		TreeItem child2 = new TreeItem(root1,SWT.NONE);
		child2.setText("Child 2");
		TreeItem child3 = new TreeItem(root2,SWT.NONE);
		child3.setText("Child 3");
		
		
		
	}

}
