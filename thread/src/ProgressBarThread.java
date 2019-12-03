import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

/**
 * 使用另外的线程更新进度条
 */
public class ProgressBarThread {
    private Display display;
    private Shell shell;

    /**
     * 加载应用
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ProgressBarThread window = new ProgressBarThread();
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
        shell.setText("进度条+线程");

        final ProgressBar bar = new ProgressBar(shell, SWT.SMOOTH);
        Rectangle clientArea = shell.getClientArea();
        bar.setBounds(clientArea.x, clientArea.y, 200, 32);
        shell.open();
        final int maximum = bar.getMaximum();
        new Thread(() -> {
//            使用int[] i似乎是从线程安全的角度考虑
            for (final int[] i = new int[1]; i[0] <= maximum; i[0]++) {
                try {
                    Thread.sleep(100);
                } catch (Throwable th) {

                }
                if (display.isDisposed()) return;
//                asyncExec:在下一个合理的机会由UI线程调用runnable的run方法（同步运行）
                display.asyncExec(() -> {
//                    注意在该方法内部访问某一部件时，要先检查该部件的有效性
                    if (bar.isDisposed()) return;
                    bar.setSelection(i[0]);
                });
            }
        }).start();
    }
}
