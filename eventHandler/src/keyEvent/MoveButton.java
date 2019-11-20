package keyEvent;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;

import javax.sound.midi.Soundbank;

import org.eclipse.jface.bindings.keys.SWTKeySupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * ʹ�÷�����ƶ���ť
 * 
 * @author 11648
 *
 */
public class MoveButton {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MoveButton window = new MoveButton();
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
		shell.setText("�ƶ���ť");

		Button moveButton = new Button(shell, SWT.FLAT);
		moveButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		moveButton.setBounds(91, 99, 98, 30);
		moveButton.setText("Move me��");

		moveButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// ��ô������¼��Ŀؼ�����ע�ⲻҪֱ��ʹ��moveButton
				Control control = (Control) e.widget;
				int x = control.getLocation().x;
				int y = control.getLocation().y;

				/*
				 * ������ƶ�,ʹ��keyCode(ASC��) SWT��û����ĸ���Ķ��壬
				 * ���Ҫʹ��keyCode��ֻ����ASC���룬Java���ַ����ַ�����unicode�� ����ֱ����'a'��
				 */
				switch (e.keyCode) {
				case SWT.ARROW_UP:
					y -= 5;
					break;
				case SWT.ARROW_DOWN:
					y += 5;
					break;
				case SWT.ARROW_LEFT:
					x -= 5;
					break;
				case SWT.ARROW_RIGHT:
					x += 5;
					break;
				default:
					break;
				}

//				WASD�ƶ���ʹ��character��Unicode��,ע���Сд����
				switch (e.character) {
				case 'w':
				case 'W':
					y -= 5;
					break;
				case 's':
				case 'S':
					y += 5;
					break;
				case 'a':
				case 'A':
					x -= 5;
					break;
				case 'd':
				case 'D':
					x += 5;
					break;
				default:
					break;
				}

				/*
				 * ctr+a: �ı䰴ť������ɫ ʹ��convertEventToUnmodifiedAccelerator������ϼ�����ݼ�����
				 * ע��ʹ�õ���stateMask+keyCode��keyCode������ASC������� ��д��ĸ��Ӧ��ֵ��65��ӦA
				 */
				if (SWTKeySupport.convertEventToUnmodifiedAccelerator(e) == (SWT.CTRL | 65)) {
					control.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
				}

//				Ҳ����������д����97��Ӧa
				if ((e.stateMask & SWT.CTRL) != 0) {
					if (e.keyCode == 97) {
						control.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
					}
				}

				moveButton.setLocation(x, y);
			}
		});
	}
}
