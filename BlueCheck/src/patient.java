/**
 * 
 * Patient class
 * 
 * @author Max Watson
 *
 */

public class patient {

	private String name; 
	private int id;
	private String email;
	private int phoneNumber;
	private int age;
	private String username;
	private String password;
	private String gender;
	
	public patient(String name, int id, String email, int phoneNumber, int age, String username, String password, String gender) {
		this.setName(name);
		this.setId(id);
		this.setEmail(email);
		this.setPassword(password);
		this.setUsername(username);
		this.setPhoneNumber(phoneNumber);
		this.setGender(gender);
		this.setAge(age);
	}
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
