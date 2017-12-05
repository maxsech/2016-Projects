//Max Sechelski 12/19/2016
//The following code allows for the user to add an entry to the database

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class AddAlumni extends DatabaseEditPage
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
	
	private static JTextArea firstNameEntry = new JTextArea(1,8);	
	private static JTextArea lastNameEntry = new JTextArea(1,8);
	private static JTextArea majorEntry = new JTextArea(1,8);	
	private static JTextArea emailEntry = new JTextArea(1,8);
	private static JTextArea graduationDateEntry = new JTextArea(1,8);
	private static JTextArea collegeEntry = new JTextArea(1,8);
	private static JTextArea phoneEntry = new JTextArea(1,8);
	private static JTextArea websiteEntry = new JTextArea(1,8);
	private static JTextArea employmentEntry = new JTextArea(1,8);

	static JPanel addAlumniGraphicTablePanel = new JPanel(new GridBagLayout());
	
	//The following creates a comboox for the gender selection
	private String[] genderOption = {"", "Male", "Female"};
	private JComboBox<String> genderList = new JComboBox<String>(genderOption);
	
	private String[] employmentInternshipOption = {"", "Yes", "No"};
	private JComboBox<String> internshipList = new JComboBox<String>(employmentInternshipOption);
	
	private String[] charityMatchingOption = {"", "Yes", "No"};
	private JComboBox<String> charityList = new JComboBox<String>(charityMatchingOption);

	//Line 51 creates an object of the FrameCreate class, with the input of the above ArrayLists
	private FrameCreate addAlumniFrame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
												texts, textLocation, textPanelLocation,
												textFields, textFieldsLocation, textFieldsPanelLocation,
												addAlumniGraphicTablePanel);
	
	private String firstName;
	private String lastName;
	private String gender;
	private String major;
	private String email;
	private String graduationDate;
	private String college;
	private String phone;
	private String website;
	private String employment;
	private String internshipOffered;
	private String charityMatchingOffered;

	//Creates the frame for the add menu
	public void CreateAddLayout()
	{		
		addAlumniListner myAddButtonEar = new addAlumniListner();
		genderComboBoxListener myCombo = new genderComboBoxListener();
		internshipComboBoxListener myInternshipCombo = new internshipComboBoxListener();
		charityComboBoxListener myCharityCombo = new charityComboBoxListener(); 
		
		
		//Add Alumni Button
		
		JButton addAlumniButton = new JButton("Add Entered Alumni");
		addAlumniButton.addActionListener(myAddButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(addAlumniButton);
		//adding X position
		buttonLocation.add(2);
		//adding Y position
		buttonLocation.add(0);
		
		//Back Button
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(myAddButtonEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(backButton);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Adding First Name Text Area and Label
		
		JLabel firstName = new JLabel("First Name:", JLabel.LEFT);
		firstName.setForeground(Color.WHITE);
		firstName.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(firstName);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(1);
		
		firstNameEntry.setBackground(Color.WHITE);
		firstNameEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		firstNameEntry.setText("");
		texts.add(firstNameEntry);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(2);
		
		//Last Name Text Area and Label
		
		JLabel lastName = new JLabel("Last Name:", JLabel.LEFT);
		lastName.setForeground(Color.WHITE);
		lastName.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(lastName);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(1);
			
		lastNameEntry.setBackground(Color.WHITE);
		lastNameEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		lastNameEntry.setText("");
		texts.add(lastNameEntry);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(2);
	
		//Gender Text Area and Label
		
		JLabel gender = new JLabel("Gender:", JLabel.LEFT);
		gender.setForeground(Color.WHITE);
		gender.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(gender);
		//adding X position
		textLocation.add(2);
		//adding Y position
		textLocation.add(1);
		
		genderList.addActionListener(myCombo);		
		texts.add(genderList);
		//adding X position
		textLocation.add(2);
		//adding Y position
		textLocation.add(2);
			
		//Major Text Area and Label
		
		JLabel major = new JLabel("Major:", JLabel.LEFT);
		major.setForeground(Color.WHITE);
		major.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(major);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(3);
				
		majorEntry.setBackground(Color.WHITE);
		majorEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		majorEntry.setText("");
		texts.add(majorEntry);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(4);
		
		//Email Text Area and Label
		
		JLabel email = new JLabel("Email:", JLabel.LEFT);
		email.setForeground(Color.WHITE);
		email.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(email);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(3);
				
		emailEntry.setBackground(Color.WHITE);
		emailEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		emailEntry.setText("");
		texts.add(emailEntry);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(4);
			
		//Graduation date Text Area and Label
		
		JLabel graduationDate = new JLabel("Graduation Date:", JLabel.LEFT);
		graduationDate.setForeground(Color.WHITE);
		graduationDate.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(graduationDate);
		//adding X position
		textLocation.add(2);
		//adding Y position
		textLocation.add(3);
				
		graduationDateEntry.setBackground(Color.WHITE);
		graduationDateEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		graduationDateEntry.setText("");
		texts.add(graduationDateEntry);
		//adding X position
		textLocation.add(2);
		//adding Y position
		textLocation.add(4);
		
		//College date Text Area and Label
		
		JLabel alumniCollege = new JLabel("College:", JLabel.LEFT);
		alumniCollege.setForeground(Color.WHITE);
		alumniCollege.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(alumniCollege);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(5);
				
		collegeEntry.setBackground(Color.WHITE);
		collegeEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		collegeEntry.setText("");
		texts.add(collegeEntry);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(6);
		
		//Phone Text Area and Label
		
		JLabel alumniPhone = new JLabel("Phone Number:", JLabel.LEFT);
		alumniPhone.setForeground(Color.WHITE);
		alumniPhone.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(alumniPhone);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(5);
				
		phoneEntry.setBackground(Color.WHITE);
		phoneEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		phoneEntry.setText("");
		texts.add(phoneEntry);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(6);
		
		//Website Text Area and Label
		
		JLabel alumniWebsite = new JLabel("Website:", JLabel.LEFT);
		alumniWebsite.setForeground(Color.WHITE);
		alumniWebsite.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(alumniWebsite);
		//adding X position
		textLocation.add(2);
		//adding Y position
		textLocation.add(5);
				
		websiteEntry.setBackground(Color.WHITE);
		websiteEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		websiteEntry.setText("");
		texts.add(websiteEntry);
		//adding X position
		textLocation.add(2);
		//adding Y position
		textLocation.add(6);
		
		//Employment Area and Label
		
		JLabel alumniEmployment = new JLabel("Employment:", JLabel.LEFT);
		alumniEmployment.setForeground(Color.WHITE);
		alumniEmployment.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(alumniEmployment);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(7);
				
		employmentEntry.setBackground(Color.WHITE);
		employmentEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		employmentEntry.setText("");
		texts.add(employmentEntry);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(8);
		
		//Employer internship Area and Combo Box
		JLabel intershipOption = new JLabel("Does their employer offer student internships?:", JLabel.LEFT);
		intershipOption.setForeground(Color.WHITE);
		intershipOption.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(intershipOption);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(9);
		
		internshipList.addActionListener(myInternshipCombo);
		texts.add(internshipList);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(10);
		
		//Charity matching Area and Combo Box
		JLabel charityOption = new JLabel("Does their employer offer charity matching?:", JLabel.LEFT);
		charityOption.setForeground(Color.WHITE);
		charityOption.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(charityOption);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(11);
		
		charityList.addActionListener(myCharityCombo);
		texts.add(charityList);
		//adding X position
		textLocation.add(1);
		//adding Y position
		textLocation.add(12);
		
		//The following code runs the method to create the graphical table of the database
		try 
		{
			setGraphicTableAddAlumni();
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
		textPanelLocation.add(3);
		
		addAlumniFrame.createFrame(2);		
	}
	
	public void setGraphicTableAddAlumni() throws ClassNotFoundException, SQLException
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
		addAlumniGraphicTablePanel.add(finalTable);
	}
	
	public class addAlumniListner implements ActionListener
	{	
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Back"))
			{
				//Removes the current frame and recreates the edit menu
				addAlumniGraphicTablePanel.removeAll();
				DatabaseEditPage myEditPage = new DatabaseEditPage();
				myEditPage.CreateEditLayout();
				addAlumniFrame.closeFrame();

			}
			if(buttonCommand.equals("Add Entered Alumni"))
			{
				int convertedDate;
				
				//Gets all of the information from the various textfields 
				firstName = firstNameEntry.getText();
				lastName = lastNameEntry.getText();
				major = majorEntry.getText();
				email = emailEntry.getText();
				graduationDate = graduationDateEntry.getText();
				college = collegeEntry.getText(); 
				phone = phoneEntry.getText(); 
				website = websiteEntry.getText();
				employment = employmentEntry.getText();				

				convertedDate = Integer.parseInt(graduationDate);
				int alumniId = getId();
				
				System.out.println(internshipOffered);
				//Calls the tableInsert method from dbMethods to add an entry into the database with the inputed data
				tableInsert(alumniId, firstName , lastName ,  gender , major , email , convertedDate, college, phone, website, employment, internshipOffered, charityMatchingOffered);
			
				//Removes the current frame and recreates itself
				addAlumniGraphicTablePanel.removeAll();
				AddAlumni myAddAlumni = new AddAlumni();
				myAddAlumni.CreateAddLayout();
				addAlumniFrame.closeFrame();

			}
			else
				System.out.println("Error2");
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
				gender = "Male";
			}
			else if(genderSelection == "Female")
			{
				System.out.println("Female");
				//This variable sets the filter parameter
				gender = "Female";
			}
			else if(genderSelection == "")
			{
				//This variable sets the filter parameter
				gender = "";
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
				charityMatchingOffered = "Yes";
			}
			else if(charitySelection == "No")
			{
				//This variable sets the filter parameter
				charityMatchingOffered = "No";
			}
			else if(charitySelection == "")
			{
				//This variable sets the filter parameter
				charityMatchingOffered = "";
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
				internshipOffered = "Yes";
			}
			else if(internshipSelection == "No")
			{
				//This variable sets the filter parameter
				internshipOffered = "No";
			}
			else if(internshipSelection == "")
			{
				//This variable sets the filter parameter
				internshipOffered = "";
			}
			else
			{
				System.out.println("Error4567");
			}
		 }
	}
}


