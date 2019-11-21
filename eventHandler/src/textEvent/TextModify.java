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
		shell.setText("�ı��޸�");

		Label lowerLabel = new Label(shell, SWT.NONE);
		lowerLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lowerLabel.setBounds(41, 33, 166, 27);
		lowerLabel.setText("������Сд��ĸ��");

		Label upperLabel = new Label(shell, SWT.NONE);
		upperLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		upperLabel.setBounds(41, 99, 166, 27);
		upperLabel.setText("��Ӧ�Ĵ�д��ĸ��");

		lowerText = new Text(shell, SWT.BORDER);
		lowerText.setBounds(213, 34, 187, 26);
//		VerifyListener:�ı��ı�֮ǰ������ModifyListener���ı�֮����
		lowerText.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				char c = e.character;

				/*
				 * ����������Сд��ĸ��ͬʱ�����˸�ɾ���������˸�Ҳ�Ǳ��������ַ����� 
				 * ���������жϣ���ô�˸��Сд��ĸ��ɾ��ʧ��
				 */
				if (!(e.keyCode == SWT.BS) && (!Character.isAlphabetic(c) || !Character.isLowerCase(c))) {
					e.doit = false;
					return;
				}

//				�������������ĸתΪ��д��ĸ��ӵ�������ı���
				upperText.append("" + Character.toUpperCase(c));
			}
		});

		upperText = new Text(shell, SWT.BORDER);
		upperText.setBounds(213, 100, 187, 26);
	}
}
