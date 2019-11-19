package myFirstSWT;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import javafx.scene.Parent;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * �����ര��
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
		shell.setText("�ര������");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
//		ע����굥���¼�
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createChildShell(shell,"�Ӵ���");
			}
		});
		btnNewButton.setBounds(22, 22, 98, 30);
		btnNewButton.setText("�����Ӵ���");
	}
	
	/**
	 * �����Ӵ���
	 * @param parent �Ӵ��ڵ�parent
	 * @param name �Ӵ��ڵ�����
	 */
	 private void createChildShell(Shell parent, String name) {
		Shell shell = new Shell(parent);
		shell.setText(name);
		shell.setSize(80, 80);
		shell.open();
	}
}
