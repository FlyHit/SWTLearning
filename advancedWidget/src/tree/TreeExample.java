package tree;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * 创建树
 * 
 * @author 11648
 *
 */
public class TreeExample {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TreeExample window = new TreeExample();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("创建树");
		shell.setLayout(new FillLayout());

		/*
		 * 创建树的步骤： 1. 创建Tree对象 2. 创建TreeItem 3. TreeItem下还可以包含TreeItem
		 */
		final Tree tree = new Tree(shell, SWT.BORDER);
		for (int i = 0; i < 4; i++) {
			TreeItem iItem = new TreeItem(tree, 0);
			iItem.setText("TreeItem (0) -" + i);
			for (int j = 0; j < 4; j++) {
				TreeItem jItem = new TreeItem(iItem, 0);
				jItem.setText("TreeItem (1) -" + j);
				for (int k = 0; k < 4; k++) {
					TreeItem kItem = new TreeItem(jItem, 0);
					kItem.setText("TreeItem (2) -" + k);
					for (int l = 0; l < 4; l++) {
						TreeItem lItem = new TreeItem(kItem, 0);
						lItem.setText("TreeItem (3) -" + l);
					}
				}
			}
		}
	}

}
