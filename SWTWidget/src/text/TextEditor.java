package text;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 一个简单的文本编辑器
 * 
 * @author 11648
 *
 */
public class TextEditor {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TextEditor window = new TextEditor();
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
		shell.setSize(502, 300);
		shell.setText("文本编辑器");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 10, 454, 181);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.selectAll();  // 选中所有文本
				text.setFocus();  // 选中的内容加底色
			}
		});
		btnNewButton.setBounds(10, 213, 98, 30);
		btnNewButton.setText("全选");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				如果选中了文本，那么清除选择
				if (text.getSelectionCount() > 0) {
					text.clearSelection();
				}
			}
		});
		btnNewButton_1.setBounds(126, 213, 98, 30);
		btnNewButton_1.setText("取消全选");

		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.copy(); // 复制文本
//				相当于在文本末尾处点击一下，目的是在末尾处粘贴文本
				text.setSelection(text.getCaretPosition());  
			}
		});
		btnNewButton_2.setBounds(244, 213, 98, 30);
		btnNewButton_2.setText("复制");

		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				粘贴文本,全选之后文本框的光标未被选择，因此点一次粘贴按钮没反应，要按两次
				text.paste();
			}
		});
		btnNewButton_3.setBounds(366, 213, 98, 30);
		btnNewButton_3.setText("粘贴");
	}
}
