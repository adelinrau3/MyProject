package myProject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class LoginBankForm extends JFrame {
	
	public LoginBankForm() {
		
		this.setTitle("Login");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setSize(300, 180);
		this.setLayout(null);
		
		JLabel lbl_user = new JLabel("User");
		lbl_user.setVisible(true);
		lbl_user.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_user.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_user.setBounds(10, 10, 100, 15);
		this.add(lbl_user);
		
		JLabel lbl_pass = new JLabel("Password");
		lbl_pass.setVisible(true);
		lbl_pass.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_pass.setBounds(10, 40, 100, 15);
		this.add(lbl_pass);
		
		JTextField txt_user = new JTextField();
		txt_user.setEditable(true);
		txt_user.setBounds(140, 10, 100, 15);
		this.add(txt_user);
		
		JPasswordField txt_pass = new JPasswordField();
		txt_pass.setEditable(true);
		txt_pass.setBounds(140, 40, 100, 15);
		this.add(txt_pass);
		
		JButton loginButton = new JButton();
		loginButton.setBounds(20, 70, 100, 15);
		loginButton.setText("Login");
		loginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) 
			{
				if(txt_user.getText().equals("admin") && txt_pass.getText().equals("admin"))
				{
					MainClass.loginBankForm.setVisible(false);
					MainClass.bankForm.setVisible(true);
					
				}
				else JOptionPane.showMessageDialog(null, "Login failed!");
				
				txt_user.setText("");
				txt_pass.setText("");
			}
		});
		this.add(loginButton);
		
		CancelButton cancelButton = new CancelButton();
		this.add(cancelButton);
		
		this.setVisible(false);
	}	
	
	
	public class CancelButton extends JButton  implements ActionListener{

		public CancelButton() {
			this.setBounds(140, 70, 100, 15);
			this.addActionListener(this);
			this.setText("Cancel");
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			MainClass.homeFrame.setVisible(true);
			MainClass.loginBankForm.setVisible(false);	
		}
	}

}
