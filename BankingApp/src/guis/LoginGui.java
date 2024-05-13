package guis;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import database.JDBC;
import database.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * This is a class that create login gui extending {@link BaseFrame} class.
 * @author Binglei Cai
 */
public class LoginGui extends BaseFrame {
	/**title label*/
	private JLabel titleLabel;
	/**username label*/
	private JLabel usernameLabel;
	/**password label*/
	private JLabel passwordLabel;
	/**username field*/
	private JTextField usernameField;
	/**password field*/
	private JPasswordField passwordField;
	/**login button*/
	private JButton loginButton;
	/**register label*/
	private JLabel registerLabel;
	/**
	 * This constructor sets layout of login gui.
	 * */
	public LoginGui() {
		super("Banking Application - Login");
		setTitleLabel();
		setUsernameLabel();
		setUsernameField();
		setPasswordField();
		setPasswordLabel();
		setLoginButton();
		setRegisterLabel();
	}
	/**
	 * This method sets {@link LoginGui#titleLabel} and adds it to login gui.
	 * */
	public void setTitleLabel() {
		titleLabel = new JLabel("Login");
		titleLabel.setBounds(0, 20, super.getWidth(), 40);
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleLabel);
	}
	/**
	 * This method sets {@link LoginGui#usernameLabel} and adds it to login gui.
	 * */
	public void setUsernameLabel() {
		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(20, 120, getWidth() - 50, 24);
		usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(usernameLabel);
	}
	/**
	 * This method sets {@link LoginGui#usernameField} and adds it to login gui.
	 * */
	public void setUsernameField() {
		usernameField = new JTextField();
		usernameField.setBounds(20, 160, getWidth() - 50, 40);
		usernameField.setFont(new Font("Dialog", Font.PLAIN, 28));
		add(usernameField);
	}
	/**
	 * This method sets {@link LoginGui#passwordLabel} and adds it to login gui.
	 * */
	public void setPasswordLabel() {
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(20, 280, getWidth() - 50, 24);
		passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(passwordLabel);
	}
	/**
	 * This method sets {@link LoginGui#passwordField} and adds it to login gui.
	 * */
	public void setPasswordField() {
		passwordField = new JPasswordField();
		passwordField.setBounds(20, 320, getWidth() - 50, 40);
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
		add(passwordField);
	}
	/**
	 * This method sets {@link LoginGui#loginButton} and adds it to login gui.
	 * */
	public void setLoginButton() {
		loginButton = new JButton("Login");
		loginButton.setBounds(20, 440, getWidth() - 50, 40);
		loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
		loginButton.setFocusable(false);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				User user = JDBC.validateLogin(username, password);
				if(user != null) {
					JOptionPane.showMessageDialog(LoginGui.this, "Login Successfully");
					LoginGui.this.dispose();
					HomePageGui homePageGui = new HomePageGui(user);
					homePageGui.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(LoginGui.this, "Login failed");
				}
			}
		});
		add(loginButton);
	}
	/**
	 * This method sets {@link LoginGui#registerLabel} and adds it to login gui.
	 * */
	public void setRegisterLabel() {
		registerLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
		registerLabel.setBounds(0, 510, getWidth() - 10, 30);
		registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginGui.this.dispose();
				RegisterGui registerGui = new RegisterGui();
				registerGui.setVisible(true);
			}
		});
		add(registerLabel);
	}
}
