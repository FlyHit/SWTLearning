package textEvent;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class TextModify {

	protected Shell shell;
	private Text lowerText;
	private Text upperText;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TextModify window = new TextModify();
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
		shell.setText("文本修改");

		Label lowerLabel = new Label(shell, SWT.NONE);
		lowerLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lowerLabel.setBounds(41, 33, 166, 27);
		lowerLabel.setText("请输入小写字母：");

		Label upperLabel = new Label(shell, SWT.NONE);
		upperLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		upperLabel.setBounds(41, 99, 166, 27);
		upperLabel.setText("相应的大写字母：");

		lowerText = new Text(shell, SWT.BORDER);
		lowerText.setBounds(213, 34, 187, 26);
//		VerifyListener:文本改变之前发生；ModifyListener：改变之后发生
		lowerText.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				char c = e.character;

				/*
				 * 不允许输入小写字母，同时允许退格删除（这里退格也是被当成了字符）， 
				 * 如果不添加判断，那么退格非小写字母，删除失败
				 */
				if (!(e.keyCode == SWT.BS) && (!Character.isAlphabetic(c) || !Character.isLowerCase(c))) {
					e.doit = false;
					return;
				}

//				将上面输入的字母转为大写字母添加到下面的文本框
				upperText.append("" + Character.toUpperCase(c));
			}
		});

		upperText = new Text(shell, SWT.BORDER);
		upperText.setBounds(213, 100, 187, 26);
	}
}
