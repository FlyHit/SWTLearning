import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 拖放的例子：在两个标签之间拖放文本
 * drag&drop的步骤：
 * 1.定义DragSource
 * 2.定义DragTarget
 * 要点是：设置好允许拖动/放置的数据类型，添加拖动放置的事件
 * 注意：拖动什么数据，拖动的数据的消失出现需要自己操作，这里是setText
 */
public class DragText {
    private Shell shell;

    /**
     * 加载应用
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            DragText window = new DragText();
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
        shell.setSize(450, 300);
        shell.setText("拖放文本");
        shell.setLayout(new FillLayout());

        final Label label1 = new Label(shell, SWT.BORDER);
        label1.setText("TEXT");
        final Label label2 = new Label(shell, SWT.BORDER);
        setDragDrop(label1);
        setDragDrop(label2);
        shell.setSize(200, 200);
    }

    /**
     * @param label
     */
    public static void setDragDrop(final Label label) {
//        Transfer：提供了一种Java数据表示和平台数据表示之间相互转换的机制，用于数据转移操作，如复制粘贴和拖放
//        定义支持转移的数据类型，这里是TextTransfer，对应的是Java的String
        Transfer[] types = new Transfer[]{TextTransfer.getInstance()};
//        DND类包含了定义DragSource和DragTarget时所用的所有常量
        int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;

//        DragSource：拖放的控件+允许的操作
        final DragSource source = new DragSource(label, operations);
        source.setTransfer(types);  // 设置DragSource允许转移的数据类型列表
        source.addDragListener(new DragSourceListener() {
            @Override
//            拖放动作开始时触发的事件，该事件提供了决定拖放动作是否该进行的机会
            public void dragStart(DragSourceEvent event) {
                event.doit = (label.getText().length() != 0);
            }

            @Override
//            设置拖动的数据
            public void dragSetData(DragSourceEvent event) {
                event.data = label.getText();
            }

            @Override
//            拖放完成后的数据清理工作
            public void dragFinished(DragSourceEvent event) {
                if (event.detail == DND.DROP_MOVE)
                    label.setText("");
            }
        });

        DropTarget target = new DropTarget(label, operations);
        target.setTransfer(types);
        target.addDropListener(new DropTargetAdapter() {
            @Override
//            拖动的数据放下时触发的事件，将detail数据域设置为DND.DROP_NONE，可以取消放置（drop）操作
            public void drop(DropTargetEvent event) {
                if (event.data == null) {
                    event.detail = DND.DROP_NONE;
                    return;
                }
                label.setText((String) event.data);
            }
        });
    }
}
