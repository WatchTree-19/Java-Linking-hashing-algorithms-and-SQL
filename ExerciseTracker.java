import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.Font;
import java.awt.Image;

public class ExerciseTracker extends JFrame {

	private JPanel contentPane;
	private JTable ExerciseInfoTable;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExerciseTracker frame = new ExerciseTracker();
					frame.setVisible(true);
					//opens the frame
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection connection = null;
//variables of the textFields
private JTextField textFieldMID;
private JTextField textFieldExercise;
private JTextField textFieldDistance;
private JTextField textFieldTime;
private JTextField textFieldDate;
private JTextField textFieldDistancegoal;
private JTextField textFieldTimeGoal;
private JTextField textFieldEID;
//refresh table subroutine
public void refreshTable(){
	try {
		//selects all the data
		String query ="select EID,MID,Exercise,Distance,Time,Date,DistanceGoal,TimeGoal from  ExerciseInfo";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		//displays it in table
		ExerciseInfoTable.setModel(DbUtils.resultSetToTableModel(rs));
		rs.close();//closes result set
	    pst.close();//closes connection so database doesnt lock
	} catch (Exception e) {
		e.printStackTrace();
		//any errors that occur
	}
}




/**
	 * Create the frame.
	 */
	public ExerciseTracker() {
		connection = sqliteConnection.dbConnector();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground( Color.decode("#66d9ff"));
		
		
		JLabel lblExerciseTracker = new JLabel("Exercise Tracker");
		lblExerciseTracker.setFont(new Font("Garamond", Font.PLAIN, 20));
		lblExerciseTracker.setBounds(417, 11, 211, 86);
		contentPane.add(lblExerciseTracker);
		//surrounding the jtable with a scroll pane
		JScrollPane ExerciseInfoscrollPane = new JScrollPane();
		ExerciseInfoscrollPane.setBounds(0, 108, 1004, 414);
		contentPane.add(ExerciseInfoscrollPane);
		//creating table for exercise information
		ExerciseInfoTable = new JTable();
		ExerciseInfoscrollPane.setViewportView(ExerciseInfoTable);
		//load button
		JButton btnExerciseTracker = new JButton("Load Exercise Tracker ");
		btnExerciseTracker.setForeground(new Color(0, 0, 0));
		btnExerciseTracker.setBackground(new Color(100, 149, 237));
		btnExerciseTracker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//selects all the data
					String query ="select EID,MID,Exercise,Distance,Time,Date,DistanceGoal,TimeGoal from  ExerciseInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//displays it in table
					ExerciseInfoTable.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();//closes result set
				    pst.close();//closes connection so database doesnt lock
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnExerciseTracker.setBounds(10, 542, 111, 48);
		contentPane.add(btnExerciseTracker);
		//creation of textFields for user input
		textFieldMID = new JTextField();
		textFieldMID.setBounds(234, 550, 86, 33);
		contentPane.add(textFieldMID);
		textFieldMID.setColumns(10);
		
		textFieldExercise = new JTextField();
		textFieldExercise.setBounds(330, 550, 101, 33);
		contentPane.add(textFieldExercise);
		textFieldExercise.setColumns(10);
		
		textFieldDistance = new JTextField();
		textFieldDistance.setBounds(441, 550, 101, 33);
		contentPane.add(textFieldDistance);
		textFieldDistance.setColumns(10);
		
		textFieldTime = new JTextField();
		textFieldTime.setBounds(552, 550, 101, 33);
		contentPane.add(textFieldTime);
		textFieldTime.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(663, 550, 101, 33);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		//the current date and time
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
	    Date date = new Date();
	    //displays the current date automatically
	    textFieldDate.setText(dateFormat.format(date));
		
		textFieldDistancegoal = new JTextField();
		textFieldDistancegoal.setBounds(774, 550, 98, 33);
		contentPane.add(textFieldDistancegoal);
		textFieldDistancegoal.setColumns(10);
		
		textFieldTimeGoal = new JTextField();
		textFieldTimeGoal.setBounds(882, 550, 101, 33);
		contentPane.add(textFieldTimeGoal);
		textFieldTimeGoal.setColumns(10);
		//jlabels above the corresponding textFields for user input
		JLabel lblMemberID = new JLabel("Member ID");
		lblMemberID.setBounds(244, 518, 76, 33);
		contentPane.add(lblMemberID);
		
		JLabel lblExercise = new JLabel("Exercise");
		lblExercise.setBounds(355, 520, 76, 29);
		contentPane.add(lblExercise);
		
		JLabel lblDistance = new JLabel("Distance (m)");
		lblDistance.setBounds(455, 520, 101, 29);
		contentPane.add(lblDistance);
		
		JLabel lblTime = new JLabel("Time (s)");
		lblTime.setBounds(574, 520, 101, 29);
		contentPane.add(lblTime);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(685, 518, 76, 32);
		contentPane.add(lblDate);
		
		JLabel lblDistanceGoal = new JLabel("Distance Goal (m)");
		lblDistanceGoal.setBounds(774, 513, 111, 43);
		contentPane.add(lblDistanceGoal);
		
		JLabel lblTimeGoal = new JLabel("Time Goal (s)");
		lblTimeGoal.setBounds(882, 517, 101, 34);
		contentPane.add(lblTimeGoal);
		//save button
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(100, 149, 237));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					//getting int variables for all the user input for the exercise table
					int MID	 = Integer.parseInt(textFieldMID.getText());
					int EID = Integer.parseInt(textFieldEID.getText());
					int Time = Integer.parseInt(textFieldTime.getText());
					int Distance = Integer.parseInt(textFieldDistance.getText());
					int TimeGoal = Integer.parseInt(textFieldTimeGoal.getText());
					int DistanceGoal = Integer.parseInt(textFieldDistancegoal.getText());
					
