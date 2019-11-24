package textEvent;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 密码输入框：
 * 1.默认隐藏密码，
 * @author 11648
 *
 */
public class PasswordInput {

	protected Shell shell;
	private Text pwText;  // 密码输入框
	private Text verifyText;  // 确认密码框
	private Label pwVerifyLbl;  
	private Label pwInputLbl;
	private Button pwVisible;  // 显示密码按钮
	private Composite inputCpse;  // 密码输入面板
	private Composite verifyCpse;  // 密码验证面板
	private Label verifyLbl;  // 提示两次输入是否一致
	private Label pwTipLbl;  // 提示密码是否正确
	private MessageBox successMsg;  
	private boolean isValid = false;
	private boolean isSame = false;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PasswordInput window = new PasswordInput();
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
//		创建窗口
		shell = new Shell();
		shell.setToolTipText("");
		shell.setSize(604, 300);
		shell.setText("请输入密码");

//		是否显示密码		
		pwVisible = new Button(shell,SWT.CHECK);
		pwVisible.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = (Button) e.widget;
				if (button.getSelection()) {
					pwText.setEchoChar('\0');  // 设置\0清除echo character，即●
					verifyText.setEchoChar('\0');
				} else {
					pwText.setEchoChar('●'); 
					verifyText.setEchoChar('●');
				}
			}
		});
		pwVisible.setBounds(244, 160, 121, 20);
		pwVisible.setText("👀显示密码");

		inputCpse = new Composite(shell, SWT.NONE);
		inputCpse.setBounds(10, 44, 321, 43);

		pwInputLbl = new Label(inputCpse, SWT.NONE);
		pwInputLbl.setBounds(23, 16, 76, 20);
		pwInputLbl.setText("请输入密码");

		pwText = new Text(inputCpse, SWT.BORDER | SWT.PASSWORD);  // 密码框style
		pwText.setBounds(118, 10, 178, 26);

		verifyCpse = new Composite(shell, SWT.NONE);
		verifyCpse.setBounds(10, 93, 321, 43);

		pwVerifyLbl = new Label(verifyCpse, SWT.NONE);
		pwVerifyLbl.setBounds(23, 16, 76, 20);
		pwVerifyLbl.setText("请确认密码");

		pwTipLbl = new Label(shell, SWT.NONE);
		pwTipLbl.setLocation(349, 61);
		pwTipLbl.setText("");

		pwText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (verifyPw(pwText.getText())) {
					pwTipLbl.setText("✔");
					pwTipLbl.pack();
					isValid = true;
				} else {
					pwTipLbl.setText("请输入合法的密码！");
					pwTipLbl.pack();
					isValid =false;
				}
				
				compare2Pw();
			}
		});
		
		verifyText = new Text(verifyCpse, SWT.BORDER | SWT.PASSWORD);
		verifyText.setBounds(118, 10, 178, 26);
		verifyText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				compare2Pw();
			}
		});
		
		verifyLbl = new Label(shell, SWT.NONE);
		verifyLbl.setLocation(349, 107);
		verifyLbl.setText("");

		successMsg = new MessageBox(shell);

		Button submitButton = new Button(shell, SWT.NONE);
		submitButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isValid&&isSame) {
					successMsg.setMessage("注册成功!");
					successMsg.open();
				}
				else {
					successMsg.setMessage("请检查密码！");
					successMsg.open();
				}
			}
		});
		submitButton.setBounds(114, 155, 98, 30);
		submitButton.setText("注册");
	}

	/**
	 * 测试密码是否包含大小写字母、数字和特殊字符，且至少8位
	 * 
	 * @param password 输入的密码
	 * @return 输入的密码符合要求则true，否则false
	 */
	public boolean verifyPw(String password) {
		return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])" + "[A-Za-z\\d$@$!%*?&]{8,}",
				password);
	}
	
	/**
	 * 比较两次输入的密码是否相同
	 */
	public void compare2Pw() {
		if(pwText.getText()!="") {
			if (verifyText.getText().equals(pwText.getText())) {
				verifyLbl.setText("✔");
				isSame = true;
			}else {
				verifyLbl.setText("两次密码输入不一致！");
				isSame = false;
			}
		}else {
			verifyLbl.setText("");
			isSame = false;
		}
		verifyLbl.pack();
	}
}
