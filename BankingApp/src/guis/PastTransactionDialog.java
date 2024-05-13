package guis;

import database.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.math.BigDecimal;
import java.sql.Date;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
/**
 * This is a class that create past transaction dialog extending {@link HomePageDialog} class.
 * @author Binglei Cai
 */
public class PastTransactionDialog extends HomePageDialog {
	/**transaction panel*/
	private JPanel pastTransactionPanel;
	/**scroll pane*/
	private JScrollPane scrollPane;
	/**ArrayList for {@link database.Transaction}*/
	private ArrayList<Transaction> pastTransactions;
	/**
	 * This constructor sets layout of past transaction dialog.
	 * @param homePageGui {@link HomePageGui} object
	 * @param user {@link User} object
	 * */
	public PastTransactionDialog(HomePageGui homePageGui, User user) {
		super(homePageGui, user);
		setSize(600, 400);
		setTitle("Past Transaction");
		setScrollPane();
		add(scrollPane);
	}
	/**
	 * This method sets {@link PastTransactionDialog#pastTransactions}
	 * */
	public void setPastTransactions() {
		pastTransactions = JDBC.getPastTransactions(user);
	}
	/**
	 * This method sets {@link PastTransactionDialog#pastTransactionPanel}
	 * */
	public void setPastTransactionPanel() {
		pastTransactionPanel = new JPanel();
		pastTransactionPanel.setLayout(new BoxLayout(pastTransactionPanel, BoxLayout.Y_AXIS));
		setPastTransactions();
		for(Transaction pastTransaction : pastTransactions) {
			JPanel pastTransactionContainer = new JPanel();
			pastTransactionContainer.setLayout(new GridLayout(1, 3));
			pastTransactionContainer.setBackground(Color.WHITE);
			pastTransactionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			String transactionType = pastTransaction.getTransactionType();
			BigDecimal transactionAmount = pastTransaction.getTransactionAmount();
			Date transactionDate = pastTransaction.getTransactionDate();
				
			JLabel transactionTypeLabel = new JLabel(transactionType);
			transactionTypeLabel.setFont(new Font("Dialog", Font.BOLD, 20));
				
			JLabel transactionAmountLabel = new JLabel("$" + String.valueOf(transactionAmount));
			transactionAmountLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			transactionAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				
			JLabel transactionDateLabel = new JLabel(String.valueOf(transactionDate));
			transactionDateLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			transactionDateLabel.setHorizontalAlignment(JLabel.RIGHT);
				
			pastTransactionContainer.add(transactionTypeLabel);
			pastTransactionContainer.add(transactionAmountLabel);
			pastTransactionContainer.add(transactionDateLabel);
			pastTransactionPanel.add(pastTransactionContainer);
		}
	}
	/**
	 * This method sets {@link PastTransactionDialog#scrollPane} and adds it to past transaction dialog.
	 * */
	public void setScrollPane() {
		setPastTransactionPanel();
		scrollPane = new JScrollPane(pastTransactionPanel);
		scrollPane.setBounds(0, 20, getWidth() - 15, getHeight() - 60);
		//display scrollbar only when required
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
		
}
