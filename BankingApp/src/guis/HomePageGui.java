package guis;

import database.User;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * This is a class that create home page gui extending {@link BaseFrame} class.
 * @author Binglei Cai
 */
public class HomePageGui extends BaseFrame {
	/**{@link User} object*/
	private User user;
	/**welcome message label*/
	private JLabel welcomeMessageLabel;
	/**current balance label*/
	private JLabel currentBalanceLabel;
	/**deposit button*/
	private JButton depositButton;
	/**withdraw button*/
	private JButton withdrawButton;
	/**transfer button*/
	private JButton transferButton;
	/**past transaction button*/
	private JButton pastTransactionButton;
	/**logout button*/
	private JButton logoutButton;
	/**
	 * This constructor sets layout of home page gui.
	 * @param user {@link User} object
	 * */
	public HomePageGui(User user) {
		super("Banking Application - Home Page");
		this.user = user;
		setWelcomeMessageLabel();
		setCurrentBalanceLabel();
		setDepositButton();
		setWithdrawButton();
		setTransferButton();
		setPastTransactionButton();
		setLogoutButton();
	}
	/**
	 * This method sets {@link HomePageGui#welcomeMessageLabel} and adds it to home page gui.
	 * */
	public void setWelcomeMessageLabel() {
		String welcomeMessage = "<html>"
				+ "<body style='text-align:center'>"
				+ "<b>Hello " + user.getUsername() + "</b>"
				+ "<br>"
				+ "What would you like to do today?</body></html>";
		welcomeMessageLabel = new JLabel(welcomeMessage);
		welcomeMessageLabel.setBounds(0, 20, getWidth() - 10, 40);
		welcomeMessageLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(welcomeMessageLabel);
	}
	/**
	 * This method sets {@link HomePageGui#currentBalanceLabel} and adds it to home page gui.
	 * */
	public void setCurrentBalanceLabel() {
		currentBalanceLabel = new JLabel("Current Balance: $" + user.getCurrentBalance());
		currentBalanceLabel.setBounds(0, 80, getWidth() - 10, 30);
		currentBalanceLabel.setFont(new Font("Dialog", Font.BOLD, 22));
		currentBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(currentBalanceLabel);
	}
	/**
	 * This method sets {@link HomePageGui#depositButton} and adds it to home page gui.
	 * */
	public void setDepositButton() {
		depositButton = new JButton("Deposit");
		depositButton.setBounds(15, 150, getWidth() - 50, 50);
		depositButton.setFont(new Font("Dialog", Font.BOLD, 22));
		depositButton.setFocusable(false);
		depositButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DepositDialog depositDialog = new DepositDialog(HomePageGui.this, user);
				depositDialog.setVisible(true);
			}
		});
		add(depositButton);
	}
	/**
	 * This method sets {@link HomePageGui#withdrawButton} and adds it to home page gui.
	 * */
	public void setWithdrawButton() {
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(15, 220, getWidth() - 50, 50);
		withdrawButton.setFont(new Font("Dialog", Font.BOLD, 22));
		withdrawButton.setFocusable(false);
		withdrawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WithdrawDialog withdrawDialog = new WithdrawDialog(HomePageGui.this, user);
				withdrawDialog.setVisible(true);
			}
		});
		add(withdrawButton);
	}
	/**
	 * This method sets {@link HomePageGui#transferButton} and adds it to home page gui.
	 * */
	public void setTransferButton() {
		transferButton = new JButton("Transfer");
		transferButton.setBounds(15, 290, getWidth() - 50, 50);
		transferButton.setFont(new Font("Dialog", Font.BOLD, 22));
		transferButton.setFocusable(false);
		transferButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TransferDialog transferDialog = new TransferDialog(HomePageGui.this, user);
				transferDialog.setVisible(true);
			}
		});
		add(transferButton);
	}
	/**
	 * This method sets {@link HomePageGui#pastTransactionButton} and adds it to home page gui.
	 * */
	public void setPastTransactionButton() {
		pastTransactionButton = new JButton("Past Transactions");
		pastTransactionButton.setBounds(15, 360, getWidth() - 50, 50);
		pastTransactionButton.setFont(new Font("Dialog", Font.BOLD, 22));
		pastTransactionButton.setFocusable(false);
		pastTransactionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PastTransactionDialog pastTransactionDialog = new PastTransactionDialog(HomePageGui.this, user);
				pastTransactionDialog.setVisible(true);
			}
		});
		add(pastTransactionButton);
	}
	/**
	 * This method sets {@link HomePageGui#logoutButton} and adds it to home page gui.
	 * */
	public void setLogoutButton() {
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(15, 500, getWidth() - 50, 50);
		logoutButton.setFont(new Font("Dialog", Font.BOLD, 22));
		logoutButton.setFocusable(false);
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HomePageGui.this.dispose();
				new LoginGui().setVisible(true);
			}
		});
		add(logoutButton);
	}
	
}
