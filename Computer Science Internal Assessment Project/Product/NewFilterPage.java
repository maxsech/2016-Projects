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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class NewFilterPage 
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
	private String[] columnArray = {"" , "First Name","Last Name","Gender","Major",
							"Email","Graduation Date","College","Phone Number", "Website",
							"Employer","Employment Internship Status", 
							"Firm Charity Matching Status"};
	private JComboBox<String> columnList = new JComboBox<String>(columnArray);
	
	//The following creates a comboox for the gender selection
	private String[] genderOption = {"", "Male", "Female"};
	private JComboBox<String> genderList = new JComboBox<String>(genderOption);
	
	private String[] yesNoOption = {"", "Yes", "No"};
	private JComboBox<String> yesNoList = new JComboBox<String>(yesNoOption);
	
	private JPanel tempPanel = new JPanel(new GridBagLayout());
	private JTextArea alumniFilterEntry = new JTextArea(1,10); 
	
	private String columnToReturn;
	private String filterValueToReturn;


	public void createFilterLayout()
	{		
		columnComboListener myComboListener = new columnComboListener();
		createFilterListner myButtonListener = new createFilterListner();
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
		
		columnList.addActionListener(myComboListener);
		//adding button to button ArrayList that will be added to frame
		texts.add(columnList);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(1);
		
		//Back Button
		
		JButton back = new JButton("Back");
		back.addActionListener(myButtonListener);
		//adding button to button ArrayList
		buttons.add(back);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Clear Filter Button
		
		JButton clearFilter = new JButton("Clear Filter");
		clearFilter.addActionListener(myButtonListener);
		//adding button to button ArrayList
		buttons.add(clearFilter);
		//adding X position
		buttonLocation.add(2);
		//adding Y position
		buttonLocation.add(0);
		
		//Filter Button
		
		JButton filterTable = new JButton("Filter With Given Parameters");
		filterTable.addActionListener(myButtonListener);
		//adding button to button ArrayList
		buttons.add(filterTable);
		//adding X position
		buttonLocation.add(3);
		//adding Y position
		buttonLocation.add(0);
		
		//The following code runs the method to create the graphical table of the database
		try 
		{
			setDefaultView();	
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
	
	
	public void setDefaultView() throws ClassNotFoundException, SQLException
	{
		databaseFilterPageGraphicTablePanel.removeAll();
		
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
		databaseFilterPageGraphicTablePanel.revalidate();
	}
	
	public void setFilteredView() throws ClassNotFoundException, SQLException
	{
		System.out.println("Here ya go " + columnToReturn + " " + filterValueToReturn);
		databaseFilterPageGraphicTablePanel.removeAll();
		
		//creating an object of DataTable and setting it equal to the object that TableCreate returns
		DataTable info = TableCreate.graphicTableSetup(columnToReturn, filterValueToReturn);
		
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
		databaseFilterPageGraphicTablePanel.revalidate();
	}
	
	public class createFilterListner implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Filter With Given Parameters"))
			{
				System.out.println(columnToReturn);
				System.out.println(filterValueToReturn);
				if(columnToReturn == null)
				{
					try 
					{
						setDefaultView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "")
				{
					try 
					{
						setDefaultView();	
					} 
					catch (ClassNotFoundException | SQLException d) 
					{
						d.printStackTrace();
					}
				}
				else if(columnToReturn == "FirstName")
				{
					try 
					{
						filterValueToReturn = alumniFilterEntry.getText();
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "LastName")
				{
					try 
					{
						filterValueToReturn = alumniFilterEntry.getText();
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "Gender")
				{
					try 
					{
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "Major")
				{
					try 
					{
						filterValueToReturn = alumniFilterEntry.getText();
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "Email")
				{
					try 
					{
						filterValueToReturn = alumniFilterEntry.getText();
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "GraduationDate")
				{
					try 
					{
						filterValueToReturn = alumniFilterEntry.getText();
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "College")
				{
					try 
					{
						filterValueToReturn = alumniFilterEntry.getText();
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "PhoneNumber")
				{
					try 
					{
						filterValueToReturn = alumniFilterEntry.getText();
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "Website")
				{
					try 
					{
						filterValueToReturn = alumniFilterEntry.getText();
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "Employer")
				{
					try 
					{
						filterValueToReturn = alumniFilterEntry.getText();
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "InternshipOffered")
				{
					try 
					{
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else if(columnToReturn == "CharityMatching")
				{
					try 
					{
						setFilteredView();	
					} 
					catch (ClassNotFoundException | SQLException f) 
					{
						f.printStackTrace();
					}
				}
				else
				{
					System.out.println("Error Filter");
				}
			}
			else if(buttonCommand.equals("Clear Filter"))
			{
				NewFilterPage myNewFilter = new NewFilterPage();
				
				databaseFilterPageGraphicTablePanel.removeAll();
				
				viewFilterFrame.closeFrame();
				myNewFilter.createFilterLayout();
			}
			else if(buttonCommand.equals("Back"))
			{
				DatabaseViewPage myViewPage = new DatabaseViewPage();
				
				databaseFilterPageGraphicTablePanel.removeAll();
				
				viewFilterFrame.closeFrame();
				myViewPage.createViewLayout();		
			}
			else
				System.out.println("Error");
		}
	}
	
	public class columnComboListener implements ActionListener
	{
		 public void actionPerformed(ActionEvent e) 
		 {	 
			//The following code gets the selected value from the combo box and compares it to the values found in the if statements, if there is a match, the appropriate column name is selected
			String internshipSelection = String.valueOf(columnList.getSelectedItem());
						
			if (internshipSelection == "")
			{
				columnToReturn = "";
				
				tempPanel.removeAll();
				tempPanel.revalidate();
			}
			else if(internshipSelection == "First Name")
			{
				columnToReturn = "FirstName";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Select the first name you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Last Name")
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
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Gender")
			{
				genderComboListener genderComboEar = new genderComboListener();
				
				columnToReturn = "Gender";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Select the gender you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				genderList.addActionListener(genderComboEar);
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(genderList, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Major")
			{
				columnToReturn = "Major";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Select the major you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Email")
			{
				columnToReturn = "Email";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Select the Email you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Graduation Date")
			{
				columnToReturn = "GraduationDate";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Select the graduation date you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "College")
			{
				columnToReturn = "College";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Select the college you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Phone Number")
			{
				columnToReturn = "PhoneNumber";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Select the phone number you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Website")
			{
				columnToReturn = "Website";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Select the website you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Employer")
			{
				columnToReturn = "Employer";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Select the employer you would like to sort by:", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				alumniFilterEntry.setBackground(Color.WHITE);
				alumniFilterEntry.setBorder(BorderFactory.createLineBorder(Color.black));
				alumniFilterEntry.setText("");
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(alumniFilterEntry, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Employment Internship Status")
			{
				yesComboListener myYesEar = new yesComboListener();
				
				columnToReturn = "InternshipOffered";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Sort by whether or not internships are offered by the alumni's employer", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				
				yesNoList.addActionListener(myYesEar);
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(yesNoList, gbContraints);
				tempPanel.revalidate();
			}
			else if(internshipSelection == "Firm Charity Matching Status")
			{
				yesComboListener myYesEar = new yesComboListener();

				columnToReturn = "CharityMatching";
				
				tempPanel.removeAll();
				JLabel instruction2 = new JLabel("Sort by whether or not the alumni's employer offers charity matching", JLabel.LEFT);
				instruction2.setForeground(Color.WHITE);
				instruction2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				gbContraints.gridx= 0;
				gbContraints.gridy= 0;
				tempPanel.add(instruction2, gbContraints);
				
				yesNoList.addActionListener(myYesEar);
				gbContraints.gridx= 0;
				gbContraints.gridy= 1;
				tempPanel.add(yesNoList, gbContraints);
				tempPanel.revalidate();
			}
			else
			{
				System.out.println("Error4567");
			}
		 }
	}
	
	public class genderComboListener implements ActionListener
	{
		 public void actionPerformed(ActionEvent e) 
		 {	 
			//The following code gets the selected value from the combo box and compares it to the values found in the if statements, if there is a match, the appropriate column name is selected
			String genderSelection = String.valueOf(genderList.getSelectedItem());
						
			if (genderSelection == "")
			{
				filterValueToReturn = "";
			}
			else if(genderSelection == "Male")
			{
				filterValueToReturn = "Male";
			}
			else if(genderSelection == "Female")
			{
				filterValueToReturn = "Female";
			}
		 }
	}
	
	public class yesComboListener implements ActionListener
	{
		 public void actionPerformed(ActionEvent e) 
		 {	 
			//The following code gets the selected value from the combo box and compares it to the values found in the if statements, if there is a match, the appropriate column name is selected
			String yesSelection = String.valueOf(yesNoList.getSelectedItem());
						
			if (yesSelection == "")
			{
				filterValueToReturn = "";
			}
			else if(yesSelection == "Yes")
			{
				filterValueToReturn = "Yes";
			}
			else if(yesSelection == "No")
			{
				filterValueToReturn = "No";
			}
		 }
	}
}
