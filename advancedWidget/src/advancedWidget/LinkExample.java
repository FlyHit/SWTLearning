package advancedWidget;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;

import java.io.IOException;

import org.eclipse.swt.SWT;

/**
 * 使用默认浏览器打开链接文本
 * @author 11648
 *
 */
public class LinkExample {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LinkExample window = new LinkExample();
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
		shell.setText("SWT Application");

//		创建链接文本
		String text = "这是一个链接文本：<a href=\"https:\\\\www.baidu.com\">百度一下</a>";
		Link link = new Link(shell, SWT.NONE);
		link.setBounds(86, 80, 66, 20);
		link.setText(text);
		link.pack();
//		点击链接，使用默认浏览器打开
		link.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				try {
					Link l = (Link) event.widget;
					String[] splitString = l.getText().split("\"", 3);  // 分割提取链接
					/*
					 * Runtime.getRuntime:获取当前Java程序的runtime
					 * exec():执行字符串类型的命令
					 * 这里命令是使用默认浏览器打开链接
					 */
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + splitString[1]);
					for (String s : splitString) {
						System.out.println(s);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
