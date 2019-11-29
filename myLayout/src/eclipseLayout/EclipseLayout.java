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
 * 完成窗体大致的布局，包括菜单、顶部栏、中间多个可调整的窗口及两侧可以容纳
 * 最小化后的窗口图标的工具栏，但是最小化最大化功能存在bug，需要重写
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

    //region middle sashForm
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
        createCpst();
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
    private void createCpst() {
        contentCpst = new Composite(viewForm_1, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        contentCpst.setLayout(gridLayout);
        createLToolBar();
        createSashForm();
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

    private void createSashForm() {
        //region SashForm1
        sashForm1 = new SashForm(contentCpst, SWT.HORIZONTAL);
        sashForm1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        //region SashForm2
        sashForm2 = new SashForm(sashForm1, SWT.VERTICAL);
        sashForm2.setLayout(new FillLayout());
        cTF_1 = createCTabFolder(sashForm2);
        cTF_1.setBackground(new Color(display, 239, 83, 80));
        cTF_1.addCTabFolder2Listener(new CTabFolder2Adapter() {
            @Override
            public void maximize(CTabFolderEvent event) {
                System.out.println(1);
                System.out.println("e" + sashForm3.getChildren());
            }
        });
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
        private CTabFolder cTabFolder1;
        private SashForm sashForm;
//        private int[] weights;

        public CTFAdapter(CTabFolder c) {
            this.cTabFolder = c;
            this.cTabFolder1 = c;
            this.sashForm = (SashForm) c.getParent();
//            this.weights=sashForm.getWeights();
        }

        @Override
        public void minimize(CTabFolderEvent event) {
            cTabFolder.setMinimized(true);

            int[] weights = sashForm.getWeights();
            int i = getIndex(cTabFolder);
            if ((i != weights.length - 1)) {
                weights[i + 1] += weights[i];
            } else {
                weights[i - 1] += weights[i];
            }
            weights[i] = 0;
            sashForm.setWeights(weights);
        }

        @Override
        public void maximize(CTabFolderEvent event) {
//            cTabFolder.setMaximized(true);
//            setMax(cTabFolder);
            System.out.println("e" + sashForm3.getChildren().length);
        }

        @Override
        public void restore(CTabFolderEvent event) {
            cTF_1.setMinimized(false);
            cTF_1.setMaximized(false);
            sashForm1.setWeights(sf1Weight);
            sashForm2.setWeights(sf2Weight);
            Control[] control = sashForm1.getChildren();
            for (Control c : control) {
                if (c instanceof SashForm) {
                    System.out.println(c.getClass());
                }
            }
        }

        /**
         * @param c
         */
        private void setMax(Control c) {
            SashForm sashForm = (SashForm) c.getParent();
            int i = getIndex(sashForm);
//            System.out.println(i);
            int[] weights = new int[sashForm.getWeights().length];
            weights[i] = 100;
            for (int weight : weights) {
//                System.out.println(weight);
            }
            sashForm.setWeights(weights);
            if (cTF_1 == cTF_2) {
//                System.out.println("yes");
            }
//            if (sashForm.getParent().equals(contentCpst)) {
//                return;
//            }
//            setMax(sashForm);
        }
    }

    /**
     * 获取对象在父级的序号
     *
     * @param child
     * @return
     */
    private int getIndex(Control child) {
        Composite c = child.getParent();
        System.out.println("e" + sashForm3.getChildren().length);
        int index = 0;
        Control[] control = c.getChildren();
//            System.out.println(c.getChildren().length);
        for (Control control1 : control) {
            if (control1 == child) {
                break;
            }
            index++;
        }

        return index;
    }
}
