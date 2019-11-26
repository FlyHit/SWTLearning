package table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * 创建带进度条的表：利用TableEditor，然后给表格绑定进度条 
 * 事实上，可以绑定其他控件以实现不同的功能
 * 
 * @author 11648
 *
 */
public class ProgressBarTable {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProgressBarTable window = new ProgressBarTable();
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
		shell.setText("带进度条的表");
		shell.setLayout(new FillLayout());

		Table table = new Table(shell, SWT.BORDER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

//		两列
		for (int i = 0; i < 2; i++) {
			new TableColumn(table, SWT.NONE);
		}
		table.getColumn(0).setText("Task");
		table.getColumn(1).setText("Progress");

//		四十行
		for (int i = 0; i < 40; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText("Task " + i);
//			行数为5的倍数时添加进度条
			if (i % 5 == 0) {
				ProgressBar bar = new ProgressBar(table, SWT.NONE);
				bar.setSelection(i); // 进度条显示的百分比
				TableEditor editor = new TableEditor(table);
				editor.grabHorizontal = editor.grabVertical = true; // 宽度高度同表格
				editor.setEditor(bar, item, 1); // 在表格上方显示进度条
			}
		}
		table.getColumn(0).pack();
		table.getColumn(1).setWidth(128);

		shell.pack();
	}

}
