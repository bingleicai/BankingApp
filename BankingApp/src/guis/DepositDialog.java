package guis;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import database.JDBC;
import database.User;

/**
 * This is a class that create deposit dialog extending {@link HomePageDialog} class.
 * @author Binglei Cai
 */
public class DepositDialog extends HomePageDialog {
	/**balance label*/
	private JLabel balanceLabel;
	/**enter amount label*/
	private JLabel enterAmountLabel;
	/**enter amount field*/
	private JTextField enterAmountField;
	/**deposit button*/
	private JButton depositButton;
	/**
	 * This constructor sets layout of deposit dialog.
	 * @param homePageGui {@link HomePageGui} object
	 * @param user {@link User} object
	 * */
	public DepositDialog(HomePageGui homePageGui, User user) {
		super(homePageGui, user);
		setTitle("Deposit");
		setBalanceLabel();
		setEnterAmountLabel();
		setEnterAmountField();
		setDepositButton();
	}
	/**
	 * This method sets {@link DepositDialog#balanceLabel} and adds it to deposit dialog.
	 * */
	public void setBalanceLabel() {
		balanceLabel = new JLabel("Balance: $" + user.getCurrentBalance());
		balanceLabel.setBounds(0, 10, getWidth() - 20, 20);
		balanceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(balanceLabel);
	}
	/**
	 * This method sets {@link DepositDialog#enterAmountLabel} and adds it to deposit dialog.
	 * */
	public void setEnterAmountLabel() {
		enterAmountLabel = new JLabel("Enter Amount:");
		enterAmountLabel.setBounds(20, 50, getWidth() - 20, 20);
		enterAmountLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		enterAmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(enterAmountLabel);
	}
	/**
	 * This method sets {@link DepositDialog#enterAmountField} and adds it to deposit dialog.
	 * */
	public void setEnterAmountField() {
		enterAmountField = new JTextField();
		enterAmountField.setBounds(20, 80, getWidth()  - 50, 40);
		enterAmountField.setFont(new Font("Dialog", Font.BOLD, 20));
		enterAmountField.setHorizontalAlignment(SwingConstants.LEFT);
		add(enterAmountField);
	}
	
	/**
	 * This method sets {@link DepositDialog#depositButton} and adds it to deposit dialog.
	 * */
	public void setDepositButton() {
		depositButton = new JButton("Deposit");
		depositButton.setBounds(15, 300, getWidth() - 50, 50);
		depositButton.setFont(new Font("Dialog", Font.BOLD, 22));
		depositButton.setFocusable(false);
		depositButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BigDecimal amount = new BigDecimal(enterAmountField.getText());
					if(JDBC.deposit(user, amount)) {
						JOptionPane.showMessageDialog(DepositDialog.this, "Deposit successfully");
						DepositDialog.this.dispose();
					}else {
						JOptionPane.showMessageDialog(DepositDialog.this, "Deposit failed");
					}

					//Update the balance
					JDBC.updateBalance(user);
					homePageGui.dispose();
					homePageGui = new HomePageGui(user);
					homePageGui.setVisible(true);
				}catch(NumberFormatException f) {
					f.printStackTrace();
					JOptionPane.showMessageDialog(DepositDialog.this, "Please enter a number");
				}
				
			}
		});
		add(depositButton);
	}
}
