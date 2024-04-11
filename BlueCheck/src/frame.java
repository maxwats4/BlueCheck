/**
 * Video to show how to make a executable jar window desktop icon is : https://www.youtube.com/watch?v=Bi8SD_3Mt3s
 * 
 * Every time you update a version you need to create a new desktop icon to access the app
 * 
 * 
 * This is the main file that runs the whole program. 
 * 
 * @author Max Watson
 */


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class frame extends JFrame {

	public static JPanel contentPane;
	static frame GUI;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI = new frame();
					GUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(new Login());
	}

}
