package myProject;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MazeFrame extends JFrame{
	
	public MazeFrame() {
		
		this.add(new MazePanel());
		this.setTitle("Maze");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() 
		{
            @Override
            public void windowClosing(WindowEvent e) 
            {
            	MainClass.entertainFrame.setVisible(true);             
            }
            
        });
		
	}

}
