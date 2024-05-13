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
 * This is a class that create transfer dialog extending {@link HomePageDialog} class.
 * @author Binglei Cai
 */
public class TransferDialog extends HomePageDialog {
	/**balance label*/
	private JLabel balanceLabel;
	/**enter amount label*/
	private JLabel enterAmountLabel;
	/**enter amount field*/
	private JTextField enterAmountField;
	/**transfer button*/
	private JButton transferButton;
	/**enter user label*/
	private JLabel enterUserLabel;
	/**enter user field*/
	private JTextField enterUserField;
	/**
	 * This constructor sets layout of transfer dialog.
	 * @param homePageGui {@link HomePageGui} object
	 * @param user {@link User} object
	 * */
	public TransferDialog(HomePageGui homePageGui, User user) {
		super(homePageGui, user);
		setTitle("Transfer");
		setBalanceLabel();
		setEnterAmountLabel();
		setEnterAmountField();
		setEnterUserLabel();
		setEnterUserField();
		setTransferButton();
	}
	/**
	 * This method sets {@link TransferDialog#balanceLabel} and adds it to transfer dialog.
	 * */
	public void setBalanceLabel() {
		balanceLabel = new JLabel("Balance: $" + user.getCurrentBalance());
		balanceLabel.setBounds(0, 10, getWidth() - 20, 20);
		balanceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(balanceLabel);
	}
	/**
	 * This method sets {@link TransferDialog#enterAmountLabel} and adds it to transfer dialog.
	 * */
	public void setEnterAmountLabel() {
		enterAmountLabel = new JLabel("Enter Amount:");
		enterAmountLabel.setBounds(20, 50, getWidth() - 20, 20);
		enterAmountLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		enterAmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(enterAmountLabel);
	}
	/**
	 * This method sets {@link TransferDialog#enterAmountField} and adds it to transfer dialog.
	 * */
	public void setEnterAmountField() {
		enterAmountField = new JTextField();
		enterAmountField.setBounds(20, 80, getWidth()  - 50, 40);
		enterAmountField.setFont(new Font("Dialog", Font.BOLD, 20));
		enterAmountField.setHorizontalAlignment(SwingConstants.LEFT);
		add(enterAmountField);
	}
	/**
	 * This method sets {@link TransferDialog#enterUserLabel} and adds it to transfer dialog.
	 * */
	public void setEnterUserLabel() {
		enterUserLabel = new JLabel("Enter Username:");
		enterUserLabel.setBounds(20, 150, getWidth() - 20, 20);
		enterUserLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		enterUserLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(enterUserLabel);
	}
	/**
	 * This method sets {@link TransferDialog#enterUserField} and adds it to transfer dialog.
	 * */
	public void setEnterUserField() {
		enterUserField = new JTextField();
		enterUserField.setBounds(20, 180, getWidth()  - 50, 40);
		enterUserField.setFont(new Font("Dialog", Font.BOLD, 20));
		enterUserField.setHorizontalAlignment(SwingConstants.LEFT);
		add(enterUserField);
	}
	/**
	 * This method sets {@link TransferDialog#transferButton} and adds it to transfer dialog.
	 * */
	public void setTransferButton() {
		transferButton = new JButton("Transfer");
		transferButton.setBounds(15, 300, getWidth() - 50, 50);
		transferButton.setFont(new Font("Dialog", Font.BOLD, 22));
		transferButton.setFocusable(false);
		transferButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BigDecimal amount = new BigDecimal(enterAmountField.getText());
					String username = enterUserField.getText();
					User toUser = JDBC.getUserByUsername(username);
					if(toUser != null) {
						if(JDBC.transfer(user, toUser, amount)) {
							JOptionPane.showMessageDialog(TransferDialog.this, "Transfer successfully");
							TransferDialog.this.dispose();
						}else {
							JOptionPane.showMessageDialog(TransferDialog.this, "Transfer failed");
							}
					}else {
						JOptionPane.showMessageDialog(TransferDialog.this, "User doesn't exist");
					}
					
					//Update the balance
					JDBC.updateBalance(user);
					homePageGui.dispose();
					homePageGui = new HomePageGui(user);
					homePageGui.setVisible(true);
				}catch(NumberFormatException f) {
					f.printStackTrace();
					JOptionPane.showMessageDialog(TransferDialog.this, "Please enter a number");
				}
				
			}
		});
		add(transferButton);
	}
}
