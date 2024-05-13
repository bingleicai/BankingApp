package guis;

import javax.swing.JFrame;

/**
 * This is an abstract class that create base frame for guis.
 * @author Binglei Cai
 */
public abstract class BaseFrame extends JFrame {
	/**
	 * This constructor sets layout of gui frames.
	 * @param title the title of the frame
	 * */
	public BaseFrame(String title) {
		setTitle(title);
		setSize(420, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
	}	
}
