package canvas;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CanvasExample {
    private Shell shell;

    /**
     * 加载应用
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            CanvasExample window = new CanvasExample();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开窗口
     */
    private void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }

    /**
     * 创建窗体内容
     */
    private void createContents() {
        shell = new Shell();
        shell.setText("画布");

//        paintListener:需要被绘制的时候调用事件，如窗体的大小发生改变
        shell.addPaintListener(event -> {
            Rectangle rect = shell.getClientArea();
            event.gc.drawOval(0, 0, rect.width - 1, rect.height - 1);
        });
        Rectangle clientArea = shell.getClientArea();
        shell.setBounds(clientArea.x + 10, clientArea.y + 10, 200, 200);
    }
}
