import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import com.mysql.cj.jdbc.CallableStatement;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.util.Random;

public class patientAddPanel extends JPanel implements ActionListener{
	private static JTextField NameTextField;
	private static JTextField AgeTextField;
	private static JTextField GenderTextField;
	private static JTextField EmailTextField;
	private static JTextField PhoneTextField;
	private static JTextField UsernameTextField;
	private static JTextField PasswordTextField;
	
	JLabel lblNewLabel;
	JLabel NameLabel; 
	JLabel AgeLabel;
	JLabel GenderLabel;
	JLabel EmailLabel;
	JLabel PhoneNumberLabel;
	JLabel UsernameLabel;
	JLabel Password;
	JLabel LoginInfoLabel;
	
	JButton CancelButton;
	JButton SubmitButton;
	private JLabel CancelMessage;
	private JLabel CancelMessage2;
	private JLabel PatientAddedMessage;
	
	public static int newIDNumber;

	/**
	 * Create the panel.
	 */
	public patientAddPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(171, 59, 446, 381);
		add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Enter New Patient Information");
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		lblNewLabel.setBounds(47, 10, 413, 42);
		panel.add(lblNewLabel);
		
		NameLabel = new JLabel("Name:");
		NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NameLabel.setBounds(47, 77, 45, 13);
		panel.add(NameLabel);
		
		AgeLabel = new JLabel("Age:");
		AgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AgeLabel.setBounds(47, 100, 45, 19);
		panel.add(AgeLabel);
		
		GenderLabel = new JLabel("Gender:");
		GenderLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GenderLabel.setBounds(47, 127, 66, 19);
		panel.add(GenderLabel);
		
		EmailLabel = new JLabel("Email:");
		EmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EmailLabel.setBounds(47, 156, 66, 19);
		panel.add(EmailLabel);
		
		PhoneNumberLabel = new JLabel("Phone #: ");
		PhoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PhoneNumberLabel.setBounds(47, 185, 85, 19);
		panel.add(PhoneNumberLabel);
		
		UsernameLabel = new JLabel("Username: ");
		UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UsernameLabel.setBounds(47, 284, 85, 19);
		panel.add(UsernameLabel);
		
		Password = new JLabel("Password:");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Password.setBounds(47, 313, 85, 19);
		panel.add(Password);
		
		LoginInfoLabel = new JLabel("Enter Patient Login Information");
		LoginInfoLabel.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		LoginInfoLabel.setBounds(99, 232, 316, 42);
		panel.add(LoginInfoLabel);
		
		NameTextField = new JTextField();
		NameTextField.setBounds(149, 76, 197, 19);
		panel.add(NameTextField);
		NameTextField.setColumns(10);
		
		AgeTextField = new JTextField();
		AgeTextField.setColumns(10);
		AgeTextField.setBounds(149, 102, 197, 19);
		panel.add(AgeTextField);
		
		GenderTextField = new JTextField();
		GenderTextField.setColumns(10);
		GenderTextField.setBounds(149, 129, 197, 19);
		panel.add(GenderTextField);
		
		EmailTextField = new JTextField();
		EmailTextField.setColumns(10);
		EmailTextField.setBounds(149, 158, 197, 19);
		panel.add(EmailTextField);
		
		PhoneTextField = new JTextField();
		PhoneTextField.setColumns(10);
		PhoneTextField.setBounds(149, 187, 197, 19);
		panel.add(PhoneTextField);
		
		UsernameTextField = new JTextField();
		UsernameTextField.setColumns(10);
		UsernameTextField.setBounds(149, 286, 197, 19);
		panel.add(UsernameTextField);
		
		PasswordTextField = new JTextField();
		PasswordTextField.setColumns(10);
		PasswordTextField.setBounds(149, 315, 197, 19);
		panel.add(PasswordTextField);
		
		CancelButton = new JButton("Cancel");
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CancelButton.setBounds(237, 351, 85, 21);
		CancelButton.addActionListener(this);
		panel.add(CancelButton);
		
		SubmitButton = new JButton("Submit");
		SubmitButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SubmitButton.setBounds(332, 351, 85, 21);
		SubmitButton.addActionListener(this);
		panel.add(SubmitButton);
		
		CancelMessage = new JLabel("New Patient Canceled");
		CancelMessage.setFont(new Font("Tahoma", Font.PLAIN, 25));
		CancelMessage.setBounds(123, 85, 368, 41);
		CancelMessage.setVisible(false);
		panel.add(CancelMessage);
		
		CancelMessage2 = new JLabel("You may safely exit this window");
		CancelMessage2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CancelMessage2.setBounds(134, 130, 285, 26);
		CancelMessage2.setVisible(false);
		panel.add(CancelMessage2);
		
