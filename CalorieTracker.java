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

public class CalorieTracker extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalorieTracker frame = new CalorieTracker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	//creating the variables for the objects that I used from the pallete in design view
	private JTable table;
	private JTextField textFieldWID;
	private JTextField textFieldMID;
	private JTextField textFieldDate;
	private JTextField textFieldWeight;
	private JTextField textFieldCalories;
	private JTextField textFieldWeightGoal;
	private JTextField textFieldCalorieGoal;
	private JLabel lblMemberId;
	private JLabel lblDate;
	private JLabel lblWeight;
	private JLabel lblCalories;
	private JLabel lblWeightGoal;
	private JLabel lblCalorieGoal;
	private JLabel lblWeightId;
	private JLabel label;
	private JButton btnNewButton;
	private JButton btnGoalDistances;
	private JTextField textFieldGoalsCalculation;
	private JTextField textFieldREALDATE;
	private JButton btnBackToMain;
	
	//refreshing the table subroutine
	public void refreshTable(){
		try {
			//selects all the data from the WeightInfo table
			String query ="select WID,MID,Date,Weight,Calories,WeightGoal,CalorieGoal from WeightInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			//displays it in table
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
			//catches any other errors
		}
	}
	/**
	 * Create the frame.
	 */
	public CalorieTracker() {
		connection = sqliteConnection.dbConnector();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 817);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground( Color.decode("#66d9ff"));//changes the colour of the background
		
		//title of the user interface, using a jlabel
		JLabel lblCalorieTracker = new JLabel("Calorie tracker");
		lblCalorieTracker.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCalorieTracker.setBounds(396, 12, 221, 69);
		contentPane.add(lblCalorieTracker);
		//surrounding the table with a scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 104, 877, 449);
		contentPane.add(scrollPane);
		//creates a new table
		table = new JTable();
		scrollPane.setViewportView(table);
		//load calorie tracker
		JButton btnCalorieTracker = new JButton("Calorie Tracker");
		btnCalorieTracker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//selects all the values from WeightInfo
					String query = "select * from WeightInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//displays results of query 
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				//catches any errors
				}
				refreshTable();
			}
		});
		btnCalorieTracker.setBounds(10, 560, 127, 37);
		contentPane.add(btnCalorieTracker);
		//save button
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//inserts user input in the WeightInfo table
				String query ="insert into WeightInfo ('WID','MID','Date','Weight','Calories','WeightGoal','CalorieGoal') values (?,?,?,?,?,?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				//gets user input , prepares the values then enters it into the database
				//GROUP A ALGORITHM USER GENERATED DDL SCRIPT
				pst.setString(1,textFieldWID.getText());
				pst.setString(2,textFieldMID.getText());
				pst.setString(3,textFieldDate.getText());
				pst.setString(4,textFieldWeight.getText());
				pst.setString(5,textFieldCalories.getText());
				pst.setString(6,textFieldWeightGoal.getText());
				pst.setString(7,textFieldCalorieGoal.getText());
				
				
				pst.execute();
				//success message
				JOptionPane.showMessageDialog(null, "Data saved");
				
				pst.close();
				} catch (Exception e) {
					e.printStackTrace();
					//catches any unforeseen errors
				}
				//calls the table to be refreshed
				refreshTable();
			}
		});
		btnSave.setBounds(10, 608, 127, 37);
		contentPane.add(btnSave);
		//creation of textFields for user input
		textFieldWID = new JTextField();
		textFieldWID.setBounds(147, 582, 86, 20);
		contentPane.add(textFieldWID);
		textFieldWID.setColumns(10);
		
		textFieldMID = new JTextField();
		textFieldMID.setBounds(243, 582, 86, 20);
		contentPane.add(textFieldMID);
		textFieldMID.setColumns(10);
		
		//gets the current the date
		textFieldDate = new JTextField();
		textFieldDate.setBounds(339, 582, 86, 20);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
	    Date date = new Date();
	    //where the current date will be displayed
	    textFieldDate.setText(dateFormat.format(date));
		
		textFieldWeight = new JTextField();
		textFieldWeight.setBounds(435, 582, 86, 20);
		contentPane.add(textFieldWeight);
		textFieldWeight.setColumns(10);
		
		textFieldCalories = new JTextField();
		textFieldCalories.setBounds(531, 582, 86, 20);
		contentPane.add(textFieldCalories);
		textFieldCalories.setColumns(10);
		
		textFieldWeightGoal = new JTextField();
		textFieldWeightGoal.setBounds(627, 582, 86, 20);
		contentPane.add(textFieldWeightGoal);
		textFieldWeightGoal.setColumns(10);
		
		textFieldCalorieGoal = new JTextField();
		textFieldCalorieGoal.setBounds(726, 582, 86, 20);
		contentPane.add(textFieldCalorieGoal);
		textFieldCalorieGoal.setColumns(10);
		//update button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
try {
					//if clicked it updates the record where the WID is the one they want to change, in the query I also get the user input and assign it directly unlike most of my other queries
					String query ="Update WeightInfo set WID ='"+ textFieldWID.getText()+"',MID='"+ textFieldMID.getText()+"',Date='"+ textFieldDate.getText()+"',Weight='"+ textFieldWeight.getText()+"',Calories='"+ textFieldCalories.getText()+"',WeightGoal='"+ textFieldWeightGoal.getText()+"',CalorieGoal='"+ textFieldCalorieGoal.getText()+"'where WID ='"+textFieldWID.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					
					pst.execute();
					//data saved message
					JOptionPane.showMessageDialog(null, "Data saved");
					
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
					//catches other errors so the program doesnt crash, but programmers can view the error in eclipse
				}
