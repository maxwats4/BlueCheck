/***
 * This holds the code for the panel displaying the selected patient information. 
 * 
 */

import javax.swing.JPanel;
import java.awt.Color;
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
import java.util.Stack;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class patientInfoPanel extends JPanel implements ActionListener{
	
	//bugs
	// large edited phonenumbers dont wort
	// Doesnt auto update when you submit the note - may be able to just create a whole new pannel and set it as component panel
	// need to trim login username and password
	// make the add patient window smaller 
	

	// global variables
	JButton EditUserButton;
	private JTextField IdTextField;
	static JLabel idPanelInfo;
	static JLabel emailPanelInfo;
	static JLabel phonePanelInfo;
	static JLabel namePanelInfo;
	static JLabel genderPanelInfo;
	static JLabel agePanelInfo;
	private static JTextField NameTextField;
	private static JTextField AgeTextField;
	private static JTextField GenderTextField;
	private static JTextField PhoneTextField;
	private static JTextField EmailTextField;
	JButton CancelButton;
	JButton SubmitButton;
	
	
	//Notes Variables
	public static JList<String> list;
	public String[] VisibleList = {};
	 static Stack<PatientNotes> currentPatientNotesList = new Stack<PatientNotes>();
	 static JLabel MaxNumberWarning;
	public static Date date; 
	public static String note; 
	public static String noteSubject; 
	public static int id; 
	public static String listSelectedValue; // the value that is clicked in the notes list
	public static JTextPane NoteTextPane;
	public static JButton EditButton;
	public static JButton SubmitNoteButton;
	private static JButton CancelEditButton;
	private static JLabel NoteHeaderLabel;
	private static JLabel NoteDateLabel;
	private JButton addNoteButton;
	public addNotesFrame newWindow;
	private JLabel DateExplination;
	private static JTextField EditedNoteSubject;
	private static JTextField EditedDate;
	private static int uniqueNoteID;
	
	 
	/**
	 * Create the panel.
	 */
	public patientInfoPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JPanel patientInfoPanel = new JPanel();
		patientInfoPanel.setBounds(10, 20, 312, 478);
		add(patientInfoPanel);
		patientInfoPanel.setLayout(null);
		
		JPanel PatientPhotoPanel = new JPanel();
		PatientPhotoPanel.setBackground(Color.PINK);
		PatientPhotoPanel.setBounds(10, 10, 282, 236);
		patientInfoPanel.add(PatientPhotoPanel);
		
		JLabel namePanel = new JLabel("Name: ");
		namePanel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		namePanel.setBounds(10, 295, 86, 27);
		patientInfoPanel.add(namePanel);
		
		agePanelInfo = new JLabel(Integer.toString(Menu.currentPatientAge));
		agePanelInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		agePanelInfo.setBounds(106, 318, 86, 27);
		patientInfoPanel.add(agePanelInfo);
		
		JLabel AgePanel = new JLabel("Age:");
		AgePanel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AgePanel.setBounds(10, 318, 86, 27);
		patientInfoPanel.add(AgePanel);
		
		namePanelInfo = new JLabel(Menu.currentPatientName);
		namePanelInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		namePanelInfo.setBounds(106, 295, 86, 27);
		patientInfoPanel.add(namePanelInfo);
		
		JLabel idPanel = new JLabel("ID: ");
		idPanel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idPanel.setBounds(10, 268, 86, 27);
		patientInfoPanel.add(idPanel);
		
		idPanelInfo = new JLabel(Integer.toString(Menu.currentPatientID));
		idPanelInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idPanelInfo.setBounds(106, 268, 86, 27);
		patientInfoPanel.add(idPanelInfo);
		
		JLabel phonePanel = new JLabel("Phone #: ");
		phonePanel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phonePanel.setBounds(10, 375, 86, 27);
		patientInfoPanel.add(phonePanel);
		
		phonePanelInfo = new JLabel(Integer.toString(Menu.currentPatientPhoneNumber));
		phonePanelInfo.setBounds(106, 375, 196, 27);
		patientInfoPanel.add(phonePanelInfo);
		phonePanelInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel emailPanel = new JLabel("Email: ");
		emailPanel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailPanel.setBounds(10, 399, 86, 27);
		patientInfoPanel.add(emailPanel);
		
		emailPanelInfo = new JLabel(Menu.currentPatientEmail);
		emailPanelInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailPanelInfo.setBounds(106, 399, 196, 27);
		patientInfoPanel.add(emailPanelInfo);
		
		JLabel genderPanel = new JLabel("Gender:");
		genderPanel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		genderPanel.setBounds(10, 343, 86, 27);
		patientInfoPanel.add(genderPanel);
		
		genderPanelInfo = new JLabel(Menu.currentPatientGender);
		genderPanelInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		genderPanelInfo.setBounds(106, 343, 86, 27);
		patientInfoPanel.add(genderPanelInfo);
		
		EditUserButton = new JButton("Edit Information");
		EditUserButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EditUserButton.setBounds(154, 448, 148, 27);
		EditUserButton.addActionListener(this);
		patientInfoPanel.add(EditUserButton);
		
		NameTextField = new JTextField();
		NameTextField.setText(Menu.currentPatientName);
		NameTextField.setBounds(96, 301, 96, 19);
		patientInfoPanel.add(NameTextField);
		NameTextField.setColumns(10);
		NameTextField.setVisible(false);
		
		AgeTextField = new JTextField();
		AgeTextField.setText(Integer.toString(Menu.currentPatientAge));
		AgeTextField.setBounds(96, 324, 96, 19);
		patientInfoPanel.add(AgeTextField);
		AgeTextField.setColumns(10);
		AgeTextField.setVisible(false);
		
		GenderTextField = new JTextField();
		GenderTextField.setText(Menu.currentPatientGender);
		GenderTextField.setBounds(96, 349, 96, 19);
		patientInfoPanel.add(GenderTextField);
		GenderTextField.setColumns(10);
		GenderTextField.setVisible(false);
		
		PhoneTextField = new JTextField();
		PhoneTextField.setText(Integer.toString(Menu.currentPatientPhoneNumber));
		PhoneTextField.setBounds(96, 381, 96, 19);
		patientInfoPanel.add(PhoneTextField);
		PhoneTextField.setColumns(10);
		PhoneTextField.setVisible(false);
		
		EmailTextField = new JTextField();
		EmailTextField.setText(Menu.currentPatientEmail);
		EmailTextField.setBounds(96, 405, 96, 19);
		patientInfoPanel.add(EmailTextField);
		EmailTextField.setColumns(10);
		EmailTextField.setVisible(false);
		
		CancelButton = new JButton("Cancel");
		CancelButton.setHorizontalAlignment(SwingConstants.LEFT);
		CancelButton.setBounds(89, 453, 85, 21);
		CancelButton.addActionListener(this);
		patientInfoPanel.add(CancelButton);
		CancelButton.setVisible(false);
		
		SubmitButton = new JButton("Submit");
		SubmitButton.setBounds(195, 453, 85, 21);
		SubmitButton.addActionListener(this);
		patientInfoPanel.add(SubmitButton);
		SubmitButton.setVisible(false);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(332, 115, 494, 383);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(8, 8, 124, 367);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		
		list = new JList<String>();
		list.setVisibleRowCount(20);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setBounds(10, 35, 104, 322);
		list.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = VisibleList;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		// Add a ListSelectionListener to the JList
		//method for when a list item is selected
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the selection is not adjusting (i.e., user finished selection)
                if (!e.getValueIsAdjusting()) {
                    // Get the selected item from the JList
                    listSelectedValue = list.getSelectedValue();
                    // Perform an action based on the selected item
                    
                    setNoteVisible();
                    
                    
                }
            }
        });
		panel_2.add(list);
		
		DateExplination = new JLabel("(YYYY-MM-DD)");
		DateExplination.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DateExplination.setBounds(10, 1, 114, 24);
		panel_2.add(DateExplination);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.ORANGE);
		panel_3.setBounds(140, 8, 346, 367);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		NoteHeaderLabel = new JLabel("Note Subject Line");
		NoteHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NoteHeaderLabel.setBounds(10, 5, 326, 19);
		NoteHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_3.add(NoteHeaderLabel);
		
		NoteDateLabel = new JLabel("12/12/2024");
		NoteDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NoteDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		NoteDateLabel.setBounds(20, 26, 302, 19);
		panel_3.add(NoteDateLabel);
		
		NoteTextPane = new JTextPane();
		NoteTextPane.setBounds(10, 55, 326, 302);
		NoteTextPane.setEditable(false);
		panel_3.add(NoteTextPane);
		
		EditedNoteSubject = new JTextField();
		EditedNoteSubject.setBounds(20, 7, 316, 19);
		EditedNoteSubject.setText(noteSubject);
		EditedNoteSubject.setVisible(false);
		panel_3.add(EditedNoteSubject);
		EditedNoteSubject.setColumns(10);
		
		EditedDate = new JTextField();
		EditedDate.setBounds(125, 27, 96, 19);
		EditedDate.setVisible(false);
		panel_3.add(EditedDate);
		EditedDate.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(334, 20, 494, 72);
		add(panel_1);
		panel_1.setLayout(null);
		
		EditButton = new JButton("Edit Note");
		EditButton.setBounds(10, 10, 115, 21);
		EditButton.addActionListener(this);
		panel_1.add(EditButton);
		
		SubmitNoteButton = new JButton("Submit Note");
		SubmitNoteButton.setBounds(10, 41, 115, 21);
		SubmitNoteButton.setVisible(false);
		SubmitNoteButton.addActionListener(this);
		panel_1.add(SubmitNoteButton);
		
		CancelEditButton = new JButton("Cancel Edit");
		CancelEditButton.setBounds(135, 41, 115, 21);
		CancelEditButton.setVisible(false);
		CancelEditButton.addActionListener(this);
		panel_1.add(CancelEditButton);
		
		addNoteButton = new JButton("Create New Note");
		addNoteButton.setBounds(135, 10, 136, 21);
		addNoteButton.addActionListener(this);
		panel_1.add(addNoteButton);
		
		MaxNumberWarning = new JLabel("All selected notes will not fit on this window");
		MaxNumberWarning.setBounds(332, 102, 502, 13);
		add(MaxNumberWarning);
		MaxNumberWarning.setForeground(Color.RED);
		MaxNumberWarning.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MaxNumberWarning.setVisible(false);
		
		//Sets the notes list
		setList();
	}

	
	
	
	
	 
	 /**
	  * 
	  * Methods for the notes
	  */
	
	// main method for all notes activities
	public static void setList() {
		//gets and updates the notes list
				try {
					getNotes(getConnection());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setViewedNotesList();

	}

	 
	 //list test method
	 public static void testList() {
		 
			DefaultListModel<String> model = new DefaultListModel<String>();
			model.addElement( "Default List" );
			model.addElement( "Default List 2" );
			list.setModel(model);
	 }
	 
	 //Method that pulls notes from DB and adds all the notes to a stack
		 public static void getNotes(Connection con) throws SQLException {
			 
			 String query = "SELECT *\r\n"
			    		+ "FROM blue_check.patient_notes\r\n"
			    		+ "WHERE id = "+Menu.currentPatientID+" ORDER BY date ASC;"; //sql statement
			    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
			      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
			     while(rs.next()) {
			    	id = rs.getInt("id");
			    	noteSubject = rs.getString("patient_note_subject");
			    	note = rs.getString("patient_note");
			    	date = rs.getDate("date");
			
			    	
			    	currentPatientNotesList.add(new PatientNotes(date,note, noteSubject,id));
			     }  
			    } catch (SQLException e) {
			    }
			 
			  }
		 
		//gets the selected note based on id and date, assuming there is only 1 note per day
		 public static void getSelectedNoteText(Connection con) throws SQLException {
			 
			 System.out.print("getNotes is getting hit");
			 String query = "SELECT *\r\n"
			    		+ "FROM blue_check.patient_notes\r\n"
			    		+ "WHERE id = "+Menu.currentPatientID+" AND date ='"+listSelectedValue+"'  ORDER BY date ASC;"; //sql statement
			    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
			      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
			     while(rs.next()) {
			    	id = rs.getInt("id");
			    	noteSubject = rs.getString("patient_note_subject");
			    	note = rs.getString("patient_note");
			    	date = rs.getDate("date");
			    	uniqueNoteID = rs.getInt("noteId");
			
			     }  
			    } catch (SQLException e) {
			    }
			    
			    NoteHeaderLabel.setText(noteSubject);
			    NoteDateLabel.setText(date.toString());
			    EditedDate.setText(date.toString());
				EditedNoteSubject.setText(noteSubject);
				NoteTextPane.setText(note);
			  }
	 
	//sets the visable Notes list on the program
		 public static void setViewedNotesList() {
			
			DefaultListModel<String> model = new DefaultListModel<String>();
			
			if(currentPatientNotesList.size() > 17) {MaxNumberWarning.setVisible(true);}
			
			while(!currentPatientNotesList.isEmpty()){
				PatientNotes current = currentPatientNotesList.pop();
				String listInput = current.getDate();
				model.addElement(listInput);
			}
			
			list.setModel(model);
			
		 }
		 
		 
		 //method to place the text on the panel
		 
		 public static void setNoteVisible() {
		
			 
			 try {
				getSelectedNoteText(getConnection());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			 
		
		 }
		 
		 // method to be able to edit a note
		 public static void editNote() throws SQLException, ParseException {
			 
			 String url = "jdbc:mysql://localhost:3306/blue_check"; //jbdc:mysql:// is basic, localhost:3306 is the sever name or can be ip address if its on the web, jdbcdemo is the table name
				String username = "root";
				String password = "Gr33ndrake";
			 
			 Connection con = DriverManager.getConnection(url, username, password);
			 con.setAutoCommit(false);
			 
			 String q  = "UPDATE blue_check.patient_notes \r\n"
			 		+ "SET patient_note = ?, patient_note_subject = ?, date = ?\r\n"
			 		+ "WHERE id = ? AND noteId = ?;";
			 
			 PreparedStatement pstmt =  con.prepareStatement(q);
			 
			 
			 // Converts date to SQL date format
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 java.util.Date utilDate = dateFormat.parse(EditedDate.getText());
			 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			 
			 pstmt.setString(1, NoteTextPane.getText());
			 pstmt.setString(2, EditedNoteSubject.getText());
			 pstmt.setDate(3, sqlDate);
			 pstmt.setInt(4, id);
			 pstmt.setInt(5, uniqueNoteID);
			
			 
			 pstmt.executeUpdate();
			 con.commit();
			 
			 
		 }
		 
		 
		 
		 //method to be able to add a note
		 

		 /**
		  * Methods for database functions
		  * 
		  * @return
		  * @throws Exception
		  */
		 
		//refreshes the displayed patient info
		 public static void getPatientsID(Connection con) throws SQLException {
			 
			 System.out.println("Function is getting hit. ID = "+ Menu.currentPatientID);
			 
			 String query = "SELECT *\r\n"
			    		+ "FROM blue_check.patient_info\r\n"
			    		+ "WHERE id = '"+Menu.currentPatientID+"';"; //sql statement
			    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
			      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
			     while(rs.next()) {
			    	 Menu.currentPatientName = rs.getString("name");
			    	Menu.currentPatientAge = rs.getInt("age");
			    	Menu.currentPatientEmail = rs.getString("email");
			    	Menu.currentPatientGender = rs.getString("gender");
			    	int filler = rs.getInt("id");
			    	Menu.currentPatientPhoneNumber = rs.getInt("phone_number");
			    	System.out.println("phone: " + Menu.currentPatientPhoneNumber);
			    	
			     }
			    } catch (SQLException e) {
			    }
			    
				emailPanelInfo.setText(Menu.currentPatientEmail);
				phonePanelInfo.setText(Integer.toString(Menu.currentPatientPhoneNumber));
				namePanelInfo.setText(Menu.currentPatientName);
				genderPanelInfo.setText(Menu.currentPatientGender);
				agePanelInfo.setText(Integer.toString(Menu.currentPatientAge));
			    
			  }
	
	//connects to the database
		public static Connection getConnection() throws Exception{
			
			try {
				String url = "jdbc:mysql://localhost:3306/blue_check"; //jbdc:mysql:// is basic, localhost:3306 is the sever name or can be ip address if its on the web, jdbcdemo is the table name
				String username = "root";
				String password = "Gr33ndrake";
				 
				Connection conn = DriverManager.getConnection(url,username,password);
				return conn;
			
			}catch(Exception e) {
				System.out.println(e);
				
			}
			
			return null;
		}
		
	
	//updates database with new user info
	 public static void updateRow() throws SQLException {
		 
		 String url = "jdbc:mysql://localhost:3306/blue_check"; //jbdc:mysql:// is basic, localhost:3306 is the sever name or can be ip address if its on the web, jdbcdemo is the table name
			String username = "root";
			String password = "Gr33ndrake";
		 
		 Connection con = DriverManager.getConnection(url, username, password);
		 con.setAutoCommit(false);
		 
		 String q  = "UPDATE blue_check.patient_info\r\n"
		 		+ "SET name = ?, age  = ?, email = ?, phone_number = ?, gender = ?\r\n"
		 		+ "WHERE id = ?;";
		 
		 PreparedStatement pstmt =  con.prepareStatement(q);
		 
		 pstmt.setString(1, NameTextField.getText().trim());
		 pstmt.setInt(2, Integer.parseInt(AgeTextField.getText().trim()));
		 pstmt.setString(3, EmailTextField.getText().trim());
		 pstmt.setInt(4, Integer.parseInt(PhoneTextField.getText().trim()));
		 pstmt.setString(5, GenderTextField.getText().trim());
		 pstmt.setInt(6, Menu.currentPatientID);
		 
		 pstmt.executeUpdate();
		 con.commit(); 
	 }
	 
	 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == EditUserButton) {
			System.out.println("It works");
			emailPanelInfo.setVisible(false);
			phonePanelInfo.setVisible(false);
			namePanelInfo.setVisible(false);
			genderPanelInfo.setVisible(false);
			agePanelInfo.setVisible(false);
			EditUserButton.setVisible(false);
			
			EmailTextField.setVisible(true);
			PhoneTextField.setVisible(true);
			NameTextField.setVisible(true);
			AgeTextField.setVisible(true);
			GenderTextField.setVisible(true);
			CancelButton.setVisible(true);
			SubmitButton.setVisible(true);
			
		}
		
		if(e.getSource() == CancelButton) {
			System.out.println("It works");
			idPanelInfo.setVisible(true);
			emailPanelInfo.setVisible(true);
			phonePanelInfo.setVisible(true);
			namePanelInfo.setVisible(true);
			genderPanelInfo.setVisible(true);
			agePanelInfo.setVisible(true);
			EditUserButton.setVisible(true);
			
			EmailTextField.setVisible(false);
			PhoneTextField.setVisible(false);
			NameTextField.setVisible(false);
			AgeTextField.setVisible(false);
			GenderTextField.setVisible(false);
			CancelButton.setVisible(false);
			SubmitButton.setVisible(false);
			
		}
		
		if(e.getSource() == SubmitButton) {
			System.out.println("It works");

	        try {

	            // Call the updateRow function
	            updateRow();
	            getPatientsID(getConnection());
	        } catch (SQLException e1) {
	            e1.printStackTrace(); // Handle the exception appropriately (logging, throwing, etc.)
	        } catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			idPanelInfo.setVisible(true);
			emailPanelInfo.setVisible(true);
			phonePanelInfo.setVisible(true);
			namePanelInfo.setVisible(true);
			genderPanelInfo.setVisible(true);
			agePanelInfo.setVisible(true);
			EditUserButton.setVisible(true);
			
			EmailTextField.setVisible(false);
			PhoneTextField.setVisible(false);
			NameTextField.setVisible(false);
			AgeTextField.setVisible(false);
			GenderTextField.setVisible(false);
			CancelButton.setVisible(false);
			SubmitButton.setVisible(false);
		
		}
		

		if(e.getSource() == SubmitNoteButton) {
			try {
				try {
					editNote();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			NoteHeaderLabel.setText(noteSubject);
		    NoteDateLabel.setText(date.toString());
		    EditedDate.setText(date.toString());
			EditedNoteSubject.setText(noteSubject);
			NoteTextPane.setText(note);
			setList();
			SubmitNoteButton.setVisible(false);
			NoteTextPane.setEditable(false);
			EditButton.setVisible(true);
			CancelEditButton.setVisible(false);
			EditedDate.setVisible(false);
			EditedNoteSubject.setVisible(false);
			NoteHeaderLabel.setVisible(true);
			NoteDateLabel.setVisible(true);
		
			
		}
		
		if(e.getSource() == EditButton) {
			System.out.println("edit button works");
			SubmitNoteButton.setVisible(true);
			NoteTextPane.setEditable(true);
			EditButton.setVisible(false);
			CancelEditButton.setVisible(true);
			EditedNoteSubject.setVisible(true);
			EditedDate.setVisible(true);
			NoteHeaderLabel.setVisible(false);
			NoteDateLabel.setVisible(false);
		}
		
		if(e.getSource() == CancelEditButton) {
			SubmitNoteButton.setVisible(false);
			NoteTextPane.setEditable(false);
			EditButton.setVisible(true);
			CancelEditButton.setVisible(false);
			NoteHeaderLabel.setVisible(true);
			NoteDateLabel.setVisible(true);
			EditedNoteSubject.setVisible(false);
			EditedDate.setVisible(false);
		}
		
		if(e.getSource() == addNoteButton) {
			newWindow = new addNotesFrame();
			newWindow.setVisible(true);
		}
		
	}
}