		PatientAddedMessage = new JLabel("New Patient Successfully Added");
		PatientAddedMessage.setFont(new Font("Tahoma", Font.PLAIN, 25));
		PatientAddedMessage.setBounds(57, 87, 375, 37);
		PatientAddedMessage.setVisible(false);
		panel.add(PatientAddedMessage);

	}
	
	
	
		// creates an ID number for the new patient and checks to makes sure that it doesnt already exist
		public static void randomIdNumber() {
			Random rand = new Random();
			
			int num = rand.nextInt(999999);
			
			String url = "jdbc:mysql://localhost:3306/blue_check"; 
			String username = "root";
			String password = "Gr33ndrake";
			
			boolean exists = false;
	        
	        try (Connection conn = DriverManager.getConnection(url, username, password);
	             PreparedStatement stmt = conn.prepareStatement("SELECT EXISTS (SELECT 1 FROM blue_check.patient_info WHERE id = ?)")) {
	            
	            stmt.setInt(1, num);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    exists = rs.getBoolean(1);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
			if(!exists) {
				newIDNumber = num;
			}else {
				randomIdNumber();
			}
			
		}
	
	//updates database with new user info
		 public static void AddPatient() throws SQLException {
			 
			 //Needs to add a part to check to make sure that all 
			 
			String url = "jdbc:mysql://localhost:3306/blue_check"; //jbdc:mysql:// is basic, localhost:3306 is the sever name or can be ip address if its on the web, jdbcdemo is the table name
			String username = "root";
			String password = "Gr33ndrake";
				
				randomIdNumber();
			 
			 Connection con = DriverManager.getConnection(url, username, password);
			 con.setAutoCommit(false);
			 
			 String q  = "INSERT INTO blue_check.patient_info(id, name, age, email, phone_number, username, password,gender)\r\n"
			 		+ "VALUES (?, ?, ?, ?, ?, ?, ?,?);\r\n"
			 		+ "";
			 
			 PreparedStatement pstmt =  con.prepareStatement(q);
			 
			 pstmt.setInt(1, newIDNumber);
			 pstmt.setString(2, NameTextField.getText().trim());
			 pstmt.setInt(3, Integer.parseInt(AgeTextField.getText().trim()));
			 pstmt.setString(4, EmailTextField.getText().trim());
			 pstmt.setInt(5, Integer.parseInt(PhoneTextField.getText().trim()));
			 pstmt.setString(6, UsernameTextField.getText().trim());
			 pstmt.setString(7, PasswordTextField.getText().trim());
			 pstmt.setString(8, GenderTextField.getText().trim());
			 
			 
			 pstmt.executeUpdate();
			 con.commit();
			 
			 System.out.println("Update Successful");
			 
		 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == CancelButton) {
			
			NameTextField.setVisible(false);
			AgeTextField.setVisible(false);
			GenderTextField.setVisible(false);
			EmailTextField.setVisible(false);
			PhoneTextField.setVisible(false);;
			UsernameTextField.setVisible(false);
			PasswordTextField.setVisible(false);
			
			lblNewLabel.setVisible(false);
			NameLabel.setVisible(false);
			AgeLabel.setVisible(false);
			GenderLabel.setVisible(false);
			EmailLabel.setVisible(false);
			PhoneNumberLabel.setVisible(false);
			UsernameLabel.setVisible(false);
			Password.setVisible(false);
			LoginInfoLabel.setVisible(false);
			
			CancelButton.setVisible(false);
			SubmitButton.setVisible(false);
			
			CancelMessage.setVisible(true);
			CancelMessage2.setVisible(true);
			
		}
		
if(e.getSource() == SubmitButton) {
	
			try {
				AddPatient();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			NameTextField.setVisible(false);
			AgeTextField.setVisible(false);
			GenderTextField.setVisible(false);
			EmailTextField.setVisible(false);
			PhoneTextField.setVisible(false);;
			UsernameTextField.setVisible(false);
			PasswordTextField.setVisible(false);
			
			lblNewLabel.setVisible(false);
			NameLabel.setVisible(false);
			AgeLabel.setVisible(false);
			GenderLabel.setVisible(false);
			EmailLabel.setVisible(false);
			PhoneNumberLabel.setVisible(false);
			UsernameLabel.setVisible(false);
			Password.setVisible(false);
			LoginInfoLabel.setVisible(false);
			
			CancelButton.setVisible(false);
			SubmitButton.setVisible(false);
			
			PatientAddedMessage.setVisible(true);
			CancelMessage2.setVisible(true);
			
			
		}
		
	}
}
