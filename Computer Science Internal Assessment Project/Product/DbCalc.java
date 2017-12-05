import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCalc extends DbMethods
{
	
	public int getId()
	{
		int alumniId = 0;

		try
		{
			//The following code gets the row number of the database
			Statement rowCount = conn.createStatement();
	
			ResultSet rs = rowCount.executeQuery("SELECT * FROM alumni");
			
		    while (rs.next()) 
		    {
		    	//System.out.println(alumniId);
		    	alumniId++;
		    }  
		}
		catch(SQLException f)
		{
			f.printStackTrace();
		}
		
    	//System.out.println(alumniId);
		return alumniId;
	}
	
	public void adjustId(int removedId)
	{
		try
		{
			//The following code gets the row number of the database
			Statement rowCount = conn.createStatement();
			
			ResultSet rs = rowCount.executeQuery("SELECT * FROM alumni");
			
		    while (rs.next()) 
		    {
		    	int id = rs.getInt("alumni_id");
		    	
		    	String newInt = "" + (id - 1) + "";
		    	
		    	if(id > removedId)
		    	{
		    		updateValue(id, "alumni_id", newInt);
		    	}
		    }
		}
		catch(SQLException f)
		{
			f.printStackTrace();
		}
	
	}
}
