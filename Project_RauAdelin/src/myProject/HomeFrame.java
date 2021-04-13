package myProject;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HomeFrame extends JFrame{
	
	public HomeFrame() {
		
		ImageIcon icon = new ImageIcon("background.png");
		JLabel label =new JLabel();
		label.setIcon(icon);
		
		this.setTitle("Home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(450, 480);
		this.setLayout(null);				
		
		this.add(new EntertainButton());
		this.add(new BankingButton());
		this.add(new ToDoListButton());
		this.add(new ContactsButton());
		
		this.add(new BackgroundPanel());
		this.setVisible(true);
	}
	public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();  
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
		return new ImageIcon(resizedImage);
	}

	public class EntertainButton extends JButton  implements ActionListener
	{
		public EntertainButton() {
			this.setBounds(10, 20, 200, 200);
			this.addActionListener(this);
			ImageIcon icon = new ImageIcon("entertainment.png");

			int offset = this.getInsets().left;
			this.setIcon(resizeIcon(icon, this.getWidth() - offset, this.getHeight() - offset));
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			//new SnakeFrame();
			
			MainClass.homeFrame.setVisible(false);
			MainClass.entertainFrame.setVisible(true);		
		}
	}
	
	public class ContactsButton extends JButton  implements ActionListener{
		
		public ContactsButton() {
			
			this.setBounds(220, 230, 200, 200);
			this.addActionListener(this);
			ImageIcon icon = new ImageIcon("contact.png");

			int offset = this.getInsets().left;
			this.setIcon(resizeIcon(icon, this.getWidth() - offset, this.getHeight() - offset));
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainClass.homeFrame.setVisible(false);
			MainClass.contactsFrame.setVisible(true);	

		}

	}

	public class BankingButton extends JButton  implements ActionListener{
		
		public BankingButton() {
			
			this.setBounds(10, 230, 200, 200);
			this.addActionListener(this);
			ImageIcon icon = new ImageIcon("banking.png");

			int offset = this.getInsets().left;
			this.setIcon(resizeIcon(icon, this.getWidth() - offset, this.getHeight() - offset));
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			MainClass.homeFrame.setVisible(false);
			MainClass.loginBankForm.setVisible(true);
		}

	}

	public class ToDoListButton extends JButton  implements ActionListener{

		public ToDoListButton() {
			
			this.setBounds(220, 20, 200, 200);
			this.addActionListener(this);
			ImageIcon icon = new ImageIcon("toDo.png");

			int offset = this.getInsets().left;
			this.setIcon(resizeIcon(icon, this.getWidth() - offset, this.getHeight() - offset));
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			MainClass.homeFrame.setVisible(false);
			MainClass.toDoForm.setVisible(true);
		}
	}


}
