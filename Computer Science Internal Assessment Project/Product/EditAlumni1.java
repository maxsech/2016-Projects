//Max Sechelski 12/19/2016
//The following code creates an initial page to edit specific entries in the database, initially asks for an iD value from the user and 
//passes that value to the next window

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class EditAlumni1 extends DatabaseEditPage
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
	
	private JPanel editAlumni1GraphicTablePanel = new JPanel(new GridBagLayout());
	
	private JTable table;
	
	private JTextArea alumniEditEntry = new JTextArea(1,8);

	private FrameCreate editAlumni1Frame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
												   texts, textLocation, textPanelLocation,
												   textFields, textFieldsLocation, textFieldsPanelLocation,
												   editAlumni1GraphicTablePanel);		

	 //creates initial Alumni Editor Page
	public void createEditAlumni1Layout()
	{	
		editAlumniListner myEditAlumni1ButtonEar = new editAlumniListner();
		
		//Back Button
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(myEditAlumni1ButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(backButton);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Select Alumni Button
		
		JButton selectAlumniButton = new JButton("Next");
		selectAlumniButton.addActionListener(myEditAlumni1ButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(selectAlumniButton);
		//adding X position
		buttonLocation.add(2);
		//adding Y position
		buttonLocation.add(0);
		
		//Edit prompt and entry
		
		JLabel editPrompt = new JLabel("Please select the Alumni you wish to edit above, then press next.", JLabel.LEFT);
		editPrompt.setForeground(Color.WHITE);
		editPrompt.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(editPrompt);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(1);
		
		//The following code runs the method to create the graphical table of the database
		try 
		{
			setGraphicTableAlumniEdit1();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		//Button Panel X location
		buttonPanelLocation.add(0);
		//Button Panel Y location
		buttonPanelLocation.add(3);

		//Text Panel X location
		textPanelLocation.add(0);
		//Text Panel Y location
		textPanelLocation.add(2);
		
		editAlumni1Frame.createFrame(2);
	}
	
	private void setGraphicTableAlumniEdit1() throws ClassNotFoundException, SQLException
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
		editAlumni1GraphicTablePanel.add(finalTable);
	}
	
	public class editAlumniListner implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			int convertedId = 0;
			
			if(buttonCommand.equals("Next"))
			{
				int rowSelection = table.getSelectedRow();
				Object tableObject = table.getModel().getValueAt(rowSelection, 0);
				String alumniId = tableObject.toString();
				//String alumniId = alumniEditEntry.getText();
				System.out.println(alumniId);
				convertedId = Integer.parseInt(alumniId);
				
				editAlumni1GraphicTablePanel.removeAll();
				editAlumni1Frame.closeFrame();
				
				System.out.println(convertedId);
				EditAlumni2 myEditAlumni = new EditAlumni2(convertedId);
				myEditAlumni.CreateAlumniEditLayout2(); //go to editalumni2
			}
			
			else if(buttonCommand.equals("Back"))
			{
				editAlumni1GraphicTablePanel.removeAll();
				editAlumni1Frame.closeFrame();
				
				DatabaseEditPage myEditPage = new DatabaseEditPage();
				myEditPage.CreateEditLayout();
			}
			else
				System.out.println("Error");
		}
	}
}
