package database;

import java.math.BigDecimal;
import java.sql.Date;

import guis.HomePageGui;
import guis.TransferDialog;
/**
 * This is a class that represents transaction records in table transaction in database
 * @author Binglei Cai
 */
public class Transaction {
	/**transaction id*/
	private final int id;
	/**transaction amount*/
	private final BigDecimal transactionAmount;
	/**transaction date*/
	private final Date transactionDate;
	/**transaction type*/
	private final String transactionType;
	/**user id of transaction*/
	private final int userId;
	/**
	 * This constructor sets information of a transaction.
	 * @param id transaction id
	 * @param transactionAmount transaction amount
	 * @param transactionDate transaction date
	 * @param transactionType transaction type
	 * @param userId user id of transaction
	 * */
	public Transaction(int id, BigDecimal transactionAmount, Date transactionDate, String transactionType, int userId) {
		this.id = id;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.userId = userId;
	}
	/**
	 * This method is used to get {@link Transaction#id}
	 * @return {@link Transaction#id}
	 * */
	public int getId() {
		return id;
	}
	/**
	 * This method is used to get {@link Transaction#transactionAmount}
	 * @return {@link Transaction#transactionAmount}
	 * */
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	/**
	 * This method is used to get {@link Transaction#transactionDate}
	 * @return {@link Transaction#transactionDate}
	 * */
	public Date getTransactionDate() {
		return transactionDate;
	}
	/**
	 * This method is used to get {@link Transaction#transactionType}
	 * @return {@link Transaction#transactionType}
	 * */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * This method is used to get {@link Transaction#userId}
	 * @return {@link userId}
	 * */
	public int getUserId() {
		return userId;
	}
}
