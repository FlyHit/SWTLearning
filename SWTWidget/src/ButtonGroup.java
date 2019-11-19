import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;

public class ButtonGroup {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ButtonGroup window = new ButtonGroup();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
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
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("\u5355\u9009\u6309\u94AE\u7EC4");
		group.setBounds(10, 10, 187, 107);
		
		Button btnRadioButton = new Button(group, SWT.RADIO);  // 创建单选按钮时要指定父类，即属于哪个组
		btnRadioButton.setBounds(33, 30, 144, 20);
		btnRadioButton.setText("\u7B2C\u4E00\u4E2A\u5355\u9009\u6309\u94AE");
		
		Button btnRadioButton_1 = new Button(group, SWT.RADIO);
		btnRadioButton_1.setBounds(33, 62, 144, 20);
		btnRadioButton_1.setText("\u7B2C\u4E8C\u4E2A\u5355\u9009\u6309\u94AE");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(161, 175, 98, 30);
		btnNewButton.setText("\u6309\u94AE");

	}
}
