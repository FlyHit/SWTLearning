package table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * �༭�У�tableItem�����ı�
 * 
 * @author 11648
 *
 */
public class EditTable {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EditTable window = new EditTable();
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
		display.dispose();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(300, 300);
		shell.setText("�༭��");
		shell.setLayout(new FillLayout());

		final Table table = new Table(shell, SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
//		������
		TableColumn column1 = new TableColumn(table, SWT.NONE);
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		for (int i = 0; i < 10; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
//			String���������һ��ͱ�����ƥ��
			item.setText(new String[] { "item " + i, "edit this value" });
		}
		column1.pack();
		column2.pack();

		/**
		 * TableEditor��һ���ڱ������Ŀؼ����������ܹ�׷�ٱ����ƶ�����С����
		 * TableEditor�������ڱ������ʾ�ı����������߰�ť��������һ���û����Ա༭��������
		 * ���ÿɱ༭��ķ���:
		 * 1. �ȳ�ʼ�����У�
		 * 2. ����TableEditor���󣨸���׷�ٱ��
		 * 3. �����ؼ�����Text,��ʾ�ڱ���ϣ��ṩ�༭���ܣ�ע��Ҫͬ������text�����ݣ�
		 * 4. setEditor����TableEditor���ؼ������������
		 */
		final TableEditor editor = new TableEditor(table);
		// TableEditor��������Ĵ�Сһ�²��Ҵ���50�����ص�
		editor.horizontalAlignment = SWT.LEFT; //ˮƽ���뷽ʽ
		editor.grabHorizontal = true; // ����Ŀ��һ��
		editor.minimumWidth = 50; 
		// ����ɱ༭���У��ڶ��пɱ༭
		final int EDITABLECOLUMN = 1;

		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// ����TableEditor����󶨵Ŀؼ�����Text���ÿؼ���ʾ�ڿɱ༭��壩
				Control oldEditor = editor.getEditor();
				if (oldEditor != null)
					oldEditor.dispose();

				// ȷ����ѡ�е���
				TableItem item = (TableItem) e.item;
				if (item == null)
					return;

				// �༭���ؼ�������Ϊ�ñ���Ӷ���
				Text newEditor = new Text(table, SWT.NONE);
//				�༭��Text�����ݺͱ������ݱ���һ��
				newEditor.setText(item.getText(EDITABLECOLUMN));
				newEditor.addModifyListener(me -> {
					Text text = (Text) editor.getEditor();
//					getItem()�����ر�TableEditor׷�ٵı�����ڵ���
//					setText(�У��ı�)���޸�ĳ�������ݣ���������ݺ�Text����һ��
					editor.getItem().setText(EDITABLECOLUMN, text.getText());
				});
				newEditor.selectAll(); // ѡ��ȫ���ı�
				newEditor.setFocus(); 
//				���ñ���Ϸ���ʾ�Ŀؼ���Text����ͨ��item���У���EDITABLECOLUMN���У���λ���
				editor.setEditor(newEditor, item, EDITABLECOLUMN);
			}
		});

	}

}
