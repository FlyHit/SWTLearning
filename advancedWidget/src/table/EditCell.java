package table;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * �������б�񶼿ɱ༭�ı�
 * 
 * @author 11648
 *
 */
public class EditCell {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EditCell window = new EditCell();
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
		shell.setSize(791, 569);
		shell.setText("�༭���");
		shell.setLayout(new FillLayout());

		final Table table = new Table(shell, SWT.BORDER | SWT.MULTI);
		table.setLinesVisible(true);

//		3��
		for (int i = 0; i < 3; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setWidth(100);
		}

//		3��
		for (int i = 0; i < 3; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "" + i, "" + i, "" + i });
		}

		final TableEditor editor = new TableEditor(table);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		table.addListener(SWT.MouseDown, event -> {
			Rectangle clientArea = table.getClientArea(); // ���ܹ���ʾ���ݵľ�������
			Point pt = new Point(event.x, event.y); // ���������
			int index = table.getTopIndex();

//			�������ʹȫ�����ɱ༭
			while (index < table.getItemCount()) {
				boolean visible = false;
				final TableItem item = table.getItem(index); // ��
//				������
				for (int i = 0; i < table.getColumnCount(); i++) {
					Rectangle rect = item.getBounds(i); // ���ľ�������
//					����Ƿ����ڸñ���ϣ�����Text�󶨵��ñ��
					if (rect.contains(pt)) {
						final int column = i;
						final Text text = new Text(table, SWT.NONE);
						Listener textListener = e -> {
							switch (e.type) {
							case SWT.FocusOut:
								item.setText(column, text.getText());
								text.dispose();
								break;
							case SWT.Traverse:
								switch (e.detail) {
//									    enter���������ı�
								case SWT.TRAVERSE_RETURN:
									item.setText(column, text.getText());

//										�˳�����ȡ���ı����޸�
								case SWT.TRAVERSE_ESCAPE:
									text.dispose();
									e.doit = false;
								}
								break;
							}
						};
						text.addListener(SWT.FocusOut, textListener);
						text.addListener(SWT.Traverse, textListener);
						editor.setEditor(text, item, i);
						text.setText(item.getText(i));
						text.selectAll();
						text.setFocus();
						return; // �ɹ���λ����㲻�ٱ���
					}

//					��ʲô�ã�
					if (!visible && rect.intersects(clientArea)) {
						visible = true;
					}
				}
				if (!visible)
					return; // ��ʲô�ã�
				index++;
			}
		});

		shell.pack();
	}
}
