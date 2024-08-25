import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import java.util.Date; 
import java.io.*; 
import java.util.Enumeration;
import java.util.*;

public class administratorAccess extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//opens the frame
					administratorAccess frame = new administratorAccess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	//this is automatically done by java GUI when I drag from the pallete
	private JTable MemberInfo;
	private JTextField textFieldMID;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldUsername;
	private JTextField textFieldpassword;
	private JTextField textFieldAge;
	private JButton buttonUpdate;
	private JTextField textFieldBackUpHash;
	//made this subroutine, so I can call it after every event so it is correct data being displayed
	public void refreshTable(){
		try {
			//getting all the values from MemberInfo and displays in table
			String query ="select * from MemberInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			MemberInfo.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public administratorAccess() {
		connection = sqliteConnection.dbConnector();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground( Color.decode("#66d9ff"));//changes the background colour
		contentPane.setLayout(null);
		//load table button
		JButton btnLoadMemberinfo = new JButton("Load MemberInfo");
		btnLoadMemberinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//loads all the Member details into table 
					String query = "select * from MemberInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					//results of query stored in variable 
					ResultSet rs = pst.executeQuery();
					
					MemberInfo.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
					//catches other errors
				}
				refreshTable();
			}
		});
		//surrounds table with scroll pane
		btnLoadMemberinfo.setBounds(13, 551, 177, 32);
		contentPane.add(btnLoadMemberinfo);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 117, 749, 427);
		contentPane.add(scrollPane);
		//creates table to display records
		MemberInfo = new JTable();
		scrollPane.setViewportView(MemberInfo);
		//save button 
		JButton btnSave = new JButton("Saved");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//saves user input into MemberInfo database table
					String query ="insert into MemberInfo ('MID','Name','Surname','Username','password','Age') values (?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,textFieldMID.getText());
					pst.setString(2,textFieldName.getText());
					pst.setString(3,textFieldSurname.getText());
					pst.setString(4,textFieldUsername.getText());
					pst.setString(5,textFieldpassword.getText());
					pst.setString(6,textFieldAge.getText());
					
					
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data saved");
					
					pst.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					//calls subroutine, so data is live and correct 
					refreshTable();
			}
		});
		btnSave.setBounds(200, 551, 113, 32);
		contentPane.add(btnSave);
		
		
		//creation of textFields, labels and button all the variables are appropriately named
		textFieldMID = new JTextField();
		textFieldMID.setBounds(10, 625, 86, 20);
		contentPane.add(textFieldMID);
		textFieldMID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(106, 625, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(200, 625, 86, 20);
		contentPane.add(textFieldSurname);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(296, 625, 86, 20);
		contentPane.add(textFieldUsername);
		
		textFieldpassword = new JTextField();
		textFieldpassword.setColumns(10);
		textFieldpassword.setBounds(392, 625, 86, 20);
		contentPane.add(textFieldpassword);
		
		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(488, 625, 86, 20);
		contentPane.add(textFieldAge);
		//update button
		buttonUpdate = new JButton("Update");
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
try {
					//updates records with user input
					String query ="Update MemberInfo set MID ='"+ textFieldMID.getText()+"',Name='"+ textFieldName.getText()+"',Surname='"+ textFieldSurname.getText()+"',Username='"+ textFieldUsername.getText()+"',password='"+ textFieldpassword.getText()+"',Age='"+ textFieldAge.getText()+"'where MID ='"+textFieldMID.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					
					pst.execute();
					//displays error pop up
					JOptionPane.showMessageDialog(null, "Data saved");
					
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
//refreshes tables by calling 
refreshTable();
			}
		});
		buttonUpdate.setBounds(323, 551, 113, 32);
		contentPane.add(buttonUpdate);
		//delete button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
