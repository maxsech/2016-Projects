import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ErrorScreen
{
	private JFrame myErrorFrame = new JFrame("Error");
	final int WIDTH = 300;
	final int LENGTH = 100;

	public void createErrorScreen(int modifier)
	{
		JPanel errorButtonPanel = new JPanel();
		
		myErrorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myErrorFrame.setSize(WIDTH, LENGTH);
		myErrorFrame.getContentPane().setBackground(new Color(0,45,88));
		myErrorFrame.setLocationRelativeTo(null);
		
		if(modifier == 1)
		{
			String inputText = "CSV File Not Found";
			JLabel alertText = new JLabel(inputText, JLabel.CENTER);
			alertText.setForeground(Color.WHITE);
			alertText.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			myErrorFrame.add(alertText, BorderLayout.NORTH);
		}
		
		JButton button1 = new JButton("Ok");	
		
		
		ErrorButtonListener myErrorEar = new ErrorButtonListener();
		button1.addActionListener(myErrorEar);
		
		errorButtonPanel.setLayout(new FlowLayout());
		errorButtonPanel.setBackground(new Color(0,45,88));
		errorButtonPanel.add(button1);
		
		myErrorFrame.add(errorButtonPanel, BorderLayout.SOUTH);
		
		myErrorFrame.setVisible(true);
	}
	
	public class ErrorButtonListener implements ActionListener	
	{
		public void actionPerformed(ActionEvent e)	
		{
			String buttonCommand = e.getActionCommand();
			
			if(buttonCommand.equals("Ok"))	
			{
				myErrorFrame.dispose();
			}
		}
	}
}
