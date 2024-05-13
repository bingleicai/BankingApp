package appLauncher;

import javax.swing.SwingUtilities;
import guis.LoginGui;
/**
* This is the driver class of the program. It just runs the application with a method
* main.
* @author Binglei Cai
*/
public class AppLauncher{
	/**
	 * This is the entry point for the application, it creates a {@link guis.LoginGui} object and make it visible for user.
	 * @param args Command line arguments are not used by this program.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LoginGui loginGui = new LoginGui();
				loginGui.setVisible(true);
			}
		});
	}

}