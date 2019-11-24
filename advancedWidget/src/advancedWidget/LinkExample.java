package advancedWidget;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;

import java.io.IOException;

import org.eclipse.swt.SWT;

/**
 * ʹ��Ĭ��������������ı�
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

//		���������ı�
		String text = "����һ�������ı���<a href=\"https:\\\\www.baidu.com\">�ٶ�һ��</a>";
		Link link = new Link(shell, SWT.NONE);
		link.setBounds(86, 80, 66, 20);
		link.setText(text);
		link.pack();
//		������ӣ�ʹ��Ĭ���������
		link.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				try {
					Link l = (Link) event.widget;
					String[] splitString = l.getText().split("\"", 3);  // �ָ���ȡ����
					/*
					 * Runtime.getRuntime:��ȡ��ǰJava�����runtime
					 * exec():ִ���ַ������͵�����
					 * ����������ʹ��Ĭ�������������
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
