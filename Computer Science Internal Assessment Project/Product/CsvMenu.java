import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class CsvMenu extends DatabaseEditPage
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
		
	//Line 41 creates an object of the FrameCreate class, with the input of the above ArrayLists
	FrameCreate csvMenuFrame = new FrameCreate(buttons, buttonLocation, buttonPanelLocation, 
												texts, textLocation, textPanelLocation,
												textFields, textFieldsLocation, textFieldsPanelLocation);
	ErrorScreen myError = new ErrorScreen();
	
	private static JTextArea pathEntry = new JTextArea(1,30);
	
	public void CreateCsvAddLayout()
	{	
		createCsvMenuListner myCsvMenuEar = new createCsvMenuListner();
		
		JLabel prompt = new JLabel("Please enter the file path to your CSV file:", JLabel.LEFT);
		prompt.setForeground(Color.WHITE);
		prompt.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		texts.add(prompt);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(0);
		
		pathEntry.setBackground(Color.WHITE);
		pathEntry.setBorder(BorderFactory.createLineBorder(Color.black));
		pathEntry.setText("");
		texts.add(pathEntry);
		//adding X position
		textLocation.add(0);
		//adding Y position
		textLocation.add(1);
		
		//Back Button
		JButton backButton = new JButton("Back");
		backButton.addActionListener(myCsvMenuEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(backButton);
		//adding X position
		buttonLocation.add(0);
		//adding Y position
		buttonLocation.add(0);
		
		//Submit Button
		JButton submitButton = new JButton("Add From This File");
		submitButton.addActionListener(myCsvMenuEar);
		//adding button to button ArrayList that will be added to frame
		buttons.add(submitButton);
		//adding X position
		buttonLocation.add(1);
		//adding Y position
		buttonLocation.add(0);
		
		//Text Panel X location
		textPanelLocation.add(0);
		//Text Panel Y location
		textPanelLocation.add(0);
		
		//Button Panel X location
		buttonPanelLocation.add(0);
		//Button Panel Y location
		buttonPanelLocation.add(1);
		
		csvMenuFrame.createFrameNoGraph(2);
	}
	
	public void addCSV()
	{
		String filePath = pathEntry.getText();
		File csv = new File(filePath);
		ArrayList arrayHolder = new ArrayList();
		
		try 
		{
			Scanner myScanner = new Scanner(csv);
			myScanner.useDelimiter(",");
			
			while(myScanner.hasNextLine())
			{
				ArrayList infoHolder = new ArrayList();

				String currentLine = myScanner.nextLine();
				
				String[] tempStringHolder = new String[12];
				tempStringHolder = currentLine.split(",");
				
				for(int i =0;i< 12;i++)
				{
					try
					{
						infoHolder.add(tempStringHolder[i]);
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						infoHolder.add(" ");
					}
					
				}
								
				arrayHolder.add(infoHolder);
			}
			

			myScanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			myError.createErrorScreen(1);
		}
		
		csvInsert(arrayHolder);
	}
	
	public class createCsvMenuListner implements ActionListener
	{
	
		public void actionPerformed(ActionEvent e)
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Back"))
			{
				//Removes the current frame and creates the add alumni window
				DatabaseEditPage myEditPage = new DatabaseEditPage();
				
				myEditPage.CreateEditLayout();
				csvMenuFrame.closeFrame();
			}
			else if(buttonCommand.equals("Add From This File"))
			{
				//Removes the current frame and creates the remove alumni window
				DatabaseEditPage myEditPage = new DatabaseEditPage();
				
				addCSV();
				myEditPage.CreateEditLayout();
				csvMenuFrame.closeFrame();
		
			}
			else
				System.out.println("Error1");
		}
	}
}
