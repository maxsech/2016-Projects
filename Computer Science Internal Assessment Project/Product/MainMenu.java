//Max Sechelski 12/19/2016
//The following code creates a main menu to get to other portions of the application
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends DbCalc
{
	//The following code creates ArrayLists for buttons, text, and textFields, and their locations
	private ArrayList<Component> buttons = new ArrayList<Component>();
	private ArrayList<Integer> buttonLocation = new ArrayList<Integer>();
	private ArrayList<Integer> buttonPanelLocation = new ArrayList<Integer>();

	private ArrayList<Component> texts = new ArrayList<Component>();
	private ArrayList<Integer> textLocation = new ArrayList<Integer>();
	private ArrayList<Integer> textPanelLocation = new ArrayList<Integer>();

	private ArrayList<Component> textFields = new ArrayList<Component>();
	private ArrayList<Integer> textFieldsLocation = new ArrayList<Integer>();
	private ArrayList<Integer> textFieldsPanelLocation = new ArrayList<Integer>();
	
	static JPanel mainMenuGraphicTablePanel = new JPanel(new GridBagLayout());
	
	//Line 42 creates an object of the FrameCreate class, with the input of the above ArrayLists
	FrameCreate mainMenuFrame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
												texts, textLocation, textPanelLocation,
												textFields, textFieldsLocation, textFieldsPanelLocation,
												mainMenuGraphicTablePanel);

	//The following method populates the ArrayLists and creates the frame
	public void createMainWindow()	
	{
		createWindowListner myButtonEar = new createWindowListner();
		
		//Adding Title
		
		JLabel mainTitle = new JLabel("Village School Alumni Association", JLabel.LEFT);
		mainTitle.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		mainTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
		mainTitle.setForeground(Color.WHITE);
		texts.add(mainTitle);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(0);
		
		//Adding image
		
		JLabel myPicture = new JLabel(new ImageIcon("/Users/Max/Java Programs/GUI/IA Project/img/Graduation.jpg"));
		texts.add(myPicture);
		//adding images X position to the text location array
		textLocation.add(0);
		//adding images Y position to the text location array
		textLocation.add(1);
		
		//View Button
		
		JButton viewDatabase = new JButton("View Alumni");
		viewDatabase.addActionListener(myButtonEar);
		//adding button to the ArrayList
		buttons.add(viewDatabase);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Edit Button
		
		JButton editDatabase = new JButton("Edit Entries");
		editDatabase.addActionListener(myButtonEar);
		//adding button to button ArrayList
		buttons.add(editDatabase);
		//adding X position
		buttonLocation.add(1);
		//adding Y position
		buttonLocation.add(0);
		
		//Exit Program Button
		
		JButton exitProgram = new JButton("Exit Program");
		exitProgram.addActionListener(myButtonEar);
		//adding button to button ArrayList
		buttons.add(exitProgram);
		//adding X position
		buttonLocation.add(2);
		//adding Y position
		buttonLocation.add(0);
		
		//Button panel X coordinate
		buttonPanelLocation.add(0);
		//Button Panel Y coordinate
		buttonPanelLocation.add(1);
		
		//Text panel X coordinate
		textPanelLocation.add(0);
		//Text panel Y coordinate
		textPanelLocation.add(0);
				
		//The following line calls on the method which creates the frame, the input of one designates the frame to close the program when it's closed
		mainMenuFrame.createFrame(1);
	}
	
	//The following class creates a listener for the buttons created for the main menu
	public class createWindowListner implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("View Alumni"))
			{
				//The following lines create an object of the view page class and run the method to create the view page
				DatabaseViewPage myViewPage = new DatabaseViewPage();
				myViewPage.createViewLayout();
			}
			else if(buttonCommand.equals("Edit Entries"))
			{
				//The following lines create an object of the edit page class and run the method to create the edit page
				DatabaseEditPage myEditPage = new DatabaseEditPage();
				myEditPage.CreateEditLayout();
			}
			else if(buttonCommand.equals("Exit Program"))
			{
				//Program closes if this button is pressed
				System.exit(0);
			}
			else
				System.out.println("Error");
		}
	}
}


