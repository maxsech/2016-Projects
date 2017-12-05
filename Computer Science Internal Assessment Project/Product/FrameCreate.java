//Max Sechelski 12/19/2016
//The following code establishes methods which create a window with buttons, text, and text fields that are input

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FrameCreate
{ 
	private JFrame myFrame = new JFrame("Alumni Search");
	JPanel buttonPanel = new JPanel(new GridBagLayout());
	JPanel textFieldPanel = new JPanel(new GridBagLayout());
	JPanel textPanel = new JPanel(new GridBagLayout());
	JPanel tempPanel = new JPanel(new GridBagLayout());
	
	private final int WIDTH=1100;	
	private final int LENGTH=775;
	private GridBagConstraints gbContraints = new GridBagConstraints();
	
	//Creating an array list that stores buttons to be put on the screen
	private ArrayList<Component> buttonToAdd = new ArrayList<Component>();
	//holds the location of the buttons in their respective panels in (x1, y1, x2, y2, ect) order
	private ArrayList<Integer> buttonGridLocation = new ArrayList<Integer>();
	//holds the location of the button panel
	private ArrayList<Integer> buttonPanelGridLocation = new ArrayList<Integer>();
	
	 //Creating an array list that stores text to be put on the screen
	private ArrayList<Component> texttoAdd = new ArrayList<Component>();
	//holds the location of the texts in their respective panels in (x1, y1, x2, y2, ect) order
	private ArrayList<Integer> textGridLocation = new ArrayList<Integer>();
	//holds the location of the text field panel
	private ArrayList<Integer> textPanelGridLocation = new ArrayList<Integer>();	

	//Creating an array list that stores text fields to be put on the screen
	private ArrayList<Component> textFieldToAdd  = new ArrayList<Component>(); 
	//holds the location of the text fields in their respective panels in (x1, y1, x2, y2, ect) order
	private ArrayList<Integer> textFieldGridLocation  = new ArrayList<Integer>();
	//holds the location of the text field panel
	private ArrayList<Integer> textFieldPanelGridLocation  = new ArrayList<Integer>();	

	private Component tablePanelToAdd;
	
	
	//The following constructor sets the above arraylists to its input 
	public FrameCreate(ArrayList<Component> buttonComponents , ArrayList<Integer> buttonLocation, ArrayList<Integer> buttonPanelLocation,
					   ArrayList<Component> textComponents, ArrayList<Integer> textLocation, ArrayList<Integer> textPanelLocation,
					   ArrayList<Component> textFieldComponents, ArrayList<Integer> textFieldLocation,  ArrayList<Integer> textFieldPanelLocation,
					   Component tablePanel)
	{
		buttonToAdd = buttonComponents;
		buttonGridLocation = buttonLocation;
		buttonPanelGridLocation = buttonPanelLocation;
		
		texttoAdd = textComponents;
		textGridLocation = textLocation;
		textPanelGridLocation = textPanelLocation;
		
		textFieldToAdd = textFieldComponents;
		textFieldGridLocation = textFieldLocation;
		textFieldPanelGridLocation = textFieldPanelLocation;
		
		tablePanelToAdd = tablePanel;
	
		myFrame.setLayout(new GridBagLayout());
	}
	
	//The following constructor sets the above arraylists to its input, this time without a graphical table 
	public FrameCreate(ArrayList<Component> buttonComponents , ArrayList<Integer> buttonLocation, ArrayList<Integer> buttonPanelLocation,
					   ArrayList<Component> textComponents, ArrayList<Integer> textLocation, ArrayList<Integer> textPanelLocation,
					   ArrayList<Component> textFieldComponents, ArrayList<Integer> textFieldLocation,  ArrayList<Integer> textFieldPanelLocation)
	{
		buttonToAdd = buttonComponents;
		buttonGridLocation = buttonLocation;
		buttonPanelGridLocation = buttonPanelLocation;
		
		texttoAdd = textComponents;
		textGridLocation = textLocation;
		textPanelGridLocation = textPanelLocation;
		
		textFieldToAdd = textFieldComponents;
		textFieldGridLocation = textFieldLocation;
		textFieldPanelGridLocation = textFieldPanelLocation;
	
		tablePanelToAdd = null;
	
		myFrame.setLayout(new GridBagLayout());
	}
	
	//The following method creates the frame according to what is spec'd above. The input 'stuff' determines what occurs when the window is closed
	public void createFrame(int stuff)
	{
		myFrame.dispose();
		
		if(stuff == 1)
		{
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else if(stuff == 2)
		{
			myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else
		{
			System.out.println("Error404");
		}
			
		myFrame.setSize(WIDTH, LENGTH);	
		myFrame.getContentPane().setBackground(new Color(0,45,88));
		
		//calling method that creates and adds the button panel
		myButtonPanel();
		//calling method that creates and adds the text panel
		myTextPanel();
		//calling method that creates and adds the textfield panel
		myTextFieldPanel();
		addTablePanel(tablePanelToAdd);
		
		//Line 137 sets the location of the frame to the center of the screen
		myFrame.setLocationRelativeTo(null); 
		myFrame.setVisible(true);
	}
	
	public void createFrameNoGraph(int stuff)
	{
		myFrame.dispose();
		
		if(stuff == 1)
		{
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else if(stuff == 2)
		{
			myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else
		{
			System.out.println("Error404");
		}
			
		myFrame.setSize(WIDTH, LENGTH);	
		myFrame.getContentPane().setBackground(new Color(0,45,88));
		
		//calling method that creates and adds the button panel
		myButtonPanel();
		//calling method that creates and adds the text panel
		myTextPanel();
		//calling method that creates and adds the textfield panel
		myTextFieldPanel();
		
		myFrame.setLocationRelativeTo(null); 
		myFrame.setVisible(true);
	}
	//The following method adds the table to the frame, its location is always set at 0,0
	private void addTablePanel(Component tablePanel)
	{
		gbContraints.gridx=0;
		gbContraints.gridy=0;
		myFrame.add(tablePanel, gbContraints);
		tablePanel.setVisible(true);
	}
	
	//The following method adds buttons to a panel and adds that panel to the frame
	private void myButtonPanel()
	{
		buttonPanel.setBackground(new Color(0,45,88));
		
		//getting the length of the input ArrayList of buttons 
		int arraySize = buttonToAdd.size();
		
		//The following loop runs through the ArrayList and adds the individual components to a panel
		for(int counter = 0; counter < arraySize; counter++)
		{	
			Component tempButton = new JButton();
			tempButton = buttonToAdd.get(counter);
			tempButton.setFont(new Font("Helvetica", Font.PLAIN, 12));
			tempButton.setPreferredSize(new Dimension(170, 60));
						
			int tempXLocation = buttonGridLocation.get(0+(2 * counter));
			int tempYLocation = buttonGridLocation.get(1+(2* counter));
			
			gbContraints.gridx=tempXLocation;
			gbContraints.gridy=tempYLocation;
			
			buttonPanel.add(tempButton, gbContraints); 
		}
		try
		{
			//The follow code gets the designated coordinates of the button panel and adds the panel to the frame with those coordinates
			int tempXLocation = buttonPanelGridLocation.get(0);
			int tempYLocation = buttonPanelGridLocation.get(1);	
			gbContraints.gridx=tempXLocation;
			gbContraints.gridy=tempYLocation;
			myFrame.add(buttonPanel,gbContraints); 
		}
		catch(IndexOutOfBoundsException e)
		{
		}
		
		buttonPanel.setVisible(true);
	}
	
	//The following method adds the text to a panel and adds that panel to the frame
	private void myTextPanel()
	{
		textPanel.setBackground(new Color(0,45,88));
		
		//getting the length of the input ArrayList of text 
		int arraySize = texttoAdd.size();
		
		//The following loop runs through the ArrayList and adds the individual components to a panel
		for(int counter = 0; counter < arraySize; counter++)
		{
			Component tempLabel = new JLabel();
			tempLabel = texttoAdd.get(counter);
			int tempXLocation = textGridLocation.get(0+(2 * counter));
			int tempYLocation = textGridLocation.get(1+(2* counter));
			
			gbContraints.gridx=tempXLocation;
			gbContraints.gridy=tempYLocation;
			
			textPanel.add(tempLabel, gbContraints); 
		}
		
		try
		{
			//The follow code gets the designated coordinates of the text panel and adds the panel to the frame with those coordinates
			int tempXLocation = textPanelGridLocation.get(0);
			int tempYLocation = textPanelGridLocation.get(1);
			gbContraints.gridx=tempXLocation;
			gbContraints.gridy=tempYLocation;
			myFrame.add(textPanel,gbContraints);
			textPanel.setVisible(true);
		} 
		catch (IndexOutOfBoundsException e) 
		{
		}
	
	}
	
	//The following method adds the textfields to a panel and adds that panel to the frame
	private void myTextFieldPanel()
	{		
		//getting the length of the input ArrayList of textFields 
		int arraySize = textFieldToAdd.size();
		
		//The following loop runs through the ArrayList and adds the individual components to a panel
		for(int counter = 0; counter < arraySize; counter++)
		{
			int tempXLocation = textFieldGridLocation.get(1+(2 * counter));
			int tempYLocation = textFieldGridLocation.get(2+(2* counter));
			
			gbContraints.gridx=tempXLocation;
			gbContraints.gridy=tempYLocation;
			
			textFieldPanel.add(textFieldToAdd.get(counter), gbContraints); 
		}
		 
		try
		{
			//The follow code gets the designated coordinates of the textField panel and adds the panel to the frame with those coordinates
			int tempXLocation = textFieldPanelGridLocation.get(0);
			int tempYLocation = textFieldPanelGridLocation.get(1);	
			gbContraints.gridx=tempXLocation;
			gbContraints.gridy=tempYLocation;
			myFrame.add(textFieldPanel,gbContraints);
			textFieldPanel.setVisible(true);
		}
		catch(IndexOutOfBoundsException e)
		{
		}
	}
	
	public void hotAdd(JPanel tempItem, int xLocation, int yLocation)
	{
		tempPanel = tempItem;
		
		tempPanel.setBackground(new Color(0,45,88));
		
		gbContraints.gridx=xLocation;
		gbContraints.gridy=yLocation;
		
		myFrame.add(tempPanel, gbContraints);
		myFrame.revalidate();
	}
	
	public void removeHotAdd()
	{
		myFrame.remove(tempPanel);
		myFrame.revalidate();
	}
	
	public void refreshButtonPanel()
	{
		buttonPanel.removeAll();
		myButtonPanel();
		myFrame.revalidate();
	}
	
	public void refreshTextPanel()
	{
		textPanel.removeAll();
		myTextPanel();
		myFrame.revalidate();
	}
	
	public void refreshGraphicPanel()
	{
		addTablePanel(tablePanelToAdd);
		myFrame.revalidate();
	}
	
	public void closeFrame()
	{

		myFrame.dispose();	
	}
	
}
