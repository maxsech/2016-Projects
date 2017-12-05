//Max Sechelski 12/19/2016
//The following code creates an second page to edit specific entries in the database, specifies what the user wants to change about the entry
//they selected in the previous window

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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EditAlumni2 extends EditAlumni1
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
	
	JPanel editAlumni2GraphicTablePanel = new JPanel(new GridBagLayout());
	
	//Line 42 creates an object of the FrameCreate class, with the input of the above ArrayLists
	FrameCreate editAlumni2Frame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
												   texts, textLocation, textPanelLocation,
												   textFields, textFieldsLocation, textFieldsPanelLocation,
												   editAlumni2GraphicTablePanel);
	
	//an array that stores the options used by the combobox
	String[] optionArray = {"" , "First Name","Last Name","Gender","Major",
							"Email","Graduation Date","College","Phone Number",
							"Website", "Employer","Employment Internship Status", 
							"Firm Charity Matching Status"};
	
	
	JComboBox<String> optionList = new JComboBox<String>(optionArray);
	
	private int idHolder;
	private String columnHolder;
	private String comboBoxOutputHolder;
	
	public EditAlumni2(int idPass)
	{
		idHolder = idPass;
	}
	
	//creates the second Alumni Editor Page
	public void CreateAlumniEditLayout2() 
	{	
		comboBoxListener mycomboBoxListener = new comboBoxListener();
		editAlumniListnerV2 myEditAlumni2ButtonEar = new editAlumniListnerV2();
		
		//Combo Box
		
		optionList.addActionListener(mycomboBoxListener);
		//adding button to button ArrayList that will be added to frame
		buttons.add(optionList);
		//adding X position
		buttonLocation.add(1);
		//adding Y position
		buttonLocation.add(1);
		
		//Back Button
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(myEditAlumni2ButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(backButton);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Next Button
		
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(myEditAlumni2ButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(nextButton);
		//adding X position
		buttonLocation.add(3);
		//adding Y position
		buttonLocation.add(0);
		
		//Adding Edit Prompt
		
		JLabel editPrompt = new JLabel("Please select the information you would like to edit from the list below, then click 'Next' to continue:", JLabel.LEFT);
		editPrompt.setForeground(Color.WHITE);
		editPrompt.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(editPrompt);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(0);
		
		//The following code runs the method to create the graphical table of the database
		try 
		{
			setGraphicTableAlumniEdit2();
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
		
		editAlumni2Frame.createFrame(2);
	}
	
	private void setGraphicTableAlumniEdit2() throws ClassNotFoundException, SQLException
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
		editAlumni2GraphicTablePanel.add(finalTable);
	}
	
	public class editAlumniListnerV2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Back"))
			{
				//Deletes this frame and goes recreates the previous frame
				editAlumni2GraphicTablePanel.removeAll();
				EditAlumni1 myEditAlumni = new EditAlumni1();
				myEditAlumni.createEditAlumni1Layout();
				editAlumni2Frame.closeFrame();
			}
			if(buttonCommand.equals("Next"))
			{
				//Deletes this frame and creates the next frame, passing the id found in the previous frame and the two values found in this one
				editAlumni2GraphicTablePanel.removeAll();
				EditAlumni3 myEditAlumni = new EditAlumni3(idHolder, columnHolder, comboBoxOutputHolder);
				myEditAlumni.CreateAlumniEditLayout3(); //go to editalumni3
				editAlumni2Frame.closeFrame();
			}
			else
				System.out.println("Error");
		}
	}
	
	public class comboBoxListener implements ActionListener
	{
		 public void actionPerformed(ActionEvent e) 
		 {
			 //The following code gets the selected value from the combo box and compares it to the values found in the if statements, if there is a match, the appropriate column name is selected
			String selected = String.valueOf(optionList.getSelectedItem());
			comboBoxOutputHolder = selected;
			String coloumnToReturn = "";
						
			if (selected == "First Name")
			{
				coloumnToReturn = "FirstName";
			}
			else if (selected == "Last Name")
			{
				coloumnToReturn = "LastName";
			}
			else if (selected == "Gender")
			{
				coloumnToReturn = "Gender";
			}
			else if (selected == "Major")
			{
				coloumnToReturn = "Major";
			}
			else if (selected == "Email")
			{
				coloumnToReturn = "Email";
			}
			else if (selected == "Graduation Date")
			{
				coloumnToReturn = "GraduationDate";
			}
			else if (selected == "College")
			{
				coloumnToReturn = "College";
			}
			else if (selected == "Phone Number")
			{
				coloumnToReturn = "PhoneNumber";
			}
			else if (selected == "Website")
			{
				coloumnToReturn = "Website";
			}
			else if (selected == "Employer")
			{
				coloumnToReturn = "Employer";
			}
			else if (selected == "Employment Internship Status")
			{
				coloumnToReturn = "InternshipOffered";
			}
			else if (selected == "Firm Charity Matching Status")
			{
				coloumnToReturn = "CharityMatching";
			}
			else
			{
				System.out.println("Error");
			}
			
			columnHolder = coloumnToReturn;
		 }
	}
}
