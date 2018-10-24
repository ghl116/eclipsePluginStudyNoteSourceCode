package book.ch7.tree;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import book.ch7.table.TableLabelProvider;

public class UsingTreeViewer {

	private Shell sShell = null;
	private Tree tree = null;

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new Shell();
		sShell.setText("Shell");
		sShell.setSize(new Point(300, 200));
		sShell.setLayout(new FillLayout());
		tree = new Tree(sShell, SWT.NONE);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
		treeColumn.setWidth(60);
		treeColumn.setText("ID");
		TreeColumn treeColumn1 = new TreeColumn(tree, SWT.NONE);
		treeColumn1.setWidth(60);
		treeColumn1.setText("Name");
		
		TreeViewer treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new UserTreeContentProvider());
		treeViewer.setLabelProvider(new TableLabelProvider());
		
		User president = new User("0","President");
		User manager1 = new User("1","Manager 1");
		User manager2 = new User("2","Manager 2");
		president.getUnderlings().add(manager1);
		president.getUnderlings().add(manager2);
		manager1.setManager(president);
		manager2.setManager(president);
		
		UserStructure input = new UserStructure(president);
		
		treeViewer.setInput(input);
	}

}
