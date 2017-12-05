//Max Sechelski 12/19/2016
//The following code contains multiple methods that interact with the database

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class DbMethods
{
	static Connection conn = null;
	static ResultSet rs = null;
	
	public static void connectionDb()
	{
		MainMenu myMainMenu = new MainMenu();
		
		String connectionURL = "jdbc:mysql://localhost/dbAlumni";	//database connection url
		String username = "root";	//database username
		String password = "Tombo4321";	//database password
	
		try 
		{
			conn = DriverManager.getConnection(connectionURL, username, password);	//trying to connect to database
			System.out.println("Connection established to alumni database!");
			//deleteTable();
			//tableCreate();
			myMainMenu.createMainWindow();	//once connection is establish successfully, the method to create the main menu is called
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Status1");
		}
	}
	
	public static void tableCreate()
	{
		//the following code creates a table with columns specified by the client
		try 
		{
			Statement s = conn.createStatement();
			s.execute("CREATE TABLE alumni" + "(alumni_id int, FirstName varchar(50), LastName varchar(50), Gender varchar(50), Major varchar(50),"
					+ "Email varchar(80), GraduationDate int, College varchar(50), PhoneNumber varchar(12), Website varchar(50), Employer varchar(50), InternshipOffered varchar(5),"
					+ "CharityMatching varchar(5))");	
			System.out.println("Created alumni table.");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();	
		}
	}
	
	//the following method inserts values given to it into the table
	public static void tableInsert(int alumniID, String firstName, String secondName,  
								   String gender, String major, String email, 
								   int graduationDate, String alumniCollege, String phoneNumber, String website,
								   String employmentFirm, String employmentInternship, String charityMatching)	
	{
		try 
		{
			Statement s = conn.createStatement();
			System.out.println("Inserting alumni...");
			s.executeUpdate("INSERT INTO alumni " + "VALUES('" + alumniID + "','"+ firstName + "','"+ secondName + 
															"','" + gender + "','" + major + "','" + email +  
															"','" + graduationDate + "','" + alumniCollege + "','" + phoneNumber + "','" + website + 
															"','" + employmentFirm + "','" + employmentInternship + "','" + charityMatching+ "')");
			System.out.println("Alumni inserted.");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void csvInsert(ArrayList input)
	{
		DbCalc myId = new DbCalc();
		
		Iterator myIterator = input.iterator();
		
		ArrayList holder = new ArrayList();
		
		while(myIterator.hasNext())
		{
			String firstNameHolder = " ";
			String secondNameHolder = " ";
			String genderHolder = " ";
			String majorHolder = " ";
			String emailHolder = " ";
			int dateHolder = 0;
			String alumniCollegeHolder = " ";
			String phoneNumberHolder = " ";
			String websiteHolder = " ";
			String employmentFirmHolder = " ";
			String employmentInternshipHolder = " ";
			String charityMatchingHolder = " ";
			
			int alumniId = myId.getId();
			
			holder = (ArrayList)myIterator.next();
			
			System.out.println("Here LOL");

			for(int i = 0; i < 12; i++)
			{
				switch(i)
				{
				case 1:
					if (holder.get(0) == null)
					{
						holder.set(0, " ");
					}
					System.out.println("Here");
					
					firstNameHolder = (String)holder.get(0);
					System.out.println(firstNameHolder);
					break;
				case 2:
					if (holder.get(1) == null)
					{
						holder.set(1, " ");
					}
					
					secondNameHolder = (String)holder.get(1);
					break;
				case 3:
					if (holder.get(2) == null)
					{
						holder.set(2, " ");
					}
					
					genderHolder = (String)holder.get(2);
					break;
				case 4:
					if (holder.get(3) == null)
					{
						holder.set(3, " ");
					}
					
					majorHolder = (String)holder.get(3);
					break;
				case 5:
					if (holder.get(4) == null)
					{
						holder.set(4, " ");
					}
					
					emailHolder = (String)holder.get(4);
					break;
				case 6:
					/*if (holder.get(5) == null)
					{
						holder.set(5, "0");
					}*/
					try
					{
					dateHolder = Integer.parseInt((String)holder.get(5));
					}
					catch(NumberFormatException e)
					{
						dateHolder = 0;
					}	
					break;
				case 7:
					if (holder.get(6) == null)
					{
						holder.set(6, " ");
					}
					
					alumniCollegeHolder = (String)holder.get(6);
					break;
				case 8:
					if (holder.get(7) == null)
					{
						holder.set(7, " ");
					}
					
					phoneNumberHolder = (String)holder.get(7);
					break;
				case 9:
					if (holder.get(8) == null)
					{
						holder.set(8, " ");
					}
					
					websiteHolder = (String)holder.get(8);
					break;
				case 10:
					if (holder.get(9) == null)
					{
						holder.set(9, " ");
					}
					
					employmentFirmHolder = (String)holder.get(9);
					break;
				case 11:
					if (holder.get(10) == null)
					{
						holder.set(10, " ");
					}
					
					employmentInternshipHolder = (String)holder.get(10);
					break;
				case 12:
					if (holder.get(11) == null)
					{
						holder.set(11, " ");
					}
					
					charityMatchingHolder = (String)holder.get(11);
					break;
				}
			}
				
			tableInsert(alumniId, firstNameHolder , secondNameHolder ,  genderHolder , majorHolder , emailHolder , dateHolder, alumniCollegeHolder, phoneNumberHolder, websiteHolder, employmentFirmHolder, employmentInternshipHolder, charityMatchingHolder);
 		}
		
	}
	
	//the following method deletes values from the table
	public static void deleteValue(int userValue)	
	{
		try 
		{
			Statement s = conn.createStatement();
	
			System.out.println("Deleteing values...");
			s.executeUpdate("DELETE FROM alumni WHERE alumni_id=" + userValue +";");
			System.out.println("Values deleted.");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//the following method deletes the table
	public static void deleteTable()
	{
		try 
		{
			int userValue;
			Scanner myScanner = new Scanner(System.in);
			Statement s = conn.createStatement();
			
			System.out.println("Type one if you want to delete the table, 2 of you don't.");
			userValue = myScanner.nextInt();
			if(userValue == 1)
			{
				System.out.println("Deleteing table...");
				s.executeUpdate("DROP TABLE alumni");
				System.out.println("table deleted.");
			}
			else
				System.out.println("You have choosen not to delete.");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//the following method updates a value of the table that is designated by its input
	public static void updateValue(int userSelectValue, String entryDetermination, String entryValue)
	{
		//userSelectValue is the id value that determines which entry is being updated
		//entryDetermination is the column designator
		//entryValue is the new information that the entry is being updated to
		
		try 
		{
			Statement s = conn.createStatement();
			System.out.println("Updating value");
			s.executeUpdate("UPDATE alumni SET " + entryDetermination + "='"+ entryValue + "' WHERE alumni_id=" + userSelectValue +";");
			System.out.println("value updated.");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
