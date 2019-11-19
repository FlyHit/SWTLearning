package myFirstSWT;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class HelloWorld {

	/*
	 * Shell����ʾ�������Ĵ��ڣ��������㴰�ڣ�û��parent�Ĵ��ڣ��� �μ����ڻ�Ի����ڣ���parent��
	 */
	protected Shell shell;

	/**
	 * ����Ӧ��
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
	 * �򿪴���
	 */
	public void open() {
		/*
		 * Display�������SWT�Ͳ���ϵͳ�Ľ���������Ҫ�Ĺ����� ʵ��SWT���¼�ѭ�������ز���������ã���Ҳ�ṩ�˷��� ����ϵͳ��Ϣ�ķ���
		 */
		Display display = Display.getDefault();
		createContents();
		shell.open(); // �򿪴��ڣ���ý��㣨�����û������������������Ϊ����״̬
		shell.layout();
//		disposed:���ٲ��������Ӳ�������������������
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * �������������
	 */
	protected void createContents() {
		/*
		 * ����display��ʾ��displays�ϴ���shell����ʱshellΪ���㴰��
		 * ����shell��ʾ��shellΪ��������shell��parent����ʱΪ�μ�����
		 * ͬʱ���Դ���style���Զ��崰����ʽ,SWT.TITLEֻ�б���û����󻯵Ȱ�ť, SWT.RESIZE��������϶��ı䴰�ڴ�С��ʹ��|����Ӧ�ö����ʽ
		 */
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application"); // ���ڵı���

		Label lblHelloWorld = new Label(shell, SWT.NONE);
		lblHelloWorld.setBounds(149, 109, 136, 20);
		lblHelloWorld.setText("Hello World!");
	}
}
