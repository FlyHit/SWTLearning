package table;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * 创建所有表格都可编辑的表
 * 
 * @author 11648
 *
 */
public class EditCell {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EditCell window = new EditCell();
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
		shell.setSize(791, 569);
		shell.setText("编辑表格");
		shell.setLayout(new FillLayout());

		final Table table = new Table(shell, SWT.BORDER | SWT.MULTI);
		table.setLinesVisible(true);

//		3列
		for (int i = 0; i < 3; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setWidth(100);
		}

//		3行
		for (int i = 0; i < 3; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "" + i, "" + i, "" + i });
		}

		final TableEditor editor = new TableEditor(table);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		table.addListener(SWT.MouseDown, event -> {
			Rectangle clientArea = table.getClientArea(); // 表能够显示数据的矩形区域
			Point pt = new Point(event.x, event.y); // 鼠标的坐标点
			int index = table.getTopIndex();

//			遍历表格，使全部表格可编辑
			while (index < table.getItemCount()) {
				boolean visible = false;
				final TableItem item = table.getItem(index); // 行
//				遍历列
				for (int i = 0; i < table.getColumnCount(); i++) {
					Rectangle rect = item.getBounds(i); // 表格的矩形区域
//					鼠标是否落在该表格上，是则将Text绑定到该表格
					if (rect.contains(pt)) {
						final int column = i;
						final Text text = new Text(table, SWT.NONE);
						Listener textListener = e -> {
							switch (e.type) {
							case SWT.FocusOut:
								item.setText(column, text.getText());
								text.dispose();
								break;
							case SWT.Traverse:
								switch (e.detail) {
//									    enter键：设置文本
								case SWT.TRAVERSE_RETURN:
									item.setText(column, text.getText());

//										退出键：取消文本的修改
								case SWT.TRAVERSE_ESCAPE:
									text.dispose();
									e.doit = false;
								}
								break;
							}
						};
						text.addListener(SWT.FocusOut, textListener);
						text.addListener(SWT.Traverse, textListener);
						editor.setEditor(text, item, i);
						text.setText(item.getText(i));
						text.selectAll();
						text.setFocus();
						return; // 成功定位鼠标后便不再遍历
					}

//					有什么用？
					if (!visible && rect.intersects(clientArea)) {
						visible = true;
					}
				}
				if (!visible)
					return; // 有什么用？
				index++;
			}
		});

		shell.pack();
	}
}
