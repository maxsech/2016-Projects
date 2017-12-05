//Max Sechelski 12/19/2016
//The following code packages data from the database into two ArrayLists, one for column names and one for data
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableCreate extends DbMethods 
{	
	//The following method packages data of all alumni into ArrayLists and returns an object of DataTable that stores those ArrayLists
	public static DataTable graphicTableSetup() throws SQLException, ClassNotFoundException
	{
		//I Should be here
	   	 ArrayList<String> columnNames = new ArrayList<String>();
	   	 ArrayList<Object> data = new ArrayList<Object>();
	   	 Statement s2 = conn.createStatement();
	   	 
	   	 String sql = "SELECT * FROM alumni";
	   	 ResultSet rs = s2.executeQuery(sql);
	   	 
	   	 //Finding the amount of columns
	   	 ResultSetMetaData md = rs.getMetaData();
	   	 int columns = md.getColumnCount();

	   	 //Populating the ArrayList for column names
	   	 for (int i = 1; i <= columns; i++)
	   	 {
	   		 columnNames.add( md.getColumnName(i) );
	   	 }
	   	 
	   	 //Populating the ArrayList for data
	   	 while (rs.next())
	   	 {
	   		 ArrayList<Object> rowCrawler = new ArrayList<Object>(columns);

	   		 for (int i = 1; i <= columns; i++)
	   		 {
	   			 rowCrawler.add( rs.getObject(i) );
	   		 }
	   		 
	   		 data.add(rowCrawler);
	   	 }
	   	 
	   	 //returning an object of type DataTable that stores the ArrayLists created above
	   	 return new DataTable(columnNames, data);
	}
	
	//The following method packages data of alumni with a certain column
	public static DataTable graphicTableSetup(String column, String filterChoice) throws SQLException, ClassNotFoundException
	{
	   	 ArrayList<String> columnNames = new ArrayList<String>();
	   	 ArrayList<Object> data = new ArrayList<Object>();
	   	 Statement s2 = conn.createStatement();
	   	 String sql;
	   	 
	   	 if(column == "GraduationDate")
	   	 {
	   		int filterChoiceFinal = Integer.parseInt(filterChoice);
	   		sql = "SELECT * FROM alumni WHERE "+ column +" = '" + filterChoiceFinal + "'";
	   	 }
	   	 else 
	   	 {
	   		sql = "SELECT * FROM alumni WHERE "+ column +" = '" + filterChoice + "'";
	   	 }
	   	
	   	 ResultSet rs = s2.executeQuery(sql);
	 
	   	 ResultSetMetaData md = rs.getMetaData();
	   	 int columns = md.getColumnCount();


	   	 for (int i = 1; i <= columns; i++)
	   	 {
	   		 columnNames.add( md.getColumnName(i) );
	   	 }
	   	 
	   	 while (rs.next())
	   	 {
	   		 ArrayList<Object> rowCrawler = new ArrayList<Object>(columns);

	   		 for (int i = 1; i <= columns; i++)
	   		 {
	   			 rowCrawler.add( rs.getObject(i) );
	   		 }
	   		 
	   		 data.add( rowCrawler );
	   	 }
	   	 
	   	 //System.out.println(columnNames);
		 //System.out.println(data);
	   	 return new DataTable(columnNames, data);
	}
	
	public static DataTable showMissing() throws SQLException, ClassNotFoundException
	{
	   	 ArrayList<String> columnNames = new ArrayList<String>();
	   	 ArrayList<Object> data = new ArrayList<Object>();
	   	 Statement s2 = conn.createStatement();
	   	 String sql;
	   	
	   	 sql = "SELECT * FROM alumni "
	   	 		+ "WHERE GraduationDate = 0 "
	   	 		+ "OR FirstName = ' '"
	   	 		+ "OR LastName = ' '"
	   	 		+ "OR Gender = ' '"
	   	 		+ "OR Major = ' '"
	   	 		+ "OR Email = ' '"
	   	 		+ "OR College = ' '"
	   	 		+ "OR PhoneNumber = ' '"
	   	 		+ "OR Website = ' '"
	   	 		+ "OR Employer = ' '"
	   	 		+ "OR InternshipOffered = ' '"
	   	 		+ "OR CharityMatching = ' ';";
	   
	   	
	   	 ResultSet rs = s2.executeQuery(sql);
	 
	   	 ResultSetMetaData md = rs.getMetaData();
	   	 int columns = md.getColumnCount();


	   	 for (int i = 1; i <= columns; i++)
	   	 {
	   		 columnNames.add( md.getColumnName(i) );
	   	 }
	   	 
	   	 while (rs.next())
	   	 {
	   		 ArrayList<Object> rowCrawler = new ArrayList<Object>(columns);

	   		 for (int i = 1; i <= columns; i++)
	   		 {
	   			 rowCrawler.add( rs.getObject(i) );
	   		 }
	   		 
	   		 data.add( rowCrawler );
	   	 }
	   	 
	   	 //System.out.println(columnNames);
		 //System.out.println(data);
	   	 return new DataTable(columnNames, data);
	}
}
