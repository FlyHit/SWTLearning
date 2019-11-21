package mouseEvent;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

/**
 * 鼠标拖动按钮: 使用内部类的形式实现鼠标输入事件的多个接口（使用 
 * 内部类可以方便访问外部类的实例域，这里是dragButton等），然后为
 * 按钮注册监听器。设置坐标时要注意部件是通过上顶点定位。
 * 
 * @author 11648
 *
 */
public class DragButton {

	protected Shell shell;
	private Button dragButton;
	cButtonListener listener;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DragButton window = new DragButton();
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
		this.shell.open();
		this.shell.layout();
		while (!this.shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		this.shell = new Shell();
		this.shell.setSize(450, 300);
		this.shell.setText("鼠标拖动");

		this.dragButton = new Button(this.shell, SWT.NONE);
		this.dragButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		this.dragButton.setBounds(133, 97, 98, 30);
		this.dragButton.setText("Drag me!");

		this.listener = new cButtonListener();
		this.dragButton.addMouseListener(this.listener);
		this.dragButton.addMouseTrackListener(this.listener);
	}

	public class cButtonListener implements MouseListener, MouseTrackListener, MouseMoveListener {
		private int mouseX;
		private int mouseY;

		@Override
//		鼠标进入某区域
		public void mouseEnter(MouseEvent e) {
			System.out.println("Now my mouse enters");
		}

		@Override
//		鼠标离开某区域
		public void mouseExit(MouseEvent e) {
			System.out.println("Now my mouse exits");
		}

		@Override
//		鼠标悬停在某区域
		public void mouseHover(MouseEvent e) {
			System.out.println("Now my mouse hovers");
		}

		@Override
//		鼠标双击
		public void mouseDoubleClick(MouseEvent e) {
			System.out.println("Now my mouse clicks twice");
		}

		@Override
//		鼠标点击
		public void mouseDown(MouseEvent e) {
			DragButton.this.dragButton.addMouseMoveListener(DragButton.this.listener);
//			保存鼠标在部件中的位置
			this.mouseX = e.x;
			this.mouseY = e.y;
		}

		@Override
//		释放鼠标
		public void mouseUp(MouseEvent e) {
			DragButton.this.dragButton.removeMouseMoveListener(DragButton.this.listener);
		}

		@Override
//		鼠标移动
		public void mouseMove(MouseEvent e) {
//			将鼠标的位置（相对于按钮）转换为相对于窗口的坐标
			Point point = Display.getCurrent().map(DragButton.this.dragButton, DragButton.this.shell, e.x, e.y);
//			由于button是通过上顶点定位，因此需要减去鼠标在部件中的位置
			DragButton.this.dragButton.setLocation(point.x - this.mouseX, point.y - this.mouseY);
		}
	}
}
