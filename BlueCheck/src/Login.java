/***
 * 
 * This is the login panel that holds the login information
 * 
 * This also hold the connector to the SQL database
 * 
 */

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JPanel implements ActionListener {
	private static JTextField LoginText;
	private JPasswordField PasswordText;
	JButton SubmitButton;
	JPanel panel;
	JPanel panel_1;
	private static JLabel incorrectPasswordLabel;
	
	static String dbUsername;
	static String dbPassword;
	static String inputUsername;
	static String inputPassword;
	private static JLabel incorrectUsernameLabel;
	

	/**
	 * Create the panel.
	 */
	public Login() {
		setBackground(new Color(192, 192, 192));
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(122, 45, 623, 116);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Blue Check");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 95));
		lblNewLabel.setBounds(72, 10, 551, 93);
		panel.add(lblNewLabel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(180, 205, 467, 210);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel LoginLabel = new JLabel("Login: ");
		LoginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		LoginLabel.setBounds(44, 51, 126, 49);
		panel_1.add(LoginLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		passwordLabel.setBounds(44, 95, 126, 49);
		panel_1.add(passwordLabel);
		
		LoginText = new JTextField();
		LoginText.setBounds(219, 56, 199, 28);
		panel_1.add(LoginText);
		LoginText.setColumns(10);
		
		PasswordText = new JPasswordField();
		PasswordText.setColumns(10);
		PasswordText.setBounds(219, 101, 199, 28);
		panel_1.add(PasswordText);
		
		SubmitButton = new JButton("Submit");
		SubmitButton.addActionListener(this);
		SubmitButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		SubmitButton.setBounds(167, 154, 107, 28);
		panel_1.add(SubmitButton);
		
		incorrectPasswordLabel = new JLabel("** PASSWORD IS INCORRECT**");
		incorrectPasswordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		incorrectPasswordLabel.setForeground(Color.RED);
		incorrectPasswordLabel.setBackground(Color.WHITE);
		incorrectPasswordLabel.setBounds(76, 16, 330, 25);
		incorrectPasswordLabel.setVisible(false);
		panel_1.add(incorrectPasswordLabel);
		
		incorrectUsernameLabel = new JLabel("**USERNAME IS INCORRECT**");
		incorrectUsernameLabel.setForeground(Color.RED);
		incorrectUsernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		incorrectUsernameLabel.setBounds(79, 17, 447, 23);
		incorrectUsernameLabel.setVisible(false);
		panel_1.add(incorrectUsernameLabel);

	}
	
	/**
	 * 
	 * Database manipulation code and Login Verification code
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	
	 //connects to the database
	public static Connection getConnection() throws Exception{
		
		try {
			String url = "jdbc:mysql://localhost:3306/blue_check"; //jbdc:mysql:// is basic, localhost:3306 is the sever name or can be ip address if its on the web, jdbcdemo is the table name
			String username = "root";
			String password = "Gr33ndrake";
			 
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("connected");
			return conn;
		
		}catch(Exception e) {
			System.out.println(e);
			
		}
		
		return null;
	}
	
	//gets returning user login Information
	 public static void getUserInfo(Connection con) throws SQLException {
		 
		 String query = "SELECT *\r\n"
		    		+ "FROM blue_check.doctor_info\r\n"
		    		+ "WHERE username = '"+inputUsername+"';"; //sql statement
		    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
		      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
		     while(rs.next()) {
		    	 dbUsername = rs.getString("username");
		    	 dbPassword = rs.getString("password");	 
		     }
		       
		    } catch (SQLException e) {
		    }
	
	 }
	 
	 /**
	  * Gets the user information from the database and compares it to the inputed data
	  * 
	  * @return true if the input and DB username and password are correct, false if otherwise
	  */
	 public static boolean validateUser() {
		
		 if(inputUsername.equals(dbUsername)) {
			 if(inputPassword.equals(dbPassword)) {
				 return true;
			 }else {
				 incorrectPasswordLabel.setVisible(true);
				 incorrectUsernameLabel.setVisible(false);
				 return false;
			 }
		 }else {
			 incorrectUsernameLabel.setVisible(true);
			 incorrectPasswordLabel.setVisible(false);
		  return false;
		  }
		
	 }
	
	/**
	 * 
	 * 
	 */
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == SubmitButton) {
			inputUsername = LoginText.getText();
			inputPassword = PasswordText.getText();
			
			try {
				getUserInfo(getConnection());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			//This changes the panel to Menu panel
			
			if(validateUser() == true) {
				frame.GUI.setContentPane(new Menu());
				frame.GUI.revalidate();
			}

		}
	}
}
