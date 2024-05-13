package database;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * This is a class that represents user records in table users in database
 * @author Binglei Cai
 */
public class User {
	/**user id*/
	private final int id;
	/**username*/
	private String username;
	/**passwordd*/
	private String password;
	/**current balance*/
	private BigDecimal currentBalance;
	/**
	 * This constructor sets information of a user.
	 * @param id user id
	 * @param password user password
	 * @param currentBalance current balance of user
	 * */
	public User(int id, String username, String password, BigDecimal currentBalance) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.currentBalance = currentBalance.setScale(2, RoundingMode.FLOOR);
	}
	/**
	 * This method is used to get {@link User#id}
	 * @return {@link User#id}
	 * */
	public int getId() {
		return id;
	}
	/**
	 * This method is used to get {@link User#username}
	 * @return {@link User#username}
	 * */
	public String getUsername() {
		return username;
	}
	/**
	 * This method is used to get {@link User#password}
	 * @return {@link User#password}
	 * */
	public String getPassword() {
		return password;
	}
	/**
	 * This method is used to get {@link User#currentBalance}
	 * @return {@link User#currentBalance}
	 * */
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	/**
	 * This method is used to set {@link User#currentBalance}
	 * @param current balance of user
	 * */
	public void setCurrentBalance(BigDecimal balance) {
		currentBalance = balance.setScale(2, RoundingMode.FLOOR);
	}
	

}