						//if the integers entered are silly values and wildly out of range
					if (MID < 0 && MID > 1000000 && EID < 0 && EID > 1000000 && Time < 0 && Time > 1000000 && Distance < 0 && Distance > 1000000 && TimeGoal < 0 && TimeGoal > 1000000 && DistanceGoal < 0 && DistanceGoal > 1000000 ){
					//inserts into exercise info
					String query ="insert into ExerciseInfo ('EID','MID','Exercise','Distance','Time','Date','DistanceGoal','TimeGoal') values (?,?,?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					//gets the user input for each column in exercise info
					pst.setString(1,textFieldEID.getText());
					pst.setString(2,textFieldMID.getText());
					pst.setString(3,textFieldExercise.getText());
					pst.setString(4,textFieldDistance.getText());
					pst.setString(5,textFieldTime.getText());
					pst.setString(6,textFieldDate.getText());
					pst.setString(7,textFieldDistancegoal.getText());
					pst.setString(8,textFieldTimeGoal.getText());
					
					pst.execute();
					//success message
					JOptionPane.showMessageDialog(null, "Data saved");
					
					pst.close();
					}else{
						JOptionPane.showMessageDialog(null, "Data is invalid");
						//the data is out of range or an integer so error message
					}
				} catch (Exception e) {
					e.printStackTrace();
					//any other error
				}
				//calls refresh table subroutine
				refreshTable();
			}
		});
		btnSave.setBounds(10, 595, 111, 48);
		contentPane.add(btnSave);
		//update button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(100, 149, 237));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
try {
	//getting int variables for all the user input for the exercise table		
	int MID	 = Integer.parseInt(textFieldMID.getText());
	int EID = Integer.parseInt(textFieldEID.getText());
	int Time = Integer.parseInt(textFieldTime.getText());
	int Distance = Integer.parseInt(textFieldDistance.getText());
	int TimeGoal = Integer.parseInt(textFieldTimeGoal.getText());
	int DistanceGoal = Integer.parseInt(textFieldDistancegoal.getText());
	
	//if the integers entered are silly values and wildly out of range
	if (MID < 0 && MID > 1000000 && EID < 0 && EID > 1000000 && Time < 0 && Time > 1000000 && Distance < 0 && Distance > 1000000 && TimeGoal < 0 && TimeGoal > 1000000 && DistanceGoal < 0 && DistanceGoal > 1000000 ){
		
	
	//updates the record corresponding to details
					String query ="Update ExerciseInfo set EID='"+ textFieldEID.getText()+"',MID='"+ textFieldMID.getText()+"',Exercise='"+ textFieldExercise.getText()+"',Distance='"+ textFieldDistance.getText()+"',Time='"+ textFieldTime.getText()+"',Date='"+ textFieldDate.getText()+"',DistanceGoal='"+ textFieldDistancegoal.getText()+"',TimeGoal='"+ textFieldTimeGoal.getText()+"'where EID ='"+textFieldEID.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					
					pst.execute();
					//success message
					JOptionPane.showMessageDialog(null, "Data updated");
					
					pst.close();
	}else{
		//if criteria arent met (string in int fields or silly values)
		JOptionPane.showMessageDialog(null, "Data is invalid");
	}
				} catch (Exception e) {
					e.printStackTrace();
					//other errors caught
				}
//calls the refresh table subroutine
refreshTable();
			}
		});
		btnUpdate.setBounds(10, 654, 111, 43);
		contentPane.add(btnUpdate);
		
		textFieldEID = new JTextField();
		textFieldEID.setBounds(138, 550, 86, 33);
		contentPane.add(textFieldEID);
		textFieldEID.setColumns(10);
		
		JLabel lblExerciseId = new JLabel("Exercise ID");
		lblExerciseId.setBounds(146, 522, 66, 24);
		contentPane.add(lblExerciseId);
		//jlabel which the logo will go in
		
		JLabel label = new JLabel("");
		//file name of image 
		Image logo = new ImageIcon(this.getClass().getResource("FitBud.png")).getImage();
		label.setIcon(new ImageIcon(logo));
		label.setBounds(-82, 0, 387, 119);
		contentPane.add(label);
		//tells the user that they dont have to enter primary key as it is autoincrement
		JLabel lblNewLabel = new JLabel("Note to user: You need only to enter the Exercise ID when updating a record ");
		lblNewLabel.setBounds(131, 594, 454, 29);
		contentPane.add(lblNewLabel);
		//delete button
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(100, 149, 237));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
try {
					//deletes records
					String query ="delete from ExerciseInfo where EID ='"+textFieldEID.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					
					pst.execute();
					//success message
					JOptionPane.showMessageDialog(null, "Data deleted");
					
					pst.close();
					
				} catch (Exception ex) {
					ex.printStackTrace();
					//other errors caught
					
				}
//calls refresh table (look at refresh table for comments on it)
refreshTable();
			}
		});
		btnDelete.setBounds(135, 654, 111, 43);
		contentPane.add(btnDelete);
		//back to main menu button
		JButton btnBackToMainMenu = new JButton("Back to main menu");
		btnBackToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//if clicked it makes the frame not visible 
				ExerciseTracker frame = new ExerciseTracker();
				frame.setVisible(false);
				//so that when the exit button is pressed it closes 
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//opens the main menu
				MainMenu openMainMenu = new MainMenu();
				openMainMenu.NewScreen();
				
			}
		});
		btnBackToMainMenu.setBounds(845, 681, 152, 24);
		contentPane.add(btnBackToMainMenu);
		
		
		
		//calls refresh table subroutine
		refreshTable();
			}
}
