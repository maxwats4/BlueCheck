import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class editPatientsPanel extends JPanel {
	private JTextField IdTextBox;
	private JTextField NameTextField;
	private JTextField AgeTextField;
	private JTextField EmailTextField;
	private JTextField PhoneNumberTextField;

	/**
	 * Create the panel.
	 */
	public editPatientsPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JButton SubmitButton = new JButton("Submit Changes");
		SubmitButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SubmitButton.setBounds(38, 264, 167, 42);
		add(SubmitButton);
		
		IdTextBox = new JTextField();
		IdTextBox.setText(Integer.toString(Menu.currentPatientID));
		IdTextBox.setBounds(109, 51, 96, 19);
		add(IdTextBox);
		IdTextBox.setColumns(10);
		
		JLabel NameLabel = new JLabel("Name: ");
		NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NameLabel.setBounds(38, 87, 61, 13);
		add(NameLabel);
		
		JLabel IDLabel_1 = new JLabel("ID: ");
		IDLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		IDLabel_1.setBounds(38, 52, 45, 13);
		add(IDLabel_1);
		
		JLabel AgeLabel = new JLabel("Age");
		AgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AgeLabel.setBounds(38, 125, 61, 18);
		add(AgeLabel);
		
		NameTextField = new JTextField();
		NameTextField.setText(Menu.currentPatientName);
		NameTextField.setColumns(10);
		NameTextField.setBounds(109, 86, 96, 19);
		add(NameTextField);
		
		AgeTextField = new JTextField();
		AgeTextField.setText(Integer.toString(Menu.currentPatientAge));
		AgeTextField.setColumns(10);
		AgeTextField.setBounds(109, 124, 96, 19);
		add(AgeTextField);
		
		JLabel EmailLabel = new JLabel("Email: ");
		EmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EmailLabel.setBounds(38, 164, 61, 18);
		add(EmailLabel);
		
		EmailTextField = new JTextField();
		EmailTextField.setText(Menu.currentPatientEmail);
		EmailTextField.setColumns(10);
		EmailTextField.setBounds(109, 166, 96, 19);
		add(EmailTextField);
		
		JLabel PhoneNumberLabel = new JLabel("Phone #: ");
		PhoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PhoneNumberLabel.setBounds(38, 200, 81, 18);
		add(PhoneNumberLabel);
		
		PhoneNumberTextField = new JTextField();
		PhoneNumberTextField.setText(Integer.toString(Menu.currentPatientPhoneNumber));
		PhoneNumberTextField.setColumns(10);
		PhoneNumberTextField.setBounds(109, 202, 96, 19);
		add(PhoneNumberTextField);

	}
}
