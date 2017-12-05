//Max Sechelski 12/19/2016
//The following code creates a screen which allows the user to view the database in its current form

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DatabaseViewPage extends MainMenu 
{
	//The following code creates ArrayLists for buttons, text, and textFields, and their locations
	private ArrayList<Component> buttons = new ArrayList<Component>();
	private ArrayList<Integer> buttonLocation = new ArrayList<Integer>();
	
	private ArrayList<Component> texts = new ArrayList<Component>();
	private ArrayList<Integer> textLocation = new ArrayList<Integer>();
	
	private ArrayList<Component> textFields = new ArrayList<Component>();
	private ArrayList<Integer> textFieldsLocation = new ArrayList<Integer>();
	
	private ArrayList<Integer> buttonPanelLocation = new ArrayList<Integer>();
	private ArrayList<Integer> textPanelLocation = new ArrayList<Integer>();
	private ArrayList<Integer> textFieldsPanelLocation = new ArrayList<Integer>();

	private JPanel databaseViewPageGraphicTablePanel = new JPanel(new GridBagLayout());
	
	//Line 43 creates an object of the FrameCreate class, with the input of the above ArrayLists
	FrameCreate viewMenuFrame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
												texts, textLocation, textPanelLocation,
												textFields, textFieldsLocation, textFieldsPanelLocation,
												databaseViewPageGraphicTablePanel);

	//Creates the frame for the View menu
	public void createViewLayout()
	{
		createViewListner myViewButtonEar = new createViewListner();
		
		//Adding Text to Screen
		JLabel alertText = new JLabel("Search Functions:", JLabel.LEFT);
		alertText.setForeground(Color.WHITE);
		texts.add(alertText);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(0);
		
		//Filter Button
		JButton filterDatabase = new JButton("Filter By...");
		filterDatabase.addActionListener(myViewButtonEar);
		//adding button to button ArrayList
		buttons.add(filterDatabase);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Show Missing Button
		JButton showMissing = new JButton("Show Entries With Missing Data");
		showMissing.addActionListener(myViewButtonEar);
		//adding button to button ArrayList
		buttons.add(showMissing);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(3);
		
		//Back Button
		JButton exitProgram = new JButton("Back");
		exitProgram.addActionListener(myViewButtonEar);
		//adding button to button ArrayList
		buttons.add(exitProgram);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(4);
		
		//The following code runs the method to create the graphical table of the database
		try 
		{
			setGraphicTableView();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		//Button Panel X location
		buttonPanelLocation.add(0);
		//Button Panel Y location
		buttonPanelLocation.add(2);

		//Text Panel X location
		textPanelLocation.add(0);
		//Text Panel Y location
		textPanelLocation.add(1);
		
		//The following line calls on the method which creates the frame, the input of two designates the frame does not close the program when it's closed
		viewMenuFrame.createFrame(2);
	}
	
	//The following method creates a graphic table representing the data in the database
	public void setGraphicTableView() throws ClassNotFoundException, SQLException
	{
		//creating an object of DataTable and setting it equal to the object that TableCreate returns
		DataTable info = TableCreate.graphicTableSetup();
		
		//Unpacking the ArrayLists from the object
		ArrayList<String> columnNames = info.getFirst();
		ArrayList<Object> tableData = info.getSecond();
			 
		//Creating a new vector to hold the column name strings
		Vector<String> columnNamesVector = new Vector<String>();
		//Creating a new vector to hold the objects that contain the table data
		Vector<Object> alumniDataVector = new Vector<Object>();
		
		//takes the arraylist of objects and adds them to the data vector
		for (int counter = 0; counter < tableData.size(); counter++)
		{
		     	ArrayList<Object> subArray = (ArrayList)tableData.get(counter);
		     	Vector<Object> subVector = new Vector<Object>();
		     	
		     	for (int j = 0; j < subArray.size(); j++)
		     	{
		         	subVector.add(subArray.get(j));
		     	}
		     	
		     	alumniDataVector.add(subVector);
		}
		
		//takes the arraylist of column names and adds them to the column names vector
		for (int counter2 = 0; counter2 < columnNames.size(); counter2++ )
		{
		     	columnNamesVector.add(columnNames.get(counter2));
		}
		
		//creates a new jtable with the above data and column names
		JTable table = new JTable(alumniDataVector,columnNamesVector);
		
		//adding the table to a scrollpanel and adding the final product to a panel
		table.setFillsViewportHeight(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
		JScrollPane finalTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		databaseViewPageGraphicTablePanel.add(finalTable);
	}
	
	public void setMissingView() throws ClassNotFoundException, SQLException
	{
		databaseViewPageGraphicTablePanel.removeAll();
		
		//creating an object of DataTable and setting it equal to the object that TableCreate returns
		DataTable info = TableCreate.showMissing();
		
		//Unpacking the ArrayLists from the object
		ArrayList<String> columnNames = info.getFirst();
		ArrayList<Object> tableData = info.getSecond();
			 
		//Creating a new vector to hold the column name strings
		Vector<String> columnNamesVector = new Vector<String>();
		//Creating a new vector to hold the objects that contain the table data
		Vector<Object> alumniDataVector = new Vector<Object>();
		
		//takes the arraylist of objects and adds them to the data vector
		for (int counter = 0; counter < tableData.size(); counter++)
		{
		     	ArrayList<Object> subArray = (ArrayList)tableData.get(counter);
		     	Vector<Object> subVector = new Vector<Object>();
		     	
		     	for (int j = 0; j < subArray.size(); j++)
		     	{
		         	subVector.add(subArray.get(j));
		     	}
		     	
		     	alumniDataVector.add(subVector);
		}
		
		//takes the arraylist of column names and adds them to the column names vector
		for (int counter2 = 0; counter2 < columnNames.size(); counter2++ )
		{
		     	columnNamesVector.add(columnNames.get(counter2));
		}
		
		//creates a new jtable with the above data and column names
		JTable table = new JTable(alumniDataVector,columnNamesVector);
		
		//adding the table to a scrollpanel and adding the final product to a panel
		table.setFillsViewportHeight(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
		JScrollPane finalTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		databaseViewPageGraphicTablePanel.add(finalTable);
		databaseViewPageGraphicTablePanel.revalidate();
	}
	
	public class createViewListner implements ActionListener
	{
	
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Filter By..."))
			{
				//clears the table panel and closes the frame
				databaseViewPageGraphicTablePanel.removeAll();
				NewFilterPage myFilter = new NewFilterPage();
				myFilter.createFilterLayout();
				viewMenuFrame.closeFrame();
			}
			else if(buttonCommand.equals("Back"))
			{
				databaseViewPageGraphicTablePanel.removeAll();
				viewMenuFrame.closeFrame();
			}
			else if(buttonCommand.equals("Show Entries With Missing Data"))
			{
				try 
				{
					setMissingView();
				} 
				catch (ClassNotFoundException | SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
			else
				System.out.println("Error");
		}
	}
}
