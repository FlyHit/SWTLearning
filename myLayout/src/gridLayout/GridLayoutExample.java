package gridLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * 网格布局例子
 */
public class GridLayoutExample {
    private Shell shell;

    /**
     * 加载应用
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            GridLayoutExample window = new GridLayoutExample();
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
    }

    /**
     * 创建窗体内容
     */
    private void createContents() {
        shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("网格布局");
        shell.setLayout(new GridLayout());

        final Composite c = new Composite(shell, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;  // 设置列数，行数视情况可以增加（没有行数方法）
        c.setLayout(layout);

//        创建10个按钮
        for (int i = 0; i < 10; i++) {
            Button b = new Button(c, SWT.PUSH);
            b.setText("Button " + i);
        }

        Button b = new Button(shell, SWT.PUSH);
        b.setText("在第二行第一列添加一个新按钮");
        final int[] index = new int[1];
//        单击按钮：新创建一个按钮，并重新布局
        b.addListener(SWT.Selection, e -> {
            Button s = new Button(c, SWT.PUSH);
            s.setText("Special " + index[0]);
            index[0]++;
            Control[] children = c.getChildren();
//            moveAbove()：在某控件之前被绘制，这里相当于移动到第四个按钮之前
            s.moveAbove(children[3]);
//            layout(Control[] changed)：强制对已更改控件的父级层次结构中的所有小部件（包括接收方,这里是shell）进行布局
//            即对面板里的按钮和shell里的按钮进行重新布局
            shell.layout(new Control[]{s});
        });
    }
}
