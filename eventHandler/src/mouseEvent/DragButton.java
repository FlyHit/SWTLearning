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
 * ����϶���ť: ʹ���ڲ������ʽʵ����������¼��Ķ���ӿڣ�ʹ�� 
 * �ڲ�����Է�������ⲿ���ʵ����������dragButton�ȣ���Ȼ��Ϊ
 * ��ťע�����������������ʱҪע�ⲿ����ͨ���϶��㶨λ��
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
		this.shell.setText("����϶�");

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
//		������ĳ����
		public void mouseEnter(MouseEvent e) {
			System.out.println("Now my mouse enters");
		}

		@Override
//		����뿪ĳ����
		public void mouseExit(MouseEvent e) {
			System.out.println("Now my mouse exits");
		}

		@Override
//		�����ͣ��ĳ����
		public void mouseHover(MouseEvent e) {
			System.out.println("Now my mouse hovers");
		}

		@Override
//		���˫��
		public void mouseDoubleClick(MouseEvent e) {
			System.out.println("Now my mouse clicks twice");
		}

		@Override
//		�����
		public void mouseDown(MouseEvent e) {
			DragButton.this.dragButton.addMouseMoveListener(DragButton.this.listener);
//			��������ڲ����е�λ��
			this.mouseX = e.x;
			this.mouseY = e.y;
		}

		@Override
//		�ͷ����
		public void mouseUp(MouseEvent e) {
			DragButton.this.dragButton.removeMouseMoveListener(DragButton.this.listener);
		}

		@Override
//		����ƶ�
		public void mouseMove(MouseEvent e) {
//			������λ�ã�����ڰ�ť��ת��Ϊ����ڴ��ڵ�����
			Point point = Display.getCurrent().map(DragButton.this.dragButton, DragButton.this.shell, e.x, e.y);
//			����button��ͨ���϶��㶨λ�������Ҫ��ȥ����ڲ����е�λ��
			DragButton.this.dragButton.setLocation(point.x - this.mouseX, point.y - this.mouseY);
		}
	}
}
