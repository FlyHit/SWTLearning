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
		shell.setText("工具栏");

		CoolBar coolBar = new CoolBar(shell, SWT.FLAT);
		coolBar.setBounds(0, 28, 150, 30);

		// 创建工具栏
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(0, 0, 46, 22);

		// 创建工具栏项目
		ToolItem redoItem = new ToolItem(toolBar, SWT.NONE);
		redoItem.setToolTipText("重做"); // 工具栏提示信息
		redoItem.setImage(
				SWTResourceManager.getImage(ToolBarExample.class,
						"/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png"));

		ToolItem infoItem = new ToolItem(toolBar, SWT.NONE);
		infoItem.setToolTipText("信息");
		infoItem.setImage(SWTResourceManager.getImage(ToolBarExample.class,
				"/org/eclipse/jface/fieldassist/images/info_ovr@2x.png"));

		toolBar.pack();

//		可以使用SWTResourceManager加载销毁图片
//		Image hjImage = SWTResourceManager.getImage("img\\huaji.png");
//		SWTResourceManager.dispose();

//		自定义类ImageFactory管理图片资源
//		Image image = ImageFactory.loadImage(display, "img\\1.png");

	}

	/**
	 * 调整图片的大小（不适用于png图片？，该方法实际上是以一张图片为底绘图）
	 * 
	 * @param image  目标图片
	 * @param width  调整后的宽度
	 * @param height 调整后的高度
	 * @return 调整大小后的图片
	 */
	public static Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON); // 消除锯齿
		gc.setInterpolation(SWT.HIGH); // 设置插值
//		将原图片矩形区域复制到矩形的绘图区域
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();  // 记得要清理资源
		image.dispose();
		return scaled;
	}
}
