package advancedWidget;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * 创建表及添加删除行
 * @author 11648
 *
 */
public class TableExample {

	protected Shell shell;
	private ViewForm viewForm;
	private ToolBar toolBar;
	private Composite composite;
	private Table table;
	private ToolItem addItem;
	private ToolItem deleteItem;
	private ToolItem upItem;
	private ToolItem downItem;
	private ToolItem saveItem;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TableExample window = new TableExample();
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
		SWTResourceManager.disposeImages();
		display.dispose();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(770, 478);
		shell.setText("表的使用");
		shell.setLayout(new FillLayout()); // shell没有layout的话不显示表？
		createViewForm();
		shell.pack();
	}

	/**
	 * ViewForm面板：3+1布局（3上1下） 
	 * ViewForm is used in the workbench to implement a view's label/menu/toolbar local bar.
	 */
	private void createViewForm() {
		viewForm = new ViewForm(shell, SWT.NONE);
		viewForm.setTopCenterSeparate(true);
		createToolBar();
		viewForm.setTopLeft(toolBar);
		createComposite();
		viewForm.setContent(composite);
	}

//	表的布局：网格布局
	private void createComposite() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		composite = new Composite(viewForm, SWT.None);
		composite.setLayout(gridLayout);
		createTable();
	}

	/**
	 * 创建表格
	 */
	private void createTable() {
//		表格布局
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL; // 格子的水平对齐方式：水平铺满
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = SWT.FILL; // 格子的垂直对齐方式
//		创建表格
		table = new Table(composite, SWT.FULL_SELECTION); // 样式：整行选中
		table.setHeaderVisible(true); // 显示表头
		table.setLayoutData(gridData);// 设置表格布局
		table.setLinesVisible(true);// 显示表格线
		String[] tableHeader = { "name", "sex", "tel", "email" };// 表头数组

//		设置表头（列名）：TableColumn
		for (int i = 0; i < tableHeader.length; i++) {
			TableColumn tableColumn = new TableColumn(table, SWT.None);
			tableColumn.setText(tableHeader[i]);
		}

//		设置行：TableItem
		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "a", "male", "123", "1@qq.com" });

		item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "b", "male", "123", "1@qq.com" });

		item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "c", "male", "123", "1@qq.com" });

		for (int i = 0; i < tableHeader.length; i++) {
			table.getColumn(i).pack();
		}

	}

	/**
	 * 创建工具栏
	 */
	private void createToolBar() {
		toolBar = new ToolBar(viewForm, SWT.FLAT);

//		前面的工具按钮设置图标后，后面的图片会适应前面的大小
		addItem = new ToolItem(toolBar, SWT.NONE);
		addItem.setImage(SWTResourceManager.getImage("res\\add.png"));

		deleteItem = new ToolItem(toolBar, SWT.NONE);
		deleteItem.setImage(SWTResourceManager.getImage("res\\delete.png"));

		upItem = new ToolItem(toolBar, SWT.NONE);
		upItem.setImage(SWTResourceManager.getImage("res\\up.png"));

		downItem = new ToolItem(toolBar, SWT.NONE);
		downItem.setImage(SWTResourceManager.getImage("res\\down.png"));

		saveItem = new ToolItem(toolBar, SWT.NONE);
		saveItem.setImage(SWTResourceManager.getImage("res\\save.png"));

		Listener listener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				int index = table.getSelectionIndex(); // 没有选中，index=-1
				if (event.widget == addItem) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(new String[] { "d", "male", "123", "1@qq.com" });
				} else if (event.widget == deleteItem) {
					if (index > -1) {
						table.remove(index);
					}

				} else if (event.widget == upItem) {
					if (index > 0) {
						table.setSelection(index - 1);
					}
				} else if (event.widget == downItem) {
					if (index > -1 && index < table.getItemCount() - 1) {
						table.setSelection(index + 1);
					}
				} else if (event.widget == saveItem) {
					System.out.println("save");
				}
			}
		};

		addItem.addListener(SWT.Selection, listener);
		deleteItem.addListener(SWT.Selection, listener);
		upItem.addListener(SWT.Selection, listener);
		downItem.addListener(SWT.Selection, listener);
		saveItem.addListener(SWT.Selection, listener);
	}
}
