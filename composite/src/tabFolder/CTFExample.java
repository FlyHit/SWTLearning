package tabFolder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 自定义标签卡：CTabFolder
 */
public class CTFExample {
    private Shell shell;
    private Display display;

    /**
     * 加载应用
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            CTFExample window = new CTFExample();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开窗口
     */
    private void open() {
        display = Display.getDefault();
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
        //region 绘制图片:16x16的蓝矩形叠加10X10的黄矩形
        Image image = new Image(display, 16, 16);
        GC gc = new GC(image);
        gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
        gc.fillRectangle(0, 0, 16, 16);
        gc.setBackground(display.getSystemColor(SWT.COLOR_YELLOW));
        gc.fillRectangle(3, 3, 10, 10);
        gc.dispose();
        //endregion

        shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("自定义标签页");
        shell.setLayout(new GridLayout());

        final CTabFolder folder = new CTabFolder(shell, SWT.BORDER);
        folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        folder.setSimple(false);  // 不使用简单传统的样式
        folder.setUnselectedImageVisible(false);  // 未选中的标签不显示图片
        folder.setUnselectedCloseVisible(true);  // 未选中的标签也显示关闭按钮
//        创建8个标签页
        for (int i = 0; i < 8; i++) {
            CTabItem item = new CTabItem(folder, SWT.CLOSE);
            item.setText("Item " + i);
            item.setImage(image);
            Text text = new Text(folder, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
            text.setText("Text for item " + i + "\n\none, two, three\n\nabcdefghijklmnop");
            item.setControl(text);
        }

        folder.setMinimizeVisible(true);
        folder.setMaximizeVisible(true);
//        标签卡最大化最小化关闭恢复及显示当前不可见标签的事件
        folder.addCTabFolder2Listener(new CTabFolder2Adapter() {
            @Override
            public void minimize(CTabFolderEvent event) {
                folder.setMinimized(true);
                folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
                shell.layout(true);  // 重新布局其子类对象
            }

            @Override
            public void maximize(CTabFolderEvent event) {
                folder.setMaximized(true);
                folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
                shell.layout(true);
            }

            @Override
            public void restore(CTabFolderEvent event) {
                folder.setMinimized(false);
                folder.setMaximized(false);
                folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
                shell.layout(true);
            }
        });
        shell.setSize(300, 300);
    }
}
