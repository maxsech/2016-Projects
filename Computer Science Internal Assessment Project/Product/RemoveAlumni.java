//Max Sechelski 12/19/2016
//The following code allows for the user to remove an entry from the database

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class RemoveAlumni extends DatabaseEditPage
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
	
	static JPanel removeButtonPanel = new JPanel(new GridBagLayout());
	static JTextArea alumniRemovalEntry = new JTextArea(1,8);
	
	private JTable table;
	
	JPanel removeAlumniGraphicTablePanel = new JPanel(new GridBagLayout());
	
	//Line 47 creates an object of the FrameCreate class, with the input of the above ArrayLists
	FrameCreate removeAlumniFrame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
												texts, textLocation, textPanelLocation,
												textFields, textFieldsLocation, textFieldsPanelLocation,
												removeAlumniGraphicTablePanel);

	//Creates the frame for the remove menu
	public void CreateRemoveLayout()
	{	
		removeAlumniListner myRemoveButtonEar = new removeAlumniListner();
		
		//Remove Alumni Button
		
		JButton removeAlumniButton = new JButton("Remove Alumni");
		removeAlumniButton.addActionListener(myRemoveButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(removeAlumniButton);
		//adding X position
		buttonLocation.add(2);
		//adding Y position
		buttonLocation.add(0);
		
		//Back Button
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(myRemoveButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(backButton);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Removal Prompt and Entry
		
		JLabel removalPrompt = new JLabel("Please select the Alumni you wish to delete above, then press \"Remove Alumni\":", JLabel.LEFT);
		removalPrompt.setForeground(Color.WHITE);
		removalPrompt.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(removalPrompt);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(1);
		
		//The following code runs the method to create the graphical table of the database
		try 
		{
			setGraphicTableRemove();
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
		
		removeAlumniFrame.createFrame(2);	
	}
	
	private void setGraphicTableRemove() throws ClassNotFoundException, SQLException
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
		table = new JTable(alumniDataVector,columnNamesVector);
		
		//adding the table to a scrollpanel and adding the final product to a panel
		table.setFillsViewportHeight(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
		JScrollPane finalTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		removeAlumniGraphicTablePanel.add(finalTable);
	}
	
	public class removeAlumniListner implements ActionListener
	{
	
		public void actionPerformed(ActionEvent e)
		{
			DbCalc myId = new DbCalc();
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Remove Alumni"))
			{
				//gets the selected value from the table and parses it into an integer
				int rowSelection = table.getSelectedRow();
				Object tableObject = table.getModel().getValueAt(rowSelection, 0);
				String alumniId = tableObject.toString();
				System.out.println(alumniId);
				int convertedId = Integer.parseInt(alumniId);
								
				//passes that integer to the deleteValue method from the dbMethods class that removes the entry with the associated id
				deleteValue(convertedId);
				myId.adjustId(convertedId);
				
				
				//Deletes current frame and recreates itself
				removeAlumniGraphicTablePanel.removeAll();
				RemoveAlumni myRemoveAlumni = new RemoveAlumni();
				myRemoveAlumni.CreateRemoveLayout();
				removeAlumniFrame.closeFrame();

			}
			
			else if(buttonCommand.equals("Back"))
			{	
				//Deletes the current frame and goes back to the edit menu
				removeAlumniGraphicTablePanel.removeAll();
				DatabaseEditPage myEditPage = new DatabaseEditPage();
				myEditPage.CreateEditLayout();
				removeAlumniFrame.closeFrame();
			}
			else
				System.out.println("Error");
		}
	}
}

