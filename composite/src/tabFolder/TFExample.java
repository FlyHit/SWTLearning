package tabFolder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;

/**
 * 6页的TabFolder
 * 层级关系是：TabFolder-TabItem-Control（用于填充Tab）
 */
public class TFExample {
    private Shell shell;

    /**
     * 加载应用
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            TFExample window = new TFExample();
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
        shell.setText("TabFolder");

        final TabFolder tabFolder = new TabFolder(shell, SWT.BORDER);
        Rectangle clientArea = shell.getClientArea();
        tabFolder.setLocation(clientArea.x, clientArea.y);
//        6页
        for (int i = 0; i < 6; i++) {
            TabItem item = new TabItem(tabFolder, SWT.NONE);
            item.setText("TabItem " + i);
            Button button = new Button(tabFolder, SWT.PUSH);
            button.setText("Page " + i);
            item.setControl(button);  // button用于填充tab
        }
        tabFolder.pack();
        shell.pack();
    }
}
