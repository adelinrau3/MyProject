package myProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class ContactsFrame extends JFrame {

	private int n = 0;
	private int state = 0;
	
	public ContactsFrame() {


		ImageIcon icon = new ImageIcon("background.png");
		JLabel label =new JLabel();
		label.setIcon(icon);

		this.setTitle("Contacts");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setSize(570, 480);
		this.setLayout(null);					



		try {
			String url = "jdbc:mysql://localhost:3306/test";
			Statement sql;
			ResultSet rs;
				
			Connection con = DriverManager.getConnection (url, "root", "admin");
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = sql.executeQuery("select * from contacts");
			rs.beforeFirst();
			
			while(rs.next()) n++;
			
			rs.first();
			
			JTextField textCount = new JTextField();
			JButton btn_moveLeft = new JButton();
			JButton btn_moveLast = new JButton();
			JButton btn_moveFirst = new JButton();
			JButton btn_moveRight = new JButton();
			JButton btn_add = new JButton();
			JButton btn_edit = new JButton();
			JButton btn_delete = new JButton();
			JButton btn_search = new JButton();
			JButton btn_save = new JButton();
			JButton btn_undo = new JButton();
			
			JTextField textName = new JTextField();
			textName.setHorizontalAlignment(SwingConstants.CENTER);
			
			JTextField textPhone = new JTextField();
			textPhone.setHorizontalAlignment(SwingConstants.CENTER);
			
			JTextField textAge = new JTextField();
			textAge.setHorizontalAlignment(SwingConstants.CENTER);
			
			JTextField textAdress = new JTextField();
			textAdress.setHorizontalAlignment(SwingConstants.CENTER);
			
			if(n==0)
	        {
				textCount.setText("0/0");
	            textName.setText("");
	            textPhone.setText("");
	            textAdress.setText("");
	            textAge.setText("");
	            btn_moveRight.setEnabled(false);
	            btn_moveLast.setEnabled(false);
	            btn_delete.setEnabled(false);
	        	btn_search.setEnabled(false);
	        	btn_edit.setEnabled(false);
	        }
			else if(n==1)
	        {
				rs.first();
	            textName.setText(rs.getString("Name")+"");
	            textCount.setText(rs.getRow()+ "/" + n);
	            textPhone.setText(rs.getString("PhoneNumber"));
	            textAge.setText(rs.getInt("Age")+"");
	            textAdress.setText(rs.getString("Adress"));
	            btn_moveRight.setEnabled(false);
	            btn_moveLast.setEnabled(false);
	        }
	        else
	        {
	            rs.first();
	            textCount.setText(rs.getRow()+ "/" + n);
	            textName.setText(rs.getString("Name")+"");
	            textPhone.setText(rs.getString("PhoneNumber"));
	            textAge.setText(rs.getInt("Age")+"");
	            textAdress.setText(rs.getString("Adress"));
	        }
			
			
			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setBounds(10, 10, 531, 31);
			this.add(toolBar);
			
			btn_moveFirst.setEnabled(false);
			btn_moveFirst.setIcon(new ImageIcon("ToolbarImages\\MoveFirst.png"));
			toolBar.add(btn_moveFirst);
			btn_moveFirst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						rs.first();
						textCount.setText("1/"+n);
						btn_moveLeft.setEnabled(false);
						btn_moveFirst.setEnabled(false);	
						btn_moveLast.setEnabled(true);
						btn_moveRight.setEnabled(true);	

			            textName.setText(rs.getString("Name")+"");
			            textPhone.setText(rs.getString("PhoneNumber"));
			            textAge.setText(rs.getInt("Age")+"");
			            textAdress.setText(rs.getString("Adress"));
							
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			});
			
			btn_moveLeft.setEnabled(false);
			btn_moveLeft.setIcon(new ImageIcon("ToolbarImages\\MovePrevious.png"));
			toolBar.add(btn_moveLeft);
			btn_moveLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						rs.previous();
						textCount.setText(rs.getRow()+"/"+n);
						if(rs.isFirst()) 
						{
							btn_moveLeft.setEnabled(false);
							btn_moveFirst.setEnabled(false);
							
						}
						btn_moveLast.setEnabled(true);
						btn_moveRight.setEnabled(true);
						
			            textName.setText(rs.getString("Name")+"");
			            textPhone.setText(rs.getString("PhoneNumber"));
			            textAge.setText(rs.getInt("Age")+"");
			            textAdress.setText(rs.getString("Adress"));
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});			
			
			textCount.setEditable(false);
			textCount.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textCount.setHorizontalAlignment(SwingConstants.CENTER);
			toolBar.add(textCount);
			textCount.setColumns(100);
			
			btn_moveRight.setIcon(new ImageIcon("ToolbarImages\\MoveNext.png"));
			toolBar.add(btn_moveRight);
			btn_moveRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						rs.next();
						textCount.setText(rs.getRow()+"/"+n);
						if(rs.isLast())
						{
							btn_moveLast.setEnabled(false);
							btn_moveRight.setEnabled(false);
						}
						
						btn_moveFirst.setEnabled(true);
						btn_moveLeft.setEnabled(true);
						
			            textName.setText(rs.getString("Name")+"");
			            textPhone.setText(rs.getString("PhoneNumber"));
			            textAge.setText(rs.getInt("Age")+"");
			            textAdress.setText(rs.getString("Adress"));
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			

			btn_moveLast.setIcon(new ImageIcon("ToolbarImages\\MoveLast.png"));
			toolBar.add(btn_moveLast);
			btn_moveLast.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						rs.last();
						textCount.setText(n+"/"+n);
						btn_moveLast.setEnabled(false);
						btn_moveFirst.setEnabled(true);
						btn_moveLeft.setEnabled(true);
						btn_moveRight.setEnabled(false);
						
			            textName.setText(rs.getString("Name")+"");
			            textPhone.setText(rs.getString("PhoneNumber"));
			            textAge.setText(rs.getInt("Age")+"");
			            textAdress.setText(rs.getString("Adress"));
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			
			
			btn_add.setIcon(new ImageIcon("ToolbarImages\\Add.png"));
			toolBar.add(btn_add);
			btn_add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					btn_moveLast.setEnabled(false);
					btn_moveFirst.setEnabled(false);
					btn_moveLeft.setEnabled(false);
					btn_moveRight.setEnabled(false);
					btn_edit.setEnabled(false);
					btn_delete.setEnabled(false);
					btn_search.setEnabled(false);
					btn_add.setEnabled(false);
					btn_save.setEnabled(true);
					btn_undo.setEnabled(true);
					
					textName.setText("");
					textName.setEditable(true);
					
					textPhone.setText("");
					textPhone.setEditable(true);
					
					textAge.setText("");
					textAge.setEditable(true);
					
					textAdress.setText("");
					textAdress.setEditable(true);
					
					textCount.setText("");
					state = 1;
								
				}
			});
			
			
			btn_edit.setIcon(new ImageIcon("ToolbarImages\\Edit.png"));
			toolBar.add(btn_edit);
			btn_edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{						
						btn_moveLast.setEnabled(false);
						btn_moveFirst.setEnabled(false);
						btn_moveLeft.setEnabled(false);
						btn_moveRight.setEnabled(false);
						btn_edit.setEnabled(false);
						btn_delete.setEnabled(false);
						btn_search.setEnabled(false);
						btn_add.setEnabled(false);
						btn_save.setEnabled(true);
						btn_undo.setEnabled(true);
						
						textName.setEditable(true);
						textPhone.setEditable(false);
						textAge.setEditable(true);
						textAdress.setEditable(true);
						
						state=2;
				}
			});
			
			btn_delete.setIcon(new ImageIcon("ToolbarImages\\Delete.png"));
			toolBar.add(btn_delete);
			btn_delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
	                    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?", "WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

	                        rs.deleteRow();
	                        if(rs.isBeforeFirst())
	                            rs.next();
	                        n--;
	                        if(n==1)
	                        {
	                        	btn_moveFirst.setEnabled(false);
	                        	btn_moveLeft.setEnabled(false);
	                        	btn_moveLast.setEnabled(false);
	                        	btn_moveRight.setEnabled(false);
	                        }
	                        else if(rs.isFirst())
	                        {
	                        	btn_moveFirst.setEnabled(false);
	                        	btn_moveLeft.setEnabled(false);
	                        	btn_moveLast.setEnabled(true);
	                        	btn_moveRight.setEnabled(true);
	                        }
	                        else  if(rs.isLast())
	                        {
	                        	btn_moveFirst.setEnabled(true);
	                        	btn_moveLeft.setEnabled(true);
	                        	btn_moveLast.setEnabled(false);
	                        	btn_moveRight.setEnabled(false);
	                        }
	                        else
	                        {
	                        	btn_moveFirst.setEnabled(true);
	                        	btn_moveLeft.setEnabled(true);
	                        	btn_moveLast.setEnabled(true);
	                        	btn_moveRight.setEnabled(true);
	                        }
	                        if(n==0)
	                        {
	                        	btn_moveLast.setEnabled(false);
	                        	btn_moveRight.setEnabled(false);

	                        	btn_moveFirst.setEnabled(false);
	                        	btn_moveLeft.setEnabled(false);
	                        	rs.beforeFirst();
	                        	textName.setText("");
	                        	textPhone.setText("");
	                        	textAge.setText("");
	                        	textAdress.setText("");
	                        	
	                        	btn_delete.setEnabled(false);
	                        	btn_search.setEnabled(false);
	                        	btn_edit.setEnabled(false);
	                        }
	                        else
	                        {	                      
	            	            textName.setText(rs.getString("Name")+"");
	            	            textPhone.setText(rs.getString("PhoneNumber"));
	            	            textAge.setText(rs.getInt("Age")+"");
	            	            textAdress.setText(rs.getString("Adress"));
	                        }
	                        textCount.setText(rs.getRow()+"/"+n);
	                    } else {
	                    	// no option
	                    }
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
	            }
					
			});
			
			btn_search.setIcon(new ImageIcon("ToolbarImages\\find.JPG"));
			toolBar.add(btn_search);
			btn_search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						String name = JOptionPane.showInputDialog("Enter name:");
						rs.beforeFirst();
						int state = 0;
						while(rs.next())
						{
							
							if(rs.getString("Name").equals(name))
							{
								rs.getRow();
					            textName.setText(rs.getString("Name")+"");
					            textPhone.setText(rs.getString("PhoneNumber"));
					            textAge.setText(rs.getInt("Age")+"");
					            textAdress.setText(rs.getString("Adress"));;
	                        	textCount.setText(rs.getRow()+"/"+n);
	                        	state = 1;
	                        	break;
							}
						}
						
						if(state==1)
						{
							rs.getRow();
							if(rs.isFirst())
	                        {
	                        	btn_moveFirst.setEnabled(false);
	                        	btn_moveLeft.setEnabled(false);
	                        	btn_moveLast.setEnabled(true);
	                        	btn_moveRight.setEnabled(true);
	                        }
	                        else if(rs.isLast())
	                        {
	                        	btn_moveFirst.setEnabled(true);
	                        	btn_moveLeft.setEnabled(true);
	                        	btn_moveLast.setEnabled(false);
	                        	btn_moveRight.setEnabled(false);
	                        }
	                        else
	                        {
	                        	btn_moveFirst.setEnabled(true);
	                        	btn_moveLeft.setEnabled(true);
	                        	btn_moveLast.setEnabled(true);
	                        	btn_moveRight.setEnabled(true);
	                        }
						}
						else if(state==0)
						{
							rs.first();
				            textName.setText(rs.getString("Name")+"");
				            textPhone.setText(rs.getString("PhoneNumber"));
				            textAge.setText(rs.getInt("Age")+"");
				            textAdress.setText(rs.getString("Adress"));
	                    	textCount.setText(rs.getRow()+"/"+n);
	                    	btn_moveFirst.setEnabled(false);
	                    	btn_moveLeft.setEnabled(false);
	                    	btn_moveLast.setEnabled(true);
	                    	btn_moveRight.setEnabled(true);
	                    	JOptionPane.showMessageDialog(null, "Name not found!");
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			
			btn_save.setEnabled(false);
			btn_save.setIcon(new ImageIcon("ToolbarImages\\save.JPG"));
			toolBar.add(btn_save);
			btn_save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						if(state == 1)
						{
							rs.moveToInsertRow();
							rs.updateString("Name", textName.getText());
							rs.updateString("PhoneNumber", textPhone.getText());
							rs.updateInt("Age",Integer.parseInt(textAge.getText()));
							rs.updateString("Adress", textAdress.getText());
							rs.insertRow();
							
							n++;
							textCount.setText(n+"/"+n);
							rs.last();
							
							btn_moveLast.setEnabled(false);
							btn_moveFirst.setEnabled(true);
							btn_moveLeft.setEnabled(true);
							btn_moveRight.setEnabled(false);
							btn_edit.setEnabled(true);
							btn_delete.setEnabled(true);
							btn_search.setEnabled(true);
							btn_add.setEnabled(true);
							btn_save.setEnabled(false);
							btn_undo.setEnabled(false);
						}
						if(state == 2)
						{
							rs.getRow();
							rs.updateString("Name", textName.getText());
							rs.updateString("PhoneNumber", textPhone.getText());
							rs.updateInt("Age",Integer.parseInt(textAge.getText()));
							rs.updateString("Adress", textAdress.getText());
							rs.updateRow();
							
							textCount.setText(rs.getRow()+"/"+n);
							
							if(!rs.isLast())
							{
								btn_moveLast.setEnabled(true);
								btn_moveRight.setEnabled(true);
							}	
							if(!rs.isFirst())
							{
								btn_moveFirst.setEnabled(true);
								btn_moveLeft.setEnabled(true);
							}
							btn_edit.setEnabled(true);
							btn_delete.setEnabled(true);
							btn_search.setEnabled(true);
							btn_add.setEnabled(true);
							btn_save.setEnabled(false);
							btn_undo.setEnabled(false);
						}
						
						
						textName.setEditable(false);
						textPhone.setEditable(false);
						textAge.setEditable(false);

						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			});
			
			btn_undo.setEnabled(false);
			btn_undo.setIcon(new ImageIcon("ToolbarImages\\undo.JPG"));
			toolBar.add(btn_undo);
			btn_undo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{	
	            	try {
        	            textName.setText(rs.getString("Name")+"");
        	            textPhone.setText(rs.getString("PhoneNumber"));
        	            textAge.setText(rs.getInt("Age")+"");
        	            textAdress.setText(rs.getString("Adress"));
						textCount.setText(rs.getRow()+"/"+n);
						btn_edit.setEnabled(true);
						btn_delete.setEnabled(true);
						btn_search.setEnabled(true);
						btn_add.setEnabled(true);
						btn_save.setEnabled(false);
						btn_undo.setEnabled(false);
						textName.setEditable(false);
						textPhone.setEditable(false);
						textAge.setEditable(false);
						
						if(rs.isFirst())
	                    {
	                    	btn_moveFirst.setEnabled(false);
	                    	btn_moveLeft.setEnabled(false);
	                    	btn_moveLast.setEnabled(true);
	                    	btn_moveRight.setEnabled(true);
	                    }
	                    else if(rs.isLast())
	                    {
	                    	btn_moveFirst.setEnabled(true);
	                    	btn_moveLeft.setEnabled(true);
	                    	btn_moveLast.setEnabled(false);
	                    	btn_moveRight.setEnabled(false);
	                    }
	                    else
	                    {
	                    	btn_moveFirst.setEnabled(true);
	                    	btn_moveLeft.setEnabled(true);
	                    	btn_moveLast.setEnabled(true);
	                    	btn_moveRight.setEnabled(true);
	                    }
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
	            	
				}
			});
			
			JLabel lblName = new JLabel("Name");
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblName.setForeground(Color.CYAN);
			lblName.setBounds(145, 134, 57, 13);
			this.add(lblName);
			
			JLabel lblPhone = new JLabel("Phone");
			lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
			lblPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblPhone.setForeground(Color.CYAN);
			lblPhone.setBounds(145, 164, 57, 13);
			this.add(lblPhone);
			
			JLabel lblAge = new JLabel("Age");
			lblAge.setHorizontalAlignment(SwingConstants.CENTER);
			lblAge.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblAge.setBounds(145, 197, 57, 13);
			lblAge.setForeground(Color.CYAN);
			this.add(lblAge);
			
			JLabel lblAdress = new JLabel("Adress");
			lblAdress.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdress.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblAdress.setBounds(145, 227, 57, 13);
			lblAdress.setForeground(Color.CYAN);
			this.add(lblAdress);
			
			textName.setEditable(false);
			textName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textName.setBounds(212, 133, 96, 19);
			this.add(textName);
			textName.setColumns(10);
			
			textPhone.setEditable(false);
			textPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textPhone.setColumns(10);
			textPhone.setBounds(212, 163, 96, 19);
			this.add(textPhone);
			
			textAge.setEditable(false);
			textAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textAge.setColumns(10);
			textAge.setBounds(212, 196, 96, 19);
			this.add(textAge);
			
			textAdress.setEditable(false);
			textAdress.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textAdress.setColumns(10);
			textAdress.setBounds(212, 226, 96, 19);
			this.add(textAdress);
			
			BackButton backButton = new BackButton();
			backButton.setBounds(218, 320, 80, 40);		
			this.add(backButton);
			
			this.add(new BackgroundPanel());
			this.setVisible(false);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //end constructor

	
}
