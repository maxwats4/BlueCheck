/***
 * 
 * Holds the code for the menu where patients are looked up. 
 * 
 */

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class Menu extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private JTextField searchTextField;
	JButton searchButton;
	static JComboBox<String> comboBox;
	static JList<String> list;
	public String[] list1 = {};
	static JButton getPatientInfoButton;
	static JButton addNewPatientButton;
	static JLabel MaxNumberWarning;
	public patientInfoFrame newWindow;
	public patientAddFrame newAddWindow;
	public JLabel EntryExplinationLabel;
	
 //Patient storage variables **Global Variables that can be used anywhere
	static String name;
	static int age;
	static String email;
	static String gender;
	static int id;
	static int phoneNumber;
	
// Data structures
	 static Stack<patient> currentPatientList = new Stack<patient>();
	 static String searchValue; // searches for what the user types in
	 static String category; //searches for what the users selects as a category
	 
	 
// current selected patient public info
	public static String currentPatientName;
	public static int currentPatientAge;
	public static int currentPatientID;
	public static String currentPatientGender; //need to finish these
	public static String currentPatientEmail; // need to finish these
	public static int currentPatientPhoneNumber;
	
	
	
	/**
	 * Create the panel.
	 */
	public Menu() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JPanel blueCheckPanel = new JPanel();
		blueCheckPanel.setLayout(null);
		blueCheckPanel.setBackground(new Color(0, 191, 255));
		blueCheckPanel.setBounds(10, 10, 191, 60);
		add(blueCheckPanel);
		
		JLabel lblNewLabel = new JLabel("Blue Check");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(28, 0, 179, 61);
		blueCheckPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(556, 21, 275, 116);
		add(panel);
		panel.setLayout(null);
		
		JLabel SearchPanelLabel = new JLabel("Search");
		SearchPanelLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		SearchPanelLabel.setBounds(106, 10, 77, 14);
		panel.add(SearchPanelLabel);
		
		searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchButton.setBounds(180, 85, 85, 21);
		searchButton.addActionListener(this);
		panel.add(searchButton);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(10, 85, 160, 22);
		panel.add(searchTextField);
		searchTextField.setColumns(10);
		
		JLabel searchByLabel = new JLabel("Search by: ");
		searchByLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchByLabel.setBounds(10, 37, 71, 21);
		panel.add(searchByLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Name", "ID Number", "Gender", "Age"}));
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBox.setBounds(104, 34, 161, 30);
		panel.add(comboBox);
		
		JPanel listPanel = new JPanel();
		listPanel.setBounds(191, 149, 640, 357);
		add(listPanel);
		listPanel.setLayout(null);
		
		list = new JList<String>();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(20);
		list.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = list1;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(41, 26, 573, 306);
		listPanel.add(list);
		
		MaxNumberWarning = new JLabel("All selected values will not fit on this window");
		MaxNumberWarning.setForeground(Color.RED);
		MaxNumberWarning.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MaxNumberWarning.setBounds(78, 10, 440, 13);
		MaxNumberWarning.setVisible(false);
		listPanel.add(MaxNumberWarning);
		
		EntryExplinationLabel = new JLabel("(NAME, AGE, ID)");
		EntryExplinationLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EntryExplinationLabel.setBounds(46, 3, 202, 21);
		listPanel.add(EntryExplinationLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 149, 171, 357);
		add(panel_1);
		panel_1.setLayout(null);
		
		getPatientInfoButton = new JButton("Get Patient Info");
		getPatientInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getPatientInfoButton.setBounds(10, 10, 153, 21);
		getPatientInfoButton.addActionListener(this);
		panel_1.add(getPatientInfoButton);
		
		addNewPatientButton = new JButton("Add New Patient");
		addNewPatientButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addNewPatientButton.setBounds(10, 39, 153, 21);
		addNewPatientButton.addActionListener(this);
		panel_1.add(addNewPatientButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == searchButton) {
			
			getListInfo();
			try {
				if(comboBox.getSelectedItem() == "Name") {getPatientsName(Login.getConnection(),searchValue);}
				if(comboBox.getSelectedItem() == "ID Number") {getPatientsID(Login.getConnection(),searchValue);}
				if(comboBox.getSelectedItem() == "Gender") {getPatientsGender(Login.getConnection(),searchValue);}
				if(comboBox.getSelectedItem() == "Age") {getPatientsAge(Login.getConnection(),searchValue);}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setViewerList();
			
		}
		
		if(e.getSource() == getPatientInfoButton) {
			
			getSelectedPatient();
			try {
				getSelectedPatientFromDB(Login.getConnection(), currentPatientID);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			getSelectedPatient();
			newWindow = new patientInfoFrame();
			newWindow.setVisible(true);
		}
		
		if(e.getSource() == addNewPatientButton) {
			newAddWindow = new patientAddFrame();
			newAddWindow.setVisible(true);
		}
		
		
		
	}
	
	//Gets the selected values from the use input
	 public static void getListInfo() {
		 
		 	category = comboBox.getSelectedItem().toString();
			System.out.println("Value = "+category);
			
			searchValue = searchTextField.getText();
			
			/*
			 * To change elements from the list you have
			 *  to just create a new list model and then 
			 *  set the list model to the new model
			 */
			DefaultListModel<String> model = new DefaultListModel<String>();
			model.addElement( "Default List" );
			model.addElement( "Default List 2" );
			list.setModel(model);
			
		 
	 }
	 /*
	  * 
	  * Getting patients by selected search type
	  * 
	  */
	//Gets patient information and stores it in a list
	 public static void getPatientsName(Connection con, String value) throws SQLException {
		 
		 String searchKey = value;
		 System.out.println("Function is getting hit. NAME = "+ value);
		 
		 String query = "SELECT *\r\n"
		    		+ "FROM blue_check.patient_info\r\n"
		    		+ "WHERE name = '"+searchKey+"' ORDER BY age DESC;"; //sql statement
		    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
		      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
		     while(rs.next()) {
		    	 name = rs.getString("name");
		    	System.out.println(name);
		    	age = rs.getInt("age");
		    	email = rs.getString("email");
		    	gender = rs.getString("gender");
		    	id = rs.getInt("id");
		    	phoneNumber = rs.getInt("phone_number");
		    	
		    	currentPatientList.add(new patient(name,id,email,0,age,null,null,gender));
		     }  
		    } catch (SQLException e) {
		    }
		  }
	 
	 
	//Gets patient information and stores it in a list
		 public static void getPatientsID(Connection con, String value) throws SQLException {
			 
			 System.out.println("Function is getting hit. ID = "+ value);
			 
			 String query = "SELECT *\r\n"
			    		+ "FROM blue_check.patient_info\r\n"
			    		+ "WHERE id = '"+value+"';"; //sql statement
			    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
			      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
			     while(rs.next()) {
			    	 name = rs.getString("name");
			    	System.out.println(name);
			    	age = rs.getInt("age");
			    	email = rs.getString("email");
			    	gender = rs.getString("gender");
			    	id = rs.getInt("id");
			    	phoneNumber = rs.getInt("phone_number");
			    	
			    	currentPatientList.add(new patient(name,id,email,0,age,null,null,gender));
			     }
			    } catch (SQLException e) {
			    }
			  }
	 
		//Gets patient information and stores it in a list
		 public static void getPatientsGender(Connection con, String value) throws SQLException {
			 
			 System.out.println("Function is getting hit. Gender = "+ value);
			 
			 String query = "SELECT *\r\n"
			    		+ "FROM blue_check.patient_info\r\n"
			    		+ "WHERE gender = '"+value+"';"; //sql statement
			    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
			      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
			     while(rs.next()) {
			    	 name = rs.getString("name");
			    	System.out.println(name);
			    	age = rs.getInt("age");
			    	email = rs.getString("email");
			    	gender = rs.getString("gender");
			    	id = rs.getInt("id");
			    	phoneNumber = rs.getInt("phone_number");
			    	
			    	currentPatientList.add(new patient(name,id,email,0,age,null,null,gender));
			     }
			    } catch (SQLException e) {
			    }
			  }
		 
		//Gets patient information and stores it in a list
		 public static void getPatientsAge(Connection con, String value) throws SQLException {
			 
			 String searchKey = value;
			 System.out.println("Function is getting hit. Age = "+ value);
			 
			 String query = "SELECT *\r\n"
			    		+ "FROM blue_check.patient_info\r\n"
			    		+ "WHERE age = '"+searchKey+"' ORDER BY name DESC;"; //sql statement
			    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
			      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
			     while(rs.next()) {
			    	 name = rs.getString("name");
			    	System.out.println(name);
			    	age = rs.getInt("age");
			    	email = rs.getString("email");
			    	gender = rs.getString("gender");
			    	id = rs.getInt("id");
			    	phoneNumber = rs.getInt("phone_number");
			    	
			    	currentPatientList.add(new patient(name,id,email,0,age,null,null,gender));
			     }  
			    } catch (SQLException e) {
			    }
			  }
		 
		 
		 
	 //sets the visable list on the program
	 public static void setViewerList() {
		System.out.println("The current patient list is updated");
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		if(currentPatientList.size() > 17) {MaxNumberWarning.setVisible(true);}
		
		while(!currentPatientList.isEmpty()){
			patient current = currentPatientList.pop();
			String listInput = current.getName() +" , "+ current.getAge() + " , " + current.getId();
			model.addElement(listInput);
		}
		
		list.setModel(model);
		
	 }
	 
	 
	 /*
	  * Breaks down the string returned from the selected patient on the list
	  * 
	  */
	 public static void getSelectedPatient() {
		
		 String input = list.getSelectedValue();
		 
		 char[] inputArray = input.toCharArray();
		 int[] commaLocations = new int[2];
		 
		 int counter = 0;
		 for(int i = 0; i < inputArray.length; i++) {
			 if(inputArray[i] == ',') {
				 commaLocations[counter] = i;
				 counter++;
			 }
		 }
		 
		  currentPatientName = input.substring(0,commaLocations[0]);
		  currentPatientAge = Integer.valueOf(input.substring(commaLocations[0]+1,commaLocations[1]).trim());
		  currentPatientID= Integer.valueOf( input.substring(commaLocations[1]+1,input.length()).trim());
		  currentPatientGender = gender;
		  currentPatientEmail = email;
		  currentPatientPhoneNumber = phoneNumber;
		  
		System.out.println("Current Selected Patient is below: ");
		System.out.println("name = " + currentPatientName);
		System.out.println("age = " + currentPatientAge);
		System.out.println("id = " + currentPatientID); 
		
		 System.out.println("Get Selected Patient Info is working");
		 System.out.println( list.getSelectedValue());
		 
	 }
	 
	//Gets selected patient information from the database based on the passed in ID
	 // Needs to be ran before getting selected patient information
	 public static void getSelectedPatientFromDB(Connection con, int id) throws SQLException {
		 
		
		 System.out.println("Function is getting hit. id = "+ id);
		 
		 String query = "SELECT *\r\n"
		    		+ "FROM blue_check.patient_info\r\n"
		    		+ "WHERE id = '"+id+"' ORDER BY name DESC;"; //sql statement
		    try (Statement stmt = con.createStatement()) { //returns true if a connection and statement is made i think
		      ResultSet rs = stmt.executeQuery(query); //sets the sql statement and runs it
		     while(rs.next()) {
		    	 name = rs.getString("name");
		    	System.out.println(name);
		    	age = rs.getInt("age");
		    	email = rs.getString("email");
		    	gender = rs.getString("gender");
		    	id = rs.getInt("id");
		    	phoneNumber = rs.getInt("phone_number");
		    	
		     }  
		    } catch (SQLException e) {
		    }
		  }
}
