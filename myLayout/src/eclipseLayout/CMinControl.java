package eclipseLayout;

import org.eclipse.swt.widgets.Control;

/**
 * 自定义能够最小化的控件
 */
public class CMinControl {
    private Control control;
    boolean isMin;

    public CMinControl(Control control) {
        this.control = control;
        isMin = false;
    }

    public boolean isMin() {
        return isMin;
    }

    public void setMin(boolean min) {
        isMin = min;
    }
}
