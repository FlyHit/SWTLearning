package advancedWidget;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;

public class ToolBarExample {

	protected Shell shell;
	Display display;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ToolBarExample window = new ToolBarExample();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		SWTResourceManager.dispose();
		display.dispose();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("������");

		CoolBar coolBar = new CoolBar(shell, SWT.FLAT);
		coolBar.setBounds(0, 28, 150, 30);

		// ����������
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(0, 0, 46, 22);

		// ������������Ŀ
		ToolItem redoItem = new ToolItem(toolBar, SWT.NONE);
		redoItem.setToolTipText("����"); // ��������ʾ��Ϣ
		redoItem.setImage(
				SWTResourceManager.getImage(ToolBarExample.class,
						"/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png"));

		ToolItem infoItem = new ToolItem(toolBar, SWT.NONE);
		infoItem.setToolTipText("��Ϣ");
		infoItem.setImage(SWTResourceManager.getImage(ToolBarExample.class,
				"/org/eclipse/jface/fieldassist/images/info_ovr@2x.png"));

		toolBar.pack();

//		����ʹ��SWTResourceManager��������ͼƬ
//		Image hjImage = SWTResourceManager.getImage("img\\huaji.png");
//		SWTResourceManager.dispose();

//		�Զ�����ImageFactory����ͼƬ��Դ
//		Image image = ImageFactory.loadImage(display, "img\\1.png");

	}

	/**
	 * ����ͼƬ�Ĵ�С����������pngͼƬ�����÷���ʵ��������һ��ͼƬΪ�׻�ͼ��
	 * 
	 * @param image  Ŀ��ͼƬ
	 * @param width  ������Ŀ��
	 * @param height ������ĸ߶�
	 * @return ������С���ͼƬ
	 */
	public static Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON); // �������
		gc.setInterpolation(SWT.HIGH); // ���ò�ֵ
//		��ԭͼƬ���������Ƶ����εĻ�ͼ����
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();  // �ǵ�Ҫ������Դ
		image.dispose();
		return scaled;
	}
}