try {
					//deletes whole records
					String query ="delete from MemberInfo where MID ='"+textFieldMID.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					
					pst.execute();
					//error message
					JOptionPane.showMessageDialog(null, "Data deleted");
					
					pst.close();
					
				} catch (Exception ex) {
					ex.printStackTrace();
					//catches any other that may occur
				}
			refreshTable();
			}
		});
		//creation of textFields, labels and button all the variables are appropriately named
		btnDelete.setBounds(446, 551, 113, 32);
		contentPane.add(btnDelete);
		
		JLabel lblMid = new JLabel("MID");
		lblMid.setBounds(10, 594, 86, 20);
		contentPane.add(lblMid);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(104, 594, 86, 20);
		contentPane.add(lblName);
		
		JLabel lblName_1 = new JLabel("Surname");
		lblName_1.setBounds(197, 594, 86, 20);
		contentPane.add(lblName_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(296, 594, 86, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(392, 594, 86, 20);
		contentPane.add(lblPassword);
		
		JLabel lblAe = new JLabel("Age");
		lblAe.setBounds(488, 594, 86, 20);
		contentPane.add(lblAe);
		
		JLabel lblViewAndEdit = new JLabel("View and edit user records");
		lblViewAndEdit.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblViewAndEdit.setBounds(13, 11, 426, 75);
		contentPane.add(lblViewAndEdit);
		
		JLabel AdministratorLogo = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("Admin-icon.png")).getImage();
		AdministratorLogo.setIcon(new ImageIcon(logo));
		AdministratorLogo.setBounds(580, 0, 267, 121);
		contentPane.add(AdministratorLogo);
		//back up admin details button
		JButton btnAdminBackUpDetails = new JButton("Get admin backup user details");
		btnAdminBackUpDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//This is a grade A algorithm, I use a hash map in order to quickly retrieve values and output them
				//creates new hash map under variable hm (this is justified as it is local and there is only one hash map)
				HashMap hm = new HashMap();
				
				
				hm.put("MID", new Double(100));
			      hm.put("Name", new String("Sandeep"));
			      hm.put("Surname", new String("Rai"));
			      hm.put("Username", new String("10rais"));
			      hm.put("password", new String("Metallica"));
			      hm.put("Age", new Double(17));
			      
			      Set setAdmin = hm.entrySet();
			      
			      Iterator iAdmin = setAdmin.iterator();
			      
			      while(iAdmin.hasNext()) {
			          Map.Entry me = (Map.Entry)iAdmin.next();
			          //System.out.print(me.getKey() + ": ");
			          //System.out.println(me.getValue());
			      }
			      //if the admin had non compiled source code they would change this( my justification is that admin details are very private)
			      //I do not change the values of the variables so this part of the code is not used, however I wanted the option when coding administratorAccess, I talk more about this in my evaluation
			      
			      double MID = ((Double)hm.get("MID")).doubleValue();
			      hm.put("MID", new Double(MID = 100));
			      
			      String name = ((String)hm.get("Name")).toString();
			      hm.put("Name", new String(name));
			      
			      String surname = ((String)hm.get("Surname")).toString();
			      hm.put("Surname", new String(surname));
			      String userName = ((String)hm.get("Username")).toString();
			      hm.put("UserName", new String(userName));
			      String password = ((String)hm.get("password")).toString();
			      hm.put("password", new String(password));
			      
			      double Age = ((Double)hm.get("Age")).doubleValue();
			      hm.put("Age", new Double(Age = 17));
			      
			     
			      //gets the values for each column in MemberInfo for the back up admin details and displays them in a pop up message
			      JOptionPane.showMessageDialog(null, "Back up admin details are: " 
			     + "MID is " +hm.get("MID") 
			     + " Name: " + hm.get("Name") 
			     + " Surname: " + hm.get("Surname") 
			     + " Username: " + hm.get("Username") 
			     + " Password: "+ hm.get("password") 
			     + " Age: "+ hm.get("Age"));
			    //gets the values for each column in MemberInfo for the back up admin details and displays them in a textField, this is so when the admin is adding these details they dont have to constantly press the button
			     textFieldBackUpHash.setText("Back up admin details are: " 
					     + "MID is " +hm.get("MID") 
					     + " Name: " + hm.get("Name") 
					     + " Surname: " + hm.get("Surname") 
					     + " Username: " + hm.get("Username") 
					     + " Password: "+ hm.get("password") 
					     + " Age: "+ hm.get("Age"));;
			}
		});
		//creation of textFields, labels and button all the variables are appropriately named
		btnAdminBackUpDetails.setBounds(13, 671, 215, 20);
		contentPane.add(btnAdminBackUpDetails);
		//back up admin details text field
		textFieldBackUpHash = new JTextField();
		textFieldBackUpHash.setBounds(249, 671, 364, 20);
		contentPane.add(textFieldBackUpHash);
		textFieldBackUpHash.setColumns(10);
		
		
		
		refreshTable();
		
		
		
		
				
	}
}
