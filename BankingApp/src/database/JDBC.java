package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import guis.HomePageDialog;
import guis.HomePageGui;
import guis.LoginGui;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
/**
 * This is a class that connects MySQL database to {@link User} and {@link Transaction}
 * @author Binglei Cai
 */
public class JDBC {
	/**datavase URL*/
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
	/**database username*/
	private static final String USERNAME = "root";
	/**database password*/
	private static final String PASSWORD = "19980821";
	/**
	 * This method is used to validate if user exists in database and return the user if exists.
	 * @param username username of the user
	 * @param password password of the user
	 * @return {@link User} object
	 * */
	public static User validateLogin(String username, String password) {
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			PreparedStatement selectUser = connection.prepareStatement("SELECT * FROM bankapp.users WHERE username = ? AND password = ?");
			selectUser.setString(1, username);
			selectUser.setString(2, password);
			
			ResultSet resultUser = selectUser.executeQuery();
			
			if(resultUser.next()) {
				int userId = resultUser.getInt("id");
				BigDecimal currentBalance = resultUser.getBigDecimal("current_balance");
				return new User(userId, username, password, currentBalance);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * This method is used to register new user if selected username doesn't exist in database.
	 * @param username username of the user
	 * @param password password of the user
	 * @return boolean value to indicate if register succeed
	 * */
	public static boolean register(String username, String password) {
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement checkUser = connection.prepareStatement("SELECT * FROM bankapp.users WHERE username = ?");
			checkUser.setString(1, username);
			
			ResultSet resultUser = checkUser.executeQuery();
			if(!resultUser.next()) {
				PreparedStatement insertUser = connection.prepareStatement("INSERT INTO bankapp.users(username, password, current_balance) VALUES (?, ?, 0.00)");
				insertUser.setString(1, username);
				insertUser.setString(2, password);
				insertUser.executeUpdate();
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * This method is used to make deposit for the user.
	 * @param user {@link User} object
	 * @param amount the amount to deposit
	 * @return boolean value to indicate if deposit succeed
	 * */
	public static boolean deposit(User user, BigDecimal amount) {
		try {
			int id = user.getId();
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			connection.setAutoCommit(false);
			
			PreparedStatement insertTransaction = connection.prepareStatement("INSERT INTO bankapp.transaction(transaction_amount, transaction_date, transaction_type, user_id) VALUES (?, NOW(), 'Deposit', ?)");
			insertTransaction.setBigDecimal(1, amount);
			insertTransaction.setInt(2, id);
			
			PreparedStatement updateUser = connection.prepareStatement("UPDATE bankapp.users SET current_balance = current_balance + ? WHERE id = ?");
			updateUser.setBigDecimal(1, amount);
			updateUser.setInt(2, id);
			
			insertTransaction.executeUpdate();
			updateUser.executeUpdate();
			connection.commit();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * This method is used to make withdraw for the user.
	 * @param user {@link User} object
	 * @param amount the amount to withdraw
	 * @return boolean value to indicate if withdraw succeed
	 * */
	public static boolean withdraw(User user, BigDecimal amount) {
		try {
			int id = user.getId();
			BigDecimal currentBalance = user.getCurrentBalance();
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			connection.setAutoCommit(false);
			
			if(currentBalance.compareTo(amount) > 0) {
				PreparedStatement insertTransaction = connection.prepareStatement("INSERT INTO bankapp.transaction(transaction_amount, transaction_date, transaction_type, user_id) VALUES (?, NOW(), 'Withdraw', ?)");
				insertTransaction.setBigDecimal(1, amount);
				insertTransaction.setInt(2, id);
				
				PreparedStatement updateUser = connection.prepareStatement("UPDATE bankapp.users SET current_balance = current_balance - ? WHERE id = ?");
				updateUser.setBigDecimal(1, amount);
				updateUser.setInt(2, id);
				
				insertTransaction.executeUpdate();
				updateUser.executeUpdate();
				connection.commit();
				return true;
			}else {
				throw new IllegalArgumentException("Insufficient funds");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * This method is used to make transfer to another user.
	 * @param fromUser {@link User} object to transfer money from
	 * @param toUser {@link User} object to transfer money to
	 * @param amount the amount to transfer
	 * @return boolean value to indicate if deposit succeed
	 * */
	public static boolean transfer(User fromUser, User toUser, BigDecimal amount) {
		try {
			int fromUserId = fromUser.getId();
			String fromUsername = fromUser.getUsername();
			int toUserId = toUser.getId();
			String toUsername = toUser.getUsername();
			
			BigDecimal fromUserBalance = fromUser.getCurrentBalance();
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			connection.setAutoCommit(false);
			
			if(fromUserBalance.compareTo(amount) > 0) {
				PreparedStatement insertFromUserTransaction = connection.prepareStatement("INSERT INTO bankapp.transaction(transaction_amount, transaction_date, transaction_type, user_id) VALUES (?, NOW(), ?, ?)");
				insertFromUserTransaction.setBigDecimal(1, amount);
				insertFromUserTransaction.setString(2, "Transfer to " + toUsername);
				insertFromUserTransaction.setInt(3, fromUserId);
				
				PreparedStatement insertToUserTransaction = connection.prepareStatement("INSERT INTO bankapp.transaction(transaction_amount, transaction_date, transaction_type, user_id) VALUES (?, NOW(), ?, ?)");
				insertToUserTransaction.setBigDecimal(1, amount);
				insertToUserTransaction.setString(2, "Transfer from " + fromUsername);
				insertToUserTransaction.setInt(3, toUserId);
				
				PreparedStatement updateFromUser = connection.prepareStatement("UPDATE bankapp.users SET current_balance = current_balance - ? WHERE id = ?");
				updateFromUser.setBigDecimal(1, amount);
				updateFromUser.setInt(2, fromUserId);
				
				PreparedStatement updateToUser = connection.prepareStatement("UPDATE bankapp.users SET current_balance = current_balance + ? WHERE id = ?");
				updateToUser.setBigDecimal(1, amount);
				updateToUser.setInt(2, toUserId);
				
				insertFromUserTransaction.executeUpdate();
				insertToUserTransaction.executeUpdate();
				updateFromUser.executeUpdate();
				updateToUser.executeUpdate();
				connection.commit();
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * This method is used to retrive current balance of the user in database and pass it to {@link User#setCurrentBalance(BigDecimal)} to set attribute currentBalance of the user.
	 * @param user {@link User} object to update balance of
	 * */
	public static void updateBalance(User user) {
		try {
			int id = user.getId();
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement selectUser = connection.prepareStatement("SELECT * FROM bankapp.users WHERE id = ?");
			selectUser.setInt(1, id);
			ResultSet resultUser = selectUser.executeQuery();
			resultUser.next();
			BigDecimal currentBalance = resultUser.getBigDecimal("current_Balance");
			user.setCurrentBalance(currentBalance);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to get {@link User} object by username
	 * @param username username of the user
	 * @return {@link User} object of the indicated username
	 * */
	public static User getUserByUsername(String username) {
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement selectUser = connection.prepareStatement("SELECT * FROM bankapp.users WHERE username = ?");
			selectUser.setString(1, username);
			ResultSet resultUser = selectUser.executeQuery();
			
			if(resultUser.next()) {
				int userId = resultUser.getInt("id");
				String password = resultUser.getString("password");
				BigDecimal currentBalance = resultUser.getBigDecimal("current_balance");
				return new User(userId, username, password, currentBalance);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * This method is used to get past transactions of certain user
	 * @param user {@link User} object to get the past transactions of
	 * @return ArrayList of {@link Transaction} of the certain user
	 * */
	public static ArrayList<Transaction> getPastTransactions(User user){
		try {
			ArrayList<Transaction> pastTransactions = new ArrayList();
			int userId = user.getId();
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement selectAllTransaction = connection.prepareStatement("SELECT * FROM bankapp.transaction WHERE user_id = ? ORDER BY transaction_date DESC");
			selectAllTransaction.setInt(1, userId);
			ResultSet resultTransaction = selectAllTransaction.executeQuery();
			
			while(resultTransaction.next()) {
				int transactionId = resultTransaction.getInt("id");
				BigDecimal transactionAmount = resultTransaction.getBigDecimal("transaction_amount");
				Date transactionDate = resultTransaction.getDate("transaction_date");
				String transactionType = resultTransaction.getString("transaction_type");
				
				Transaction transaction = new Transaction(transactionId, transactionAmount, transactionDate, transactionType, userId);
				pastTransactions.add(transaction);
			}
			
			return pastTransactions;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
