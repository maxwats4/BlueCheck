import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JButton;

public class addNotesPanel extends JPanel implements ActionListener {
	private static JTextField SubjectTextField;
	private static JTextField DateTextField;
	public static JEditorPane editorPane;
	public static JButton SubmitButton;
	public static JLabel SubjectLabel;
	public static JLabel DateLabel; 
	public static JLabel HeaderLabel; 
	public static int newNoteID;
	public static Date date;
	private JLabel SafeToExitLabel;
	private JLabel SafeToExit2;

	/**
	 * Create the panel.
	 */
	public addNotesPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(42, 104, 399, 316);
		add(editorPane);
		
		SubjectLabel = new JLabel("Subject: ");
		SubjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		SubjectLabel.setBounds(42, 44, 67, 28);
		add(SubjectLabel);
		
		SubjectTextField = new JTextField();
		SubjectTextField.setBounds(119, 52, 322, 19);
		add(SubjectTextField);
		SubjectTextField.setColumns(10);
		
		DateLabel = new JLabel("Date: (YYYY-MM-DD)");
		DateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DateLabel.setBounds(42, 75, 209, 19);
		add(DateLabel);
		
		DateTextField = new JTextField();
		DateTextField.setColumns(10);
		DateTextField.setBounds(178, 75, 263, 19);
		add(DateTextField);
		
		SubmitButton = new JButton("Submit");
		SubmitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		SubmitButton.setBounds(356, 430, 85, 21);
		SubmitButton.addActionListener(this);
		add(SubmitButton);
		
		HeaderLabel = new JLabel("Create New Note Form");
		HeaderLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		HeaderLabel.setBounds(102, 10, 284, 24);
		add(HeaderLabel);
		
		SafeToExitLabel = new JLabel("Note Has Been Saved");
		SafeToExitLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SafeToExitLabel.setBounds(119, 122, 263, 79);
		SafeToExitLabel.setVisible(false);
		add(SafeToExitLabel);
		
		SafeToExit2 = new JLabel("You May Exit This Window");
		SafeToExit2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SafeToExit2.setBounds(152, 211, 230, 28);
		SafeToExit2.setVisible(false);
		add(SafeToExit2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == SubmitButton) {
			try {
				getNextNoteId(patientInfoPanel.getConnection());
				addNoteToDB();
				patientInfoPanel.setList();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			SafeToExit2.setVisible(true);
			SafeToExitLabel.setVisible(true);
			
			SubjectTextField.setVisible(false);
			DateTextField.setVisible(false);
			editorPane.setVisible(false);
			SubmitButton.setVisible(false);
			SubjectLabel.setVisible(false);
			DateLabel.setVisible(false); 
			HeaderLabel.setVisible(false); 
			
			
		}
		
	}
	
	 public static void addNoteToDB() throws SQLException, ParseException {
		 
		 //Add a part to check to make sure that the Date format is correct
		 
		 String url = "jdbc:mysql://localhost:3306/blue_check"; //jbdc:mysql:// is basic, localhost:3306 is the sever name or can be ip address if its on the web, jdbcdemo is the table name
			String username = "root";
			String password = "Gr33ndrake";
			
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 // Parse the string into a java.util.Date object
        java.util.Date utilDate = dateFormat.parse(DateTextField.getText());

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		System.out.println("Date: "+date);
		 
		 Connection con = DriverManager.getConnection(url, username, password);
		 con.setAutoCommit(false);
		 
		 String q  = "INSERT INTO blue_check.patient_notes (id, patient_note_subject, patient_note, date, noteId)\r\n"
		 		+ "VALUES (?, ?, ?, ?, ?);";
		 
		 PreparedStatement pstmt =  con.prepareStatement(q);
		 
		 
		 pstmt.setInt(1, Menu.currentPatientID);
		 pstmt.setString(2, SubjectTextField.getText());
		 pstmt.setString(3, editorPane.getText());
		 pstmt.setDate(4, sqlDate);
		 pstmt.setInt(5, newNoteID);
		
		 pstmt.executeUpdate();
		 con.commit();
		 
		 
	 }
	 
	 
	 // function to get the highest noteId
	//gets the selected note based on id and date, assuming there is only 1 note per day
	 public static void getNextNoteId(Connection con) throws SQLException {
		 
		 String query = "SELECT MAX(noteId) AS highest_number\r\n"
		 		+ "FROM blue_check.patient_notes;"; //sql statement
		    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
		      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
		     while(rs.next()) {
		    	newNoteID = rs.getInt("highest_number");
		    	System.out.println(newNoteID);
		    
		
		     }  
		    } catch (SQLException e) {
		    }
		    
		    newNoteID += 1;
		  }
	 
	 		
 
	
	
}
