package eclipseLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

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
    private ToolBar lToolBar;
    private ToolBar rToolBar;

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
    private int[] sf1Weight = new int[]{20, 80};
    private int[] sf2Weight = new int[]{70, 30};
    private int[] sf3Weight = new int[]{80, 20};
    private int[] sf4Weight = new int[]{70, 30};
    private int[] sf5Weight = new int[]{30, 30, 40};
    //endregion
    private GridData lGridData;
    private GridData rGridData;
    private GridData sf1GridData;

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

    /**
     * 创建工具栏
     */
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
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.horizontalSpacing = 0;
        contentCpst.setLayout(gridLayout);
        createLToolBar();
        createMiddleCpst();
        createRToolBar();
    }

    /**
     * 创建左侧边栏
     */
    private void createLToolBar() {
        lToolBar = new ToolBar(contentCpst, SWT.VERTICAL);
        lGridData = new GridData(SWT.FILL, SWT.FILL, false, true);
        lToolBar.setLayoutData(lGridData);
//        隐藏侧边栏
        lToolBar.setVisible(false);
        lGridData.exclude = true;
    }

    /**
     * 创建右侧边栏
     */
    private void createRToolBar() {
        rToolBar = new ToolBar(contentCpst, SWT.VERTICAL);
        rGridData = new GridData(SWT.FILL, SWT.FILL, false, true);
        rToolBar.setLayoutData(rGridData);
//        隐藏侧边栏
        rToolBar.setVisible(false);
        rGridData.exclude = true;
    }

    /**
     * 创建工具按钮
     *
     * @param t    按钮所在的工具栏
     * @param name 按钮名称
     * @param i    按钮的图片
     */
    private void createToolItem(ToolBar t, String name, Image i) {
        ToolItem toolItem = new ToolItem(t, SWT.PUSH | SWT.BORDER);
        toolItem.setImage(i);
        toolItem.addSelectionListener(new TabSelection());
        toolItem.setData("name", name);
    }

    /**
     * 创建中间的面板
     */
    private void createMiddleCpst() {
        middleCpst = new Composite(contentCpst, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.horizontalSpacing = 0;
        middleCpst.setLayout(gridLayout);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        middleCpst.setLayoutData(gridData);
        //region SashForm1
        sf1GridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        sashForm1 = new SashForm(middleCpst, SWT.HORIZONTAL);
        sashForm1.setLayoutData(sf1GridData);
        sashForm1.setData("isMin", "false");
        //region SashForm2
        sashForm2 = new SashForm(sashForm1, SWT.VERTICAL);
        sashForm2.setLayout(new FillLayout());
        sashForm2.setData("isMin", "false");
        cTF_1 = createCTabFolder(sashForm2, "1");
        cTF_1.setBackground(new Color(display, 239, 83, 80));
        cTF_1.addCTabFolder2Listener(new CTFAdapter(cTF_1));
//        cTF_1.addControlListener(new resizeListener());
        cTF_2 = createCTabFolder(sashForm2, "2");
        cTF_2.setBackground(new Color(display, 66, 165, 245));
        cTF_2.addCTabFolder2Listener(new CTFAdapter(cTF_2));
        sashForm2.setWeights(sf2Weight);
        //endregion
        //region SashForm3
        sashForm3 = new SashForm(sashForm1, SWT.VERTICAL);
        sashForm3.setLayout(new FillLayout());
        sashForm3.setData("isMin", "false");
        //region SashForm4
        sashForm4 = new SashForm(sashForm3, SWT.HORIZONTAL);
        sashForm4.setLayout(new FillLayout());
        sashForm4.setData("isMin", "false");
        cTF_4 = createCTabFolder(sashForm4, "4");
        cTF_4.setBackground(new Color(display, 255, 167, 38));
        cTF_4.addCTabFolder2Listener(new CTFAdapter(cTF_4));
        sashForm5 = new SashForm(sashForm4, SWT.VERTICAL);
        sashForm5.setLayout(new FillLayout());
        sashForm5.setData("isMin", "false");
        cTF_5 = createCTabFolder(sashForm5, "5");
        cTF_5.setBackground(new Color(display, 102, 187, 106));
        cTF_5.addCTabFolder2Listener(new CTFAdapter(cTF_5));
        cTF_6 = createCTabFolder(sashForm5, "6");
        cTF_6.setBackground(new Color(display, 38, 166, 154));
        cTF_6.addCTabFolder2Listener(new CTFAdapter(cTF_6));
        cTF_7 = createCTabFolder(sashForm5, "7");
        cTF_7.setBackground(new Color(display, 38, 198, 218));
        cTF_7.addCTabFolder2Listener(new CTFAdapter(cTF_7));
        sashForm5.setWeights(sf5Weight);
        sashForm4.setWeights(sf4Weight);
        //endregion
        cTF_3 = createCTabFolder(sashForm3, "3");
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
     * @param name   标签卡的名称
     * @return 创建的标签卡
     */
    private CTabFolder createCTabFolder(Composite parent, String name) {
        CTabFolder cTabFolder = new CTabFolder(parent, SWT.NONE);
        cTabFolder.setData("name", name);
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
            String name = (String) cTabFolder.getData("name");
            if (name == "1" || name == "2") {
                switch (name) {
                    case "1":
                        createToolItem(lToolBar, name, getImage("1"));
                        break;
                    case "2":
                        createToolItem(lToolBar, name, getImage("2"));
                        break;
                    default:
                        break;
                }
                lToolBar.setVisible(true);
                lGridData.exclude = false;
                contentCpst.layout();
            } else {
                switch (name) {
                    case "3":
                        createToolItem(rToolBar, name, getImage("3"));
                        break;
                    case "4":
                        createToolItem(rToolBar, name, getImage("4"));
                        break;
                    case "5":
                        createToolItem(rToolBar, name, getImage("5"));
                        break;
                    case "6":
                        createToolItem(rToolBar, name, getImage("6"));
                        break;
                    case "7":
                        createToolItem(rToolBar, name, getImage("7"));
                        break;
                    default:
                        break;
                }
                rToolBar.setVisible(true);
                rGridData.exclude = false;
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
         * 将标签页最大化：这里传入控件的parent必须是SashForm
         *
         * @param max 最大化的标签页
         */
        private void setMax(Control max) {
            SashForm s = (SashForm) max.getParent();
            s.setMaximizedControl(max);
            if (!s.getParent().equals(middleCpst)) {
                setMax(s);
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


            if (min instanceof SashForm) {
                min.setData("isMin", "true");
            }

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
//                setWeights(int[] weights)：weights长度必须同sashform的非sash的children，且和为100
//                因此这里全部最小化后的元素全为0的weights数组不能传给setWeights（），此时sashForm的weights属性为0,...,100
                s.setWeights(weights);
            } else {
                if (!s.getParent().equals(middleCpst)) {
                    setMin(s);
                } else {
                    sashForm1.setVisible(false);
                    sf1GridData.exclude = true;
                    middleCpst.layout();
                }
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
            if (!s.getParent().equals(middleCpst)) {
                restore(s);
            }
        }
    }

    class TabSelection extends SelectionAdapter {
        @Override
        public void widgetSelected(SelectionEvent e) {
            ToolItem t = (ToolItem) e.widget;
            ToolBar parent = t.getParent();
            boolean isLeft = false;
            CTabFolder c = null;

            switch ((String) t.getData("name")) {
                case "1":
                    c = cTF_1;
                    isLeft = true;
                    break;
                case "2":
                    c = cTF_2;
                    isLeft = true;
                    break;
                case "3":
                    c = cTF_3;
                    break;
                case "4":
                    c = cTF_4;
                    break;
                case "5":
                    c = cTF_5;
                    break;
                case "6":
                    c = cTF_6;
                    break;
                case "7":
                    c = cTF_7;
                    break;
                default:
                    break;
            }

//            不能用t.getParent().getChildren().length==0，ToolItem不算ToolBar的children（control类型）
//            ToolItem还未dispose，因此剩余1
            if (parent.getItemCount() == 1) {
                if (isLeft) {
                    lToolBar.setVisible(false);
                    lGridData.exclude = true;
                } else {
                    rToolBar.setVisible(false);
                    rGridData.exclude = true;
                }
                contentCpst.layout();
            }

            c.setMinimized(false);
            c.setMaximized(false);
            restoreMin(c);
            t.dispose();
        }
    }

    class resizeListener extends ControlAdapter {
        @Override
        public void controlResized(ControlEvent e) {
            sf1Weight = sashForm1.getWeights();
            sf2Weight = sashForm2.getWeights();
            sf3Weight = sashForm3.getWeights();
            sf4Weight = sashForm4.getWeights();
            sf5Weight = sashForm5.getWeights();
        }
    }

    /**
     * 获取图片
     *
     * @param name 图片名
     * @return 和名字对应的图片
     */
    private Image getImage(String name) {
        final RGB color1 = new RGB(239, 83, 80);
        final RGB color2 = new RGB(66, 165, 245);
        final RGB color3 = new RGB(255, 238, 88);
        final RGB color4 = new RGB(255, 167, 38);
        final RGB color5 = new RGB(102, 187, 106);
        final RGB color6 = new RGB(38, 166, 154);
        final RGB color7 = new RGB(38, 198, 218);
        RGB rgb = null;
        switch (name) {
            case "1":
                rgb = color1;
                break;
            case "2":
                rgb = color2;
                break;
            case "3":
                rgb = color3;
                break;
            case "4":
                rgb = color4;
                break;
            case "5":
                rgb = color5;
                break;
            case "6":
                rgb = color6;
                break;
            case "7":
                rgb = color7;
                break;
        }

        return drawImage(new Color(display, rgb));
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

    private void restoreMin(Control c) {
        SashForm s = (SashForm) c.getParent();
        Control[] controls = s.getChildren();
        int[] weights = new int[0];
        int[] currentWeights = s.getWeights();

        for (Control child : s.getChildren()) {
            if (child instanceof CTabFolder) {
                CTabFolder cTabFolder = (CTabFolder) child;
                if (cTabFolder.getMinimized() && currentWeights[getIndex(cTabFolder)] != 0) {
                    currentWeights[getIndex(cTabFolder)] = 0;
                }
            } else if (child instanceof SashForm) {
                SashForm sashForm = (SashForm) child;
                if (sashForm.getData("isMin") == "true" && currentWeights[getIndex(sashForm)] != 0) {
                    currentWeights[getIndex(sashForm)] = 0;
                }
            }
        }

        if (s.equals(sashForm1)) {
            weights = sf1Weight;
        } else if (s.equals(sashForm2)) {
            weights = sf2Weight;
        } else if (s.equals(sashForm3)) {
            weights = sf3Weight;
        } else if (s.equals(sashForm4)) {
            weights = sf4Weight;
        } else if (s.equals(sashForm5)) {
            weights = sf5Weight;
        }
        int index = getIndex(c);
        int weight = weights[index];

        for (Control control : controls) {
            int i = getIndex(control);
            if (control instanceof CTabFolder) {
                CTabFolder cTabFolder = (CTabFolder) control;
                if (!control.equals(c)) {
                    if (cTabFolder.getMinimized()) {
                        weight += weights[i];
                    } else {
                        currentWeights[i] = weights[i];
                    }

                }
            } else if (control instanceof SashForm) {
                SashForm sashForm = (SashForm) control;
                if (!control.equals(c)) {
                    if (sashForm.getData("isMin") == "true") {
                        weight += weights[i];
                    } else {
                        currentWeights[i] = weights[i];
                    }
                }
            }
        }

        currentWeights[index] = weight;
        s.setWeights(currentWeights);

        if (!s.getParent().equals(middleCpst)) {
            s.setData("isMin", "false");
            restoreMin(s);
        } else if (!sashForm1.getVisible()) {
            sashForm1.setVisible(true);
            sf1GridData.exclude = false;
            middleCpst.layout();
        }
    }
}
