package eclipseLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * 添加了恢复及最小化功能，最小化的时候侧栏会出现以容纳最小化后的窗口
 * 目前工具栏的按钮只占位，功能未实现
 */
public class EclipseLayout {
    private Shell shell;
    private Display display;
    //region 菜单栏变量
    private Menu menuBar;
    private MenuItem fileItem;
    private Menu fileMenu;
    private MenuItem newItem;
    private MenuItem editItem;
    //endregion
    //region 窗体VF1变量
    private ViewForm viewForm_1;
    //region TopLeft-VF1
    private ToolBar tlToolBar_1;
    private ToolItem toolItem1;
    private ToolItem toolItem2;
    //endregion

    //region TopRight-VF1
    private ToolBar trToolBar_1;
    private Composite toolCpst;
    private RowLayout toolRowLayout;
    private Text searchText;
    private ToolItem toolItem3;
    private ToolItem toolItem4;
    //endregion

    //region content-VF1:contentCpst
    private Composite contentCpst;
    //region left toolBar
    private ToolBar lToolBar;
    private ToolItem cTF_1_Item;
    private ToolItem cTF_2_Item;
    //endregion

    //region right toolBar
    private ToolBar rToolBar;
    private ToolItem cTF_3_Item;
    private ToolItem cTF_4_Item;
    private ToolItem cTF_5_Item;
    private ToolItem cTF_6_Item;
    private ToolItem cTF_7_Item;
    //endregion

    //region middle composite
    private Composite middleCpst;
    private SashForm sashForm1;
    private SashForm sashForm2;
    private CTabFolder cTF_1;
    private CTabFolder cTF_2;
    private SashForm sashForm3;
    private CTabFolder cTF_3;
    private SashForm sashForm4;
    private CTabFolder cTF_4;
    private SashForm sashForm5;
    private CTabFolder cTF_5;
    private CTabFolder cTF_6;
    private CTabFolder cTF_7;
    //endregion
    //endregion
    //endregion
    //region SashForm宽度数组
    private int[] sf1Weight = new int[]{20, 70};
    private int[] sf2Weight = new int[]{70, 30};
    private int[] sf3Weight = new int[]{80, 20};
    private int[] sf4Weight = new int[]{70, 30};
    private int[] sf5Weight = new int[]{30, 30, 40};
    //endregion

    /**
     * 加载应用
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            EclipseLayout window = new EclipseLayout();
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
//        SashForm会在每个children之间放一个Sash，这个操作是在open()发生
//        Sash也算是SashForm的children，并且绘制顺序在其他children之后
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
        shell.setSize(1000, 800);
        shell.setText("eclipse");
        shell.setLayout(new FillLayout(SWT.VERTICAL));
        createMenu();
        createVF1();
    }

    /**
     * 创建菜单
     */
    private void createMenu() {
        menuBar = new Menu(shell, SWT.BAR);

        //region File菜单选项
        fileItem = new MenuItem(menuBar, SWT.CASCADE);
        fileItem.setText("&File");
        fileMenu = new Menu(fileItem);
        newItem = new MenuItem(fileMenu, SWT.NONE);
        newItem.setText("&New");
        fileItem.setMenu(fileMenu);
        //endregion

        //region Edit菜单选项
        editItem = new MenuItem(menuBar, SWT.CASCADE);
        editItem.setText("&Edit");
        shell.setMenuBar(menuBar);
        //endregion
    }

    /**
     * 创建窗体ViewForm面板
     */
    private void createVF1() {
        viewForm_1 = new ViewForm(shell, SWT.NONE);
        viewForm_1.setTopCenterSeparate(true);
        createTopBar();
        createContentCpst();
        viewForm_1.setContent(contentCpst);
    }

    /**
     * 创建顶部栏
     */
    private void createTopBar() {
        createToolBar();
        viewForm_1.setTopLeft(tlToolBar_1);
        viewForm_1.setTopRight(toolCpst);
    }

    private void createToolBar() {
        tlToolBar_1 = new ToolBar(viewForm_1, SWT.FLAT);
        toolItem1 = new ToolItem(tlToolBar_1, SWT.NONE);
        toolItem1.setText("tool1");
        toolItem2 = new ToolItem(tlToolBar_1, SWT.NONE);
        toolItem2.setText("tool2");

        toolCpst = new Composite(viewForm_1, SWT.NONE);
        toolRowLayout = new RowLayout();
        toolCpst.setLayout(toolRowLayout);
        searchText = new Text(toolCpst, SWT.NONE);
        trToolBar_1 = new ToolBar(toolCpst, SWT.FLAT);
        toolItem3 = new ToolItem(trToolBar_1, SWT.NONE);
        toolItem3.setText("tool3");
        toolItem4 = new ToolItem(trToolBar_1, SWT.NONE);
        toolItem4.setText("tool4");
    }

