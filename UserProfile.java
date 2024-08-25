//this frame is dedicated to showing just the users information, by allowing them to type in their MID and load all their data

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UserProfile extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//opens the frame
					UserProfile frame = new UserProfile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTextField textFieldUserMemberID;
	private JTextField textFieldTotalDistance;
	private JTextField textFieldBuddyComparison;
	private JTextField textFieldTotalCalories;
	private JTextField textFieldBuddyComparisonCalories;
	
	/**
	 * Create the frame.
	 */
	public UserProfile() {
		connection =sqliteConnection.dbConnector();
		
		try {
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 776);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground( Color.decode("#66d9ff"));
		//surrounds the table with a scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 104, 513, 474);
		contentPane.add(scrollPane);
		//creates a new table
		table = new JTable();
		scrollPane.setViewportView(table);
		//load exercise tracker button
		JButton btnNewButton = new JButton("Load exercise tracker");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//gets user input and assigns it MID string variable
					String MID = textFieldUserMemberID.getText(); 
					//select record where the MID in the table is equal to the MID local variable
					String query ="select * from ExerciseInfo where MID = '" +MID+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					//stores result set of query rs local variable
					ResultSet rs = pst.executeQuery();
					//displays the result set in the table
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
				/*one of my objectives is to encourage so everytime they load their table 
				 * a supporting pop up appears
				 * 
				 */
				JOptionPane.showMessageDialog(null, "Refer to Improve your lifestyle on how to analyse your tracker and improve your productivity");
			}
		});
		btnNewButton.setBounds(10, 589, 135, 42);
		contentPane.add(btnNewButton);
		/* note: there is no need to change the global variables 
		 * as these are automatically created and 
		 * as a programmer I do not need to reference the name as it does it for me
		 * 
		 */
		//surrounds the table in a scroll pane
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(513, 104, 493, 474);
		contentPane.add(scrollPane_1);
		//creates the table
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		//button that loads the WeightInfo table
		JButton btnNewButton_1 = new JButton("Load calorie tracker");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//gets user input and assigns it MID string variable
					String MID = textFieldUserMemberID.getText();
					//select record where the MID in the table is equal to the MID local variable
					String query ="select * from WeightInfo where MID = '" +MID+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					//stores result set of query rs local variable
					ResultSet rs = pst.executeQuery();
					//displays the result set in the table
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
				//pop up 
				JOptionPane.showMessageDialog(null, "Refer to Improve your lifestyle on how to change your nutritional intake and take the next step in achieving your goals!");
			}
		});
		btnNewButton_1.setBounds(155, 589, 135, 42);
		contentPane.add(btnNewButton_1);
		//filling a jlabel with the fitBud logo
		JLabel label = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("FitBud.png")).getImage();
		label.setIcon(new ImageIcon(logo));
		label.setBounds(651, -14, 457, 142);
		contentPane.add(label);
		//title for the page 
		JLabel lblYourProfile = new JLabel("Your profile");
		lblYourProfile.setForeground(Color.GRAY);
		lblYourProfile.setFont(new Font("Garamond", Font.PLAIN, 50));
		lblYourProfile.setBounds(0, 38, 373, 73);
		contentPane.add(lblYourProfile);
		//text field so they can enter MID
		textFieldUserMemberID = new JTextField();
		textFieldUserMemberID.setBounds(446, 73, 112, 20);
		contentPane.add(textFieldUserMemberID);
		textFieldUserMemberID.setColumns(10);
		//tells them where to enter MID
		JLabel lblNewLabel = new JLabel("Enter your MemberID number");
		lblNewLabel.setBounds(269, 67, 167, 36);
		contentPane.add(lblNewLabel);
	//button for total distance
		JButton btnTotalDistanceThisMonth = new JButton("Total distance");
		btnTotalDistanceThisMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String UserMID = textFieldUserMemberID.getText(); 
					//stores their input for MID in a string
					//sets int sum to 0
					int sum = 0;
					/*GROUP A ALGORITHM AGGREGATE SQL FUNCTION
					 * adds together all the values for the distance column where it equals their MID
					 */
					String query ="select sum(Distance) from ExerciseInfo where MID ='"+UserMID+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					/*converts the result set local variable into an integer
					 * appropriately named c as it is a local variable stands for calculation
					 */
					int c = rs.getInt(1);
					sum = sum + c;
					//displays the total distance in text field
					textFieldTotalDistance.setText(""+c );;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnTotalDistanceThisMonth.setBounds(10, 675, 167, 23);
		contentPane.add(btnTotalDistanceThisMonth);
		
		textFieldTotalDistance = new JTextField();
		textFieldTotalDistance.setBounds(187, 676, 86, 20);
		contentPane.add(textFieldTotalDistance);
		textFieldTotalDistance.setColumns(10);
		//buddy comparison button
		JButton btnBuddyComparison = new JButton("Compare to your buddy! Enter their MID:\r\n");
		btnBuddyComparison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					/*
					 * gets both the users MID from a text field
					 * and their buddy's MID so and stores them as strings
					 * so that I can use them in the query
					 */
					String UserMID = textFieldUserMemberID.getText(); 
					String BuddyMID = textFieldBuddyComparison.getText(); 
					
					/*This is a A group algorithm, using SUM which is an Aggregate SQL function 
					 * 
					 */
					//sets sum of the buddy total distance to 0
					int sumBuddy = 0;
					//selects the total of all the distance values where it equals the relevant MID
					String queryBuddy ="select sum(Distance) from ExerciseInfo where MID ='"+BuddyMID+"'";
					PreparedStatement pstBuddy = connection.prepareStatement(queryBuddy);
					ResultSet rsBuddy = pstBuddy.executeQuery();
					//converts the result set to an int so I can use it in mathematical function
					int c = rsBuddy.getInt(1);
					//new value for sumBuddy by adding 0 to the result of the query
					sumBuddy = sumBuddy + c;
					
					//This is a A group algorithm, using SUM which is an Aggregate SQL function
					
					/*This is a A group algorithm, using SUM which is an Aggregate SQL function 
					 * 
					 */
					//sets sum of the user total distance to 0
					int sumUser = 0;
					//selects the total of all the distance values where it equals the relevant MID
					String queryUser ="select sum(Distance) from ExerciseInfo where MID ='"+UserMID+"'";
					PreparedStatement pstUser = connection.prepareStatement(queryUser);
					ResultSet rsUser = pstUser.executeQuery();
					//converts the result set to an int so I can use it in mathematical function
					int TotalDistanceUser = rsUser.getInt(1);
					
					//new value for sumBuddy by adding 0 to the result of the query
				    int BuddyComparison = TotalDistanceUser-sumBuddy;
				    //textFieldBuddyComparison.setText(""+BuddyComparison);
				    
				    /*
				     * if the buddy comparison is greater than 0 then it means that the result is positive
				     * this means that they are beating their buddy
				     * so we display a pop up saying that
				     */
				    if (BuddyComparison > 0){
				    	JOptionPane.showMessageDialog(null, "You are beating your buddy by "+ BuddyComparison +" Stay on top of your game" );
				    }
					
				} catch (Exception e) {
					e.printStackTrace();
					//catches other errors that may occur
				}
				
			}
		});
		btnBuddyComparison.setBounds(10, 709, 329, 23);
		contentPane.add(btnBuddyComparison);
		
		textFieldBuddyComparison = new JTextField();
		textFieldBuddyComparison.setBounds(349, 710, 86, 20);
		contentPane.add(textFieldBuddyComparison);
		textFieldBuddyComparison.setColumns(10);
		
		/*
		 * Note to user: all of this code runs off similar logic to the total distance
		 * and total distance compare algorithm so there is no need to rewrite comments
		 */
		
		JButton btnTotalCalories = new JButton("Total Calories");
		btnTotalCalories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String UserMID = textFieldUserMemberID.getText(); 
					int sum = 0;
					String query ="select sum(Calories) from WeightInfo where MID ='"+UserMID+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					int c = rs.getInt(1);
					sum = sum + c;
					textFieldTotalCalories.setText(""+c );;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnTotalCalories.setBounds(446, 675, 167, 23);
		contentPane.add(btnTotalCalories);
		
		textFieldTotalCalories = new JTextField();
		textFieldTotalCalories.setColumns(10);
		textFieldTotalCalories.setBounds(623, 676, 86, 20);
		contentPane.add(textFieldTotalCalories);
		
		JButton btnCompareYourCalories = new JButton("Compare to your buddy! Enter their MID:\r\n");
		btnCompareYourCalories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String UserMID = textFieldUserMemberID.getText(); 
					String BuddyMID = textFieldBuddyComparisonCalories.getText(); 
					
					int sumBuddy = 0;
					String queryBuddy ="select sum(Calories) from WeightInfo where MID ='"+BuddyMID+"'";
					PreparedStatement pstBuddy = connection.prepareStatement(queryBuddy);
					ResultSet rsBuddy = pstBuddy.executeQuery();
					int c = rsBuddy.getInt(1);
					sumBuddy = sumBuddy + c;
					
					int sumUser = 0;
					String queryUser ="select sum(Calories) from WeightInfo where MID ='"+UserMID+"'";
					PreparedStatement pstUser = connection.prepareStatement(queryUser);
					ResultSet rsUser = pstUser.executeQuery();
					int TotalCalUser = rsUser.getInt(1);
					
					
				    int BuddyComparisonCal = TotalCalUser-c;
				    //textFieldBuddyComparison.setText(""+BuddyComparison);
				    if (BuddyComparisonCal > 0){
				    	JOptionPane.showMessageDialog(null, "There is a difference of "+ BuddyComparisonCal +" calories, depending on your goal this means success or failure" );
				    }
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnCompareYourCalories.setBounds(446, 707, 270, 23);
		contentPane.add(btnCompareYourCalories);
		
		textFieldBuddyComparisonCalories = new JTextField();
		textFieldBuddyComparisonCalories.setColumns(10);
		textFieldBuddyComparisonCalories.setBounds(726, 710, 86, 20);
		contentPane.add(textFieldBuddyComparisonCalories);
		
		JButton btnBackToMain = new JButton("Back to main menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu openMainMenu = new MainMenu();
				openMainMenu.NewScreen();
			}
		});
		btnBackToMain.setBounds(839, 709, 167, 23);
		contentPane.add(btnBackToMain);
	}
}