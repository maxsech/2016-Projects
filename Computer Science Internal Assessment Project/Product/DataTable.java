//Max Sechelski 12/19/2016
//The following code creates a class that stores ArrayLists given to it and has methods to access those ArrayLists
import java.util.ArrayList;


public class DataTable 
{
		//Stores the column names
     	private final ArrayList<String> firstDataTableInput;
     	//Stores the data
    	private final ArrayList<Object> secondDataTableInput;

    	public DataTable(ArrayList<String> columnNames, ArrayList<Object> data) 
    	{
        	firstDataTableInput = columnNames;
        	secondDataTableInput = data;
    	}

		public ArrayList<String> getFirst() 
    	{
        	return firstDataTableInput;
    	}

    	public ArrayList<Object> getSecond() 
    	{
        	return secondDataTableInput;
    	}
}