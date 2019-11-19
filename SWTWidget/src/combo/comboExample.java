package combo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

public class comboExample {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			comboExample window = new comboExample();
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
		shell.setText("��Ͽ�");
		
		String[] items = new String[4];
		for(int i = 0;i<items.length;i++) {
			items[i] ="ѡ��"+i;
		}
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(72, 45, 195, 28);
		combo.setItems(items);
		
//		ע����������¼���ʹ���ҷ����ͬ���·����
		combo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ARROW_LEFT)
					combo.select(combo.getSelectionIndex()-1);
				else if(e.keyCode==SWT.ARROW_RIGHT)
					combo.select(combo.getSelectionIndex()+1);
			}
		});	
	}
}
