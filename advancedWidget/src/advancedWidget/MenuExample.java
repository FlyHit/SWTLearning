package advancedWidget;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

/**
 * 创建菜单栏 菜单栏的层级一般是menu-menuItem-menu（子菜单）-menuItem
 * 
 * @author 11648
 *
 */
public class MenuExample {

	protected Shell shell;
	private Menu menu;
	private MenuItem fileItem;
	private MenuItem viewItem;
	private Menu fileItem_child;
	private MenuItem newFile;
	private MenuItem newWin;
	private MenuItem exitItem;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MenuExample window = new MenuExample();
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
		shell.setText("菜单栏的使用");

//		菜单栏，可包含多个菜单项（menuItem）
		menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

//		菜单项，样式为CASCADE(瀑布，即点击后出现一栏竖直的菜单项）
		fileItem = new MenuItem(menu, SWT.CASCADE);
		fileItem.setText("文件(&F)"); // 使用&+字母的方式设置助记键

//		文件菜单项的子菜单
		fileItem_child = new Menu(fileItem);
		fileItem.setMenu(fileItem_child);

//		子菜单的各菜单项
		newFile = new MenuItem(fileItem_child, SWT.NONE);
		newFile.setText("新建(&N)	Ctrl+N");
		newFile.setAccelerator(SWT.CTRL + 'N'); // 设置快捷键

		newWin = new MenuItem(fileItem_child, SWT.NONE);
		newWin.setText("新窗口(&W)	Ctrl+Shift+N");

		new MenuItem(fileItem_child, SWT.SEPARATOR); // 菜单项分割线

//		✔样式菜单项
		MenuItem checkBoxItem = new MenuItem(fileItem_child, SWT.CHECK);
		checkBoxItem.setText("checkBox");

//		●样式菜单项
		MenuItem mntmNewRadiobutton = new MenuItem(fileItem_child, SWT.RADIO);
		mntmNewRadiobutton.setText("radioButton");

		exitItem = new MenuItem(fileItem_child, SWT.NONE);
//		退出菜单项点击事件
		exitItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		exitItem.setText("退出(&X)");

		viewItem = new MenuItem(menu, SWT.NONE);
		viewItem.setText("视图（&V）");

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(153, 97, 98, 30);
		btnNewButton.setText("New Button");

//		右键菜单
		Menu popupMenu1 = new Menu(btnNewButton);
		btnNewButton.setMenu(popupMenu1);

		MenuItem mntmNewItem = new MenuItem(popupMenu1, SWT.NONE);
		mntmNewItem.setText("右键菜单");

		Menu menu_2 = new Menu(shell);
		shell.setMenu(menu_2);

		MenuItem popup_menu2 = new MenuItem(menu_2, SWT.NONE);
		popup_menu2.setText("右键菜单");
	}
}
