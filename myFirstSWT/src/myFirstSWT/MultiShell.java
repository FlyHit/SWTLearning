package myFirstSWT;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import javafx.scene.Parent;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 创建多窗口
 * @author 11648
 *
 */
public class MultiShell {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MultiShell window = new MultiShell();
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
		shell.setText("多窗口例子");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
//		注册鼠标单击事件
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createChildShell(shell,"子窗口");
			}
		});
		btnNewButton.setBounds(22, 22, 98, 30);
		btnNewButton.setText("创建子窗口");
	}
	
	/**
	 * 创建子窗口
	 * @param parent 子窗口的parent
	 * @param name 子窗口的名称
	 */
	 private void createChildShell(Shell parent, String name) {
		Shell shell = new Shell(parent);
		shell.setText(name);
		shell.setSize(80, 80);
		shell.open();
	}
}
