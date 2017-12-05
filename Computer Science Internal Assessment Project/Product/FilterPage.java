import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class FilterPage 
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
	
	private GridBagConstraints gbContraints = new GridBagConstraints();

	private JPanel databaseFilterPageGraphicTablePanel = new JPanel(new GridBagLayout());
	
	//Line 44 creates an object of the FrameCreate class, with the input of the above ArrayLists
	private FrameCreate viewFilterFrame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
												texts, textLocation, textPanelLocation,
												textFields, textFieldsLocation, textFieldsPanelLocation,
												databaseFilterPageGraphicTablePanel);
	
	//The following creates a combobox for the initial selection
	private String[] optionArray = {"" , "First Name","Last Name","Gender","Major",
							"URL","Graduation Date","College","Phone Number", "Website",
							"Employer","Employment Internship Status", 
							"Firm Charity Matching Status"};
	private JComboBox<String> optionList = new JComboBox<String>(optionArray);
	
	//The following creates a comboox for the gender selection
	private String[] genderOption = {"", "Male", "Female"};
	private JComboBox<String> genderList = new JComboBox<String>(genderOption);

	//The variables that get passed to the table create method that provide filter specification
	private String columnChoice;
	private String filterChoice;
	
	private boolean goToCombo = false;
	
	private JTextArea alumniFilterEntry = new JTextArea(1,10);
	private JPanel tempPanel = new JPanel(new GridBagLayout());
	

	public void createFilterLayout()
	{
		comboBoxListener mycomboBoxListener = new comboBoxListener();
		createFilterListner myFilterButtonEar = new createFilterListner();
		
		//Instruction label
		
		JLabel instruction = new JLabel("Select the column you would like to sort by:", JLabel.LEFT);
		instruction.setForeground(Color.WHITE);
		instruction.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(instruction);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(0);
				
		//Combo Box
		
		optionList.addActionListener(mycomboBoxListener);
		//adding button to button ArrayList that will be added to frame
		texts.add(optionList);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(1);
		
		//Back Button
		
		JButton exitProgram = new JButton("Back");
		exitProgram.addActionListener(myFilterButtonEar);
		//adding button to button ArrayList
		buttons.add(exitProgram);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Filter Button
		
		JButton filterTable = new JButton("Filter with given parameters");
		filterTable.addActionListener(myFilterButtonEar);
		//adding button to button ArrayList
		buttons.add(filterTable);
		//adding X position
		buttonLocation.add(2);
		//adding Y position
		buttonLocation.add(0);
		viewFilterFrame.refreshButtonPanel();
		
		//The following code runs the method to create the graphical table of the database
		try 
		{
			setGraphicTableFilterDefault();	
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
		textPanelLocation.add(3);
		
		//Creates a panel that this class can control, rather than having the frame class create
		viewFilterFrame.hotAdd(tempPanel, 0, 4);

		//The following line calls on the method which creates the frame, the input of two designates the frame does not close the program when it's closed
		viewFilterFrame.createFrame(2);
	}
	
	//The following method creates a graphic table representing the data in the database
	public void setGraphicTableFilterDefault() throws ClassNotFoundException, SQLException
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
		databaseFilterPageGraphicTablePanel.add(finalTable);
	}
	
	//The following method creates a filtered graphic table representing the data in the database
	public void setGraphicTableFilterFiltered() throws ClassNotFoundException, SQLException
	{
		//creating an object of DataTable and setting it equal to the object that TableCreate returns
		DataTable info = TableCreate.graphicTableSetup(columnChoice, filterChoice);
		
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
		databaseFilterPageGraphicTablePanel.add(finalTable);
	}
	
	//the following class creates a listener for the buttons on the page
	public class createFilterListner implements ActionListener
	{
	
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Filter with given parameters"))
			{
				filterChoice = "";
				
				System.out.println(filterChoice);
				System.out.println(columnChoice);
				String inputData;
				
				databaseFilterPageGraphicTablePanel.removeAll();
				

				//if the filter is empty, the code will run the default graphic table create method, and show all of the data in the database
				if(columnChoice == null)
				{
					System.out.println("Not that one this one");
					
					try 
					{
						setGraphicTableFilterDefault();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
					
					viewFilterFrame.refreshGraphicPanel();
				}
				else if(columnChoice == "")
				{
					System.out.println("This one here");
					
					try 
					{
						setGraphicTableFilterDefault();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
					
					viewFilterFrame.refreshGraphicPanel();
				}
				//otherwise it runs the method that inputs the filter parameters and creates a table that only shows the information the user wants
				else
				{
					if(goToCombo == false)
					{
						filterChoice = alumniFilterEntry.getText();
					}
					
					System.out.println(filterChoice);
					System.out.println(columnChoice);
					
					if(filterChoice == "")
					{
						try 
						{
							System.out.println("Now at this one");
							setGraphicTableFilterDefault();	
						} 
						catch (ClassNotFoundException | SQLException f) 
						{
							f.printStackTrace();
						}
					}
					else
					{
						try 
						{
							System.out.println("Now at that one");
							setGraphicTableFilterFiltered();
						} 
						catch (ClassNotFoundException | SQLException f) 
						{
							f.printStackTrace();
						}
					}

					
					//calls on a method to refresh the panel so the changes show
					viewFilterFrame.refreshGraphicPanel();
				}
			}
			//Sends the user back to the view menu
			else if(buttonCommand.equals("Back"))
			{
				databaseFilterPageGraphicTablePanel.removeAll();
				DatabaseViewPage viewPage = new DatabaseViewPage();
				viewPage.createViewLayout();
				viewFilterFrame.closeFrame();
			}
			else
				System.out.println("Error1234");
		}
	}
	
	//The following class creates a listener for the initial combobox
	public class comboBoxListener implements ActionListener
	{
		 public void actionPerformed(ActionEvent e) 
		 {
			createFilterListner myFilterButtonEar = new createFilterListner();

			//The following code gets the selected value from the combo box and compares it to the values found in the if statements, if there is a match, the appropriate column name is selected
			String selected = String.valueOf(optionList.getSelectedItem());
			String columnToReturn = "";
			
			//If first name is selected the following code runs		
			if (selected == "First Name")
			{
				//sets the variable that will be sent to the graphic table creation method to determine what column the table will be filtered by
				columnToReturn = "FirstName";
				
				tempPanel.removeAll();
				
				JLabel instruction2 = new JLabel("Select the first name you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				//This code adds a text entry to the temp panel
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setForeground(Color.BLACK);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(selected == "")
			{
				columnToReturn = "";
				filterChoice = "";
				
				tempPanel.removeAll();
				tempPanel.revalidate();
			}
			//Same as first name
			else if (selected == "Last Name")
			{
				columnToReturn = "LastName";
				
				tempPanel.removeAll();
				
				JLabel instruction2 = new JLabel("Select the last name you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setForeground(Color.BLACK);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if (selected == "Gender")
			{
				goToCombo = true;
				genderComboBoxListener myCombo = new genderComboBoxListener();
				
				//sets the variable that will be sent to the graphic table creation method to determine what column the table will be filtered by
				columnToReturn = "gender";
				
				//Adds the combobox to a panel, which is then added to the screen
				tempPanel.removeAll();
				
				JLabel instruction2 = new JLabel("Select the gender you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				genderList.addActionListener(myCombo);
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(genderList, gbContraints);
				tempPanel.revalidate();
				
			}
			else if (selected == "Major")
			{
				columnToReturn = "major";
			}
			else if (selected == "URL")
			{
				columnToReturn = "url";
			}
			else if (selected == "Graduation Date")
			{	
				columnToReturn = "graduationDate";
			}
			else if (selected == "College")
			{
				columnToReturn = "alumniCollege";
			}
			else if (selected == "Phone Number")
			{
				columnToReturn = "phoneNumber";
			}
			else if (selected == "Employer")
			{
				columnToReturn = "employmentFirm";
			}
			else if (selected == "Employment Internship Status")
			{
				columnToReturn = "employmentInternship";
			}
			else if (selected == "Firm Charity Matching Status")
			{
				columnToReturn = "charityMatching";
			}
			else
			{
				System.out.println("Error0987");
			}		
			
			columnChoice = columnToReturn;
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
				filterChoice = "Male";
			}
			else if(genderSelection == "Female")
			{
				System.out.println("Female");
				//This variable sets the filter parameter
				filterChoice = "Female";
			}
			else if(genderSelection == "")
			{
				//This variable sets the filter parameter
				filterChoice = "";
			}
			else
			{
				System.out.println("Error4567");
			}
		 }
	}
}
