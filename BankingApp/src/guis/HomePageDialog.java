package guis;

import database.User;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import database.JDBC;
import java.math.BigDecimal;
/**
 * This is an abstract class that create home page dialog extending {@link javax.swing.JDialog} class.
 * @author Binglei Cai
 */
public abstract class HomePageDialog extends JDialog {
	/**{@link HomePageGui} object*/
	protected HomePageGui homePageGui;
	/**{@link User} object*/
	protected User user;
	/**
	 * This constructor sets layout of home page dialogs.
	 * @param homePageGui {@link HomePageGui} object
	 * @param user {@link User} object
	 * */
	public HomePageDialog(HomePageGui homePageGui, User user) {
		setSize(400, 400);
		setModal(true);
		setLocationRelativeTo(homePageGui);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		this.homePageGui = homePageGui;
		this.user = user;
	}
}
