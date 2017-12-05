import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInstallation 
{
	static Connection conn = null;
	static ResultSet rs = null;
	
	public static void main(String[] args) 
	{
		DBInstallation myDb = new DBInstallation();
		myDb.connect();
		myDb.createDB();
		myDb.createTable();
	}
	
	public void connect()
	{
		MainMenu myMainMenu = new MainMenu();
		
		String connectionURL = "jdbc:mysql://localhost";	//database connection url
		String username = "root";	//database username
		String password = "password1234";	//database password
	
		try 
		{
			conn = DriverManager.getConnection(connectionURL, username, password);	//trying to connect to database
			System.out.println("Connection established");
			myMainMenu.createMainWindow();	//once connection is establish successfully, the method to create the main menu is called
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Could not establish connection");
		}
	}
	
	public void createDB()
	{
		Statement s;
		try 
		{
			s = conn.createStatement();
			s.executeUpdate("CREATE DATABASE dbAlumni");
			System.out.println("Database created");
		} 
		catch (SQLException e) 
		{
			System.out.println("Could not create database");
			e.printStackTrace();
		}
		
	}
	
	public void createTable()
	{
		String connectionURL = "jdbc:mysql://localhost/dbAlumni";	//database connection url
		String username = "root";	//database username
		String password = "password1234";	//database password
		
		//the following code creates a table with columns specified by the client
		try 
		{
			conn = DriverManager.getConnection(connectionURL, username, password);	//trying to connect to database created above
			System.out.println("Connection 2 established");
			
			Statement s = conn.createStatement();
			s.execute("CREATE TABLE alumni" + "(alumni_id int, FirstName varchar(50), LastName varchar(50), Gender varchar(50), Major varchar(50),"
					+ "Email varchar(80), GraduationDate int, College varchar(50), PhoneNumber varchar(12), Website varchar(50), Employer varchar(50), InternshipOffered varchar(5),"
					+ "CharityMatching varchar(5))");	
			System.out.println("Created alumni table.");
		} 
		catch (SQLException e) 
		{
			System.out.println("Could not create alumni table.");
			e.printStackTrace();
		}
	}
}
