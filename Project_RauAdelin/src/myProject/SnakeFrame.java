package myProject;

import java.awt.event.WindowAdapter;
import java.awt.event.*;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SnakeFrame extends JFrame
{
	SnakeFrame()
	{
	
		this.add(new SnakePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() 
		{
            @Override
            public void windowClosing(WindowEvent e) 
            {
            	MainClass.homeFrame.setVisible(false);
            	MainClass.entertainFrame.setVisible(true);             
            }
            
        });
	}

}
