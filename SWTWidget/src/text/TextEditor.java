package text;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * һ���򵥵��ı��༭��
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
		shell.setText("�ı��༭��");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 10, 454, 181);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.selectAll();  // ѡ�������ı�
				text.setFocus();  // ѡ�е����ݼӵ�ɫ
			}
		});
		btnNewButton.setBounds(10, 213, 98, 30);
		btnNewButton.setText("ȫѡ");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				���ѡ�����ı�����ô���ѡ��
				if (text.getSelectionCount() > 0) {
					text.clearSelection();
				}
			}
		});
		btnNewButton_1.setBounds(126, 213, 98, 30);
		btnNewButton_1.setText("ȡ��ȫѡ");

		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.copy(); // �����ı�
//				�൱�����ı�ĩβ�����һ�£�Ŀ������ĩβ��ճ���ı�
				text.setSelection(text.getCaretPosition());  
			}
		});
		btnNewButton_2.setBounds(244, 213, 98, 30);
		btnNewButton_2.setText("����");

		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				ճ���ı�,ȫѡ֮���ı���Ĺ��δ��ѡ����˵�һ��ճ����ťû��Ӧ��Ҫ������
				text.paste();
			}
		});
		btnNewButton_3.setBounds(366, 213, 98, 30);
		btnNewButton_3.setText("ճ��");
	}
}