    /**
     * 创建VF1的content：contentCpst
     */
    private void createContentCpst() {
        contentCpst = new Composite(viewForm_1, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        contentCpst.setLayout(gridLayout);
        createLToolBar();
        createMiddleCpst();
        createRToolBar();
    }

    private void createLToolBar() {
        lToolBar = new ToolBar(contentCpst, SWT.VERTICAL);
        lToolBar.setLayoutData(new GridData(0, 0));

        Image image1 = drawImage(new Color(display, 239, 83, 80));
        Image image2 = drawImage(new Color(display, 66, 165, 245));
        cTF_1_Item = new ToolItem(lToolBar, SWT.PUSH);
        cTF_1_Item.setImage(image1);
        cTF_2_Item = new ToolItem(lToolBar, SWT.PUSH);
        cTF_2_Item.setImage(image2);
    }

    private void createRToolBar() {
        rToolBar = new ToolBar(contentCpst, SWT.VERTICAL);
        rToolBar.setLayoutData(new GridData(0, 0));

        Image image3 = drawImage(new Color(display, 255, 238, 88));
        Image image4 = drawImage(new Color(display, 255, 167, 38));
        Image image5 = drawImage(new Color(display, 102, 187, 106));
        Image image6 = drawImage(new Color(display, 38, 166, 154));
        Image image7 = drawImage(new Color(display, 38, 198, 218));
        cTF_3_Item = new ToolItem(rToolBar, SWT.PUSH);
        cTF_3_Item.setImage(image3);
        cTF_4_Item = new ToolItem(rToolBar, SWT.PUSH);
        cTF_4_Item.setImage(image4);
        cTF_5_Item = new ToolItem(rToolBar, SWT.PUSH);
        cTF_5_Item.setImage(image5);
        cTF_6_Item = new ToolItem(rToolBar, SWT.PUSH);
        cTF_6_Item.setImage(image6);
        cTF_7_Item = new ToolItem(rToolBar, SWT.PUSH);
        cTF_7_Item.setImage(image7);
    }

    private void createMiddleCpst() {
        //region SashForm1
        middleCpst = new Composite(contentCpst, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        middleCpst.setLayout(gridLayout);
        middleCpst.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        sashForm1 = new SashForm(middleCpst, SWT.HORIZONTAL);
        sashForm1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        //region SashForm2
        sashForm2 = new SashForm(sashForm1, SWT.VERTICAL);
        sashForm2.setLayout(new FillLayout());
        cTF_1 = createCTabFolder(sashForm2);
        cTF_1.setBackground(new Color(display, 239, 83, 80));
        cTF_1.addCTabFolder2Listener(new CTFAdapter(cTF_1));
        cTF_2 = createCTabFolder(sashForm2);
        cTF_2.setBackground(new Color(display, 66, 165, 245));
        cTF_2.addCTabFolder2Listener(new CTFAdapter(cTF_2));
        sashForm2.setWeights(sf2Weight);
        //endregion
        //region SashForm3
        sashForm3 = new SashForm(sashForm1, SWT.VERTICAL);
        sashForm3.setLayout(new FillLayout());
        //region SashForm4
        sashForm4 = new SashForm(sashForm3, SWT.HORIZONTAL);
        sashForm4.setLayout(new FillLayout());
        cTF_4 = createCTabFolder(sashForm4);
        cTF_4.setBackground(new Color(display, 255, 167, 38));
        cTF_4.addCTabFolder2Listener(new CTFAdapter(cTF_4));
        sashForm5 = new SashForm(sashForm4, SWT.VERTICAL);
        sashForm5.setLayout(new FillLayout());
        cTF_5 = createCTabFolder(sashForm5);
        cTF_5.setBackground(new Color(display, 102, 187, 106));
        cTF_5.addCTabFolder2Listener(new CTFAdapter(cTF_5));
        cTF_6 = createCTabFolder(sashForm5);
        cTF_6.setBackground(new Color(display, 38, 166, 154));
        cTF_6.addCTabFolder2Listener(new CTFAdapter(cTF_6));
        cTF_7 = createCTabFolder(sashForm5);
        cTF_7.setBackground(new Color(display, 38, 198, 218));
        cTF_7.addCTabFolder2Listener(new CTFAdapter(cTF_7));
        sashForm5.setWeights(sf5Weight);
        sashForm4.setWeights(sf4Weight);
        //endregion
        cTF_3 = createCTabFolder(sashForm3);
        cTF_3.setBackground(new Color(display, 255, 238, 88));
        cTF_3.addCTabFolder2Listener(new CTFAdapter(cTF_3));
        sashForm3.setWeights(sf3Weight);
        //endregion
        sashForm1.setWeights(sf1Weight);
        //endregion
    }

    /**
     * 创建标签卡
     *
     * @param parent 标签卡的parent
     * @return 创建的标签卡
     */
    private CTabFolder createCTabFolder(Composite parent) {
        CTabFolder cTabFolder = new CTabFolder(parent, SWT.NONE);
        cTabFolder.setSimple(false);
        cTabFolder.setMinimizeVisible(true);
        cTabFolder.setMaximizeVisible(true);

        for (int i = 0; i < 3; i++) {
            CTabItem cTabItem = new CTabItem(cTabFolder, SWT.CLOSE);
            cTabItem.setText("Tab " + i);
            Text text = new Text(cTabFolder, SWT.NONE);
            cTabItem.setControl(text);
        }

        return cTabFolder;
    }

    /**
     * 绘制纯色的图片
     *
     * @param color 图片的颜色
     * @return 绘制的图片
     */
    private Image drawImage(Color color) {
        Image image = new Image(display, 20, 20);
        GC gc = new GC(image);
        gc.setBackground(color);
        gc.fillRectangle(0, 0, 20, 20);
        gc.dispose();
        return image;
    }

    class CTFAdapter extends CTabFolder2Adapter {
        private CTabFolder cTabFolder;

        public CTFAdapter(CTabFolder c) {
            this.cTabFolder = c;
        }

        @Override
        public void minimize(CTabFolderEvent event) {
            cTabFolder.setMinimized(true);
            setMin(cTabFolder);
            if (cTabFolder.equals(cTF_1) || cTabFolder.equals(cTF_2)) {
                lToolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
                contentCpst.layout();
            } else {
                rToolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
                contentCpst.layout();
            }
        }

        @Override
        public void maximize(CTabFolderEvent event) {
            cTabFolder.setMaximized(true);
            setMax(cTabFolder);
        }

        @Override
        public void restore(CTabFolderEvent event) {
            cTabFolder.setMaximized(false);
            cTabFolder.setMinimized(false);
            restore(cTabFolder);
        }

        /**
         * 获取child在parent的序号
         *
         * @param child 要获取序号的child
         * @return child的序号
         */
        private int getIndex(Control child) {
            Composite c = child.getParent();

            int index = 0;
            Control[] control = c.getChildren();

            for (Control control1 : control) {
                if (control1.equals(child)) {
                    break;
                }
                index++;
            }

            return index;
        }

        /**
         * 将标签页最大化：这里传入控件的parent必须是SashForm
         *
         * @param max 最大化的标签页
         */
        private void setMax(Control max) {
            SashForm s = (SashForm) max.getParent();
            s.setMaximizedControl(max);
            if (!s.getParent().equals(contentCpst)) {
                setMax(s);
            }
        }

        /**
         * 恢复布局
         *
         * @param c
         */
        private void restore(Control c) {
            SashForm s = (SashForm) c.getParent();
            s.setMaximizedControl(null);
            if (!s.getParent().equals(contentCpst)) {
                restore(s);
            }
        }

        /**
         * 将标签页最小化
         *
         * @param min 最小化的标签页
         */
        private void setMin(Control min) {
            boolean isAllMin = true;
            SashForm s = (SashForm) min.getParent();
            int[] weights = s.getWeights();
            int i = getIndex(min);
            for (int j = i + 1; j < weights.length; j++) {
                if (weights[j] != 0) {
                    weights[j] += weights[i];
                    break;
                }
            }

            if (i == weights.length - 1) {
                for (int j = i - 1; j > 0; j--) {
                    if (weights[j] != 0) {
                        weights[j] += weights[i];
                        break;
                    }
                }
            }
            weights[i] = 0;

            for (int weight : weights) {
                if (weight != 0) {
                    isAllMin = false;
                }
            }

            if (!isAllMin) {
                s.setWeights(weights);
            } else {
                if (!s.getParent().equals(middleCpst)) {
                    setMin(s);
                } else {
                    s.setLayoutData(new GridData(0, 0));
                    middleCpst.layout();
                }
            }
        }
    }
}
