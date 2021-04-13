package myProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BackButton extends JButton  implements ActionListener{

	public BackButton() {
		
		this.setBounds(70, 10, 80, 40);
		this.addActionListener(this);
		ImageIcon icon = new ImageIcon("back.png");
		int offset = this.getInsets().left;
		this.setIcon(HomeFrame.resizeIcon(icon, this.getWidth() - offset, this.getHeight() - offset));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		MainClass.homeFrame.setVisible(true);	
		MainClass.entertainFrame.setVisible(false);
		MainClass.contactsFrame.setVisible(false);
		MainClass.bankForm.setVisible(false);
	}

}
