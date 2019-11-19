package myFirstSWT;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class HelloWorld {

	/*
	 * Shell：表示桌面管理的窗口，包括顶层窗口（没有parent的窗口）， 次级窗口或对话窗口（有parent）
	 */
	protected Shell shell;

	/**
	 * 加载应用
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HelloWorld window = new HelloWorld();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打开窗口
	 */
	public void open() {
		/*
		 * Display负责管理SWT和操作系统的交互，最重要的功能是 实现SWT的事件循环（本地部件界面调用），也提供了访问 操作系统信息的方法
		 */
		Display display = Display.getDefault();
		createContents();
		shell.open(); // 打开窗口，获得焦点（接收用户输入的能力），并设为激活状态
		shell.layout();
//		disposed:销毁部件及其子部件，并进行垃圾回收
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * 创建窗体的内容
	 */
	protected void createContents() {
		/*
		 * 传入display表示在displays上创建shell，此时shell为顶层窗口
		 * 传入shell表示该shell为待构建的shell的parent，此时为次级窗口
		 * 同时可以传入style来自定义窗口样式,SWT.TITLE只有标题没有最大化等按钮, SWT.RESIZE可以鼠标拖动改变窗口大小，使用|可以应用多个样式
		 */
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application"); // 窗口的标题

		Label lblHelloWorld = new Label(shell, SWT.NONE);
		lblHelloWorld.setBounds(149, 109, 136, 20);
		lblHelloWorld.setText("Hello World!");
	}
}
