package eclipseLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import swt.SWTResourceManager;

public class EclipseLayout {
    private Shell shell;
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
    private Composite sash2Cpst1;
    private Composite sash2Cpst2;
    private SashForm sashForm3;
    private Composite sash3Cpst1;
    private Composite sash3Cpst2;
    private SashForm sashForm4;
    private Composite sash4Cpst1;
    private Composite sash4Cpst2;
    private SashForm sashForm5;
    private Composite sash5Cpst1;
    private Composite sash5Cpst2;
    private Composite sash5Cpst3;

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
        sash2Cpst1 = new Composite(sashForm2, SWT.NONE);
        sash2Cpst1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
        sash2Cpst2 = new Composite(sashForm2, SWT.NONE);
        sash2Cpst2.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
        sashForm2.setWeights(new int[]{70, 30});

        sashForm3 = new SashForm(sash1Cpst2, SWT.VERTICAL | SWT.BORDER);
        sash3Cpst1 = new Composite(sashForm3, SWT.NONE);
        sash3Cpst1.setLayout(new FillLayout());
        sash3Cpst1.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
        sash3Cpst2 = new Composite(sashForm3, SWT.NONE);
        sash3Cpst2.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
        sashForm3.setWeights(new int[]{80, 20});

        sashForm4 = new SashForm(sash3Cpst1, SWT.HORIZONTAL | SWT.BORDER);
        sash4Cpst1 = new Composite(sashForm4, SWT.NONE);
        sash4Cpst1.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
        sash4Cpst2 = new Composite(sashForm4, SWT.NONE);
        sash4Cpst2.setLayout(new FillLayout());
        sashForm4.setWeights(new int[]{70, 30});

        sashForm5 = new SashForm(sash4Cpst2, SWT.VERTICAL | SWT.BORDER);
        sash5Cpst1 = new Composite(sashForm5, SWT.NONE);
        sash5Cpst1.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
        sash5Cpst2 = new Composite(sashForm5, SWT.NONE);
        sash5Cpst2.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
        sash5Cpst3 = new Composite(sashForm5, SWT.NONE);
        sash5Cpst3.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
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
}
