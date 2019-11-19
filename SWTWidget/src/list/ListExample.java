package list;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ListExample {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ListExample window = new ListExample();
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
		final int UP = 0;
		final int DOWN = 1;

		shell = new Shell();
		shell.setSize(462, 300);
		shell.setText("�б�ʾ��");
		shell.setLayout(new FormLayout());

		String[] itemLeft = new String[20];
		String[] itemRight = new String[0];

//		��ʼ�����б���Ŀ
		for (int i = 0; i < itemLeft.length; i++) {
			itemLeft[i] = "item" + i;
		}

//		�б���ʽ:��ֱ������+����ѡ��
		List list_left = new List(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		list_left.setItems(itemLeft); // �����б���Ŀ
		FormData fd_list_left = new FormData();
		fd_list_left.bottom = new FormAttachment(0, 240);
		fd_list_left.right = new FormAttachment(0, 146);
		fd_list_left.top = new FormAttachment(0, 9);
		fd_list_left.left = new FormAttachment(0, 17);
		list_left.setLayoutData(fd_list_left);

		Group group = new Group(shell, SWT.NONE);
		GridLayout gl_group = new GridLayout(1, false);
		gl_group.verticalSpacing = 20;
		group.setLayout(gl_group);
		FormData fd_group = new FormData();
		fd_group.right = new FormAttachment(list_left, 64, SWT.RIGHT);
		fd_group.top = new FormAttachment(0, 8);
		fd_group.left = new FormAttachment(list_left, 14);
		fd_group.bottom = new FormAttachment(0, 242);
		group.setLayoutData(fd_group);

		List list_right = new List(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		list_right.setItems(itemRight);
		FormData fd_list_right = new FormData();
		fd_list_right.right = new FormAttachment(group, 157, SWT.RIGHT);
		fd_list_right.top = new FormAttachment(0, 9);
		fd_list_right.left = new FormAttachment(group, 28);
		fd_list_right.bottom = new FormAttachment(0, 243);
		list_right.setLayoutData(fd_list_right);

		Group group_1 = new Group(shell, SWT.NONE);
		GridLayout gl_group_1 = new GridLayout(1, false);
		gl_group_1.verticalSpacing = 20;
		group_1.setLayout(gl_group_1);
		FormData fd_group_1 = new FormData();
		fd_group_1.top = new FormAttachment(0, 60);
		fd_group_1.left = new FormAttachment(list_right, 20);
		fd_group_1.bottom = new FormAttachment(0, 183);
		group_1.setLayoutData(fd_group_1);

//		�¼������ࣨ�ڲ��ࣩ����һ�����жԲ�ͬ�¼����д���
		SelectionAdapter listener = new SelectionAdapter() {
//			��ť�����¼��Ĵ�����
			public void widgetSelected(SelectionEvent e) {
				Button button = (Button) e.widget;  // ��ȡ�����¼��Ŀؼ�����

				switch (button.getText()) {
				case ">":
					verifyVal(list_left.getSelection(), list_left, list_right);
					break;
				case "<":
					verifyVal(list_right.getSelection(), list_right, list_left);
					break;
				case ">>":
					verifyVal(list_left.getItems(), list_left, list_right);
					break;
				case "<<":
					verifyVal(list_right.getItems(), list_right, list_left);
					break;
				case "��":
					move(list_right, UP);
					break;
				case "��":
					move(list_right, DOWN);
				}
			}
			
			/**
			 * �б���Ŀ��������
			 * @param selected ѡ�����Ŀ
			 * @param from ת�����б�
			 * @param to ת����б�
			 */
			public void verifyVal(String[] selected, List from, List to) {
				for (String s : selected) {
					from.remove(s);
					to.add(s);
				}
			}

			/**
			 * �б���Ŀ�����ƶ�
			 * @param list �������б�
			 * @param direction �ƶ��ķ���
			 */
			public void move(List list, int direction) {
				String[] itemSelected = list.getSelection(); // ѡ�����Ŀ
				java.util.List<String> listMoved = new ArrayList<>(Arrays.asList(itemSelected)); // ����תlist
				String[] items = list.getItems(); // ������Ŀ
				int startIndex = list.indexOf(itemSelected[0]);
				int num_Items = itemSelected.length;
				int endIndex = list.indexOf(itemSelected[num_Items - 1]);

				if (direction == UP && startIndex > 0) {
					for (int i = startIndex - 1; i <= endIndex; i++) {
						if (!listMoved.contains(items[i])) {
							listMoved.add(items[i]);
						}
					}

					int j = 0;
					for (int i = startIndex - 1; i <= endIndex; i++) {
						items[i] = listMoved.get(j++);
					}
				} else if (direction == DOWN && endIndex < items.length - 1) {
					for (int i = endIndex + 1; i >= startIndex; i--) {
						if (!listMoved.contains(items[i])) {
							listMoved.add(0, items[i]);
						}
					}

					int j = 0;
					for (int i = startIndex; i <= endIndex + 1; i++) {
						items[i] = listMoved.get(j++);
					}
				}

				list.setItems(items);
			}
		};

		// ���尴ť��ע�ᵥ���¼�
		Button add2Right = new Button(group, SWT.NONE);
		add2Right.setText(">");
		add2Right.addSelectionListener(listener);

		Button addAll2R = new Button(group, SWT.NONE);
		addAll2R.setText(">>");
		addAll2R.addSelectionListener(listener);

		Button add2Left = new Button(group, SWT.NONE);
		add2Left.setText("<");
		add2Left.addSelectionListener(listener);

		Button addAll2L = new Button(group, SWT.NONE);
		addAll2L.setText("<<");
		addAll2L.addSelectionListener(listener);

		Button moveUp = new Button(group_1, SWT.NONE);
		moveUp.setText("��");
		moveUp.addSelectionListener(listener);

		Button moveDown = new Button(group_1, SWT.NONE);
		moveDown.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		moveDown.setText("��");
		moveDown.addSelectionListener(listener);
	}
}
