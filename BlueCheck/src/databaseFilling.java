import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


/**
 * These methods were used to fill the database with fake values. Purely setup
 * 
 * 
 * @author Max Watson
 *
 */
public class databaseFilling {
	
	static int id;
	static String name;
	static int age;
	static String email;
	static int phone;
	static String username;
	static String password;
	static String gender;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			for(int i = 0;i < 100; i++) {
				//setValues();
				try {
					addToTable();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
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
	
	//adds to database a new user
			public static void addToTable() throws Exception{
				String statement = "insert into blue_check.patient_info(id,name,age,email,phone_number,username,password,gender)\r\n"
						+ "values("+id+", '"+name+"',"+age+", '"+email+"',"+phone+", '"+username+"', '"+password+"', '"+gender+"');";
				try {
					Connection conn = getConnection(); //establishes connectionmac
					PreparedStatement insert = conn.prepareStatement(statement); //sql statement that the sever does
					insert.execute();//executes the prepared statment
				}catch(Exception e) {
					System.out.println(e);
				}finally {
					System.out.println("Function complete.");
					
				}
				
			}
			
			
			public static void setValues() {
				
				Random rand = new Random();
				String[] names = {"Max", "Mary", "Tyson", "Jenny", "Emma", "Shad", "Luke", "Jarom", "Greg", "Karen", "Matt", "Sally", "Tim", "Carly", "Hannah","Romy"
						,"Jared","April","Brinlee","Ella","Jude","Mark","Luke","John","Roman","River","Water","Owen","James","Barret","Nash","Quincey","Barbara","Yellow"
						,"Moses"};
				
				String[] genders = {"Male", "Female"};

				// Obtain a number between [0 - 49].
				int n = rand.nextInt(999999);
				int nameNumber =rand.nextInt(35); 
				
				id = n;
				name = names[nameNumber];
				age = rand.nextInt(99);
				email = null;
				phone = rand.nextInt(9999999);
				username = null;
				password = null;
				gender = genders[rand.nextInt(1)];
				
				
			}
	 
	
	/**
	 * 
	 * 
	 */
	

}
