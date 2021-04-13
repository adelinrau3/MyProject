package myProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class EntertainFrame extends JFrame{
	
	public EntertainFrame() {
		
		ImageIcon icon = new ImageIcon("background.png");
		JLabel label =new JLabel();
		label.setIcon(icon);
		
		
		this.setTitle("Entertainment");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setSize(240, 550);
		this.setLayout(null);				
		
		this.add(new BackButton());
		this.add(new SnakeButton());
		this.add(new MazeButton());
		
		this.add(new BackgroundPanel());
		this.setVisible(false);
	}

	public class SnakeButton extends JButton  implements ActionListener{

		public SnakeButton() {
			this.setBounds(10, 70, 200, 200);
			this.addActionListener(this);
			ImageIcon icon = new ImageIcon("snake.jpg");

			int offset = this.getInsets().left;
			this.setIcon(HomeFrame.resizeIcon(icon, this.getWidth() - offset, this.getHeight() - offset));
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new SnakeFrame();
			MainClass.entertainFrame.setVisible(false);	
		}
	}
	
	public class MazeButton extends JButton  implements ActionListener{

		public MazeButton() {
			this.setBounds(10, 290, 200, 200);
			this.addActionListener(this);
			ImageIcon icon = new ImageIcon("maze.png");

			int offset = this.getInsets().left;
			this.setIcon(HomeFrame.resizeIcon(icon, this.getWidth() - offset, this.getHeight() - offset));
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new MazeFrame();
			MainClass.entertainFrame.setVisible(false);	
		}
	}
	
}
