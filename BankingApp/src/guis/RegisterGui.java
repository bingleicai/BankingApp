package guis;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import database.JDBC;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * This is a class that create register gui extending {@link BaseFrame} class.
 * @author Binglei Cai
 */
public class RegisterGui extends BaseFrame {
	/**title label*/
	private JLabel titleLabel;
	/**username label*/
	private JLabel usernameLabel;
	/**password label*/
	private JLabel passwordLabel;
	/**re-type password label*/
	private JLabel retypePasswordLabel;
	/**username field*/
	private JTextField usernameField;
	/**password field*/
	private JPasswordField passwordField;
	/**retype password field*/
	private JPasswordField retypePasswordField;
	/**register button*/
	private JButton registerButton;
	/**login label*/
	private JLabel loginLabel;
	/**
	 * This constructor sets layout of register gui.
	 * */
	public RegisterGui() {
		super("Banking Application - Register");
		setTitleLabel();
		setUsernameLabel();
		setUsernameField();
		setPasswordField();
		setPasswordLabel();
		setRegisterButton();
		setLoginLabel();
		setRetypePasswordLabel();
		setRetypePasswordField();
	}
	/**
	 * This method sets {@link RegisterGui#titleLabel} and adds it to register gui.
	 * */
	public void setTitleLabel() {
		titleLabel = new JLabel("Register");
		titleLabel.setBounds(0, 20, super.getWidth(), 40);
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleLabel);
	}
	/**
	 * This method sets {@link RegisterGui#usernameLabel} and adds it to register gui.
	 * */
	public void setUsernameLabel() {
		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(20, 120, getWidth() - 50, 24);
		usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(usernameLabel);
	}
	/**
	 * This method sets {@link RegisterGui#usernameField} and adds it to register gui.
	 * */
	public void setUsernameField() {
		usernameField = new JTextField();
		usernameField.setBounds(20, 160, getWidth() - 50, 40);
		usernameField.setFont(new Font("Dialog", Font.PLAIN, 28));
		add(usernameField);
	}
	/**
	 * This method sets {@link RegisterGui#passwordLabel} and adds it to register gui.
	 * */
	public void setPasswordLabel() {
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(20, 220, getWidth() - 50, 24);
		passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(passwordLabel);
	}
	/**
	 * This method sets {@link RegisterGui#passwordField} and adds it to register gui.
	 * */
	public void setPasswordField() {
		passwordField = new JPasswordField();
		passwordField.setBounds(20, 260, getWidth() - 50, 40);
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
		add(passwordField);
	}
	/**
	 * This method sets {@link RegisterGui#retypePasswordLabel} and adds it to register gui.
	 * */
	public void setRetypePasswordLabel() {
		retypePasswordLabel = new JLabel("Re-type Password:");
		retypePasswordLabel.setBounds(20, 320, getWidth() - 50, 24);
		retypePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(retypePasswordLabel);
	}
	/**
	 * This method sets {@link RegisterGui#retypePasswordField} and adds it to register gui.
	 * */
	public void setRetypePasswordField() {
		retypePasswordField = new JPasswordField();
		retypePasswordField.setBounds(20, 360, getWidth() - 50, 40);
		retypePasswordField.setFont(new Font("Dialog", Font.PLAIN, 28));
		add(retypePasswordField);
	}
	/**
	 * This method sets {@link RegisterGui#registerButton} and adds it to register gui.
	 * */
	public void setRegisterButton() {
		registerButton = new JButton("Register");
		registerButton.setBounds(20, 440, getWidth() - 50, 40);
		registerButton.setFont(new Font("Dialog", Font.BOLD, 20));
		registerButton.setFocusable(false);
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String retypePassword = String.valueOf(retypePasswordField.getPassword());
				if(username.length() == 0) {
					JOptionPane.showMessageDialog(RegisterGui.this, "Username can't be blank");
				}else if(password.length() == 0) {
					JOptionPane.showMessageDialog(RegisterGui.this, "Password can't be blank");
				}else if(password.length() < 6) {
					JOptionPane.showMessageDialog(RegisterGui.this, "Password must be at least 6 characters");
				}else if(!password.equals(retypePassword)) {
					JOptionPane.showMessageDialog(RegisterGui.this, "Psswords do not match");
				}else {
					if(JDBC.register(username, password)) {
						JOptionPane.showMessageDialog(RegisterGui.this, "Register Successfully");
						RegisterGui.this.dispose();
						LoginGui loginGui = new LoginGui();
						loginGui.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(RegisterGui.this, "Error: Username taken");
					}
				}
			}
		});
		add(registerButton);
	}
	/**
	 * This method sets {@link RegisterGui#loginLabel} and adds it to register gui.
	 * */
	public void setLoginLabel() {
		loginLabel = new JLabel("<html><a href=\"#\">Have an account? Sign-in here</a></html>");
		loginLabel.setBounds(0, 510, getWidth() - 10, 30);
		loginLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterGui.this.dispose();
				LoginGui loginGui = new LoginGui();
				loginGui.setVisible(true);
			}
		});
		add(loginLabel);
	}
}
