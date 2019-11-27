package eclipseLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class EclipseLayout {
    private Shell shell;
    Display display;
    private FillLayout verticalLayout;
    private FillLayout horizonLayout;
    //region 菜单栏变量
    private Menu menuBar;
    private MenuItem fileItem;
    private Menu fileMenu;
    private MenuItem newItem;
    private MenuItem editItem;
    //endregion
    //region 顶部栏变量
    private ToolBar leftTool;
    private ToolBar rightTool;
    private Composite toolCpst;
    private RowLayout toolRowLayout;
    private ToolItem toolItem1;
    private ToolItem toolItem2;
    private ToolItem toolItem3;
    private ToolItem toolItem4;
    private Text searchText;
    private ViewForm topBarVF;
    //endregion
    private SashForm sashForm1;
    private Composite sash1Cpst1;
    private Composite sash1Cpst2;
    private SashForm sashForm2;
    private CTabFolder cTF_1;
    private CTabFolder cTF_2;
    private SashForm sashForm3;
    private Composite sash3Cpst1;
    private CTabFolder cTF_3;
    private SashForm sashForm4;
    private CTabFolder cTF_4;
    private Composite sash4Cpst2;
    private SashForm sashForm5;
    private CTabFolder cTF_5;
    private CTabFolder cTF_6;
    private CTabFolder cTF_7;

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
        verticalLayout = new FillLayout();
        verticalLayout.type = SWT.VERTICAL;
        horizonLayout = new FillLayout();
        horizonLayout.type = SWT.HORIZONTAL;

        shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("eclipse");
        shell.setLayout(verticalLayout);
        createMenu();
        createTopBar();

        sashForm1 = new SashForm(topBarVF, SWT.HORIZONTAL | SWT.BORDER);
        sashForm1.setLayout(new FillLayout());
        sash1Cpst1 = new Composite(sashForm1, SWT.NONE);
        sash1Cpst1.setLayout(verticalLayout);
        sash1Cpst2 = new Composite(sashForm1, SWT.NONE);
        sash1Cpst2.setLayout(verticalLayout);
        sashForm1.setWeights(new int[]{20, 70});
        topBarVF.setContent(sashForm1);

        sashForm2 = new SashForm(sash1Cpst1, SWT.VERTICAL | SWT.BORDER);
        cTF_1 = createCTabFolder(sashForm2);
        cTF_1.setBackground(new Color(display, 239, 83, 80));
        cTF_2 = createCTabFolder(sashForm2);
        cTF_2.setBackground(new Color(display, 66, 165, 245));
        sashForm2.setWeights(new int[]{70, 30});

        sashForm3 = new SashForm(sash1Cpst2, SWT.VERTICAL | SWT.BORDER);
        sash3Cpst1 = new Composite(sashForm3, SWT.NONE);
        sash3Cpst1.setLayout(new FillLayout());
        cTF_3 = createCTabFolder(sashForm3);
        cTF_3.setBackground(new Color(display, 255, 238, 88));
        sashForm3.setWeights(new int[]{80, 20});

        sashForm4 = new SashForm(sash3Cpst1, SWT.HORIZONTAL | SWT.BORDER);
        cTF_4 = createCTabFolder(sashForm4);
        cTF_4.setBackground(new Color(display, 255, 167, 38));
        sash4Cpst2 = new Composite(sashForm4, SWT.NONE);
        sash4Cpst2.setLayout(new FillLayout());
        sashForm4.setWeights(new int[]{70, 30});

        sashForm5 = new SashForm(sash4Cpst2, SWT.VERTICAL | SWT.BORDER);
        cTF_5 = createCTabFolder(sashForm5);
        cTF_5.setBackground(new Color(display, 102, 187, 106));
        cTF_6 = createCTabFolder(sashForm5);
        cTF_6.setBackground(new Color(display, 38, 166, 154));
        cTF_7 = createCTabFolder(sashForm5);
        cTF_7.setBackground(new Color(display, 38, 198, 218));
        sashForm5.setWeights(new int[]{30, 30, 40});
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
     * 创建顶部栏
     */
    private void createTopBar() {
        topBarVF = new ViewForm(shell, SWT.NONE);
        topBarVF.setTopCenterSeparate(true);
        createToolBar();
        topBarVF.setTopLeft(leftTool);
        topBarVF.setTopRight(toolCpst);
    }

    private void createToolBar() {
        leftTool = new ToolBar(topBarVF, SWT.FLAT);
        toolItem1 = new ToolItem(leftTool, SWT.NONE);
        toolItem1.setText("tool1");
        toolItem2 = new ToolItem(leftTool, SWT.NONE);
        toolItem2.setText("tool2");

        toolCpst = new Composite(topBarVF, SWT.NONE);
        toolRowLayout = new RowLayout();
        toolCpst.setLayout(toolRowLayout);
        searchText = new Text(toolCpst, SWT.NONE);
        rightTool = new ToolBar(toolCpst, SWT.FLAT);
        toolItem3 = new ToolItem(rightTool, SWT.NONE);
        toolItem3.setText("tool3");
        toolItem4 = new ToolItem(rightTool, SWT.NONE);
        toolItem4.setText("tool4");
    }

    /**
     * 创建标签卡
     *
     * @param parent 标签卡的parent
     * @return 创建的标签卡
     */
    private CTabFolder createCTabFolder(Composite parent) {
        CTabFolder cTabFolder = new CTabFolder(parent, SWT.BORDER);
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
}
