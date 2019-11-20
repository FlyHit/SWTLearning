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
 * 使用方向键移动按钮
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
		shell.setText("移动按钮");

		Button moveButton = new Button(shell, SWT.FLAT);
		moveButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		moveButton.setBounds(91, 99, 98, 30);
		moveButton.setText("Move me！");

		moveButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 获得触发该事件的控件对象，注意不要直接使用moveButton
				Control control = (Control) e.widget;
				int x = control.getLocation().x;
				int y = control.getLocation().y;

				/*
				 * 方向键移动,使用keyCode(ASCⅡ) SWT里没有字母键的定义，
				 * 如果要使用keyCode，只能用ASCⅡ码，Java里字符和字符串是unicode， 不能直接用'a'等
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

//				WASD移动，使用character（Unicode）,注意大小写敏感
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
				 * ctr+a: 改变按钮背景颜色 使用convertEventToUnmodifiedAccelerator设置组合键（快捷键），
				 * 注意使用的是stateMask+keyCode，keyCode首先是ASCⅡ，其次是 大写字母对应的值，65对应A
				 */
				if (SWTKeySupport.convertEventToUnmodifiedAccelerator(e) == (SWT.CTRL | 65)) {
					control.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
				}

//				也可以用这种写法，97对应a
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