refreshTable();
			}
		});
		btnUpdate.setBounds(10, 656, 127, 37);
		contentPane.add(btnUpdate);
		//creation of jlabels so the user knows where to input their data
		lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(243, 554, 75, 17);
		contentPane.add(lblMemberId);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(339, 554, 59, 17);
		contentPane.add(lblDate);
		
		lblWeight = new JLabel("Weight (kg)\r\n");
		lblWeight.setBounds(435, 554, 86, 17);
		contentPane.add(lblWeight);
		
		lblCalories = new JLabel("Calories (kJ)");
		lblCalories.setBounds(531, 554, 86, 17);
		contentPane.add(lblCalories);
		
		lblWeightGoal = new JLabel("Weight Goal (kg)");
		lblWeightGoal.setBounds(627, 552, 100, 20);
		contentPane.add(lblWeightGoal);
		
		lblCalorieGoal = new JLabel("Calorie Goal (kJ)");
		lblCalorieGoal.setBounds(726, 554, 106, 17);
		contentPane.add(lblCalorieGoal);
		
		lblWeightId = new JLabel("Weight ID");
		lblWeightId.setBounds(147, 554, 59, 17);
		contentPane.add(lblWeightId);
		//creates a jlabel with no text but stores the image, where the name corresponds to its name in the resources folder
		label = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("FitBud.png")).getImage();
		label.setIcon(new ImageIcon(logo));
		label.setBounds(-80, 0, 388, 114);
		contentPane.add(label);
		//delete button
		btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
try {
					//if its clicked it will delete the field where the WID is the value entered in the textField by the user
					String query ="delete from WeightInfo where WID ='"+textFieldWID.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					
					pst.execute();
					//tells the user that it was successfully deleted
					JOptionPane.showMessageDialog(null, "Data deleted");
					
					pst.close();
					
				} catch (Exception ex) {
					ex.printStackTrace();
					
				}
			refreshTable();
			}
			
		});
		btnNewButton.setBounds(10, 704, 127, 37);
		contentPane.add(btnNewButton);
		//distance to weight goal button (see my design for further insight into this)
		btnGoalDistances = new JButton("Distance to Weight Goal");
		btnGoalDistances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
try {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
    Date date = new Date();
	
    //declaration and assignment of integer sum to 0
    int sum = 0;
    Statement st = connection.createStatement();
    //Aggregate SQL function  
    ResultSet res = st.executeQuery("SELECT Sum(Weight) FROM WeightInfo where WID = '" +textFieldWID.getText()+"'");
    while (res.next()) {
    //stores the result of the query in a integer names c 
    	int c = res.getInt(1);
    //add to the local variable sum by adding the result of the query
    	sum = sum + c;

    String str = Integer.toString(sum);
    //textFieldGoalsCalculation.setText(str);
  
    
    //declaration and assignment of integer sumGoal to 0
    int sumGoal = 0;
    Statement stGoal = connection.createStatement();
    ResultSet resGoal = st.executeQuery("SELECT (WeightGoal) FROM WeightInfo where WID = '" +textFieldWID.getText()+"'");
    while (resGoal.next()) {
    	/*stores the result of the query in a integer names c 
    	 *It is names cGoal as this is the value weight from the weight goal column
    	 *you will notice that both queries are the same but with different, this is because
    	 *appropriate variable names must be used so I can successfully use the results of the queries, and deduct the variables from one another
    	 *so I have a variable to output to the user
    	 */
    	int cGoal = resGoal.getInt(1);
  //add to the local variable sumGoal by adding the result of the query
    sumGoal = sumGoal + cGoal;

    //String strGoal = Integer.toString(sumGoal);
    //declares weightGoalDistance , the value is the result of the weight goal value - the current weight (see my design)
    int weightGoalDistance = sumGoal - sum;
    //converting the integer value to a string so I can use setText and display it to the user
    String GoalsCalculation = Integer.toString(weightGoalDistance);
    //shows the result in a text field
    textFieldGoalsCalculation.setText(GoalsCalculation);
    
    
    }
    }	
				} catch (Exception ex) {
					ex.printStackTrace();
					
				}
			}
		});
		btnGoalDistances.setBounds(147, 704, 182, 37);
		contentPane.add(btnGoalDistances);
		
		textFieldGoalsCalculation = new JTextField();
		textFieldGoalsCalculation.setBounds(354, 712, 86, 20);
		contentPane.add(textFieldGoalsCalculation);
		textFieldGoalsCalculation.setColumns(10);
		
		textFieldREALDATE = new JTextField();
		textFieldREALDATE.setBounds(781, 12, 86, 20);
		contentPane.add(textFieldREALDATE);
		textFieldREALDATE.setColumns(10);
	    textFieldREALDATE.setText(dateFormat.format(date));
	    //back to main menu button
	    btnBackToMain = new JButton("Back to main menu");
	    btnBackToMain.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		//opens the main menu if clicked
	    		MainMenu openMainMenu = new MainMenu();
				openMainMenu.NewScreen();
	    	}
	    });
	    btnBackToMain.setBounds(726, 711, 151, 23);
	    contentPane.add(btnBackToMain);
		refreshTable();
	}
}
