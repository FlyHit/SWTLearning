package table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * �������������ı�����TableEditor��Ȼ������󶨽����� 
 * ��ʵ�ϣ����԰������ؼ���ʵ�ֲ�ͬ�Ĺ���
 * 
 * @author 11648
 *
 */
public class ProgressBarTable {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProgressBarTable window = new ProgressBarTable();
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
		shell.setText("���������ı�");
		shell.setLayout(new FillLayout());

		Table table = new Table(shell, SWT.BORDER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

//		����
		for (int i = 0; i < 2; i++) {
			new TableColumn(table, SWT.NONE);
		}
		table.getColumn(0).setText("Task");
		table.getColumn(1).setText("Progress");

//		��ʮ��
		for (int i = 0; i < 40; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText("Task " + i);
//			����Ϊ5�ı���ʱ��ӽ�����
			if (i % 5 == 0) {
				ProgressBar bar = new ProgressBar(table, SWT.NONE);
				bar.setSelection(i); // ��������ʾ�İٷֱ�
				TableEditor editor = new TableEditor(table);
				editor.grabHorizontal = editor.grabVertical = true; // ��ȸ߶�ͬ���
				editor.setEditor(bar, item, 1); // �ڱ���Ϸ���ʾ������
			}
		}
		table.getColumn(0).pack();
		table.getColumn(1).setWidth(128);

		shell.pack();
	}

}
