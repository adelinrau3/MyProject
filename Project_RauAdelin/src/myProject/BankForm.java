package myProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class BankForm extends JFrame{
	
	private int n = 0;
	private int state = 0; // 0-first entry or change account, 1-send money, 2-receive money, 3-delete
	
	public BankForm() {
		ImageIcon icon = new ImageIcon("background.png");
		JLabel label =new JLabel();
		label.setIcon(icon);
		
		this.setTitle("Banking");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setSize(400, 480);
		this.setLayout(null);				
		
		BackButton backButton = new BackButton();
		backButton.setBounds(300, 10, 80, 40);
		this.add(backButton);
		
		JLabel lbl_info = new JLabel("Enter the ID of your account");	
		lbl_info.setVisible(true);
		lbl_info.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_info.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_info.setForeground(Color.CYAN);
		lbl_info.setBounds(30, 180, 300, 15);
		this.add(lbl_info);
		
		try {
		String url = "jdbc:mysql://localhost:3306/test";
		Statement sql;
		ResultSet rs;
		
		Connection con = DriverManager.getConnection (url, "root", "admin");
		sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs = sql.executeQuery("select * from banking");
		
		String[] id = new String[100];
		
		rs.beforeFirst();
		while(rs.next())
		{
			n++;
			id[n] = rs.getInt("Id") + "" ;
		}
		
		ArrayList list = new ArrayList();

		for(String s : id) 
			if(s != null && s.length() > 0) 
				list.add(s);

		id = (String[]) list.toArray(new String[list.size()]);

		JComboBox combo_id = new JComboBox(id);
		combo_id.setVisible(true);
		combo_id.setBounds(125, 220, 100, 20);
		this.add(combo_id);
		
		JLabel lbl_id = new JLabel("ID");
		lbl_id.setVisible(false);
		lbl_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id.setForeground(Color.CYAN);
		lbl_id.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_id.setBounds(20, 40, 100, 20);
		this.add(lbl_id);
		
		JLabel lbl_account = new JLabel("Account");
		lbl_account.setVisible(false);
		lbl_account.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_account.setForeground(Color.CYAN);
		lbl_account.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_account.setBounds(20, 70, 100, 20);
		this.add(lbl_account);
		
		JLabel lbl_owner = new JLabel("Owner");
		lbl_owner.setVisible(false);
		lbl_owner.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_owner.setForeground(Color.CYAN);
		lbl_owner.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_owner.setBounds(20, 100, 100, 20);
		this.add(lbl_owner);
		
		JLabel lbl_balance = new JLabel("Balance");
		lbl_balance.setVisible(false);
		lbl_balance.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_balance.setForeground(Color.CYAN);
		lbl_balance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_balance.setBounds(20, 130, 100, 20);
		this.add(lbl_balance);
		
		JTextField txt_id = new JTextField();
		txt_id.setVisible(false);
		txt_id.setEditable(false);
		txt_id.setHorizontalAlignment(SwingConstants.CENTER);
		txt_id.setBounds(140, 40, 100, 20);
		this.add(txt_id);
		
		JTextField txt_account = new JTextField();
		txt_account.setVisible(false);
		txt_account.setEditable(false);
		txt_account.setHorizontalAlignment(SwingConstants.CENTER);
		txt_account.setBounds(140, 70, 100, 20);
		this.add(txt_account);
		
		JTextField txt_owner = new JTextField();
		txt_owner.setVisible(false);
		txt_owner.setEditable(false);
		txt_owner.setHorizontalAlignment(SwingConstants.CENTER);
		txt_owner.setBounds(140, 100, 100, 20);
		this.add(txt_owner);
		
		JTextField txt_balance = new JTextField();
		txt_balance.setVisible(false);
		txt_balance.setEditable(false);
		txt_balance.setHorizontalAlignment(SwingConstants.CENTER);
		txt_balance.setBounds(140, 130, 100, 20);
		this.add(txt_balance);
		
		JLabel lbl_amount = new JLabel("Amount");
		lbl_amount.setVisible(false);
		lbl_amount.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_amount.setForeground(Color.CYAN);
		lbl_amount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_amount.setBounds(20, 190, 150, 20);
		this.add(lbl_amount);
		
		JLabel lbl_exchange = new JLabel("Exchange account");
		lbl_exchange.setVisible(false);
		lbl_exchange.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_exchange.setForeground(Color.CYAN);
		lbl_exchange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_exchange.setBounds(20, 220, 150, 20);
		this.add(lbl_exchange);
		
		JTextField txt_amount = new JTextField();
		txt_amount.setVisible(false);
		txt_amount.setEditable(true);
		txt_amount.setHorizontalAlignment(SwingConstants.CENTER);
		txt_amount.setBounds(175, 190, 100, 20);
		this.add(txt_amount);
		
		JButton confirmButton = new JButton();
		JButton sendButton = new JButton();
		JButton receiveButton = new JButton();
		JButton changeButton = new JButton();
		JButton addButton = new JButton();
		JButton deleteButton = new JButton();
		JButton cancelButton = new JButton();
		
		
		confirmButton.setBounds(125, 260, 100, 20);
		confirmButton.setText("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(state == 0) //first entry
				{

					combo_id.setBounds(125, 220, 100, 20);

					int idChosed = Integer.parseInt((String) combo_id.getSelectedItem());
					combo_id.setVisible(false);
					confirmButton.setVisible(false);
					cancelButton.setVisible(false);
					lbl_info.setVisible(false);

					lbl_id.setVisible(true);
					lbl_account.setVisible(true);
					lbl_owner.setVisible(true);
					lbl_balance.setVisible(true);

					txt_id.setVisible(true);
					txt_account.setVisible(true);
					txt_owner.setVisible(true);
					txt_balance.setVisible(true);

					sendButton.setVisible(true);
					receiveButton.setVisible(true);
					changeButton.setVisible(true);
					addButton.setVisible(true);
					deleteButton.setVisible(true);

					lbl_amount.setVisible(false);
					lbl_exchange.setVisible(false);
					txt_amount.setVisible(false);
					
					backButton.setVisible(true);

					try {
						rs.beforeFirst();

						while(rs.next())
						{

							if(rs.getInt("Id")== idChosed )
							{
								rs.getRow();

								txt_id.setText(rs.getInt("Id")+"");
								txt_account.setText(rs.getString("AccNo"));
								txt_owner.setText(rs.getString("Owner"));
								txt_balance.setText(rs.getInt("Balance")+"");

								break;
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}//end state = 0
				else if(state == 1 ) //send money
				{
					try {
						int idChosed = Integer.parseInt((String) combo_id.getSelectedItem());
						int idCurrent = Integer.parseInt(txt_id.getText());
						int amount = Integer.parseInt(txt_amount.getText());


						rs.beforeFirst();

						while(rs.next())
						{
							if(rs.getInt("Id")== idCurrent )
							{
								rs.getRow();
								break;
							}
						}

						if(rs.getInt("Balance") < amount)						
							JOptionPane.showMessageDialog(null, "Amount not available");
						else if(idChosed == idCurrent)
							JOptionPane.showMessageDialog(null, "You can't send money from one account to the same account");
						else
						{
							rs.updateInt("Balance", rs.getInt("Balance") - amount);
							rs.updateRow();
							
							rs.beforeFirst();
							while(rs.next())
								if(rs.getInt("Id")== idChosed )
								{
									rs.getRow();
									rs.updateInt("Balance", rs.getInt("Balance") + amount);
									rs.updateRow();
									break;
								}
							
							rs.beforeFirst();

							while(rs.next())
							{

								if(rs.getInt("Id")== idCurrent )
								{
									rs.getRow();

									txt_id.setText(rs.getInt("Id")+"");
									txt_account.setText(rs.getString("AccNo"));
									txt_owner.setText(rs.getString("Owner"));
									txt_balance.setText(rs.getInt("Balance")+"");

									break;
								}
							}
						}	
					} catch (SQLException e) {
						e.printStackTrace();
					}catch(NumberFormatException e){

						JOptionPane.showMessageDialog(null, "Amount field must be INTEGER only!");
						
					}
					
					combo_id.setVisible(false);
					confirmButton.setVisible(false);
					cancelButton.setVisible(false);
					lbl_info.setVisible(false);
					
					lbl_id.setVisible(true);
					lbl_account.setVisible(true);
					lbl_owner.setVisible(true);
					lbl_balance.setVisible(true);
					
					txt_id.setVisible(true);
					txt_account.setVisible(true);
					txt_owner.setVisible(true);
					txt_balance.setVisible(true);
					
					sendButton.setVisible(true);
					receiveButton.setVisible(true);
					changeButton.setVisible(true);
					addButton.setVisible(true);
					deleteButton.setVisible(true);
					
					lbl_amount.setVisible(false);
					lbl_exchange.setVisible(false);
					txt_amount.setVisible(false);
					
					backButton.setVisible(true);
				}//end state = 1 
				else if(state == 2 ) //receive money
				{
					try {
						int idChosed = Integer.parseInt((String) combo_id.getSelectedItem());
						int idCurrent = Integer.parseInt(txt_id.getText());
						int amount = Integer.parseInt(txt_amount.getText());


						rs.beforeFirst();

						while(rs.next())
						{
							if(rs.getInt("Id")== idChosed )
							{
								rs.getRow();
								break;
							}
						}
						
						if(rs.getInt("Balance") < amount)						
							JOptionPane.showMessageDialog(null, "Amount not available");
						else if(idChosed == idCurrent)
							JOptionPane.showMessageDialog(null, "You can't receive money from one account to the same account");
						else
						{
							rs.updateInt("Balance", rs.getInt("Balance") - amount);
							rs.updateRow();
							
							rs.beforeFirst();
							while(rs.next())
								if(rs.getInt("Id")== idCurrent )
								{
									rs.getRow();
									rs.updateInt("Balance", rs.getInt("Balance") + amount);
									rs.updateRow();
									break;
								}
							
							rs.beforeFirst();

							while(rs.next())
							{

								if(rs.getInt("Id")== idCurrent )
								{
									rs.getRow();

									txt_id.setText(rs.getInt("Id")+"");
									txt_account.setText(rs.getString("AccNo"));
									txt_owner.setText(rs.getString("Owner"));
									txt_balance.setText(rs.getInt("Balance")+"");

									break;
								}
							}
						}	
					} catch (SQLException e) {
						e.printStackTrace();
					}catch(NumberFormatException e){

						JOptionPane.showMessageDialog(null, "Amount field must be INTEGER only!");
						
					}
					
					combo_id.setVisible(false);
					confirmButton.setVisible(false);
					cancelButton.setVisible(false);
					lbl_info.setVisible(false);
					
					lbl_id.setVisible(true);
					lbl_account.setVisible(true);
					lbl_owner.setVisible(true);
					lbl_balance.setVisible(true);
					
					txt_id.setVisible(true);
					txt_account.setVisible(true);
					txt_owner.setVisible(true);
					txt_balance.setVisible(true);
					
					sendButton.setVisible(true);
					receiveButton.setVisible(true);
					changeButton.setVisible(true);
					addButton.setVisible(true);
					deleteButton.setVisible(true);
					
					lbl_amount.setVisible(false);
					lbl_exchange.setVisible(false);
					txt_amount.setVisible(false);

					backButton.setVisible(true);
				}//end state = 2 
				else if(state == 3) //add account
				{
					try {
						rs.moveToInsertRow();


						rs.updateInt("Id", Integer.parseInt(txt_id.getText()));

						rs.updateString("AccNo", txt_account.getText());
						rs.updateString("Owner", txt_owner.getText());
						rs.updateInt("Balance", Integer.parseInt(txt_balance.getText()));


						rs.insertRow();

						n++;

						combo_id.addItem(txt_id.getText());

						combo_id.setVisible(false);
						confirmButton.setVisible(false);
						cancelButton.setVisible(false);
						lbl_info.setVisible(false);

						lbl_id.setVisible(true);
						lbl_account.setVisible(true);
						lbl_owner.setVisible(true);
						lbl_balance.setVisible(true);

						txt_id.setVisible(true);
						txt_account.setVisible(true);
						txt_owner.setVisible(true);
						txt_balance.setVisible(true);

						sendButton.setVisible(true);
						receiveButton.setVisible(true);
						changeButton.setVisible(true);
						addButton.setVisible(true);
						deleteButton.setVisible(true);

						lbl_amount.setVisible(false);
						lbl_exchange.setVisible(false);
						txt_amount.setVisible(false);

						backButton.setVisible(true);

						txt_id.setEditable(false);
						txt_account.setEditable(false);
						txt_owner.setEditable(false);
						txt_balance.setEditable(false);
					}catch(NumberFormatException e){

						JOptionPane.showMessageDialog(null, "Balance and ID fields must be INTEGER only!");
						txt_id.setText("");
						txt_account.setText("");
						txt_owner.setText("");
						txt_balance.setText("");
					}
					catch (SQLException e) {
						
						JOptionPane.showMessageDialog(null, "ID already exists!");
						txt_id.setText("");
						txt_account.setText("");
						txt_owner.setText("");
						txt_balance.setText("");
					}


					
				}//end state 3
			}
		});
		this.add(confirmButton);
		
		
		
		sendButton.setBounds(120, 170, 130, 20);
		sendButton.setText("Send Money");
		sendButton.setVisible(false);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				combo_id.setBounds(175, 220, 100, 20);

				combo_id.setVisible(true);
				confirmButton.setVisible(true);
				cancelButton.setVisible(false);
				lbl_info.setVisible(false);

				lbl_id.setVisible(false);
				lbl_account.setVisible(false);
				lbl_owner.setVisible(false);
				lbl_balance.setVisible(false);

				txt_id.setVisible(false);
				txt_account.setVisible(false);
				txt_owner.setVisible(false);
				txt_balance.setVisible(false);
				
				sendButton.setVisible(false);
				receiveButton.setVisible(false);
				changeButton.setVisible(false);
				addButton.setVisible(false);
				deleteButton.setVisible(false);
				
				lbl_amount.setVisible(true);
				lbl_exchange.setVisible(true);
				txt_amount.setVisible(true);
				
				backButton.setVisible(false);
				
				state = 1;
			}
		});
		this.add(sendButton);
		
		
		receiveButton.setBounds(120, 200, 130, 20);
		receiveButton.setText("Receive Money");
		receiveButton.setVisible(false);
		receiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				combo_id.setBounds(175, 220, 100, 20);

				combo_id.setVisible(true);
				confirmButton.setVisible(true);
				cancelButton.setVisible(false);
				lbl_info.setVisible(false);

				lbl_id.setVisible(false);
				lbl_account.setVisible(false);
				lbl_owner.setVisible(false);
				lbl_balance.setVisible(false);

				txt_id.setVisible(false);
				txt_account.setVisible(false);
				txt_owner.setVisible(false);
				txt_balance.setVisible(false);
				
				sendButton.setVisible(false);
				receiveButton.setVisible(false);
				changeButton.setVisible(false);
				addButton.setVisible(false);
				deleteButton.setVisible(false);
				
				lbl_amount.setVisible(true);
				lbl_exchange.setVisible(true);
				txt_amount.setVisible(true);
				
				backButton.setVisible(false);
				
				state = 2;
			}
		});
		this.add(receiveButton);

		changeButton.setBounds(120, 230, 130, 20);
		changeButton.setText("Change Account");
		changeButton.setVisible(false);
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				combo_id.setBounds(125, 220, 100, 20);

				combo_id.setVisible(true);
				confirmButton.setVisible(true);
				cancelButton.setVisible(false);
				lbl_info.setVisible(true);

				lbl_id.setVisible(false);
				lbl_account.setVisible(false);
				lbl_owner.setVisible(false);
				lbl_balance.setVisible(false);

				txt_id.setVisible(false);
				txt_account.setVisible(false);
				txt_owner.setVisible(false);
				txt_balance.setVisible(false);
				
				sendButton.setVisible(false);
				receiveButton.setVisible(false);
				changeButton.setVisible(false);
				addButton.setVisible(false);
				deleteButton.setVisible(false);
				
				lbl_amount.setVisible(false);
				lbl_exchange.setVisible(false);
				txt_amount.setVisible(false);
				
				backButton.setVisible(true);
				
				state = 0;
			}
		});
		this.add(changeButton);
		
		
		addButton.setBounds(120, 260, 130, 20);
		addButton.setText("Add Account");
		addButton.setVisible(false);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				combo_id.setBounds(125, 220, 100, 20);

				combo_id.setVisible(false);
				confirmButton.setVisible(true);
				cancelButton.setVisible(true);
				lbl_info.setVisible(false);

				lbl_id.setVisible(true);
				lbl_account.setVisible(true);
				lbl_owner.setVisible(true);
				lbl_balance.setVisible(true);

				txt_id.setVisible(true);
				txt_id.setText("");
				txt_id.setEditable(true);
				
				txt_account.setVisible(true);
				txt_account.setText("");
				txt_account.setEditable(true);
				
				txt_owner.setVisible(true);
				txt_owner.setText("");
				txt_owner.setEditable(true);
				
				txt_balance.setVisible(true);
				txt_balance.setText("");
				txt_balance.setEditable(true);
				
				sendButton.setVisible(false);
				receiveButton.setVisible(false);
				changeButton.setVisible(false);
				addButton.setVisible(false);
				deleteButton.setVisible(false);
				
				lbl_amount.setVisible(false);
				lbl_exchange.setVisible(false);
				txt_amount.setVisible(false);
				
				backButton.setVisible(false);
				
				state = 3;
			}
		});
		this.add(addButton);

		
		deleteButton.setBounds(120, 290, 130, 20);
		deleteButton.setText("Delete Account");
		deleteButton.setVisible(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
				{
					try {
						rs.deleteRow();
											
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					combo_id.removeItem(txt_id.getText());	
					
					combo_id.setVisible(true);
					confirmButton.setVisible(true);
					cancelButton.setVisible(false);
					lbl_info.setVisible(true);

					lbl_id.setVisible(false);
					lbl_account.setVisible(false);
					lbl_owner.setVisible(false);
					lbl_balance.setVisible(false);

					txt_id.setVisible(false);
					txt_account.setVisible(false);
					txt_owner.setVisible(false);
					txt_balance.setVisible(false);
					
					sendButton.setVisible(false);
					receiveButton.setVisible(false);
					changeButton.setVisible(false);
					addButton.setVisible(false);
					deleteButton.setVisible(false);
					
					lbl_amount.setVisible(false);
					lbl_exchange.setVisible(false);
					txt_amount.setVisible(false);
					
					backButton.setVisible(true);
					
					state = 0;
				}
			}
		});
		this.add(deleteButton);
	
		
		cancelButton.setBounds(125, 290, 100, 20);
		cancelButton.setText("Cancel");
		cancelButton.setVisible(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				combo_id.setVisible(true);
				confirmButton.setVisible(true);
				cancelButton.setVisible(false);
				lbl_info.setVisible(true);

				lbl_id.setVisible(false);
				lbl_account.setVisible(false);
				lbl_owner.setVisible(false);
				lbl_balance.setVisible(false);

				txt_id.setVisible(false);
				txt_account.setVisible(false);
				txt_owner.setVisible(false);
				txt_balance.setVisible(false);
				
				sendButton.setVisible(false);
				receiveButton.setVisible(false);
				changeButton.setVisible(false);
				addButton.setVisible(false);
				deleteButton.setVisible(false);
				
				lbl_amount.setVisible(false);
				lbl_exchange.setVisible(false);
				txt_amount.setVisible(false);
				
				backButton.setVisible(true);
				
				state = 0;
			}
		});
		this.add(cancelButton);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.add(new BackgroundPanel());
		this.setVisible(false);

	}

}
