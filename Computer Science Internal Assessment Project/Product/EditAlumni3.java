//Max Sechelski 12/19/2016
//The following code creates an third page to edit specific entries in the database, specifies what the user wants to change about the entry
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class EditAlumni3 extends EditAlumni2
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
	
	JPanel editAlumni3GraphicTablePanel = new JPanel(new GridBagLayout());
	
	//The following creates a comboox for the gender selection
	private String[] genderOption = {"", "Male", "Female"};
	private JComboBox<String> genderList = new JComboBox<String>(genderOption);
	
	private String[] employmentInternshipOption = {"", "Yes", "No"};
	private JComboBox<String> internshipList = new JComboBox<String>(employmentInternshipOption);
	
	private String[] charityMatchingOption = {"", "Yes", "No"};
	private JComboBox<String> charityList = new JComboBox<String>(charityMatchingOption);
	
	private boolean comboBox = false;
	
	//Line 45 creates an object of the FrameCreate class, with the input of the above ArrayLists
	FrameCreate editAlumni3Frame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
			   									   texts, textLocation, textPanelLocation,
			   									   textFields, textFieldsLocation, textFieldsPanelLocation,
			   									   editAlumni3GraphicTablePanel);
	
	static JTextArea alumniInfoEntry = new JTextArea(1,8);
	
	private int convertedIdEndPoint;
	private String comboBoxOutputEndPoint;
	private String coloumnToReturnEndPoint;
	private String changedInfoToReturnEndPoint;
	
	
	public EditAlumni3(int idPass, String columnPass, String comboPass)
	{
		super(idPass);
		convertedIdEndPoint = idPass;
		coloumnToReturnEndPoint = columnPass;
		comboBoxOutputEndPoint = comboPass;
	}
		
	//creates the third Alumni Editor Page
	public void CreateAlumniEditLayout3() 
	{
		editAlumniListnerV3 myEditAlumni3ButtonEar = new editAlumniListnerV3();
		genderComboBoxListener myCombo = new genderComboBoxListener();
		charityComboBoxListener myCharityCombo = new charityComboBoxListener();
		internshipComboBoxListener myInternshipCombo = new internshipComboBoxListener();
		
		//Back Button
		JButton backButton = new JButton("Back");
		backButton.addActionListener(myEditAlumni3ButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(backButton);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Save Edit Changes Button
		JButton confirmChangeButton = new JButton("Save Changes");	
		confirmChangeButton.addActionListener(myEditAlumni3ButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(confirmChangeButton);
		//adding X position
		buttonLocation.add(2);
		//adding Y position
		buttonLocation.add(0);
		
		//Adding Edit Prompt and Entry
		
		JLabel editPrompt = new JLabel("Please enter your edit for Alumni "+ convertedIdEndPoint +"'s "+ comboBoxOutputEndPoint +" and click 'Save Changes' to continue:", JLabel.LEFT);
		editPrompt.setForeground(Color.WHITE);
		editPrompt.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(editPrompt);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(1);
		
		if(comboBoxOutputEndPoint == "Gender")
		{
			comboBox = true;
			genderList.addActionListener(myCombo);		
			texts.add(genderList);
			//adding X position
			textLocation.add(0);
			//adding Y position
			textLocation.add(2);
		}
		else if(comboBoxOutputEndPoint == "Employment Internship Status")
		{
			comboBox = true;
			internshipList.addActionListener(myInternshipCombo);		
			texts.add(internshipList);
			//adding X position
			textLocation.add(0);
			//adding Y position
			textLocation.add(2);
		}
		else if(comboBoxOutputEndPoint == "Firm Charity Matching Status")
		{
			comboBox = true;
			charityList.addActionListener(myCharityCombo);		
			texts.add(charityList);
			//adding X position
			textLocation.add(0);
			//adding Y position
			textLocation.add(2);
		}
		else
		{
			alumniInfoEntry.setBackground(Color.WHITE);
			alumniInfoEntry.setBorder(BorderFactory.createLineBorder(Color.black));
			alumniInfoEntry.setText("");
			texts.add(alumniInfoEntry);
			//adding X position
			textLocation.add(0);
			//adding Y position
			textLocation.add(2);
		}

	
		//The following code runs the method to create the graphical table of the database
		try 
		{
			setGraphicTableAlumniEdit3();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		//Button Panel X location
		buttonPanelLocation.add(0);
		//Button Panel Y location
		buttonPanelLocation.add(1);

		//Text Panel X location
		textPanelLocation.add(0);
		//Text Panel Y location
		textPanelLocation.add(2);
		
		editAlumni3Frame.createFrame(2);
	}
	
	private void setGraphicTableAlumniEdit3() throws ClassNotFoundException, SQLException
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
		editAlumni3GraphicTablePanel.add(finalTable);
	}
	
	public class editAlumniListnerV3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Save Changes"))
			{				
				if(comboBox == true)
				{
					
					//calls the update value method from the dbMethods class that updates the value in the database
					updateValue(convertedIdEndPoint, coloumnToReturnEndPoint, changedInfoToReturnEndPoint);
					
					//Closes the current frame and recreates the first alumni editor page 
					editAlumni3GraphicTablePanel.removeAll();
					EditAlumni1 myAddAlumni = new EditAlumni1();
					myAddAlumni.createEditAlumni1Layout();
					editAlumni3Frame.closeFrame();
				}
				else
				{
					//gets the input from the text field
					changedInfoToReturnEndPoint = alumniInfoEntry.getText();
					
					//calls the update value method from the dbMethods class that updates the value in the database
					updateValue(convertedIdEndPoint, coloumnToReturnEndPoint, changedInfoToReturnEndPoint);
					
					//Closes the current frame and recreates the first alumni editor page 
					editAlumni3GraphicTablePanel.removeAll();
					EditAlumni1 myAddAlumni = new EditAlumni1();
					myAddAlumni.createEditAlumni1Layout();
					editAlumni3Frame.closeFrame();
				}
					

			}
			
			if(buttonCommand.equals("Back"))
			{	
				//Closes the current frame and recreates the second alumni editor page, passing back the parameter that was passed to this class
				editAlumni3GraphicTablePanel.removeAll();
				EditAlumni2 myEditAlumni = new EditAlumni2(convertedIdEndPoint);
				myEditAlumni.CreateAlumniEditLayout2(); //go to editalumni2
				editAlumni3Frame.closeFrame();
			}
			else
				System.out.println("Erroryo");
		}
	}
	
	public class genderComboBoxListener implements ActionListener
	{
		 public void actionPerformed(ActionEvent e) 
		 {
			//The following code gets the selected value from the combo box and compares it to the values found in the if statements, if there is a match, the appropriate column name is selected
			String genderSelection = String.valueOf(genderList.getSelectedItem());
						
			if (genderSelection == "Male")
			{
				System.out.println("Male");
				//This variable sets the filter parameter
				changedInfoToReturnEndPoint = "Male";
			}
			else if(genderSelection == "Female")
			{
				System.out.println("Female");
				//This variable sets the filter parameter
				changedInfoToReturnEndPoint = "Female";
			}
			else if(genderSelection == "")
			{
				//This variable sets the filter parameter
				changedInfoToReturnEndPoint = "";
			}
			else
			{
				System.out.println("Error4567");
			}
		 }
	}
	
	public class internshipComboBoxListener implements ActionListener
	{
		 public void actionPerformed(ActionEvent e) 
		 {
			//The following code gets the selected value from the combo box and compares it to the values found in the if statements, if there is a match, the appropriate column name is selected
			String internshipSelection = String.valueOf(internshipList.getSelectedItem());
						
			if (internshipSelection == "Yes")
			{
				//This variable sets the filter parameter
				changedInfoToReturnEndPoint = "Yes";
			}
			else if(internshipSelection == "No")
			{
				//This variable sets the filter parameter
				changedInfoToReturnEndPoint = "No";
			}
			else if(internshipSelection == "")
			{
				//This variable sets the filter parameter
				changedInfoToReturnEndPoint = "";
			}
			else
			{
				System.out.println("Error4567");
			}
		 }
	}
	
	public class charityComboBoxListener implements ActionListener
	{
		 public void actionPerformed(ActionEvent e) 
		 {
			//The following code gets the selected value from the combo box and compares it to the values found in the if statements, if there is a match, the appropriate column name is selected
			String charitySelection = String.valueOf(charityList.getSelectedItem());
						
			if (charitySelection == "Yes")
			{
				//This variable sets the filter parameter
				changedInfoToReturnEndPoint = "Yes";
			}
			else if(charitySelection == "No")
			{
				//This variable sets the filter parameter
				changedInfoToReturnEndPoint = "No";
			}
			else if(charitySelection == "")
			{
				//This variable sets the filter parameter
				changedInfoToReturnEndPoint = " ";
			}
			else
			{
				System.out.println("Error4567");
			}
		 }
	}
}
