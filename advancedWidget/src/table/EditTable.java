package table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * 编辑行（tableItem）的文本
 * 
 * @author 11648
 *
 */
public class EditTable {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EditTable window = new EditTable();
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
		display.dispose();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(300, 300);
		shell.setText("编辑表");
		shell.setLayout(new FillLayout());

		final Table table = new Table(shell, SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
//		创建列
		TableColumn column1 = new TableColumn(table, SWT.NONE);
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		for (int i = 0; i < 10; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
//			String数组的列数一般和表列数匹配
			item.setText(new String[] { "item " + i, "edit this value" });
		}
		column1.pack();
		column2.pack();

		/**
		 * TableEditor：一个在表格上面的控件管理器，能够追踪表格的移动及大小调整
		 * TableEditor可用于在表格上显示文本部件（或者按钮），这样一来用户可以编辑表格的内容
		 * 设置可编辑表的方法:
		 * 1. 先初始化表（行）
		 * 2. 创建TableEditor对象（负责追踪表格）
		 * 3. 创建控件（如Text,显示在表格上，提供编辑功能，注意要同步表格和text的内容）
		 * 4. setEditor：将TableEditor、控件、表格三个绑定
		 */
		final TableEditor editor = new TableEditor(table);
		// TableEditor必须与表格的大小一致并且大于50个像素点
		editor.horizontalAlignment = SWT.LEFT; //水平对齐方式
		editor.grabHorizontal = true; // 与表格的宽度一致
		editor.minimumWidth = 50; 
		// 定义可编辑的列：第二列可编辑
		final int EDITABLECOLUMN = 1;

		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 清理TableEditor对象绑定的控件，如Text（该控件显示在可编辑面板）
				Control oldEditor = editor.getEditor();
				if (oldEditor != null)
					oldEditor.dispose();

				// 确定被选中的行
				TableItem item = (TableItem) e.item;
				if (item == null)
					return;

				// 编辑器控件必须设为该表的子对象
				Text newEditor = new Text(table, SWT.NONE);
//				编辑器Text的内容和表格的内容保持一致
				newEditor.setText(item.getText(EDITABLECOLUMN));
				newEditor.addModifyListener(me -> {
					Text text = (Text) editor.getEditor();
//					getItem()：返回被TableEditor追踪的表格所在的行
//					setText(列，文本)：修改某表格的内容，即表格内容和Text保持一致
					editor.getItem().setText(EDITABLECOLUMN, text.getText());
				});
				newEditor.selectAll(); // 选中全部文本
				newEditor.setFocus(); 
//				设置表格上方显示的控件（Text），通过item（行）、EDITABLECOLUMN（列）定位表格
				editor.setEditor(newEditor, item, EDITABLECOLUMN);
			}
		});

	}

}
