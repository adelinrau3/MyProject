package myProject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{

	public BackgroundPanel() {
		
		ImageIcon icon = new ImageIcon("background.png");
		JLabel label =new JLabel();
		label.setIcon(icon);
		this.add(label);
		this.setBounds(0,0, 800, 800);
		
	}

}
