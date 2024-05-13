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
 * This is a class that create withdraw dialog extending {@link HomePageDialog} class.
 * @author Binglei Cai
 */
public class WithdrawDialog extends HomePageDialog {
	/**balance label*/
	private JLabel balanceLabel;
	/**enter amount label*/
	private JLabel enterAmountLabel;
	/**enter amount field*/
	private JTextField enterAmountField;
	/**withdraw button*/
	private JButton withdrawButton;
	/**
	 * This constructor sets layout of withdraw dialog.
	 * @param homePageGui {@link HomePageGui} object
	 * @param user {@link User} object
	 * */
	public WithdrawDialog(HomePageGui homePageGui, User user) {
		super(homePageGui, user);
		setTitle("Withdraw");
		setBalanceLabel();
		setEnterAmountLabel();
		setEnterAmountField();
		setWithdrawButton();
	}
	/**
	 * This method sets {@link WithdrawDialog#balanceLabel} and adds it to withdraw dialog.
	 * */
	public void setBalanceLabel() {
		balanceLabel = new JLabel("Balance: $" + user.getCurrentBalance());
		balanceLabel.setBounds(0, 10, getWidth() - 20, 20);
		balanceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(balanceLabel);
	}
	/**
	 * This method sets {@link WithdrawDialog#enterAmountLabel} and adds it to withdraw dialog.
	 * */
	public void setEnterAmountLabel() {
		enterAmountLabel = new JLabel("Enter Amount:");
		enterAmountLabel.setBounds(20, 50, getWidth() - 20, 20);
		enterAmountLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		enterAmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(enterAmountLabel);
	}
	/**
	 * This method sets {@link WithdrawDialog#enterAmountField} and adds it to withdraw dialog.
	 * */
	public void setEnterAmountField() {
		enterAmountField = new JTextField();
		enterAmountField.setBounds(20, 80, getWidth()  - 50, 40);
		enterAmountField.setFont(new Font("Dialog", Font.BOLD, 20));
		enterAmountField.setHorizontalAlignment(SwingConstants.LEFT);
		add(enterAmountField);
	}
	/**
	 * This method sets {@link WithdrawDialog#withdrawButton} and adds it to withdraw dialog.
	 * */
	public void setWithdrawButton() {
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(15, 300, getWidth() - 50, 50);
		withdrawButton.setFont(new Font("Dialog", Font.BOLD, 22));
		withdrawButton.setFocusable(false);
		withdrawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BigDecimal amount = new BigDecimal(enterAmountField.getText());
					if(JDBC.withdraw(user, amount)) {
						JOptionPane.showMessageDialog(WithdrawDialog.this, "Withdraw successfully");
						WithdrawDialog.this.dispose();
					}else {
						JOptionPane.showMessageDialog(WithdrawDialog.this, "Withdraw failed");
					}
			
					//Update the balance
					JDBC.updateBalance(user);
					homePageGui.dispose();
					homePageGui = new HomePageGui(user);
					homePageGui.setVisible(true);
				}catch(NumberFormatException f) {
					f.printStackTrace();
					JOptionPane.showMessageDialog(WithdrawDialog.this, "Please enter a number");
				}
			}	
		});
		add(withdrawButton);
	}
}
