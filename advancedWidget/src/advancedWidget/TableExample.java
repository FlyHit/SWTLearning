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
 * ���������ɾ����
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
		shell.setText("���ʹ��");
		shell.setLayout(new FillLayout()); // shellû��layout�Ļ�����ʾ��
		createViewForm();
		shell.pack();
	}

	/**
	 * ViewForm��壺3+1���֣�3��1�£� 
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

//	��Ĳ��֣����񲼾�
	private void createComposite() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		composite = new Composite(viewForm, SWT.None);
		composite.setLayout(gridLayout);
		createTable();
	}

	/**
	 * �������
	 */
	private void createTable() {
//		��񲼾�
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL; // ���ӵ�ˮƽ���뷽ʽ��ˮƽ����
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = SWT.FILL; // ���ӵĴ�ֱ���뷽ʽ
//		�������
		table = new Table(composite, SWT.FULL_SELECTION); // ��ʽ������ѡ��
		table.setHeaderVisible(true); // ��ʾ��ͷ
		table.setLayoutData(gridData);// ���ñ�񲼾�
		table.setLinesVisible(true);// ��ʾ�����
		String[] tableHeader = { "name", "sex", "tel", "email" };// ��ͷ����

//		���ñ�ͷ����������TableColumn
		for (int i = 0; i < tableHeader.length; i++) {
			TableColumn tableColumn = new TableColumn(table, SWT.None);
			tableColumn.setText(tableHeader[i]);
		}

//		�����У�TableItem
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
	 * ����������
	 */
	private void createToolBar() {
		toolBar = new ToolBar(viewForm, SWT.FLAT);

//		ǰ��Ĺ��߰�ť����ͼ��󣬺����ͼƬ����Ӧǰ��Ĵ�С
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
				int index = table.getSelectionIndex(); // û��ѡ�У�index=-1
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
