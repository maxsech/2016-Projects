//Max Sechelski 12/19/2016
//The following code creates a submenu that allows the user to choose how they want to edit the database

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class DatabaseEditPage extends MainMenu 
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
	
	static JPanel DatabaseEditPageGraphicTablePanel = new JPanel(new GridBagLayout());
	
	//Line 41 creates an object of the FrameCreate class, with the input of the above ArrayLists
	FrameCreate editMenuFrame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
												texts, textLocation, textPanelLocation,
												textFields, textFieldsLocation, textFieldsPanelLocation,
												DatabaseEditPageGraphicTablePanel);

	//Creates the frame for the edit menu
	public void CreateEditLayout()
	{
		createEditListner myEditButtonEar = new createEditListner();	

		//Add Alumni Button
		JButton addAlumni = new JButton("Manually Add Alumni");
		addAlumni.addActionListener(myEditButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(addAlumni);
		//adding X position
		buttonLocation.add(1);
		//adding Y position
		buttonLocation.add(10);
		
		//Add Alumni from CSV Button
		JButton addAlumniCSV = new JButton("Add Alumni From CSV");
		addAlumniCSV.addActionListener(myEditButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(addAlumniCSV);
		//adding X position
		buttonLocation.add(2);
		//adding Y position
		buttonLocation.add(10);
		
		//Remove Alumni Button
		JButton removeAlumni = new JButton("Remove Alumni");	
		removeAlumni.addActionListener(myEditButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(removeAlumni);
		//adding X position
		buttonLocation.add(3);
		//adding Y position
		buttonLocation.add(10);	
		
		//Edit Alumni Button
		JButton editAlumni = new JButton("Edit Alumni");	
		editAlumni.addActionListener(myEditButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(editAlumni);
		//adding X position
		buttonLocation.add(4);
		//adding Y position
		buttonLocation.add(10);	
		
		//Back Button
		JButton backButton = new JButton("Back");
		backButton.addActionListener(myEditButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(backButton);
		//adding X position
		buttonLocation.add(5);
		//adding Y position
		buttonLocation.add(10);
		
		//The following code runs the method to create the graphical table of the database
		try 
		{
			setGraphicTableEdit();
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
		
		editMenuFrame.createFrame(2);		
	}
	
	private void setGraphicTableEdit() throws ClassNotFoundException, SQLException
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
		DatabaseEditPageGraphicTablePanel.add(finalTable);
	}
	
	public class createEditListner implements ActionListener
	{
	
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Manually Add Alumni"))
			{
				AddAlumni myAddAlumni = new AddAlumni();

				//Removes the current frame and creates the add alumni window
				DatabaseEditPageGraphicTablePanel.removeAll();
				myAddAlumni.CreateAddLayout();
				editMenuFrame.closeFrame();
			}
			else if(buttonCommand.equals("Add Alumni From CSV"))
			{
				CsvMenu myCsvAdd = new CsvMenu();

				DatabaseEditPageGraphicTablePanel.removeAll();
				myCsvAdd.CreateCsvAddLayout();
				editMenuFrame.closeFrame();
			}
			else if(buttonCommand.equals("Remove Alumni"))
			{
				RemoveAlumni myRemoveAlumni = new RemoveAlumni();

				//Removes the current frame and creates the remove alumni window
				DatabaseEditPageGraphicTablePanel.removeAll();
				myRemoveAlumni.CreateRemoveLayout();
				editMenuFrame.closeFrame();
		
			}
			else if(buttonCommand.equals("Edit Alumni"))
			{
				EditAlumni1 myEditAlumni = new EditAlumni1();

				//Removes the current frame and creates the edit alumni window
				DatabaseEditPageGraphicTablePanel.removeAll();
				myEditAlumni.createEditAlumni1Layout();
				editMenuFrame.closeFrame();
			}
			else if(buttonCommand.equals("Back"))
			{
				//Removes the current frame and recreates the main menu
				DatabaseEditPageGraphicTablePanel.removeAll();
				editMenuFrame.closeFrame();
			}
			else
				System.out.println("Error1");
		}
	}
}
