package keyEvent;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * 使用方向键移动按钮
 * @author 11648
 *
 */
public class MoveButton {

	protected Shell shell;

	/**
	 * Launch the application.
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
		moveButton.setBounds(112, 96, 98, 30);
		moveButton.setText("Move me！");
		
		moveButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 获得触发该事件的控件对象，注意不要直接使用moveButton
				Control control = (Control)e.widget;  
				int x = control.getLocation().x;
				int y = control.getLocation().y;
				
				switch (e.keyCode) {
				case SWT.ARROW_UP:
					y-=5;
					break;
				case SWT.ARROW_DOWN:
					y+=5;
					break;
				case SWT.ARROW_LEFT:
					x-=5;
					break;
				case SWT.ARROW_RIGHT:
					x+=5;
					break;
				default:
					break;
				}
	
				moveButton.setLocation(x,y);
			}
		});
	}
}
