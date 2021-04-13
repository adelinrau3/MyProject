package myProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class ToDoFrame extends JFrame{
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ToDoFrame() {
		
		ImageIcon icon = new ImageIcon("background.png");
		JLabel label =new JLabel();
		label.setIcon(icon);
		
		this.setTitle("ToDo");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setSize(700, 310);
		this.setLayout(null);				

		this.addWindowListener(new WindowAdapter() 
		{
            @Override
            public void windowClosing(WindowEvent e) 
            {
            	MainClass.homeFrame.setVisible(true);;            
            }
            
        });

		
		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		table.setVisible(true);
		
		tableModel.addColumn("Title");
		tableModel.addColumn("Description");
		tableModel.addColumn("State");
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(180);
		columnModel.getColumn(2).setPreferredWidth(30);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		
		JLabel lbl_title = new JLabel("Title");
		lbl_title.setVisible(true);
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setForeground(Color.CYAN);
		lbl_title.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_title.setBounds(20, 40, 100, 20);
		this.add(lbl_title);
		
		JLabel lbl_description = new JLabel("Description");
		lbl_description.setVisible(true);
		lbl_description.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_description.setForeground(Color.CYAN);
		lbl_description.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_description.setBounds(20, 70, 100, 20);
		this.add(lbl_description);
		
		JLabel lbl_state = new JLabel("State");
		lbl_state.setVisible(true);
		lbl_state.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_state.setForeground(Color.CYAN);
		lbl_state.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_state.setBounds(20, 100, 100, 20);
		this.add(lbl_state);
		
		JTextField txt_title = new JTextField();
		txt_title.setVisible(true);
		txt_title.setEditable(true);
		txt_title.setHorizontalAlignment(SwingConstants.CENTER);
		txt_title.setBounds(130, 40, 100, 20);
		this.add(txt_title);
		
		JTextField txt_description = new JTextField();
		txt_description.setVisible(true);
		txt_description.setEditable(true);
		txt_description.setHorizontalAlignment(SwingConstants.CENTER);
		txt_description.setBounds(130, 70, 100, 20);
		this.add(txt_description);
		
		JButton addButton = new JButton();
		JButton deleteButton = new JButton();
		JButton doneButton = new JButton();
		JButton notDoneButton = new JButton();
		
		String[] state = {"Not Done", "Done"};
		
		JComboBox combo_state = new JComboBox(state);
		combo_state.setVisible(true);
		combo_state.setBounds(130, 100, 100, 20);
		this.add(combo_state);
		
		try {
			String url = "jdbc:mysql://localhost:3306/test";
			Statement sql;
			ResultSet rs;
			
			Connection con = DriverManager.getConnection (url, "root", "admin");
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = sql.executeQuery("select * from todo");
			
			rs.beforeFirst();
			while(rs.next())
			{
			    String a = rs.getString("Title");
			    String b = rs.getString("Description");
			    int aux = rs.getInt("State");
			    
			    String c;
			    if(aux == 1) c = "Done";
			    else c = "Not Done";
			    
			    tableModel.insertRow(tableModel.getRowCount(),new Object[]{a, b, c});
			}
			
			
			addButton.setBounds(20, 140, 210, 20);
			addButton.setText("Add New Task");
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						

						String a = txt_title.getText();
						String b = txt_description.getText();
						String c = (String) combo_state.getSelectedItem();
						
						int aux;
						if(c.equals("Done")) aux = 1;
						else aux = 0;
						
						int ok = 1;
						rs.beforeFirst();
						while(rs.next())
						{
							if(rs.getString("Title").equals(a))
							{
								ok = 0;
								break;
							}
						}
						if(ok == 1)
						{
							rs.moveToInsertRow();

							rs.updateString("Title", a);
							rs.updateString("Description", b);
							rs.updateInt("State", aux);
							
							rs.insertRow();
							
							tableModel.insertRow(tableModel.getRowCount(),new Object[]{a, b, c});				
						}
						else JOptionPane.showMessageDialog(null, "The Task already exists!");
						
						txt_title.setText("");
						txt_description.setText("");
						combo_state.setSelectedIndex(0);
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}
			});
			this.add(addButton);
			
			
			deleteButton.setBounds(20, 170, 210, 20);
			deleteButton.setText("Delete Task");
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						String titleToBeDeleted = (String) table.getValueAt(table.getSelectedRow(), 0);

						if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this task?", "WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						{
							rs.beforeFirst();
							while(rs.next())
							{
								if(rs.getString("Title").equals(titleToBeDeleted))
								{
									rs.getRow();
									break;
								}
							}
							rs.deleteRow();
							tableModel.removeRow(table.getSelectedRow());
						}

					} catch (SQLException e) {

						e.printStackTrace();

					}
				}
			});
			this.add(deleteButton);
			
			
			doneButton.setBounds(20, 200, 210, 20);
			doneButton.setText("Mark as Done");
			doneButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						String titleToBeDone = (String) table.getValueAt(table.getSelectedRow(), 0);

						rs.beforeFirst();
						while(rs.next())
						{
							if(rs.getString("Title").equals(titleToBeDone))
							{
								rs.getRow();
								break;
							}
						}
						rs.updateInt("State",1);
						rs.updateRow();
						
						tableModel.setValueAt("Done", table.getSelectedRow(), 2);


					} catch (SQLException e) {

						e.printStackTrace();

					}
				}
				
			});
			this.add(doneButton);
			
			
			notDoneButton.setBounds(20, 230, 210, 20);
			notDoneButton.setText("Mark as Not Done");
			notDoneButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{	
					try {
						String titleToBeNotDone = (String) table.getValueAt(table.getSelectedRow(), 0);

						rs.beforeFirst();
						while(rs.next())
						{
							if(rs.getString("Title").equals(titleToBeNotDone))
							{
								rs.getRow();
								break;
							}
						}
						rs.updateInt("State",0);
						rs.updateRow();
						
						tableModel.setValueAt("Not Done", table.getSelectedRow(), 2);


					} catch (SQLException e) {

						e.printStackTrace();

					}
				}
			});
			this.add(notDoneButton);
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(250, 40, 400, 210);
		this.add(scrollPane);
		
		this.add(new BackgroundPanel());
		this.setVisible(false);

	}

}
